var param = {};
var table; 
function reflushTable() {
	// table.draw(false);// 刷新表格数据false为当前页数，true为刷新到第一页
}
$(function() {
	var currentDTOpt = {
		"ajax" : {
			"url" : "shop/getShopList.html",
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
					"data" : "shopname",
					"className" : "text-c"
				},
				{
					"data" : "name",
					"className" : "text-c"
				},
				{
					"mDataProp" : "type",
					"className" : "text-c",
					"orderable" : false,
					"render" : function(data, type, full) {
						var str = '冻结中';
						var html = "";
						if (data == '001') {
							str = '企业店铺';
							html += '<span class="label label-success radius">'
									+ str + '</span>';
						} else if (data == '000') {
							str = '个人店铺';
							html += '<span class="label label-success radius">'
									+ str + '</span>';
						}
						return html;
					}
				},
				{
					"mDataProp" : "status",
					"className" : "text-c",
					"orderable" : false,
					"render" : function(data, type, full) {
						var str = '冻结中';
						var html = "";
						if (data == '002') {
							str = '冻结中';
							html += '<span class="label label-success radius">'
									+ str + '</span>';
						}else if (data == '001') {
							str = '正在申请中';
							html += '<span class="label label-success radius">'
									+ str + '</span>';
						} else if (data == '000') {
							str = '正常营业';
							html += '<span class="label label-info radius">'
									+ str + '</span>';
						}
						return html;
					}
				},
				{
					"data" : "applytime",
					"className" : "text-c"
				},
				{
					"data" : "approver",
					"className" : "text-c"
				},
				{
					"data" : "adopttime",
					"className" : "text-c"
				},
				{
					"data" : "remark",
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
					html += '<a style="text-decoration:none" onClick="update(\''
							+ full.id
							+ '\',\''
							+ full.name
							+ '\',\''
							+ full.username
							+ '\',\'修改店铺\',\'userAuthorize.html\')" href="javascript:;" title="修改店铺">';
					html += '<i class="Hui-iconfont" style="font-size:18px;color:#B2ED83;">&#xe6cc;</i>修改店铺</a>';
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
	$("#update").click(function() {
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
			shop_edit(id_array[0], "修改商铺信息", "shop/shop-add.html");
		}

	});
});

/* 修改商铺状态 */
function shop_edit(id,title,url){
	layer.open({
		type : 2,
		title : title,
		content : url + "?shopid=" + id,
		area : [ '600px', '420px' ]
	});
}
/* 批量冻结商铺状态 */
function shop_delete(){
	var id_array = new Array();
	$("input[type='checkbox']:checked").each(function() {
		id_array.push($(this).val());// 向数组中添加元素
	});
	var num = id_array.length;
//	console.log(id_array);
	
	if (num <= 0) {
		layer.msg("请选择一项！", {
			icon : 2,
			time : 1000
		});
	} else {
		$.ajax({
			"url":"shop/deletShop.html",
			"type":"POST",
			"data": {
				"idArray" : id_array.join(",")
			},
			"dataType" : "json",
			"success":function(data){
				if(data.code == '001'){
					layer.msg("冻结成功",{icon: 1,time:1000});
					table.draw(true);
				}else if(data.code == '000'){
					layer.msg("冻结失败",{icon: 6,time:1000});
				}
				
			},
		    "error":function(data){
		    	layer.msg("操作失败",{icon: 6,time:1000});
		    }
		});
	}
}