<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <%@include file="/static/public.jsp"%>
    <title>新增角色</title>
     <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/H-ui.js"></script> 
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/H-ui.admin.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/lib/icheck/jquery.icheck.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/lib/Validform/5.3.2/Validform.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/jsp/admin/js/role_add.js"></script>
  </head>
  <body>
    <div class="pd-20">
	<div class="text-c" id="paramContainer"> 
	    <div class="row cl pd-5">
			<label class="form-label col-3 text-r"><span class="c-red">*</span>角色名称：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" value="" placeholder=""
				 id="rolename" name="rolename" datatype="*2-16" nullmsg="角色名不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl pd-5">
			<label class="form-label col-3 text-r">角色说明：</label>
			<div class="formControls col-5">
				<textarea id="remark" name="remark" cols="" rows="" class="textarea" 
				 placeholder="说点什么...100个字符以内" dragonfly="true" onKeyUp="textarealength(this,100)" nullmsg="角色说明不能为空"></textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl pd-5">
			<div class="col-3"></div>
			<div class="col-5 text-l">
				<input id="submit" class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
		<input id="roleid" type="hidden" value="" />
	</div>
</div>
  </body>
</html>
