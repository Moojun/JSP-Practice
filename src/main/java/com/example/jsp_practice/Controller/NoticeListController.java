package com.example.jsp_practice.Controller;

import com.example.jsp_practice.Service.NoticeService;
import com.example.jsp_practice.entity.Notice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 전달되어 오는 query: list?f=title&q=a
        String field_ = request.getParameter("f");  // 사용자의 검색 기준 전달받음
        String query_ = request.getParameter("q");  // 사용자의 검색어 입력 받음

        String field = "title";
        if (field_ != null) {
            field = field_;
        }

        String query = "";
        if (query_ != null) {
            query = query_;
        }


        NoticeService service = new NoticeService();
        List<Notice> list = service.getNoticeList(field, query, 1);

        request.setAttribute("list", list);

        // forward
        request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request, response);
    }
}
