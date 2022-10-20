package com.example.jsp_practice.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.Date;

import com.example.jsp_practice.entity.Notice;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost/newlecture";
            String user = "root";
            String password = "mac";
            String sql = "select * from notice where id= ?";

            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            rs.next();  // 서버쪽에 있는 실행 결과에서, 레코드 하나를 가져옴

            String title = rs.getString("title");
            String writerId = rs.getString("writer_id");
            Date regDate = rs.getDate("regdate");
            int hit = rs.getInt("hit");
            String files = rs.getString("files");
            String content = rs.getString("content");

            Notice notice = new Notice(id, title, writerId, regDate, hit, files, content);
            request.setAttribute("n", notice);

            /*
            request.setAttribute("title", title);
            request.setAttribute("writerId", writerId);
            request.setAttribute("regDate", regDate);
            request.setAttribute("hit", hit);
            request.setAttribute("files", files);
            request.setAttribute("content", content);
             */

            rs.close();
            pst.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }



        // redirect

        // forward
        request.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp").forward(request, response);

    }
}
