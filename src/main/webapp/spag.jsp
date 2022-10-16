<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <%= request.getAttribute("result") %> 입니다.
    ${result}<br>
    ${names[0]}<br>
    ${notice.title}<br>
</body>
</html>
