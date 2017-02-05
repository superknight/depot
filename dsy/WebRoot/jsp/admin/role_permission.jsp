<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <%@include file="/H-ui/public.jsp"%>
    <title>角色授权</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/H-ui/js/H-ui.js"></script> 
    <script type="text/javascript" src="<%=request.getContextPath()%>/H-ui/js/H-ui.admin.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/jsp/admin/js/role_permission.js"></script>
  </head>
  <body>
    <div class="pd-10">
	    <div class="row cl text-c" style="font-size:24px;color:#E16B26;">
	    ${rolename}
	    </div>
	    <div id="content" class="row cl">
	         
	    </div>
		<div class="row cl">
			<div class="col-9 col-offset-3">
				<input id="submit" class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;授权&nbsp;&nbsp;">
			</div>
		</div>
</div>
  </body>
</html>
