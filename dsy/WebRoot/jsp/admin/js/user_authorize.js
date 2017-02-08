$(function(){
	var index=layer.load(2);
	var userid=getUrlParam("userId");
	$.ajax({
		"url":"getUserRole.html",
		"type":"POST",
		"data":{"userid":userid},
		"success":function(data){
			var list=$.parseJSON(data);
			var str='<dl class="permission-list"><dd style="height:150px;">';
			var array=list.role;
			for(var i=0;i<array.length;i++){	
				str+='<label class="col-3" style="color:#FFAB12;padding-left:4px; "><input type="checkbox" value="'
					+array[i].roleId+'" name="user-Character-0-0-0" id="role_'
					+array[i].roleId+'">'+array[i].roleName+'</label>';
				}
				str+='</dd>';
			str+='</dl>';
			$("#content").html("");
			$(str).appendTo("#content");
			var RMarray=list.URid;
			$("#role_"+RMarray.roleid).attr("checked", true);
			layer.close(index);
		},
	    "error":function(data){
	    	layer.msg("操作失败",{icon: 6,time:1000});
	    }
	});
	
	$("#submit").on("click",function(){
		var id_array=new Array();  
		$("#content input[type='checkbox']:checked").each(function(){  
		    id_array.push($(this).val());//向数组中添加元素  
		});
		var num=id_array.length;
		if(num==1){
			$.ajax({
				"url":"saveUserRole.html",
				"type":"POST",
				"data":{"userid":userid,"roleid":id_array.join(",")},
				"success":function(data){
					var list=$.parseJSON(data);
					if(list.status=='000'){
						layer.msg(list.data, {
							icon : 2,
							time : 1000
						});
					}
					window.close(); //关闭子窗口.
					parent.location.reload(); //刷新父窗口
				},
			    "error":function(data){
			    	layer.msg("操作失败",{icon: 6,time:1000});
			    }
			});
		}
		else{
			layer.msg("只能选择一项！", {
				icon : 2,
				time : 1000
			});
			return false;
		}
	});
});