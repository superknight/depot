var param = {}; //参数对列
var table;  
function reflushTable() {
	// table.draw(false);// 刷新表格数据false为当前页数，true为刷新到第一页
}
$(function() {
	var currentDTOpt = {
		"ajax" : {
			"url" : "log/getLogList.html",
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
					"data" : "creater",
					"className" : "text-c"
				},
				{
					"data" : "descripe",
					"className" : "text-c"
				},
				{
					"data" : "createTime",
					"className" : "text-c"
				}],
		"order":[[3, 'desc']]
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

var id_array = new Array();
/*删除*/
function datadel(){
	$("#dataTable tbody input[type='checkbox']:checked").each(function() {
		id_array.push($(this).val());// 向数组中添加元素
	});
	$.ajax({
		"url":"log/deleteLog.html",
		"type":"POST",
		"data":{"idArray":id_array.toString()},
		"success":function(data){
			var list=$.parseJSON(data);
			console.log(list);
			if(list.status=="000"){
				layer.msg("删除成功！", {icon: 1,time:1000});
				table.draw(true);
			}
			if(list.status=="001"){
				layer.msg("删除失败！", {icon: 2,time:1000});
			}
		},
	    "error":function(data){
	    	layer.msg("操作失败",{icon: 2,time:1000});
	    }
	});
}
