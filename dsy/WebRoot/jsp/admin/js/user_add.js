var param = {};
$(function(){
	//打钩验证样式
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	var demo = $("#paramContainer").Validform({
		tiptype : 2
	});
	
	var userid=getUrlParam("userid");
	if(userid){

		var index = layer.load(2);
		$.ajax({
			"url":"getSingleUser.html",
			"type":"POST",
			"data":{"userid":userid},
			"success":function(data){
				var list = $.parseJSON(data);
				console.log(list[0].name);
				    $("#id").val(list[0].id);
				    $("#name").val(list[0].name);
				    $("#username").val(list[0].username);
				    $("#password").val(list[0].password);
					$('#sex option[value='+list[0].sex+']').attr('selected',true);
				    $("#phone").val(list[0].phone);
				    $("#email").val(list[0].email);
				    $("#address").val(list[0].address);
					$('#status option[value='+list[0].status+']').attr('selected',true);
					layer.close(index); 
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
		var index = layer.load(1, {
			shade : [ 0.5, '#fff' ]
		// 0.1透明度的白色背景
		});
		var json = $("#paramContainer").toJson();
		$.extend(param, json);
		$.ajax({
			"url":"saveAndEditUser.html",
			"type":"POST",
			"data":param,
			"success":function(data){
				var list=$.parseJSON(data);
				console.log(list);
				if(list.msg=="000"){
					layer.msg("新增成功！", {icon: 1,time:1000});
				}
				if(list.msg=="001"){
					
					layer.msg("新增失败！", {icon: 6,time:1000});
				}
				if(list.mst=="002"){
					layer.msg("账号不能为空！", {icon: 5,time:1000});
				}
				if(list.msg=="003"){
					layer.msg("该用户已存在！",{icon: 5,time:1000});
				}
				if(list.msg=="100"){
					layer.msg("修改成功！",{icon: 1,time:1000});
				}
				if(list.msg=="101"){
					layer.msg("修改失败！",{icon: 5,time:1000});
				}
//				parent.location.reload(); //刷新父窗口
			},
		    "error":function(data){
		    	layer.msg("操作失败",{icon: 6,time:1000});
		    }
		});
	});
	
});
