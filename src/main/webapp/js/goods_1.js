//获取容器
var smallImg = document.getElementById("smallImg");
var bigImg = document.getElementById("bigImg");
var title = document.getElementById("title");
var price1 = document.getElementById("price1");
var price2 = document.getElementById("price2");
var getGoods = document.getElementById("getGoods");
var Description = document.getElementById("Description");
<<<<<<< HEAD


//处理表情图片
=======
// 加入内容
smallImg.setAttribute("src",getCookie("imageUrl"))
bigImg.setAttribute("src",getCookie("imageUrl"));
title.innerHTML=getCookie("title");
price1.innerHTML="价格：￥"+getCookie("price");
if(getCookie("isBargain")=="1"){
    price2.innerHTML="可否议价：可议价";
}else if(getCookie("isBargain")=="0"){
    price2.innerHTML="可否议价：不可议价";
}
if(getCookie("tradeMethod")=="1"){
    getGoods.innerHTML="交货方式：上门自提";
}else if(getCookie("tradeMethod")=="0"){
    getGoods.innerHTML="交货方式：送货上门";
}
// 富文本编辑器加入内容
Description.innerHTML+="更多内容："+"<br>"+getCookie("pdDescription");

// 用户名 发布者
if(getCookie("goodsUsername")!="undefined"){
    $("#username").html(getCookie("goodsUsername"));
}else{
    $("#username").html("匿名");
}
isInTrolley();
>>>>>>> 758d44a44ff6b7f4730c993b256496d710ee246e
var allimg=document.getElementsByTagName("img");
for(var i=0;i<allimg.length;i++){
    if(allimg[i].getAttribute("alt")!=null){
        allimg[i].style="width:16px;height:16px;"
    }
}
// 点击关注按钮
city.onclick= function(){
    $("#city").val("已关注");
}
<<<<<<< HEAD


window.onload =function loadgoods() {
    var string=window.location.href;
    var productId=string.split("=")[1];
    var saveData={"productId":productId};
    // alert(productId);
    $.ajax({
        url:"/GetDetailProduct",
=======
// 加入购物车
JOshopping.onclick=function () {
    var productId = getCookie("productId");
    var saveData = {"productId":productId};
    $.ajax({
        url:"/AddShoppingTrolley",
        type:"post",
        dataType:"json",
        data:JSON.stringify(saveData),
        contentType:"application/json;charset=UTF-8",
        headers:{
            contentType:"application/json;charset=UTF-8"
        },
        success:function(result,testStatus)
        {
          if (result.code == "101") {
              $("#JOshopping").val("已加入购物车");
              var JOshopping = document.getElementById("JOshopping");
              JOshopping.setAttribute("style","background-color:#da7370ad");
          } else if (result.code == "102") {
              alert("加入失败");
          }
        },
        error:function(xhr,errrorMessage,e){
            alert("系统异常！"+e+"\n"+errrorMessage);
        }
    });
}

function isInTrolley() {
    var saveData = {"productId":getCookie("productId")};
    $.ajax({
        url:"/isInTrolley",
>>>>>>> 758d44a44ff6b7f4730c993b256496d710ee246e
        type:"get",
        headers:{
            contentType:"application/json;charset=UTF-8"
        },
        data:saveData,
        contentType:"application/json",
        success:function(result,testStatus)
        {
<<<<<<< HEAD
            if(result.code==124){

                data=result.data;
                setCookie("goodsUsername",data.username);


                // 加入内容
                smallImg.setAttribute("src",data.imageUrl)
                bigImg.setAttribute("src",data.imageUrl);
                title.innerHTML=data.title;
                price1.innerHTML="价格：￥"+data.price;
                if(data.isBargain=="1"){
                    price2.innerHTML="可否议价：可议价";
                }else if(data.isBargain=="0"){
                    price2.innerHTML="可否议价：不可议价";
                }
                if(data.tradeMethod=="1"){
                    getGoods.innerHTML="交货方式：上门自提";
                }else if(data.tradeMethod=="0"){
                    getGoods.innerHTML="交货方式：送货上门";
                }
                // 富文本编辑器加入内容
                Description.innerHTML+="更多内容："+"<br>"+data.pdDescription;

                // 用户名 发布者
                if(data.username!="undefined"||data.username!=""||data.username!=null){
                    $("#username").html(data.username);
                }else{
                    $("#username").html("匿名");
                }

            }else{
                console.log(result.msg);
            }
        },
        error:function(xhr,errrorMessage,e){
            alert("系统异常！");
        }
    });
}
=======
            if (result.code == "101") {
                $("#JOshopping").val("已加入购物车");
                var JOshopping = document.getElementById("JOshopping");
                JOshopping.setAttribute("style","background-color:#da7370ad");
            }
        },
        error:function(xhr,errrorMessage,e){
            alert("系统异常！"+e+"\n"+errrorMessage);
        }
    });
}
>>>>>>> 758d44a44ff6b7f4730c993b256496d710ee246e
