var param = {};
var table;
function reflushTable() {
	table.draw(false);// 刷新表格数据false为当前页数，true为刷新到第一页
}
$(function(){
   var currentDTOpt = { 
            "ajax":{
             "url":"admin/getAdminRoleList.html",
             "type":"POST",
             "data":function ( d ) {
             //添加额外的参数传给服务器
             d.extra_search = param;
                }
           
             },
      
          "columns" : [ //数据列 
                    {
                        "mDataProp" : "id",
						"sClass" : "text-c",
						"bSortable": false,
						"mRender" : function(data, type, full) {
							var html = '<input type="checkbox" value="'
									+ data + '" title="' + data
									+ '" id="checkbox_' + data
									+ '" name="checkbox_' + data
									+ '" />';
							return html;
						}
                     },          
			{data : 'role',"className":"text-c"},
			{data : 'remark',"className":"text-c"}, 
			{data : 'creator',"className":"text-c"}, 
			{data : 'createTime',"className":"text-c"}, 
			{data : 'lastUpdate',"className":"text-c"}, 
			{data : 'lastUpdateTime',"className":"text-c"},
         ],
         "columnDefs":[
             { //定制需要操作的列
            "targets":[7], 
            "data":"id",
            "sClass" : "text-c",
			// data:代表当前的值，full:代表当前行的数据
			"mRender" : function(data, type, full) {
				var html="";
			   	html += '<a style="text-decoration:none" onClick="authorize(\''
					+data
					+'\',\''+full.role+'\',\'角色授权\',\'role-permission.html\')" href="javascript:;">';
				html += '<i class="Hui-iconfont" style="font-size:18px;color:#B2ED83;">&#xe62d;</i>角色授权</a>';
				return html;
			}
            }],
            "order":[[1, 'asc']],
          };

	var dtOption = $.extend({},DT_COMMON_CONFIG, currentDTOpt); // JSON 合并
	table = $('.table-sort').DataTable(dtOption); // 渲染DT
	
	selectCheckAndTable(); //全选与table行选中
	
	$("#search").click(function() {
		var json = $("#paramContainer").toJson();
		$.extend(param, json);
		table.draw(true);
	});
	
$("#updaterole").click(function(){
	var id_array=new Array();  
	$("#dataTable tbody input[type='checkbox']:checked").each(function(){  
	    id_array.push($(this).val());//向数组中添加元素  
	});
	var num=id_array.length;
	if(num<=0)
	{
		layer.msg("请选择一项！", {
			icon : 2,
			time : 1000
		});
	}
	if(num>1){
		layer.msg("只能够选择一项！", {
			icon : 2,
			time : 1000
		});
	}
	if(num==1){
		role_edit(id_array[0],"修改角色","role-add.html");
	}
	
});

    });

//角色授权
 function authorize(role_id,rolename,title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url+"?roleid="+role_id+"&rolename="+rolename,
		area: ['800px', '90%'],
		cancel: function(index){ 
			table.draw(true);
		}
	});
}


/*角色-添加*/
function role_add(title,url,w,h){
	var index = layer.open({
		type: 2,
		title: title,
		content: url,
		area: ['60%', '50%'],
		cancel: function(index){ 
			table.draw(true);
		}
	});
}
/*角色-修改*/
function role_edit(roleid,title,url){
	var index = layer.open({
		type: 2,
		title:title,
		content: url+"?roleid="+roleid,
		area: ['60%', '50%'],
		cancel: function(index){ 
			table.draw(true);
		}
	});
}

/*角色-删除*/
function datadel(){
	var id_array=new Array();  
	$("#dataTable tbody input[type='checkbox']:checked").each(function(){  
	    id_array.push($(this).val());//向数组中添加元素  
	});
	var num=id_array.length;
	if(num<=0)
	{
		layer.msg("删除请至少选择一项！", {
			icon : 1,
			time : 1000
		});
	}
	if(num>0)
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			"url":"admin/deleteRole.html",
			"type":"POST",
			"data":{"idArray":id_array.join(",")},
			"success":function(data){
				var list = $.parseJSON(data);
				if (list.status == '000') {
					layer.msg('已删除!', {icon : 1,time : 1000});
					table.draw(true);
				}
				if (list.status == '001') {
					layer.msg('删除失败!',{icon : 2,time : 1000});
				}
				if (list.status == "002"){
					layer.msg('超级管理员不可删除！',{icon : 5,time : 1000});
				}
			},
		    "error":function(data){
		    	layer.msg("操作失败",{icon: 6,time:1000});
		    }
		});
	});
	
	
}
