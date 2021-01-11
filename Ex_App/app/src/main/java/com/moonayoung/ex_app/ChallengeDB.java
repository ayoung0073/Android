package com.moonayoung.ex_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChallengeDB {
    String url = "jdbc:mysql://localhost:3306/apptest";
    String user = "root";
    String pw = "33366666622";
    String sql = "";
    PreparedStatement ps = null;
    int result = 0;

    Connection con = null;

    public ChallengeDB() throws SQLException {
        con = DriverManager.getConnection(url, user,pw);
    }

    public void insertData(String writer, String title, String content){
        sql = "insert into challenge values(?,?,?,?,?);";
        // writer, title, content, image, timestamp(now())
        try {
            ps = con.prepareStatement(sql);
            result = ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void deleteData(Challenge data){
        sql = "insert into challenge values(?,?,?,?,?);";
        // writer, title, content, image, timestamp(now())
        try {
            ps = con.prepareStatement(sql);
            result = ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public Challenge selectData(String title){
        Challenge data = null;
        ResultSet rs = null;
        sql = "select * from challenge where title like '*" + title + "*'";
        System.out.println(sql);
        try {
            ps = con.prepareStatement(sql);
            result = ps.executeUpdate();
            if (rs.next()){
                String title2 = rs.getString("title");
                String content = rs.getString("content");

                data = new Challenge(title2, content);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }



}
