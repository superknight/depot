<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <%@include file="/static/public.jsp"%>
    <title>查看图片</title>
  </head>
  <body>
	<img alt=""  style="width: 380px;height:380px;margin-left:60;margin-top:35px;" src="<%=request.getContextPath() %>/getStaicImage.html?id=${id}">
  </body>
</html>
