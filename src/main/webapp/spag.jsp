<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<%
    pageContext.setAttribute("result", "hello");
%>
<body>
    <%= request.getAttribute("result") %> 입니다. <br>
    ${requestScope.result}<br>  <%-- 짝수 or 홀수 출력됨 --%>
    ${names[0]}<br>
    ${notice.title}<br>
    ${result}<br>               <%-- hello 출력됨 --%>

    ${param.n/2} <br>
    ${header.accept} <br>
</body>
</html>
