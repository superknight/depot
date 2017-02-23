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
			isCopy: true,
			isMove: false,
			prev: true, //允许 移动到目标节点前面
			next: true,  //允许 移动到目标节点后面
			inner: true,  //允许 成为目标节点的子节点
			borderMax: 10,  //拖拽节点成为根节点时的 Tree 内边界范围
			borderMin: -10, //拖拽节点成为根节点时的 Tree 外边界范围
			minMoveSize: 10, //判定是否拖拽操作的最小位移值
		}
	},
	callback: {
//		beforeClick: beforeClick,   //点击前的回调函数
//		beforeAsync: beforeAsync,	//异步加载前的回调函数
		onAsyncError: onAsyncError,  //异步加载错误回调函数
		onAsyncSuccess: onAsyncSuccess,  //异步加载成功回调函数
	
		beforeRemove: beforeRemove, //移除前的回调函数
		beforeRename: beforeRename,  
	}
};

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
		return false;
	});
};
function removeHoverDom(treeId, treeNode) {
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

$(function(){
	var t = $("#treeDemo");
	t = $.fn.zTree.init(t, setting);
});
