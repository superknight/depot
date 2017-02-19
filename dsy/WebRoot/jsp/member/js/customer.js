var param = {}; //参数对列
var table;  
function reflushTable() {
	// table.draw(false);// 刷新表格数据false为当前页数，true为刷新到第一页
}
$(function() {
	var currentDTOpt = {
		"ajax" : {
			"url" : "member/getCustomerList.html",
			"type" : "POST",
			"data" : function(d) {
				// 添加额外的参数传给服务器
				d.extra_search = param;
			}
		},
		"columns" : [ // 数据列
				{
					"mDataProp" : "id",
					"className" : "text-c",
					"orderable" : false,
					"render" : function(data, type, full) {
						var html = '<input type="checkbox" value="' + data
								+ '" title="' + data + '" id="checkbox_' + data
								+ '" name="checkbox_' + data + '" />';
						return html;
					}
				},

				{
					"data" : "customer",
					"className" : "text-c"
				},
				{
					"data" : "accout",
					"className" : "text-c"
				},
				{
					"data" : "password",
					"className" : "text-c"
				},
				{
					"mDataProp" : "status",
					"className" : "text-c",
					"orderable" : false,
					"render" : function(data, type, full) {
						var html = "";
						if (data == '1') {
							str = '被冻结中';
							html += '<span class="label label-info radius">'
									+ str + '</span>';
						} else if (data == '0') {
							str = '正常';
							html += '<span class="label label-success radius">'
									+ str + '</span>';
						}
						return html;
					}

				},
				{
					"data" : "email",
					"className" : "text-c"
				},
				{ 
					"data" : null,
					"className" : "text-c"
				},
				{
					"data" : "applyTime",
					"className" : "text-c"
				},
				{
					"data" : "lastUpdator",
					"className" : "text-c"
				},
				{
					"data" : "lastUpdateTime",
					"className" : "text-c"
				}
				],
		"columnDefs" : [ 
		                {
		                	"targets" : [ 6 ],
		        			"data" : "id",
		        			"orderable" : false,
		        			"className" : "text-c",
		        			"render" :function(data,type,full){
		        				var html = '<a style="text-decoration:none" onClick="look_address(\''
		        					+ full.id + '\',\'查看<'+full.customer+'>地址\',\'getAddress.html\')"'
		        					+'href="javascript:;" title="查看地址">查看地址</a>';
		        				return html;
		        			}
		                }
		                ],
		"order":[[7, 'asc']]
	};
	var dtOption = $.extend({},DT_COMMON_CONFIG, currentDTOpt); // JSON 合并
	 table = $('.table-sort').DataTable(dtOption); // 渲染DT
	//检索
	$("#search").click(function() {
		var json = $("#paramContainer").toJson();
		$.extend(param, json);
		table.draw(true);
	});

	selectCheckAndTable(); // 全选与table行选中
	$("#updateuser").click(function() {
		var id_array = new Array();
		$("input[type='checkbox']:checked").each(function() {
			id_array.push($(this).val());// 向数组中添加元素
		});
		var num = id_array.length;
		if (num <= 0) {
			layer.msg("请选择一项！", {
				icon : 2,
				time : 1000
			});
		}
		if (num > 1) {
			layer.msg("只能够选择一项！", {
				icon : 2,
				time : 1000
			});
		}
		if (num == 1) {
			adminUser_edit(id_array[0], "修改用户", "user-add.html");
		}

	});
});

/*查看-地址*/
function look_address(id,title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url+"?customerId="+id,
		area: ['60%', '80%'],
		cancel: function(index){ 
			table.draw(true);
		}
	});
}

var id_array = new Array();
/*冻结*/
function freeze(){
	$("#dataTable tbody input[type='checkbox']:checked").each(function() {
		id_array.push($(this).val());// 向数组中添加元素
	});
	$.ajax({
		"url":"member/freezeCustomer.html",
		"type":"POST",
		"data":{"idArray":id_array.toString()},
		"success":function(data){
			var list=$.parseJSON(data);
			console.log(list);
			if(list.status=="000"){
				layer.msg("冻结成功！", {icon: 1,time:1000});
				table.draw(true);
			}
			if(list.status=="001"){
				layer.msg("冻结失败！", {icon: 2,time:1000});
			}
		},
	    "error":function(data){
	    	layer.msg("操作失败",{icon: 2,time:1000});
	    }
	});
}

/*解冻*/
function unfreeze(){
	$("#dataTable tbody input[type='checkbox']:checked").each(function() {
		id_array.push($(this).val());// 向数组中添加元素
	});
	$.ajax({
		"url":"member/unfreezeCustomer.html",
		"type":"POST",
		"data":{"idArray":id_array.toString()},
		"success":function(data){
			var list=$.parseJSON(data);
			console.log(list);
			if(list.status=="000"){
				layer.msg("解冻成功！", {icon: 1,time:1000});
				table.draw(true);
			}
			if(list.status=="001"){
				layer.msg("解冻失败！", {icon: 2,time:1000});
			}
		},
	    "error":function(data){
	    	layer.msg("操作失败",{icon: 2,time:1000});
	    }
	});
}