<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@include file="/static/public.jsp"%>
    
    <link href="<%=request.getContextPath()%>/static/lib/icheck/icheck.css" rel="stylesheet" type="text/css" />   
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/lib/icheck/jquery.icheck.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/lib/Validform/5.3.2/Validform.min.js"></script>  
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/public.js"></script>
    
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/H-ui.js"></script> 
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/H-ui.admin.js"></script>  
    <script type="text/javascript" src="<%=request.getContextPath() %>/jsp/user/js/user_add.js"></script>
    
    <title></title>
  </head>
  <title>添加用户</title>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="paramContainer">
	    <div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>用户名称：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" value="" placeholder="" id="realName" name="realName" datatype="*2-16" nullmsg="用户名不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>用户账号：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" value="" placeholder="" id="userName" name="userName" datatype="*2-16" nullmsg="用账号不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>密码：</label>
			<div class="formControls col-5">
				<input id="password" type="password" placeholder="密码" autocomplete="off" value="" class="input-text" datatype="*6-20" nullmsg="密码不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3">所属部门：</label>
			<div class="formControls col-5"><span class="select-box" style="width:150px;">
				<select class="select" id="dept" name="dept" size="1">
				</select>
				</span> 
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3">状态：</label>
			<div class="formControls col-5"> <span class="select-box" style="width:150px;">
				<select id="status" class="select" name="lock" size="1">
					<option value="0">未激活</option>
					<option value="1">已激活	</option>
				</select>
				</span> </div>
		</div>
		<!-- <div class="row cl">
			<label class="form-label col-3">备注：</label>
			<div class="formControls col-5">
				<textarea id="remark" name="" cols="" rows="" class="textarea"  placeholder="说点什么...100个字符以内" dragonfly="true" onKeyUp="textarealength(this,100)"></textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>
			</div>
			<div class="col-4"> </div>
		</div> -->
		<div class="row cl">
			<div class="col-9 col-offset-3">
				<input id="submit" class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
		<input id="userid" type="hidden" value="" />
	</form>
</div>
  </body>
</html>
