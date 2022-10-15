package com.example.jsp_practice;

import org.graalvm.polyglot.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calculator")
public class Calculator extends HttpServlet {

    /*
    @Override
    protected void service(HttpServletRequest req,
                           HttpServletResponse resp) throws ServletException, IOException {

        if (req.getMethod().equals("GET")) {    // "GET" 대문자 표기 할 것
            System.out.println("GET 요청이 왔습니다.");
        }
        else if (req.getMethod().equals("POST")) {    // "POST" 대문자 표기 할 것
            System.out.println("POST 요청이 왔습니다.");
        }

        super.service(req, resp);
    } */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie [] cookies = req.getCookies();

        String exp = "0";
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("exp")) {
                    exp = c.getValue();
                    break;
                }
            }
        }

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.write("<!DOCTYPE html>");
        out.write("<html lang=\"en\">");
        out.write("<head>");
        out.write("<meta charset=\"UTF-8\">");
        out.write("<title>Add</title>");
        out.write("<style>");
        out.write("input {");
        out.write("width: 50px;");
        out.write("height: 50px;");
        out.write("}");

        out.write(".output {");
        out.write("height: 50px;");
        out.write("background-color: #e9e9e9;");
        out.write("font-size: 24px;");
        out.write("font-weight: bold;");
        out.write("text-align: right;");
        out.write("padding: 0px 5px;");
        out.write("}");
        out.write("</style>");
        out.write("</head>");
        out.write("<body>");
        out.write("<form action=\"calculator\" method=\"post\">");
        out.write("<table>");
        out.write("<tr>");
        out.printf("<td class=\"output\" colspan=\"4\">%s</td>", exp);
        out.write("</tr>");
        out.write("<tr>");
        out.write("<td><input type=\"submit\" name=\"operator\" value=\"CE\"></td>");
        out.write("<td><input type=\"submit\" name=\"operator\" value=\"C\"></td>");
        out.write("<td><input type=\"submit\" name=\"operator\" value=\"BS\"></td>");
        out.write("<td><input type=\"submit\" name=\"operator\" value=\"/\"></td>");
        out.write("</tr>");
        out.write("<tr>");
        out.write("<td><input type=\"submit\" name=\"value\" value=\"7\"></td>");
        out.write("<td><input type=\"submit\" name=\"value\" value=\"8\"></td>");
        out.write("<td><input type=\"submit\" name=\"value\" value=\"9\"></td>");
        out.write("<td><input type=\"submit\" name=\"operator\" value=\"*\"></td>");
        out.write("      </tr>");
        out.write("      <tr>");
        out.write("       <td><input type=\"submit\" name=\"value\" value=\"4\"></td>");
        out.write("       <td><input type=\"submit\" name=\"value\" value=\"5\"></td>");
        out.write("       <td><input type=\"submit\" name=\"value\" value=\"6\"></td>");
        out.write("       <td><input type=\"submit\" name=\"operator\" value=\"-\"></td>");
        out.write("     </tr>");
        out.write("     <tr>");
        out.write("       <td><input type=\"submit\" name=\"value\" value=\"1\"></td>");
        out.write("       <td><input type=\"submit\" name=\"value\" value=\"2\"></td>");
        out.write("       <td><input type=\"submit\" name=\"value\" value=\"3\"></td>");
        out.write("       <td><input type=\"submit\" name=\"operator\" value=\"+\"></td>");
        out.write("     </tr>");
        out.write("    <tr>");
        out.write("      <td></td>");
        out.write("      <td><input type=\"submit\" name=\"value\" value=\"0\"></td>");
        out.write("       <td><input type=\"submit\" name=\"dot\" value=\".\"></td>");
        out.write("      <td><input type=\"submit\" name=\"operator\" value=\"=\"></td>");
        out.write("    </tr>");
        out.write("  </table>");
        out.write(" </form>");

        out.write("</body>");
        out.write("</html>");
        System.out.println("doGET 메소드가 호출 되었습니다.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie[] cookies = req.getCookies();

        String value = req.getParameter("value");
        String operator = req.getParameter("operator");
        String dot = req.getParameter("dot");

        String exp = "";
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("exp")) {
                    exp = c.getValue();
                    break;
                }
            }
        }

        if (operator != null && operator.equals("=")) {
            // 참고 링크: https://madplay.github.io/post/call-javascript-function-from-java-using-graalvm
            try (Context context = Context.create("js")) {
                exp = String.valueOf(context.eval("js", exp));
            } catch (Exception e) {
                System.err.println();
            }
        }
        else if (operator != null && operator.equals("C")) {
            exp = "";
        }
        else {
            exp += (value == null) ? "" : value;
            exp += (operator == null) ? "" : operator;
            exp += (dot == null) ? "" : dot;
        }

        Cookie expCookie = new Cookie("exp", exp);
        if (operator != null && operator.equals("C")) {
            expCookie.setMaxAge(0); // cookie가 남지 않고 바로 없어짐
        }

        expCookie.setPath("/calculator");   // 범위 축소. 다른 url 에는 cookie 가 전달되지 않는다.
        resp.addCookie(expCookie);
        resp.sendRedirect("calculator");


        System.out.println("doPOST 메소드가 호출 되었습니다.");
    }
}
