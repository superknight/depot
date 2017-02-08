<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <%@include file="/static/public.jsp"%>
    <title>查看站台</title>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/public.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/jsp/info/js/station.js"></script>
  </head>
  <body>
    <div class="pd-10">
	    <div id="content" class="row cl text-c" style="font-size:16px;">
	  
		</div>
		<input id="idArray" type="hidden" value="${idArray}" />
</div>
  </body>
</html>
