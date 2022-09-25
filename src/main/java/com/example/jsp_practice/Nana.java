package com.example.jsp_practice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hi")
public class Nana extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter out = resp.getWriter();

        /*
        http//..//hi?cnt=20		: 기본적인 쿼리 스트링

        http//..//hi?cnt=3		:  "3"
        http//..//hi?cnt=		: "" (빈문자열)
        http//..//hi?			:  null
        http//..//hi			    :  ull
        */

        String cnt_ = req.getParameter("cnt");
        int cnt = 30;

        if (cnt_ != null && !cnt_.equals("")) {
            cnt = Integer.parseInt(cnt_);
        }

        for (int i = 0; i < cnt; i++) {
            out.println("안녕 Servlet <br>");
        }


    }
}
