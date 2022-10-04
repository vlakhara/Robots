package com.vipul.restDemo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlienRepository {
    Connection conn = null;

    public AlienRepository() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Rest", "root", "Vipul@060101");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Alien> getAliens() {
        List<Alien> aliens = new ArrayList<>();
        String sql = "Select * from Aliens";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Alien a = new Alien();
                a.setId(rs.getInt(1));
                a.setName(rs.getString(2));
                a.setPoints(rs.getInt(3));
                a.setImage(rs.getString(4));
                a.setTech(rs.getString(5));

                aliens.add(a);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return aliens;
    }

    public Alien getAlien(int id) {
        String sql = "Select * from Aliens where aId=" + id;
        Alien a = new Alien();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                a.setId(rs.getInt(1));
                a.setName(rs.getString(2));
                a.setPoints(rs.getInt(3));
                a.setImage(rs.getString(4));
                a.setTech(rs.getString(5));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return a;
    }

    public void createAlien(Alien a) {
        String sql = "insert into Aliens values(?,?,?,?,?)";
        try {
            PreparedStatement ptst = conn.prepareStatement(sql);
            ptst.setInt(1, a.getId());
            ptst.setString(2, a.getName());
            ptst.setInt(3, a.getPoints());
            ptst.setString(4, a.getImage());
            ptst.setString(5, a.getTech());
            ptst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String delAlien(int id) {
        String sql = "delete from Aliens where aId=" + id;
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "Deleted";
    }

    public Alien upAlien(Alien a) {
        String sql = "update Aliens set aName=?,aPoints=?,atech=? where aId=?";
        try {
            PreparedStatement ptst = conn.prepareStatement(sql);
            ptst.setInt(4, a.getId());
            ptst.setString(1, a.getName());
            ptst.setInt(2, a.getPoints());
            ptst.setString(3, a.getTech());
            ptst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return a;
    }
}
