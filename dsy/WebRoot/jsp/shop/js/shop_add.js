var param = {};
$(function(){
	$("#submit").click(function(){
		var json = $("#paramContainer").toJson();
		$.extend(param, json);
		$.ajax({
			"url":"saveShop.html",
			"type":"POST",
			"data":param,
			"dataType" : "json",
			"success":function(data){
				if(data.code == '001'){
					layer.msg("操作成功",{icon: 1,time:1000});
				}else if(data.code == '000'){
					layer.msg("操作失败",{icon: 6,time:1000});
				}
				window.close(); //关闭子窗口.
				parent.location.reload(); //刷新父窗口
			},
		    "error":function(data){
		    	layer.msg("操作失败",{icon: 6,time:1000});
		    }
		});
	});
});


//获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}