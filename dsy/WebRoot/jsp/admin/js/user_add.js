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
		callback:function(){
			var index = parent.layer.getFrameIndex(window.name);
			parent.$('.btn-refresh').click();
			parent.layer.close(index);
		}
	});
	loadRole();
	loadDept();
	
	var userid=getUrlParam("userid");
	if(userid){

		var index = layer.load(2);
		$.ajax({
			"url":"getSingleUser.html",
			"type":"POST",
			"data":{"userid":userid},
			"success":function(data){
				var list=$.parseJSON(data);
				    $("#userid").val(list.userid);
					$("#realName").val(list.realName);
					$("#userName").val(list.userName);
					$("#password").val(list.password);
					$('#dept option[value='+list.dept+']').attr('selected',true);
					$('#status option[value='+list.status+']').attr('selected',true);
					layer.close(index); 
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
			"url":"saveAndEditUser.html",
			"type":"POST",
			"data":param,
			"success":function(data){
				var list=$.parseJSON(data);
				console.log(list);
				if(list.msg=="000"){
					layer.msg("新增成功！", 1);
				}
				if(list.msg=="001"){
					
					layer.msg("新增失败！", 2);
				}
				if(list.mst=="002"){
					layer.msg("账号不能为空！", 2);
				}
				if(list.msg=="003"){
					layer.msg("该用户已存在！",5);
				}
				if(list.msg=="100"){
					layer.msg("修改成功！",1);
				}
				if(list.msg=="101"){
					layer.msg("修改失败！",1);
				}
				
				
//				parent.location.reload(); //刷新父窗口
			},
		    "error":function(data){
		    	layer.msg("操作失败",{icon: 6,time:1000});
		    }
		});
	});
	//layer.close(index);
	
});


//获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}