<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <%@include file="/static/public.jsp"%>
    <title>角色管理</title>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/admin	/js/role.js"></script>
  </head>
  <body>
   <nav class="breadcrumb"><i class="Hui-iconfont" style="font-size: 18px;">&#xe67f;</i> 首页 
   <span class="c-gray en">&gt;</span> 用户管理
    <span class="c-gray en">&gt;</span> 角色管理
    <a class="btn btn-success radius r mr-20" 
    style="line-height:1.6em;margin-top:3px" 
    href="javascript:location.replace(location.href);"
     title="刷新" ><i class="Hui-iconfont">&#xe68f;</i>
     </a>
   </nav>
     <div class="pd-20">
    <div id="paramContainer" class="text-c"> 
    <input id="role" type="text" class="input-text" style="width:250px" placeholder="输入角色名称" id="" name=""> 
    <button name="search" id="search" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i>查询</button>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20"> 
    <span class="l">
    <a class="btn btn-primary radius" onclick="role_add('添加角色','role-add.html')" href="javascript:;">
    <i class="Hui-iconfont">&#xe600;</i> 添加</a>
    <a class="btn btn-warning radius" id="updaterole" href="javascript:;">
    <i class="Hui-iconfont">&#xe60c;</i> 修改</a>
    <a href="javascript:;" onclick="datadel()" class="btn btn-danger radius">
    <i class="Hui-iconfont">&#xe6e2;</i> 删除</a> 
    </span></div>
    <div class="mt-20">
        <table id="dataTable" class="table table-border table-bordered table-bg table-hover table-sort">
            <thead>
                <tr class="text-c">
                    <th width="25"><input type="checkbox" id="selectAll" name="" value=""></th>
                    <th width="120">角色名称</th>
                    <th width="120">角色说明</th>
                    <th width="60">创建人</th>
                    <th width="120">创建时间</th>
                    <th width="120">最后修改人</th>
                    <th width="120">最后修改时间</th>
                    <th width="120">操作</th>
                </tr>
            </thead>
            <tbody>
              </tbody>
        </table>
    </div>
</div>
</body>
</html>
