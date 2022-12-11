package com.example.jsp_practice.Service;

import com.example.jsp_practice.entity.Notice;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoticeService {

    public List<Notice> getNoticeList() {

        return getNoticeList("title", "", 1);
    }

    public List<Notice> getNoticeList(int page) {

        return getNoticeList("title", "", page);
    }

    public List<Notice> getNoticeList(String field, String query, int page) {
        /*
         field와 query는 "WHERE TITLE LIKE ?" 부분에 들어간다.
         field: title or writer_id
         query: 문자열 A
         page(앞): 1, 11, 21, 31 -> an = 1 + (page - 1) * 10 [등차수열]
         page(뒤): 10, 20, 30, 40 -> page * 10
         */

        List<Notice> list = new ArrayList<>();

        String sql = "SELECT * FROM ( " +
                " SELECT @ROWNUM := @ROWNUM + 1 AS ROWNUM, N.* " +
                " FROM (SELECT * FROM notice WHERE " + field + " LIKE ? " +
                " ORDER BY REGDATE desc) AS N " +
                ") AS A, (SELECT @ROWNUM := 0) AS B " +
                "WHERE (ROWNUM BETWEEN ? AND ?); ";

        String url = "jdbc:mysql://localhost/newlecture";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String user = "root";
            String password = "mac";

            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "%" + query + "%");    // 검색을 위한 패턴 (query)
            pst.setInt(2, 1 + (page - 1) * 10);
            pst.setInt(3, page * 10);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String writerId = rs.getString("writer_id");
                Date regDate = rs.getDate("regdate");
                int hit = rs.getInt("hit");
                String files = rs.getString("files");
                String content = rs.getString("content");

                Notice notice = new Notice(id, title, writerId, regDate, hit, files, content);
                list.add(notice);
            }

            rs.close();
            pst.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return list;

    }
    public int getNoticeCount() {

        return getNoticeCount("title", "");
    }

    public int getNoticeCount(String field, String query) {

        int count = 0;

        String sql = "SELECT COUNT(id) AS COUNT FROM (" +
                " SELECT @ROWNUM := @ROWNUM + 1 AS ROWNUM, N.*" +
                " FROM (SELECT * FROM notice WHERE " + field + " LIKE ? " +
                " ORDER BY REGDATE desc) AS N " +
                " AND (@ROWNUM := 0) = 0";

        String url = "jdbc:mysql://localhost/newlecture";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String user = "root";
            String password = "mac";

            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "%" + query + "%");    // 검색을 위한 패턴 (query)
            ResultSet rs = pst.executeQuery();

            count = rs.getInt("COUNT");

            rs.close();
            pst.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return count;
    }

    public Notice getNotice(int id) {

        Notice notice = null;

        String sql = "SELECT * FROM board WHERE id = ?";

        String url = "jdbc:mysql://localhost/newlecture";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String user = "root";
            String password = "mac";

            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int nid = rs.getInt("id");
                String title = rs.getString("title");
                String writerId = rs.getString("writer_id");
                Date regDate = rs.getDate("regdate");
                int hit = rs.getInt("hit");
                String files = rs.getString("files");
                String content = rs.getString("content");

                notice = new Notice(nid, title, writerId, regDate, hit, files, content);

            }

            rs.close();
            pst.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return notice;
    }

    public Notice getNextNotice(int id) {
        Notice notice = null;

        String sql = "SELECT @ROWNUM := @ROWNUM + 1 as ROWNUM, board.* " +
                " FROM board, (SELECT @ROWNUM := 0) AS R" +
                " WHERE id = any ( " +
                " SELECT id FROM board " +
                "    WHERE regdate > (SELECT regdate FROM board WHERE id = ? )" +
                ") " +
                " ORDER BY @ROWNUM ASC" +
                " LIMIT 1";

        String url = "jdbc:mysql://localhost/newlecture";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String user = "root";
            String password = "mac";

            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int nid = rs.getInt("id");
                String title = rs.getString("title");
                String writerId = rs.getString("writer_id");
                Date regDate = rs.getDate("regdate");
                int hit = rs.getInt("hit");
                String files = rs.getString("files");
                String content = rs.getString("content");

                notice = new Notice(nid, title, writerId, regDate, hit, files, content);

            }

            rs.close();
            pst.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return notice;
    }

    public Notice getPrevNotice(int id) {
        Notice notice = null;

        String sql = "SELECT @ROWNUM := @ROWNUM + 1 AS ROWNUM, A.* " +
                "FROM (SELECT * FROM notice ORDER BY regdate DESC) AS A, " +
                "(SELECT @ROWNUM := 0) AS R " +
                "WHERE regdate <  ( " +
                "SELECT regdate FROM notice WHERE id = ? " +
                ") " +
                "LIMIT 1 ";

        String url = "jdbc:mysql://localhost/newlecture";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String user = "root";
            String password = "mac";

            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int nid = rs.getInt("id");
                String title = rs.getString("title");
                String writerId = rs.getString("writer_id");
                Date regDate = rs.getDate("regdate");
                int hit = rs.getInt("hit");
                String files = rs.getString("files");
                String content = rs.getString("content");

                notice = new Notice(nid, title, writerId, regDate, hit, files, content);

            }

            rs.close();
            pst.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return notice;
    }
}
