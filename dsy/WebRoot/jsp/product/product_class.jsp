<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@include file="/static/public.jsp"%>
    <title>产品分类</title>
   <%--  <link rel="stylesheet" href="<%=request.getContextPath() %>/static/lib/ztree/css/metroStyle/metroStyle.css" type="text/css">
    --%> <link rel="stylesheet" href="<%=request.getContextPath() %>/static/lib/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/lib/ztree/jquery.ztree.all-3.5.min.js"></script> 
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/H-ui.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/H-ui.admin.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/product/js/product_class.js"></script>
  </head>
  <body>
  <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 产品管理 <span class="c-gray en">&gt;</span> 产品分类 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<table class="table">
	<tr>
		<td width="200" class="va-t">
		<div width="200" class="text-c" style="float:left; font-size:18px;background-color: #00FF80;color:#ffffff">产品目录</div>
		<div width="200" height="" class="text-c"></div>
		<ul id="treeDemo" class="ztree"></ul>
		</td>
		<td class="va-t">
				<div class="pd-20">
    <div id="paramContainer" class="text-c">
    &nbsp;&nbsp;用户：
    <input id="username" type="text" class="input-text" style="width:250px" placeholder="输入用户名称" id="" name=""> 
    <button name="search" id="search" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i>查询</button>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20"> 
    <span class="l">
    <a class="btn btn-primary radius" onclick="adminUser_add('添加信息','user-add.html')" href="javascript:;">
    <i class="Hui-iconfont">&#xe600;</i> 添加</a>
    <a class="btn btn-warning radius" id="updateuser" href="javascript:;">
    <i class="Hui-iconfont">&#xe60c;</i> 修改</a>
    <a href="javascript:;" onclick="datadel()" class="btn btn-danger radius">
    <i class="Hui-iconfont">&#xe6e2;</i> 删除</a> 
    </span></div>
    <div class="mt-20">
        <table id="dataTable" class="table table-border table-bordered table-bg table-hover table-sort">
            <thead>
                <tr class="text-c">
                    <th width="25"><input type="checkbox" id="selectAll" name="" value=""></th>
                    <th width="120">类名</th>
                    <th width="80">类的级别</th>
                    <th width="120">父类</th>
                    <th width="120">类的上下文路径</th>
                    <th width="150">类的描述</th>
                    <th width="80">创建人</th>
                    <th width="80">创建时间</th>
                </tr>
            </thead>
            <tbody>
              </tbody>
        </table>
    </div>
   </div>
</td>
	</tr>
</table>
</body>
</html>