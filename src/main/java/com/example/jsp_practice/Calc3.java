package com.example.jsp_practice;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.graalvm.polyglot.Context;

@WebServlet("/calc3")
public class Calc3 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie [] cookies = req.getCookies();

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
        resp.addCookie(expCookie);
        resp.sendRedirect("calcpage");

    }

}
