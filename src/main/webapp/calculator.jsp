<%--
코드 블럭의 종류 1: 출력 코드
y = x + 3

코드 블럭의 종류 2: 코드 블록
<% y = x + 3 %>

코드 블럭의 종류 3: 코드 블록
y의 값은: <% out.print(y) %>
y의 값은: <%= y%>

코드 블럭의 종류 4: 선언부(Declaration)
<%!
  public int sum(int a, int b) {
    return a + b;
  }
%>

코드 블럭의 종류 5: 초기 설정을 위한 page 지시자
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" %>

--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add</title>
  <style>
    input {
      width: 50px;
      height: 50px;
    }

    .output {
      height: 50px;
      background-color: #e9e9e9;
      font-size: 24px;
      font-weight: bold;
      text-align: right;
      padding: 0px 5px;
    }
  </style>
</head>
<body>
  <form action="calc3" method="post">
    <table>
      <tr>
        <td class="output" colspan="4">${3+4}</td>
      </tr>
      <tr>
        <td><input type="submit" name="operator" value="CE"></td>
        <td><input type="submit" name="operator" value="C"></td>
        <td><input type="submit" name="operator" value="BS"></td>
        <td><input type="submit" name="operator" value="➗"></td>
      </tr>
      <tr>
        <td><input type="submit" name="value" value="7"></td>
        <td><input type="submit" name="value" value="8"></td>
        <td><input type="submit" name="value" value="9"></td>
        <td><input type="submit" name="operator" value="x"></td>
      </tr>
      <tr>
        <td><input type="submit" name="value" value="4"></td>
        <td><input type="submit" name="value" value="5"></td>
        <td><input type="submit" name="value" value="6"></td>
        <td><input type="submit" name="operator" value="-"></td>
      </tr>
      <tr>
        <td><input type="submit" name="value" value="1"></td>
        <td><input type="submit" name="value" value="2"></td>
        <td><input type="submit" name="value" value="3"></td>
        <td><input type="submit" name="operator" value="+"></td>
      </tr>
      <tr>
        <td></td>
        <td><input type="submit" name="value" value="0"></td>
        <td><input type="submit" name="dot" value="."></td>
        <td><input type="submit" name="operator" value="="></td>
      </tr>
    </table>
  </form>

</body>
</html>