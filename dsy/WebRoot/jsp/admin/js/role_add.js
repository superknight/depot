var param = {};
$(function(){
	//打钩验证样式
	/*$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});*/
	
	$("#paramContainer").Validform({
		tiptype:2,
		callback:function(form){
			form[0].submit();
			var index = parent.layer.getFrameIndex(window.name);
			parent.$('.btn-refresh').click();
			parent.layer.close(index);
		}
	});
	var roleid=getUrlParam("roleid");
	if(roleid){
		$.ajax({
			"url":"getSingleRole.html",
			"type":"POST",
			"data":{"roleid":roleid},
			"success":function(data){
				var list=$.parseJSON(data);
				    $("#roleid").val(list.roleid);
					$("#rolename").val(list.rolename);
					$("#order").val(list.order);
					$("#remark").val(list.remark);
			},
		    "error":function(data){
		    	layer.msg(list.msg,{icon: 6,time:1000});
		    }
		});
	}
	
	
	$("#submit").click(function(){
		var json = $("#paramContainer").toJson();
		$.extend(param, json);
		$.ajax({
			"url":"saveAndEditRole.html",
			"type":"POST",
			"data":param,
			"success":function(data){
				var list=$.parseJSON(data);		
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