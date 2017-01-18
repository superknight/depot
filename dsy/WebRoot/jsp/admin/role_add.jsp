<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <%@include file="/H-ui/public.jsp"%>
    <title>新增角色</title>
     <script type="text/javascript" src="<%=request.getContextPath()%>/H-ui/js/H-ui.js"></script> 
    <script type="text/javascript" src="<%=request.getContextPath()%>/H-ui/js/H-ui.admin.js"></script>
    
    <script type="text/javascript" src="<%=request.getContextPath()%>/H-ui/lib/icheck/jquery.icheck.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/H-ui/lib/Validform/5.3.2/Validform.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/jsp/admin/js/role_add.js"></script>
  </head>
  <body>
    <div class="pd-20">
	<form action="" class="form form-horizontal" id="paramContainer">
	    <div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>角色名称：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" value="" placeholder="" id="rolename" name="rolename" datatype="*2-16" nullmsg="角色名不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>排列序号：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" value="" placeholder="" id="order" name="order" datatype="*1-10" nullmsg="角色序号不为空">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3">角色说明：</label>
			<div class="formControls col-5">
				<textarea id="remark" name="remark" cols="" rows="" class="textarea"  placeholder="说点什么...100个字符以内" dragonfly="true" onKeyUp="textarealength(this,100)" nullmsg="角色说明不能为空"></textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<div class="col-9 col-offset-3">
				<input id="submit" class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
		<input id="roleid" type="hidden" value="" />
	</form>
</div>
  </body>
</html>
