<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <%@include file="/static/public.jsp"%>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/H-ui.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/H-ui.admin.js"></script>

	<script type="text/javascript" src="<%=request.getContextPath()%>/static/lib/icheck/jquery.icheck.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/lib/Validform/5.3.2/Validform.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/jsp/shop/js/shop_add.js"></script>
</head>
  <body>
    <div class="pd-20">
	<form action="" class="form form-horizontal" id="paramContainer">
	    <div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>商铺名称：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="${shop.shopname }" placeholder="" id="shopname" name="shopname" datatype="*2-16" nullmsg="商铺名称不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>店铺类型：</label>
			<%-- <div class="formControls col-8">
				<input type="text" class="input-text" value="${shop.type }" placeholder="" id="type" name="type" datatype="*1-10" nullmsg="店铺类型不为空">
			</div> --%>
			<div class="formControls col-8"> <span class="select-box" style="width:150px;">
				<select id="type" class="select" name="type" size="1">
					<option value="${shop.type }">
						<c:if test="${shop.type==000 }">个人店铺</c:if>
						<c:if test="${shop.type==001 }">企业店铺</c:if>
					</option>
					<c:if test="${shop.type!=000 }">
						<option value="000">个人店铺</option>
					</c:if>
					<c:if test="${shop.type!=001 }">
						<option value="001">企业店铺</option>
					</c:if>
				</select>
			</span> </div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>店铺状态：</label>
			<%-- <div class="formControls col-8">
				<input type="text" class="input-text" value="${shop.status }" placeholder="" id="status" name="status" datatype="*1-10" nullmsg="店铺状态不为空">
			</div> --%>
			<div class="formControls col-8"> <span class="select-box" style="width:150px;">
				<select id="status" class="select" name="status" size="1">
					<option value="${shop.status }">
						<c:if test="${shop.status==000 }">正常营业</c:if>
						<c:if test="${shop.status==001 }">正在申请中</c:if>
						<c:if test="${shop.status==002 }">冻结中</c:if>
					</option>
					<c:if test="${shop.status!=000 }">
						<option value="000">正常营业</option>
					</c:if>
					<c:if test="${shop.status!=001 }">
						<option value="001">正在申请中</option>
					</c:if>
					<c:if test="${shop.status!=002 }">
						<option value="002">冻结中</option>
					</c:if>
				</select>
			</span> </div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3">商铺说明：</label>
			<div class="formControls col-8">
				<textarea id="remark" name="remark" value="" cols="" rows="" class="textarea"  placeholder="说点什么...100个字符以内" dragonfly="true" onKeyUp="textarealength(this,100)" nullmsg="角色说明不能为空">${shop.remark }</textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<div class="col-9 col-offset-3">
				<input id="submit" class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
		<input id="shopid" type="hidden" value="${shop.id }" />
	</form>
</div>
  </body>
</html>
