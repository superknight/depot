<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@include file="/static/public.jsp"%>
    <title>客户管理</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jsp/member/js/address.js"></script>
  </head>
  <body>
    <div class="pd-20">
    <div class="mt-20">
        <table id="dataTable" class="table table-border table-bordered table-bg table-hover table-sort">
            <thead>
                <tr class="text-c">
                    <th width="120">地址</th>
                    <th width="120">电话</th>
                    <th width="120">收货人</th>
                    <th width="120">录入时间</th>
                    <th width="120">备注</th>
                </tr>
            </thead>
            <tbody>
              </tbody>
        </table>
    </div>
</div>
</body>
</html>