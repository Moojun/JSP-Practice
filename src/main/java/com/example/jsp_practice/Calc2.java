package com.example.jsp_practice;

import javax.servlet.ServletContext;
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

        ServletContext application = req.getServletContext();

        PrintWriter out = resp.getWriter();

        String v_ = req.getParameter("value");
        String operator = req.getParameter("operator");

        int v = 0;
        if (!v_.equals(""))
            v = Integer.parseInt(v_);

        // 계산
        if (operator.equals("=")) {

            int x = (Integer) application.getAttribute("value");    // 담겨있던 값
            int y = v;  // 사용자가 두 번째로 입력한 값
            String op = (String) application.getAttribute("operator");

            int result = 0;

            if (op.equals("+")){
                result = x + y;
            }
            else {
                result = x - y;
            }

            out.println("계산 결과는 : " + result);

        }
        // 값을 저장
        else {
            application.setAttribute("value", v);
            application.setAttribute("operator", operator);
        }




    }
}
