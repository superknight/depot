<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <%@include file="/static/public.jsp"%>
    <title>角色授权</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/H-ui.js"></script> 
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/H-ui.admin.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/jsp/user/js/user_authorize.js"></script>
  </head>
  <body>
    <div class="pd-10">
	    <div class="row cl text-c" style="font-size:16px;">
	   名称:<span style="font-size:18px;color:#E16B26">${realName}</span>&nbsp;&nbsp;账号:<span style="font-size:18px;color:#E16B26">${userName}</span> 
	    </div>
	    <div id="content" class="row cl pd-10">
	         
	    </div>
		<div class="row cl pd-10">
			<div class="col-9 col-offset-3">
				<input id="submit" class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;授予角色&nbsp;&nbsp;">
			</div>
		</div>
</div>
  </body>
</html>
