<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@include file="/static/public.jsp"%>
    <title>产品分类</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/lib/ztree/css/metroStyle/metroStyle.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/lib/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/lib/ztree/jquery.ztree.all-3.5.min.js"></script> 
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/H-ui.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/H-ui.admin.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/product/js/product_class.js"></script>
  </head>
  <body>
  <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 产品管理 <span class="c-gray en">&gt;</span> 产品分类 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<table class="table">
	<tr>
		<td width="200" class="va-t"><ul id="treeDemo" class="ztree"></ul></td>
		<td class="va-t"><IFRAME ID="testIframe" Name="testIframe" FRAMEBORDER=0 SCROLLING=AUTO width=100%  height=390px SRC=""></IFRAME></td>
	</tr>
</table>
</body>
</html>