var param = {};
$(function(){
	//打钩验证样式
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	var demo=$("#paramContainer").Validform({
		tiptype:2,
	});
	var roleid=getUrlParam("roleid");
	if(roleid){
		$.ajax({
			"url":"admin/getSingleRole.html",
			"type":"POST",
			"data":{"roleid":roleid},
			"success":function(data){
				var list=$.parseJSON(data);
				    $("#roleid").val(list.roleid);
					$("#rolename").val(list.rolename);
					$("#remark").val(list.remark);
			},
		    "error":function(data){
		    	layer.msg(list.msg,{icon: 6,time:1000});
		    }
		});
	}
	
	
	$("#submit").click(function(){
		if (!demo.check()) {
			return;
		}
		var json = $("#paramContainer").toJson();
		$.extend(param, json);
		$.ajax({
			"url":"admin/saveAndEditRole.html",
			"type":"POST",
			"data":param,
			"success":function(data){
				var list=$.parseJSON(data);		
				if(list.status=="000"){
					layer.msg("新增成功！", {icon: 1,time:1000});
				}
				if(list.status=="001"){
					
					layer.msg("新增失败！", {icon: 2,time:1000});
				}
				if(list.status=="003"){
					layer.msg("该角色已经存在！", {icon: 5,time:1000});
				}
				if(list.status=="100"){
					layer.msg("修改成功！",{icon: 1,time:1000});
				}
				if(list.status=="101"){
					layer.msg("修改失败！",{icon: 2,time:1000});
				}
			},
		    "error":function(data){
		    	layer.msg("操作失败",{icon: 2,time:1000});
		    }
		});
	});
});