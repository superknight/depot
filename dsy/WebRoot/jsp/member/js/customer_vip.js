var param = {}; //参数对列
var table;  
function reflushTable() {
	// table.draw(false);// 刷新表格数据false为当前页数，true为刷新到第一页
}
$(function() {
	var currentDTOpt = {
		"ajax" : {
			"url" : "member/getCustomerVipList.html",
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
					"mDataProp" : "vip_grade",
					"className" : "text-c",
					"orderable" : false,
					"render" : function(data, type, full) {
						var html = "";
						if (data == '001') {
							str = '青铜会员';
							html += '<span class="label label-info radius" style="background-color:#9D91B5">'
									+ str + '</span>';
						} else if (data == '002') {
							str = '白银会员';
							html += '<span class="label label-success radius" style="background-color:#759154">'
									+ str + '</span>';
						}else if (data == '003') {
							str = '黄金会员';
							html += '<span class="label label-success radius" style="background-color:#B28A4F">'
									+ str + '</span>';
						}else if (data == '004') {
							str = '白金会员';
							html += '<span class="label label-success radius" style="background-color:#517DA0">'
									+ str + '</span>';
						}else if (data == '005') {
							str = '钻石会员';
							html += '<span class="label label-success radius" style="background-color:#D2CECB">'
									+ str + '</span>';
						}
						return html;
					}
				},
				{
					"data" : "vip_integral",
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
					"data" : "apply_time",
					"className" : "text-c"
				}
				],
		"order":[[5, 'asc']]
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
});
