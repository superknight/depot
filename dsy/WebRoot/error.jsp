<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
    isErrorPage="true"%>
<%
    Integer errorStatus = (Integer) request.getAttribute("javax.servlet.error.status_code");
%>
<!DOCTYPE HTML">
<html lang="zh-CN">
<head>
<title>error</title>
<style type="text/css">
.content {
    margin: 0px auto;
    text-align: center;
    margin-top: 200px；
}

.error {
    color: blue;
}
</style>
</head>

<body>
    <div class="content">
        <h3>您的请求发生<span class="error"><%=errorStatus%></span>错误，请联系管理员！</h3>
    </div>
</body>
</html>
