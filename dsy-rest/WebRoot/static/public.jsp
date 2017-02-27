<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
	request.setAttribute("contextPath", contextPath);
	request.setAttribute("serverPath", request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + contextPath);
	response.setHeader("P3P", "CP=CAO PSA OUR");
%>
<LINK rel="Bookmark" href="<%=request.getContextPath()%>/static/images/favicon.ico" >
<LINK rel="Shortcut Icon" href="<%=request.getContextPath()%>/static/images/favicon.ico" />
<link href="<%=request.getContextPath()%>/static/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/static/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/static/skin/default/skin.css" rel="stylesheet" type="text/css" id="skin" />
<link href="<%=request.getContextPath()%>/static/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/static/css/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<!--[if lt IE 9]>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/html5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/respond.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/PIE_IE678.js"></script>
<![endif]-->
<script type="text/javascript" src="<%=request.getContextPath()%>/static/lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/static/lib/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/lib/dataTables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/lib/layer/layer.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/common.js"></script>
<script type="text/javascript">
	var Global = {};
	Global.contextPath = '${contextPath}';
	Global.serverPath = '${serverPath}';
	var Config = Global ;
	
	$.fn.dataTable.ext.errMode = function(s,h,m){
		layer.msg("请求发生错误", {
			icon : 2,
			time : 1000
		});
	}
</script>
