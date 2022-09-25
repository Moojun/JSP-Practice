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
        http://localhost/hello   => GET
        http://localhost/hello?cnt=3   => GET		쿼리 스트링: ?를 쓰고 키값을 전달할수있다.
        추가적으로 옵션을 읽어서 그옵션에 맞는 문서를 줘야겠다 해서 쓴다.
        hello 를 3번만 반복하는 hello 문서를 반복한다.
        */

        int cnt = Integer.parseInt(req.getParameter("cnt"));
        for (int i = 0; i < cnt; i++) {
            out.println("안녕 Servlet <br>");
        }


    }
}
