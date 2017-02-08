<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/static/public.jsp"%>
<title>图片上传</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/public.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/material/js/mater-upload.js"></script>
</head>
<body style="margin:0px;width: 100%;height:100%">
	<div class="pd-20">
		<div style="width:10%;float:left;margin-left:5%">
			<a class="btn btn-success radius" id="add" onclick="metarial_add()" href="javascript:;">
				<i class="Hui-iconfont">&#xe600;</i> 添加
			</a>
		</div>
		<div style="width:80%;float:left;">
			<div class="row cl">
				<div class="formControls col-10" id="content">
					<dl class="permission-list" style="margin-bottom: 10px;">
						<dd style="height:120px;">
							<div class="row cl pd-5" >
								<label class="form-label col-2"><span class="c-red">*</span>上传类型：</label>
								<div class="formControls col-9">
									<span class="select-box inline"> 
									<select id="type" name="type" class="select">
									    <option selected='selected' value='0'>请选择</option>
									  	<option selected='' value='1'>图片</option>
									  	<option selected='' value='2'>视频</option>
									</select>
									</span>
								</div>
								<div class="formControls col-1">
								<a onclick="remove(this)" href="javascript:;">
								<span class="label radius" style="color:#222;background-color: #ffffff; border:1px solid #ccc;">关闭
								<i class="Hui-iconfont" style="float:right;top:0px;color:#B7170F;">&#xe6a6;</i>
								</span>
								</a>
								</div>
							</div>
							<div class="row cl pd-5">
								<label class="form-label col-2"><span class="c-red">*</span>简略标题：</label>
								<div class="formControls col-10">
									<input type="text" class="input-text" value="" placeholder=""
										id="" name="">
								</div>
							</div>
							<div class="row cl pd-5">
								<label class="form-label col-2"><span class="c-red">*</span>上传文件：</label>
								<div class="formControls col-10">
									<input type="file" class="input-text" id="imgFile" name="imgFile">
								</div>
							</div>
						</dd>
					</dl>
					
				</div>
			</div>
			
			<div class="row cl pd-20" style="top:20px;">
				<div class="col-9 col-offset-3">
				<input id="submit" class="btn btn-primary radius" type="submit"
					value="&nbsp;&nbsp;上传&nbsp;&nbsp;"> <input id="rest"
					class="btn btn-primary radius" style="margin-left:60px;"
					type="submit" value="&nbsp;&nbsp;取消&nbsp;&nbsp;">
				</div>
		    </div>
		</div>
	</div>
</body>
</html>
