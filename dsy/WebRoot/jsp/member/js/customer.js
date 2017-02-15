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
						var str = '未激活';
						var html = "";
						if (data == '1') {
							str = '已激活';
							html += '<span class="label label-success radius">'
									+ str + '</span>';
						} else if (data == '0') {
							str = '未激活';
							html += '<span class="label label-info radius">'
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
		"columnDefs" : [ { // 定制需要操作的列
			"targets" : [ 9 ],
			"data" : "id",
			"orderable" : false,
			"className" : "text-c",
			// data:代表当前的值，full:代表当前行的数据
			"render" : function(data, type, full) {
				var html = '';
				if (data == '1') {
					html += '';
				} else {
					html += '<a style="text-decoration:none" onClick="authorize(\''
							+ full.id
							+ '\',\''
							+ full.name
							+ '\',\''
							+ full.username
							+ '\',\'角色授权\',\'userAuthorize.html\')" href="javascript:;" title="授予角色">';
					html += '<i class="Hui-iconfont" style="font-size:18px;color:#B2ED83;">&#xe6cc;</i>授予角色</a>';
				}
				return html;
			}
		}],
		"order":[[1, 'asc']]
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