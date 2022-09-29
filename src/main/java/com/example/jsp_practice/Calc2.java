package com.example.jsp_practice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calc2")
public class Calc2 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter out = resp.getWriter();

        String x_ = req.getParameter("x");
        String y_ = req.getParameter("y");
        String operator = req.getParameter("operator");

        int x = 0, y = 0;

        if (!x_.equals("")) {
            x = Integer.parseInt(x_);
        }
        if (!y_.equals("")) {
            y = Integer.parseInt(y_);
        }

        int result = 0;
        if (operator.equals("덧셈"))
            result = x + y;
        else
            result = x - y;

        out.println("계산 결과 : " + result);

    }
}
