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
    <script type="text/javascript" src="<%=request.getContextPath() %>/jsp/admin/js/user_add.js"></script>
    
    <title></title>
  </head>
  <title>添加用户</title>
</head>
<body>
<div class="pd-20">
	<div class="text-c" id="paramContainer"> 
	    <div class="row cl pd-5">
			<label class="form-label col-3 text-r"><span class="c-red">*</span>真实姓名：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" value="" placeholder="请输入名字"
				 id="name" name="name" datatype="s2-6" nullmsg="真实姓名不能为空" errormsg="真实姓名格式不对">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl pd-5">
			<label class="form-label col-3 text-r"><span class="c-red">*</span>用户账号：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" value="" placeholder="请输入以字母开头的5至20位字母和数字" 
				id="username" name="username" datatype="/^[a-zA-Z][a-z0-9A-Z]{4,20}$/" nullmsg="账号不能为空"  errormsg="账号格式不对">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl pd-5">
			<label class="form-label col-3 text-r"><span class="c-red">*</span>密码：</label>
			<div class="formControls col-5">
				<input id="password" type="text" placeholder="请输入密码，至少6位" 
				autocomplete="off" value="" class="input-text" datatype="s6-20" nullmsg="密码不能为空" errormsg="密码格式不对">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl pd-5">
			<label class="form-label col-3 text-r"><span class="c-red">*</span>性别：</label>
			<div class="formControls col-5 text-l"> <span class="select-box" style="width:150px;">
				<select id="sex" class="select" name="sex" size="1">
					<option value="男">男</option>
					<option value="女">女</option>
				</select>
				</span> </div>
		</div>
		
		<div class="row cl pd-5">
			<label class="form-label col-3 text-r"><span class="c-red">*</span>电话号码：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" value="" 
				placeholder="请输入手机号码" id="phone" name="phone" 
				datatype="m" nullmsg="手机号码不能为空" errormsg="手机号码格式不对">
			</div>
			<div class="col-4"> </div>
		</div>
		
		<div class="row cl pd-5">
			<label class="form-label col-3 text-r">邮箱：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" value="" placeholder="请输入邮箱" 
				id="email" name="email" datatype="e" errormsg="邮箱格式不对">
			</div>
			<div class="col-4"> </div>
		</div>
		
		<div class="row cl pd-5">
			<label class="form-label col-3 text-r">地址：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" value="" 
				placeholder="" id="address" name="address" datatype="s6-30" errormsg="地址格式不对">
			</div>
			<div class="col-4"> </div>
		</div>
		
		<div class="row cl pd-5">
			<label class="form-label col-3 text-r">状态：</label>
			<div class="formControls col-5 text-l"> <span class="select-box" style="width:150px;">
				<select id="status" class="select" name="status" size="1">
					<option value="0">未激活</option>
					<option value="1">已激活	</option>
				</select>
				</span> </div>
		</div>
		<div class="row cl pd-5">
			<div class="col-3"></div>
			<div class="col-9 text-l">
				<input id="submit" class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
		<input id="id" type="hidden" value="" />
	</div>
</div>
  </body>
</html>
