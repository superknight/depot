function jsonDateFormat(jsonDate) {
	try {
		// var date = new Date(parseInt(jsonDate.replace("/Date(",
		// "").replace(")/", ""), 10));
		// alert("jsondate:"+jsonDate);
		if (jsonDate.length == 17) {
			return jsonDate;
		} else {

			var date = new Date(jsonDate);
			var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1)
					: date.getMonth() + 1;
			var day = date.getDate() < 10 ? "0" + date.getDate() : date
					.getDate();
			var hours = date.getHours();
			var minutes = date.getMinutes();
			var seconds = date.getSeconds();
			// var milliseconds = date.getMilliseconds();
			return date.getFullYear() + "-" + month + "-" + day + " " + hours
					+ ":" + minutes + ":" + seconds;// + "." + milliseconds;
		}
	} catch (ex) {
		return "";
	}
}

// 检查手机或电话
var ValidatePhone = {
	isTel : function(value) {
		var patrn = /(\+86)?((\d{3,4})|(\d{3,4})-|)?\d{7,8}/;
		return patrn.test(value);
	},
	isMobile : function(value) {
		var validateReg = /(\+86)?1\d{10}/;
		return validateReg.test(value);
	}
};

// 校验身份证合法性（15或18位）
function isValidCard(data) {
	var rep = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
	return rep.test(data);
}

// 查询结果为空时，中间文字显示“暂无查询结果”
function setNoDataMsg(data) {
	if (data.total == 0) {
		$("#divNoData").remove();
		var div = $(".datagrid-view");
		div.append("<div id='divNoData' style='padding-top:"
				+ (div.height() / 2 - 15) + "px; padding-left:"
				+ (div.width() / 2 - 40) + "px;'>暂无查询结果</div>");
	} else {
		$("#divNoData").remove();
	}
}

// 加载角色
function loadRole() {
	$.ajax({
		url : "getRoleName.html",
		type : "post",
		dateType : "json",
		success : function(data) {
			$("#role").find("option").remove();
			var list = $.parseJSON(data);
			$("<option selected='selected' value=''>不限</option>").appendTo(
					$("#role"));
			for ( var i = 0; i < list.length; i++) {
				var str = "<option value='" + list[i].roleId + "'>"
						+ list[i].roleName + "</option>";
				$(str).appendTo($("#role"));
			}
		},
		error : function(data) {
		}
	});
}

// 加载部门
function loadDept() {
	$.ajax({
		url : "getDeptName.html",
		type : "post",
		dateType : "json",
		success : function(data) {
			$("#dept").find("option").remove();
			var list = $.parseJSON(data);
			$("<option selected='selected' value=''>不限</option>").appendTo(
					$("#dept"));

			for ( var i = 0; i < list.length; i++) {
				var str = "<option value='" + list[i].deptId + "'>"
						+ list[i].deptName + "</option>";
				$(str).appendTo($("#dept"));
			}
		},
		error : function(data) {
			// $.messager.alert('提示',"加载角色失败！",'info');
		}
	});
}

// 加载已关联的站台
function loadStation() {
	$
			.ajax({
				"url" : "getRealStation.html",
				"type" : "POST",
				"data" : {
					"idArray" : $("#idArray").val()
				},
				"success" : function(data) {
					var list = $.parseJSON(data);
					var array = list.data;
					console.log(list);
					var str = "";
					for ( var i = 0; i < array.length; i++) {
						str += '<span id="'
								+ array[i].sid
								+ '" class="label radius" style="margin:8px;font-size:12px; background-color:#F6F7F9;color:#333333">'
								+ array[i].sName
								+ ' &nbsp;<i class="Hui-iconfont" style="float:right;top:0px;color:#B7170F;">&#xe60b;</i></span>';
					}
					$("#content").append(str);
				},
				"error" : function(data) {
					layer.msg("操作失败", {
						icon : 6,
						time : 1000
					});
				}
			});
}

function selectCheckAndTable() {
	// 全选与取消
	$("#selectAll").on("click", function() {
		var f = $(this).prop("checked");
		if (f) {
			$("#dataTable tbody").find(":checkbox").each(function() {
				$(this).prop("checked", true);
			});
		} else {
			$("#dataTable tbody").find(":checkbox").each(function() {
				$(this).prop("checked", false);
			});
		}
	});
	// 点击选中
	$('.table-sort tbody').on('click', 'tr', function() {
		$(this).toggleClass('selected');
	});
}
// dataTables common config
var DT_COMMON_CONFIG = {
	"processing" : true,
	"serverSide" : true,
	"autoWidth" : false, // 自动获取宽度
	"searching" : false, // 检索栏
	"orderMulti" : false,// 多列排序
	"paging" : true,
	"retrieve" : false,
	"pagingType" : "full_numbers", // 分页类型
	"language" : {
		"lengthMenu" : '<select class="form-control input-xsmall">'
				+ '<option value="1">1</option>'
				+ '<option value="5">5</option>'
				+ '<option value="10">10</option>'
				+ '<option value="20">20</option>'
				+ '<option value="30">30</option>'
				+ '<option value="40">40</option>'
				+ '</select><span>条记录</span>',// 左上角的分页大小显示。

		"search" : '<span style="color:#F3745B">从当前数据中检索:</span>',// 右上角的搜索文本，可以写html标签
		"paginate" : {
			"first" : "首页",
			"previous" : "上页",
			"next" : "下页",
			"last" : "末页"
		},
		"loadingRecords" : "正在获取数据，请稍后..",
		"zeroRecords" : "没有内容",// table tbody内容为空时，tbody的内容。//下面三者构成了总体的左下角的内容。
		"info" : "总共_PAGES_ 页，共 _TOTAL_ 条记录 ",// 左下角的信息显示，大写的词为关键字。
		"infoEmpty" : "0条记录",// 筛选为空时左下角的显示。
		"infoFiltered" : ""// 筛选之后的左下角筛选提示，
	},
	"initComplete" : function(settings, json) {
		$("#total").html("");
		$("#total").append(json.recordsTotal);
	}
};