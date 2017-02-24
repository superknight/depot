
//zTree
var setting = {
	//显示
	view:{
		dblClickExpand:true, //双击节点时，是否自动展开父节点的标识
		showLine:true, //设置 zTree 是否显示节点之间的连线。
		addHoverDom:addHoverDom, //用于当鼠标移动到节点上时，显示隐藏状态的编辑和删除按钮
		removeHoverDom:removeHoverDom, //用于当鼠标移出节点是，隐藏编辑和删除按钮
		selectedMulti:false,  //禁止同时选中多个节点
	},
    async:{ //加载
    	enable: true, // 设置 zTree 是否开启异步加载模式
    	autoParam: ["id"],  //异步加载时需要自动提交父节点属性的参数。
    	contentType: "application/json", //Ajax 提交参数的数据类型
    	dataType:"json",
    	type: "post",
    	url: "product/getProductClass.html",
    	dataFilter:ajaxDataFilter
    },
    data: {
		simpleData: {
			enable: true,
		}
	},
    edit: {
		enable: true, //设置 zTree 是否处于编辑状态
		drag: { 	  //isCopy = false; isMove = true 时，所有拖拽操作都是 move
			isCopy: false,
			isMove: false,
		}
	},
	callback: {
//		beforeClick: beforeClick,   //点击前的回调函数
//		beforeAsync: beforeAsync,	//异步加载前的回调函数
		onAsyncError: onAsyncError,  //异步加载错误回调函数
		onAsyncSuccess: onAsyncSuccess,  //异步加载成功回调函数
		beforeRemove: beforeRemove, //移除前的回调函数
		beforeRename: beforeRename, //修改名字的回调函数 
		onRename:onRename,  //修改名字之后的操作
		onRemove:onRemove,
	}
};

function onRename(event, treeId, treeNode, isCancel){
	saveAndEdit(treeNode.id,null,treeNode.name);
}

function onRemove(event, treeId, treeNode){
	alert(treeNode.id + ", " + treeNode.name);
	$.ajax({
		"url":"product/deleteProductClass.html",
		"type":"post",
		"data":{"id":treeNode.id},
		"success":function(data){
			var list = $.parseJSON(data);
			if(list.status == "000")
			{ layer.msg("删除成功",{icon: 6,time:1000});}
			if(list.status == "001")
			{ layer.msg("删除失败！",{icon: 5,time:1000});}	
		},
		"error":function(data){
			layer.msg("请求发生错误！",{icon: 2,time:1000});
		}
	});
}

function onAsyncError(treeId,treeNode){ 
	layer.msg("加载错误！",{icon: 5,time:1000});
}

function onAsyncSuccess(treeId,treeNode){
	console.log(treeNode);
}

function beforeRemove(treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.selectNode(treeNode);
	return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
}
function beforeRename(treeId, treeNode, newName) {
	if (newName.length == 0) {
		layer.msg("节点不能为空！",{icon: 5,time:1000});
		return false;
	}
	return true;
}
var newCount = 1;
function addHoverDom(treeId, treeNode) {
	var sObj = $("#" + treeNode.tId + "_span");
	if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) 
		return;
	var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
		+ "' title='新增' onfocus='this.blur();'></span>";
	
	sObj.after(addStr);
	var btn = $("#addBtn_"+treeNode.tId);
	if (btn) btn.bind("click", function(){
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"新建文件" + (newCount++)});
		saveAndEdit(null,treeNode.id,"新建文件"+newCount);
		return false;
	});
};
function removeHoverDom(treeId, treeNode) {
	$("#addBtn_"+treeNode.tId).unbind().remove();
	$("#diyBtn_"+treeNode.id).unbind().remove();
	$("#diyBtn_space_" +treeNode.id).unbind().remove();
};

function ajaxDataFilter(treeId, parentNode, childNodes){
	zNodes = childNodes;
	if (!childNodes) return null;
	for (var i = 0, l = childNodes.length; i < l; i++) {
		childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
	}
	return childNodes;
}		

//新增与修改
function saveAndEdit(id,pId,name){
	$.ajax({
		"url":"product/saveAndEditProductClass.html",
		"type":"post",
		"data":{"id":id,"pId":pId,"name":name},
		"success":function(data){
			var list = $.parseJSON(data);
			if(list.msg == "000")
			{ layer.msg("新增了一个文件",{icon: 6,time:1000});}
			if(list.msg == "001")
			{ layer.msg("新增文件失败！",{icon: 5,time:1000});}	
			if(list.msg == "100")
			{ layer.msg("重命名成功！",{icon: 6,time:1000});}
			if(list.msg == "001")
			{ layer.msg("重命名失败！",{icon: 5,time:1000});}	
		},
		"error":function(data){
			layer.msg("请求发生错误！",{icon: 2,time:1000});
		}
	});
}


//dataTable
var param = {}; //参数对列
var table; 
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
$(function(){
	var t = $("#treeDemo");
	t = $.fn.zTree.init(t, setting);
});


