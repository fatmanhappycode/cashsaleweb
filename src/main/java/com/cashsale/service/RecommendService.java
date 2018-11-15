package com.cashsale.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.cashsale.bean.ProductDO;
import com.cashsale.dao.ListRecommendDAO;

/**
 * @author Sylvia
 * 2018年11月4日
 * 获得每个用户于被推荐用户的皮尔逊相关系数
 * 获得其它用户浏览过而被推荐用户没有浏览过的商品以及各个用户对它的评分
 * 加权（皮尔逊相关系数*评分）
 */
public class RecommendService {

    public static String username = "";
    /**
     * 返回推荐结果
     * @param username
     *        被推荐用户
     * @return
     *        推荐结果
     */
    public List<ProductDO> getList(String username){
        this.username = username;
        Map<String, Double> simUserSimMap = new HashMap<String, Double>();
        Map<String, Map<String, Integer>> userPerfMap = new ListRecommendDAO().getUserScore(username);
        //获得被推荐用户的map
        Map<String, Integer> recommed = userPerfMap.get(username);

        //遍历键值对
        for (Entry<String, Map<String, Integer>> userPerfEn : userPerfMap.entrySet()) {
            String other = userPerfEn.getKey();
            if (!username.equals(other)) {
                double sim = getUserSimilar(recommed, userPerfEn.getValue());
                System.out.println(username+"与" + other + "之间的相关系数:" + sim);
                // 将被推荐用户与其它用户的相关系数存进map中
                simUserSimMap.put(other, sim);
            }
        }
        //根据皮尔逊系数对相似用户进行排序
        List<Entry<String, Double>> enList = getSort(simUserSimMap);
        Map<String, Map<String, Integer>> simUserObjMap = new ListRecommendDAO().getSimUserObjMap(username, enList);
        //System.out.println("推荐结果:" + getRecommend(simUserObjMap, simUserSimMap));
        return getRecommend(simUserObjMap, simUserSimMap);
    }

    /**
     * 计算用户之间的皮尔逊相关系数
     * @param pm1
     *      被推荐用户的map
     * @param pm2
     *      其它用户的map
     * @return
     *      其它用户和被推荐用户的皮尔逊系数
     */
    private static double getUserSimilar(Map<String, Integer> pm1, Map<String, Integer> pm2) {
        try {
            // 数量n
            int n = 0;
            // Σxy=x1*y1+x2*y2+....xn*yn
            int sxy = 0;
            // Σx=x1+x2+....xn
            int sx = 0;
            // Σy=y1+y2+...yn
            int sy = 0;
            // Σx2=(x1)2+(x2)2+....(xn)2
            int sx2 = 0;
            // Σy2=(y1)2+(y2)2+....(yn)2
            int sy2 = 0;

            //遍历被推荐用户的商品id和对其的评分
            for (Entry<String, Integer> pme : pm1.entrySet()) {
                //获得商品id
                String key = pme.getKey();
                //被推荐用户对该商品的评分
                Integer x = pme.getValue();
                //其它用户对该商品的评分
                Integer y = pm2.get(key);
                //System.out.println("key="+key);
                //System.out.println("y="+pm2.get(key));
                if (x != null && y != null) {
                    n++;
                    sxy += x * y;
                    sx += x;
                    sy += y;
                    sx2 += Math.pow(x, 2);
                    sy2 += Math.pow(y, 2);
                    System.out.println("sxy= "+sxy+"  sx= "+sx+"  sy= "+sy+"  sx2="+sx2+" sy2="+sy2);
                }
                System.out.println("product_id= "+key+"  x= "+x+"  y= "+y);
            }
            // p=(Σxy-Σx*Σy/n)/Math.sqrt((Σx2-(Σx)2/n)(Σy2-(Σy)2/n));
            double sd = sxy - sx * sy / n;
            double sm = Math.sqrt((sx2 - Math.pow(sx, 2) / n) * (sy2 - Math.pow(sy, 2) / n));
            System.out.println("sd= "+sd+" sm= "+sm);
            return Math.abs(sm == 0 ? 1 : sd / sm);
        }
        catch (Exception e){
            e.printStackTrace();
            System.err.println("皮尔逊系数计算失败！");
        }
        return 0;
    }


    /**
     * 加权排序推荐，获取推荐结果
     * @param simUserObjMap
     *          相似用户和他的浏览过的商品及评分
     * @param simUserSimMap
     *          相似用户和他的皮尔逊系数
     * @return
     *        推荐结果
     */
    private static List<ProductDO> getRecommend(Map<String, Map<String, Integer>> simUserObjMap,
                                                Map<String, Double> simUserSimMap) {
        Map<String, Double> objScoreMap = new HashMap<String, Double>();
        //被推荐的商品id列表
        ArrayList<String> result = new ArrayList<String>();
        //不能被推荐的商品id列表
        ArrayList<String> noResult = new ArrayList<String>();
        for (Entry<String, Map<String, Integer>> simUserEn : simUserObjMap.entrySet()) {
            //获得其它用户的用户名
            String user = simUserEn.getKey();
            //获得该用户和被推荐用户的相似度
            double sim = simUserSimMap.get(user);
            //simUserEn.getValue().entrySet()：存放user对每一个商品的评分以及商品id
            for (Entry<String, Integer> simObjEn : simUserEn.getValue().entrySet()) {
                //加权（相似度*评分）
                double objScore = sim * simObjEn.getValue();
                //商品id
                String objName = simObjEn.getKey();
                if (objScoreMap.get(objName) == null) {
                    objScoreMap.put(objName, objScore);
                } else {
                    double totalScore = objScoreMap.get(objName);
                    //将所有用户的加权评分作为最后的推荐结果数据
                    objScoreMap.put(objName, totalScore + objScore);
                }
            }
        }

        List<Entry<String, Double>> enList = getSort(objScoreMap);

        for (Entry<String, Double> entry : enList) {
            System.out.println(entry.getKey()+"的加权推荐值:"+entry.getValue());
            if(entry.getValue() > 0){
                result.add(entry.getKey());
            }
            else{
                //若加权推荐值小于0，则加入不被推荐的列表
                noResult.add(entry.getKey());
            }
        }
        if(result.size() < 6){
            ArrayList<String> random = new ListRecommendDAO().getRandom(username);
            for(int i = 0; i < random.size() && result.size() <= 6; i++){
                //获得的随机商品id不能已存在被推荐列表以及不被推荐列表
                if(result.indexOf(random.get(i)) == -1 && noResult.indexOf(random.get(i)) == -1){
                    result.add(random.get(i));
                }
            }
        }

        //返回推荐结果
        return new ListRecommendDAO().getProductData(result);
    }

    /**
     * 从大到小排序
     * @param map
     *          需排序的map
     * @return
     *      排序后的map
     */
    private static List<Entry<String, Double>> getSort(Map<String, Double> map){
        List<Entry<String, Double>> enList = new ArrayList<Entry<String, Double>>(map.entrySet());
        //排序
        Collections.sort(enList, new Comparator<Map.Entry<String, Double>>() {
            //重写compare方法
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                Double a = o1.getValue() - o2.getValue();
                if (a == 0) {
                    return 0;
                } else if (a > 0) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        return enList;
    }
}