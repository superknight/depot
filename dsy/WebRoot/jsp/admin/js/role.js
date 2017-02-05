var param = {};
function reflushTable() {
	table.draw(false);// 刷新表格数据false为当前页数，true为刷新到第一页
}
$(function(){
	$("#search").click(function() {
		var json = $("#paramContainer").toJson();
		$.extend(param, json);
		table.fnDraw(true);
	});
var table= $('.table-sort').dataTable({ 
	       "processing": true,
	       "serverSide": true,
	       "autoWidth":false, //自动获取宽度
	       "searching": false, //检索栏
	       "paging": true,
	       "retrieve": true,
	       "pagingType": "full_numbers", //分页类型
          "language": {
        	"lengthMenu": '<select class="form-control input-xsmall">' +
        	'<option value="1">1</option>' +'<option value="10">10</option>'
        	+ '<option value="20">20</option>' + '<option value="30">30</option>' 
        	+ '<option value="40">40</option>' + '<option value="50">50</option>'
        	+ '</select><span>条记录</span>',//左上角的分页大小显示。
        	
           "sSearch": '<span style="color:#F3745B">从当前数据中检索:</span>',//右上角的搜索文本，可以写html标签
            "oPaginate": {
            "sFirst": "首页",
            "sPrevious": "上页",
            "sNext": "下页",
            "sLast": "末页" 
              },
              "loadingRecords": "正在获取数据，请稍后..",
             "zeroRecords": "没有内容",//table tbody内容为空时，tbody的内容。//下面三者构成了总体的左下角的内容。
              "info": "总共_PAGES_ 页，共 _TOTAL_ 条记录 ",//左下角的信息显示，大写的词为关键字。
               "infoEmpty": "0条记录",//筛选为空时左下角的显示。
              "infoFiltered": ""//筛选之后的左下角筛选提示，
              },
            "ajax":{
             "url":"getRoleList.html",
             "type":"get",
             "data":function ( d ) {
             //添加额外的参数传给服务器
             d.extra_search = param;
                },
             "cache":false,//禁用缓存
             "dataType": "json",
             "contentType": 'application/json;charset=UTF-8',
             },
             "initComplete": function( settings, json ) {
            	 $("#total").html("");
            	 $("#total").append(json.recordsTotal);
            	  },
          "aoColumns" : [ //数据列 
                    {
                        "mDataProp" : "role_id",
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
			{data : 'role_name'},
			{data : 'remark'}, 
			{data : 'order_number'},
         ],
         "columnDefs":[
             { //定制需要操作的列
            "targets":[4], 
            "data":"role_id",
            "sClass" : "text-c",
			// data:代表当前的值，full:代表当前行的数据
			"mRender" : function(data, type, full) {
				var html="";
			   	html += '<a style="text-decoration:none" onClick="authorize(\''
					+data
					+'\',\''+full.role_name+'\',\'角色授权\',\'role-permission.html\')" href="javascript:;">';
				html += '<i class="Hui-iconfont" style="font-size:18px;color:#B2ED83;">&#xe62d;</i>角色授权</a>';
				return html;
			}
            }]
          });

$("#updaterole").click(function(){
	var id_array=new Array();  
	$("input[type='checkbox']:checked").each(function(){  
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
		area: ['800px', '650px']
	});
}


/*角色-添加*/
function role_add(title,url,w,h){
	var index = layer.open({
		type: 2,
		title: title,
		content: url,
		area: ['700px', '500px']
	});
}
/*角色-修改*/
function role_edit(roleid,title,url){
	var index = layer.open({
		type: 2,
		title:title,
		content: url+"?roleid="+roleid,
		area: ['700px', '500px']
	});
}

/*角色-删除*/
function datadel(){
	var id_array=new Array();  
	$("input[type='checkbox']:checked").each(function(){  
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
			"url":"deleteRole.html",
			"type":"POST",
			"data":{"idArray":id_array.join(",")},
			"success":function(data){
				var list=$.parseJSON(data);		
				if(list.status=='001'){
					layer.msg('已删除!',1);
					
				}
				if(list.status=='000'){
					layer.msg('删除失败!',2);
				}
			},
		    "error":function(data){
		    	layer.msg("操作失败",{icon: 6,time:1000});
		    }
		});
        layer.closeAll();
        window.location.reload();
	});
	
	
}
