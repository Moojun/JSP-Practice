package com.example.jsp_practice.Service;

import com.example.jsp_practice.entity.Notice;

import java.util.List;

public class NoticeService {

    public List<Notice> getNoticeList() {

        return getNoticeList("title", "", 1);
    }

    public List<Notice> getNoticeList(int page) {
        String sql = "SELECT * FROM ( " +
                "SELECT @ROWNUM := @ROWNUM + 1 AS ROWNUM, N.* " +
                "FROM (SELECT * FROM notice ORDER BY REGDATE ASC) AS N " +
                ") AS A, (SELECT @ROWNUM := 0) AS B " +
                "WHERE (ROWNUM BETWEEN 6 AND 10)";

        return getNoticeList("title", "", page);
    }
    public List<Notice> getNoticeList(String filed, String query, int page) {

        return null;
    }
    public int getNoticeCount() {

        return getNoticeCount("title", "");
    }

    public int getNoticeCount(String filed, String query) {
        String sql = "SELECT * FROM (" +
                " SELECT @ROWNUM := @ROWNUM + 1 AS ROWNUM, N.*" +
                " FROM (SELECT * FROM board ORDER BY regdate DESC) AS N" +
                ") AS A" +
                " WHERE (ROWNUM BETWEEN 6 AND 10)" +
                " AND  (@ROWNUM := 0) = 0";

        return 0;
    }

    public Notice getNotice(int id) {
        String sql = "SELECT * FROM board WHERE id = ?";

        return null;
    }

    public Notice getNextNotice(int id) {
        String sql = "SELECT @ROWNUM := @ROWNUM + 1 as ROWNUM, board.* " +
                " FROM board, (SELECT @ROWNUM := 0) AS R" +
                " WHERE id = any ( " +
                " SELECT id FROM board " +
                "    WHERE regdate > (SELECT regdate FROM board WHERE id = 3 )" +
                ") " +
                " ORDER BY @ROWNUM ASC" +
                " LIMIT 1";

        return null;
    }

    public Notice getPrevNotice(int id) {
        String sql = "SELECT @ROWNUM := @ROWNUM + 1 AS ROWNUM, A.* " +
                "FROM (SELECT * FROM notice ORDER BY regdate DESC) AS A, " +
                "(SELECT @ROWNUM := 0) AS R " +
                "WHERE regdate <  ( " +
                "SELECT regdate FROM notice WHERE id = 3 " +
                ") " +
                "LIMIT 1 ";

        return null;
    }
}
