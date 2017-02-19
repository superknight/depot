var param = {}; //参数对列
var table;  
function reflushTable() {
	// table.draw(false);// 刷新表格数据false为当前页数，true为刷新到第一页
}
$(function() {
	var currentDTOpt = {
		"ajax" : {
			"url" : "admin/getAdminUserList.html",
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
					"data" : "name",
					"className" : "text-c"
				},
				{
					"data" : "username",
					"className" : "text-c"
				},
				{
					"data" : "password",
					"className" : "text-c"
				},
				{
					"data" : "sex",
					"className" : "text-c"
				},
				{
					"data" : "role",
					"className" : "text-c"
				},
				{
					"data" : "phone",
					"className" : "text-c"
				},
				{
					"data" : "email",
					"className" : "text-c"
				},
				{
					"data" : "address",
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

				}, {
					"data" : "creator",
					"className" : "text-c"
				},
				{
					"data" : "createTime",
					"className" : "text-c"
				},
				{
					"data" : "lastUpdate",
					"className" : "text-c"
				},
				{
					"data" : "lastUpdateTime",
					"className" : "text-c"
				},
				{
					"data" : "remark",
					"className" : "text-c"
				}],
		"columnDefs" : [ { // 定制需要操作的列
			"targets" : [ 14 ],
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
// 授予角色
function authorize(userid, name, user, title, url) {
	layer.open({
		type : 2,
		title : title,
		content : url + "?userId=" + userid + "&realName=" + name + "&userName="
				+ user,
		area : [ '500px', '350px' ],
		cancel: function(index){ 
			table.draw(true);
		}
	});
}
/* 用户-添加 */
function adminUser_add(title, url, w, h) {
	layer.open({
		type : 2,
		title : title,
		content : url,
		area : [ '600px', '90%' ],
		cancel: function(index){ 
			table.draw(true);
		}
	});
}
/* 用户-修改 */
function adminUser_edit(userid, title, url) {
	layer.open({
		type : 2,
		title : title,
		content : url + "?userid=" + userid,
		area : [ '600px', '90%' ],
		cancel: function(index){ 
			table.draw(true);
		}
	});
}
/* 用户-删除 */
function datadel() {
	var id_array = new Array();
	$("#dataTable tbody input[type='checkbox']:checked").each(function() {
		id_array.push($(this).val());// 向数组中添加元素
	});
	var num = id_array.length;
	if (num <= 0) {
		layer.msg("删除请至少选择一项！", {
			icon : 2,
			time : 1000
		});
	}
	if (num > 0)
		layer.confirm('确认要删除吗？', function(index) {
			$.ajax({
				"url" : "admin/deleteUser.html",
				"type" : "POST",
				"data" : {
					"idArray" : id_array.join(",")
				},
				"success" : function(data) {
					var list = $.parseJSON(data);
					if (list.status == '000') {
						layer.msg('已删除!', {icon : 1,time : 1000});
						table.draw(true);
					}
					if (list.status == '001') {
						layer.msg('删除失败!',{icon : 2,time : 1000});
					}
					if (list.status == "002"){
						layer.msg('该用户为超级管理员，没有足够的权限删除！',{icon : 5,time : 1000});
					}
				},
				"error" : function(data) {
					layer.msg("操作失败", {icon : 2,time : 1000});
				}
			});
		});
}