<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>

<%
    String cnt_ = request.getParameter("cnt");
    int cnt = 30;

    if (cnt_ != null && !cnt_.equals("")) {
        cnt = Integer.parseInt(cnt_);
    }
    %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <% for (int i = 0; i < cnt; i++) { %>
    안녕 Servlet<br>
    <% } %>
</body>
</html>
