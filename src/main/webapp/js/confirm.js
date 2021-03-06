/*
* 认证ajax请求
* 作者：lu
* */
// 点击认证时调用
function f() {
    var username = $("#Xusername").val();
    var password = $("#Xpassword").val();
    var sno = username;
    username = encodeInp(username);
    password = encodeInp(password);
    var saveData={"encode": username+"%%%"+password,"sno":sno};

    $.ajax({
        url:"/cashsale/confirm",
        type:"post",
        dataType:"json",
        data:JSON.stringify(saveData),
        contentType:"application/json",
        success:function(result,testStatus)
        {
            if(result.code== "200"){
                window.location.href="verifysuccess.html";
            }else if(result.code== "401" || result.code == "500"){
                window.location.href="verifyfailure.html";
            }
        },
        error:function(xhr,errrorMessage,e){
            alert("系统异常！");
        }
    });
}

//模拟教务系统加密的请求
var keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

function encodeInp(input) {
    var output = "";
    var chr1, chr2, chr3 = "";
    var enc1, enc2, enc3, enc4 = "";
    var i = 0;
    do {
        chr1 = input.charCodeAt(i++);
        chr2 = input.charCodeAt(i++);
        chr3 = input.charCodeAt(i++);
        enc1 = chr1 >> 2;
        enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
        enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
        enc4 = chr3 & 63;
        if (isNaN(chr2)) {
            enc3 = enc4 = 64
        } else if (isNaN(chr3)) {
            enc4 = 64
        }
        output = output + keyStr.charAt(enc1) + keyStr.charAt(enc2) + keyStr.charAt(enc3) + keyStr.charAt(enc4);
        chr1 = chr2 = chr3 = "";
        enc1 = enc2 = enc3 = enc4 = ""
    } while (i < input.length);
    return output
}

//认证回车事件
function enterSubmit(obj) {
    var button = document.getElementById('confirm');
    //enter按键的keyCode编码为13
    if (obj.keyCode == 13) {
        button.click();
    }
}

//刷新input获取焦点
window.onload=function setFocus()
{
    document.getElementById('Xusername').focus();
}