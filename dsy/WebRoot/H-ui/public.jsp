<%
	String contextPath = request.getContextPath();
	request.setAttribute("contextPath", contextPath);
	request.setAttribute("serverPath", request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + contextPath);
	response.setHeader("P3P", "CP=CAO PSA OUR");
%>
<LINK rel="Bookmark" href="<%=request.getContextPath()%>/H-ui/images/favicon.ico" >
<LINK rel="Shortcut Icon" href="<%=request.getContextPath()%>/H-ui/images/favicon.ico" />
<link href="<%=request.getContextPath()%>/H-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/H-ui/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/H-ui/skin/default/skin.css" rel="stylesheet" type="text/css" id="skin" />
<link href="<%=request.getContextPath()%>/H-ui/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/H-ui/css/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<!--[if lt IE 9]>
<script type="text/javascript" src="<%=request.getContextPath()%>/H-ui/js/html5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/H-ui/js/respond.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/H-ui/js/PIE_IE678.js"></script>
<![endif]-->
<script type="text/javascript" src="<%=request.getContextPath()%>/H-ui/lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/H-ui/lib/jquery/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/H-ui/lib/dataTables/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/H-ui/lib/layer/layer.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/H-ui/js/common.js"></script>
<script type="text/javascript">
	var Global = {};
	Global.contextPath = '${contextPath}';
	Global.serverPath = '${serverPath}';
	var Config = Global ;
</script>
