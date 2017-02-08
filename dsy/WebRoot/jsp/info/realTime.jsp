<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
  <%@include file="/static/public.jsp"%>
    <title>小屏静态信息</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/info/js/realTime.js"></script>
  </head>
  <body>
   <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 
   <span class="c-gray en">&gt;</span> 信息发布
    <span class="c-gray en">&gt;</span> 小屏实时信息 
    <a class="btn btn-success radius r mr-20" 
    style="line-height:1.6em;margin-top:3px" 
    href="javascript:location.replace(location.href);"
     title="刷新" ><i class="Hui-iconfont">&#xe68f;</i>
     </a>
     </nav>
      <div class="pd-20">
	<div class="text-c"> 
	 日期范围：
		<input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="logmin" class="input-text Wdate" style="width:180px;">
		-
		<input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'logmin\')}'})" id="logmax" class="input-text Wdate" style="width:180px;">
	&nbsp;&nbsp;
	状态：
	<span class="select-box inline">
	     <select name="" class="select">
			<option value="0">请选择</option>
			<option value="11">未审核</option>
			<option value="00">已审核</option>
			<option value="10">不通过</option>
		</select>
	</span>
	&nbsp;&nbsp;
	站台：
	<span class="select-box inline">
	     <select name="" class="select">
			<option value="0">请选择</option>
			<option value="1">未审核</option>
			<option value="2">已审核</option>
		</select>
	</span>	
		<button name="" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i>查询</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> 
	<span class="l">
	<a class="btn btn-primary radius" onclick="static_add('添加信息','static_add.html')" href="javascript:;">
	<i class="Hui-iconfont">&#xe600;</i> 添加</a>
	<a class="btn btn-warning radius" onclick="article_edit('编辑信息','static_add.html')" href="javascript:;">
	<i class="Hui-iconfont">&#xe60c;</i> 修改</a>
	<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius">
	<i class="Hui-iconfont">&#xe6e2;</i> 删除</a> 
	</span> <span class="r">共有数据：<strong id="total">3</strong>条</span> </div>
	<div class="mt-20">
		<table id="dataTable" class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th width="25"><input type="checkbox" id="selectAll" name="" value=""></th>
					<th width="200">标题</th>
					<th width="250">描述</th>
					<th width="50">图片</th>
					<th width="350">有效时间</th>
					<th width="50">站台</th>
					<th width="75">发布者</th>
					<th width="180">发布时间</th>
					<th width="120">状态</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</div>
  </body>
</html>
