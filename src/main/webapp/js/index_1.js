//data=[{"price": 23,"imageUrl": "www.abc.com","isBargain": 1,"label": "23","title": "bc"},{"price": 223,"imageUrl": "www.abc.com","isBargain": 12,"label": "223","title": "b2c"}];
var currentPage="";
function innerGoods(data) {
	$.each(data,function(index,obj){
		var goodsmain = document.getElementById("goodsmain");
		var goods= document.createElement('div');
		var img = document.createElement('img');
		var h4= document.createElement('h4');
		var p= document.createElement('p');
        var id= document.createElement('input');
        id.setAttribute("class","id");
		goods.setAttribute("class", "goods");
		h4.setAttribute("class", "myH");
		p.setAttribute("class", "price");
		id.setAttribute("style","display:none");
		id.setAttribute("value",obj.product_id)
	    img.src = obj.imageUrl;
	    h4.innerHTML=obj.title;
	    p.innerHTML=obj.price;
	    goods.appendChild(img);
	    goods.appendChild(h4);
	    goods.appendChild(p);
	    main.appendChild(goods);
	});
}
function loadXMLDoc()
{
	var title = $(".mySearch").val();
	token=getCookie("token");
	var saveData={"title":title,"currentPage":currentPage};
	$("#goodsmain").html("");
	$.ajax({
		url:"/search",
		type:"get",
		headers:{
			"token":token
		},
        data:saveData,
		contentType:"application/json",
		success:function(result,testStatus)
		{
			currentPage=result.data.currentPage;//是否需要加一
			data=result.data.data;
			if(result.code== "107"){
				innerGoods(data);
			}else{
				console.log("查询失败！");
			}
		},
		error:function(xhr,errrorMessage,e){
			alert("系统异常！");
		}
	});
}

window.onload =function init() {
    var username = window.location.search.split('=')[1];
    $("#username").text(username);
    currentPage='';
    token='';
    var saveData={"time":"asc","currentPage":currentPage};
    $("#main").html("");
    $.ajax({
        url:"/searchByTime",
        type:"get",
        headers:{
            "token":token,
            contentType:"application/json;charset=UTF-8"
        },
        data:saveData,
        contentType:"application/json",
        success:function(result,testStatus)
        {
            currentPage=result.currentPage;
            data=result.data.data;
            innerGoods(data);
        },
        error:function(xhr,errrorMessage,e){
            alert("系统异常！");
        }
    });
}
$(window).scroll(function(){
    var docHeight=$(document).height();//整个窗体的高度
    var winHeight=$(window).height();//当前窗体高度
    var winScrollHeight=$(window).scrollTop();//滚动条滚动距离
    console.log(winScrollHeight);
    if(docHeight-30<=winHeight+winScrollHeight){
        //innerGoods();
    }
});

