$(function(){
	var index=layer.load(2);
	var roleid=getUrlParam("roleid");
	$.ajax({
		"url":"admin/getMenus.html",
		"type":"POST",
		"data":{"roleid":roleid},
		"success":function(data){
			var list=$.parseJSON(data);
			console.log(list);
			if(list.status == "000"){
			var array=list.data;
			var str='<dl class="permission-list">';
			var arrayNum=array.length-1
			for(var i=0;i<arrayNum;i++){
				str+='<dt><i class="Hui-iconfont" style="font-size:18px;padding-right:4px;">'+array[i].icon+'</i>'
					+'<label><input type="checkbox" value="'+array[i].menu_id
					+'" name="user-Character-0" id="menu_'+array[i].menu_id+'">'
					+array[i].menu_name+'</label></dt><dd style="height:25px;">';
				var menus=array[i].menus;
				if(menus.length<=0)
					str+='<label style="color:red;padding-left:4px;">亲，没有子菜单</label>'
				else
				for(var j=0;j<menus.length;j++){	
				str+='<label class="col-3" style="color:#FFAB12;padding-left:4px; "><input type="checkbox" value="'
					+menus[j].menuid+'" name="user-Character-0-0-0" id="menu_'
					+menus[j].menuid+'">'+menus[j].menuname+'</label>';
				}
				str+='</dd>';
			}
			str+='</dl>';
			$("#content").html("");
			$(str).appendTo("#content");
			var RMarray=array[arrayNum].RMid;
			for(var i=0;i<RMarray.length;i++){
				$("#menu_"+RMarray[i].id).attr("checked", true);
			}
			layer.close(index);
			}
			if(list.status == "001"){
				layer.close(index);
				layer.msg("请求出错！",{icon: 2,time:1000});
			}
		},
	    "error":function(data){
	    	layer.msg("操作失败",{icon: 2,time:1000});
	    }
	});
	
	
	$("#submit").on("click",function(){
		var id_array=new Array();  
		$("#content input[type='checkbox']:checked").each(function(){  
		    id_array.push($(this).val());//向数组中添加元素  
		});
		var num=id_array.length;
		if(num<1){
			layer.msg("删除请至少选择一项！", {
				icon : 2,
				time : 1000
			});
			return false;
		}
		if(num>=1){
			$.ajax({
				"url":"admin/role-authorize.html",
				"type":"POST",
				"data":{"roleid":roleid,"menuid":id_array.join(",")},
				"success":function(data){
					var list=$.parseJSON(data);
					if(list.status=='000'){
						layer.msg(list.data, {
							icon : 1,
							time : 1000
						});
					}
				},
			    "error":function(data){
			    	layer.msg("操作失败",{icon: 6,time:1000});
			    }
			});
		return false;
		}
	});
	layer.close(index);
});