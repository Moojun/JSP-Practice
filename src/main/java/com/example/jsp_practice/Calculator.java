package com.example.jsp_practice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        // super.doPost(req, resp);
        System.out.println("doGET 메소드가 호출 되었습니다.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // super.doPost(req, resp);
        System.out.println("doPOST 메소드가 호출 되었습니다.");
    }
}
