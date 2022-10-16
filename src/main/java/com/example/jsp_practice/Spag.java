package com.example.jsp_practice;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/spag")
public class Spag extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        int num = 0;
        String num_ = request.getParameter("n");
        if (num_ != null && !num_.equals("")) {
            num = Integer.parseInt(num_);
        }

        String result;
        if (num % 2 != 0) {
            result = "홀수";
        }
        else {
            result = "짝수";
        }

        request.setAttribute("result", result);

        // forward 를 통해 현재 작업했던 내역들을 spag.jsp 로 이어져서 요청이 진행된다.
        RequestDispatcher dispatcher = request.getRequestDispatcher("spag.jsp");
        dispatcher.forward(request, response);
    }

}
