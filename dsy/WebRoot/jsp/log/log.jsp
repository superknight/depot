<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
  <%@include file="/static/public.jsp"%>
    <title>系统日志</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/log/js/log.js"></script>
  </head>
  <body>
   <nav class="breadcrumb"><i class="Hui-iconfont" style="font-size: 18px;">&#xe67f;</i> 首页 
   <span class="c-gray en">&gt;</span> 系统管理
    <span class="c-gray en">&gt;</span> 系统日志
    <a class="btn btn-success radius r mr-20" 
    style="line-height:1.6em;margin-top:3px" 
    href="javascript:location.replace(location.href);"
     title="刷新" ><i class="Hui-iconfont">&#xe68f;</i>
     </a>
     </nav>
     <div class="pd-20">
	<div id="paramContainer" class="text-c"> 
	 日期范围：
		<input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="logmin" class="input-text Wdate" style="width:180px;">
		-
		<input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'logmin\')}'})" id="logmax" class="input-text Wdate" style="width:180px;">
	&nbsp;&nbsp;
		<button name="search" id="search" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i>查询</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> 
	<span class="l">
	<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius">
	<i class="Hui-iconfont">&#xe6e2;</i> 删除</a> 
	</span></div>
	<div class="mt-20">
		<table id="dataTable" class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th width="25"><input type="checkbox" id="selectAll" name="" value=""></th>
					<th width="200">操作人</th>
					<th width="250">描述</th>
					<th width="220">时间</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</div>
</body>
</html>
