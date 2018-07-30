package com.henry.jigsaw.db;


import java.sql.*;

public class PostgreSQLJDBC {

    public static Connection c = null;

    private static PostgreSQLJDBC pg = null;

    public static PostgreSQLJDBC getInstance() {
        if (pg == null) return new PostgreSQLJDBC();
        return pg;
    }


    public static void main(String args[]) {
        getInstance().createTable();
        getInstance().insertData();
        getInstance().updateData();
        getInstance().deleteData();
    }

    private void createConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            getInstance().c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/db1",
                            "wu", "wu");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }


    private void refreshTable() {
        dropTable();
        createTable();
        insertData();
    }


    private void createTable() {

        try {
            createConnection();
            Statement stmt = c.createStatement();
            String sql = "CREATE TABLE if not exists COMPANY02 " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " AGE            INT     NOT NULL, " +
                    " ADDRESS        CHAR(50), " +
                    " SALARY         REAL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
            System.out.println("create table company02 successfully");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }


    private void dropTable() {
        try {
            createConnection();
            Statement stmt = c.createStatement();
            String sql = "drop TABLE COMPANY02";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
            System.out.println("drop table company02 successfully");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }


    private void insertData() {
        try {
            dropTable();
            createTable();
            createConnection();
            c.setAutoCommit(false);
            Statement stmt = c.createStatement();
            String sql = "INSERT INTO COMPANY02 (ID,NAME,AGE,ADDRESS,SALARY) "
                    + "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO COMPANY02 (ID,NAME,AGE,ADDRESS,SALARY) "
                    + "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO COMPANY02 (ID,NAME,AGE,ADDRESS,SALARY) "
                    + "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO COMPANY02 (ID,NAME,AGE,ADDRESS,SALARY) "
                    + "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();


        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }


    private void deleteData() {
        refreshTable();
        try {
            createConnection();
            Statement stmt = c.createStatement();
            c.setAutoCommit(false);

            System.out.println("连接数据库成功！");
            stmt = c.createStatement();

            String sql = "Delete from COMPANY02 where ID=4 ";
            stmt.executeUpdate(sql);
            c.commit();

            ResultSet rs = stmt.executeQuery("select * from company02 order by id");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                float salary = rs.getFloat("salary");
                System.out.println(id + "," + name + "," + age + "," + address.trim() + "," + salary);
            }

            rs.close();
            stmt.close();

            c.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void updateData() {
        refreshTable();
        try {
            createConnection();
            Statement stmt = c.createStatement();
            c.setAutoCommit(false);

            String sql = "UPDATE COMPANY02 set SALARY = 250 where ID=1 ";
            stmt.executeUpdate(sql);
            c.commit();

            ResultSet rs = stmt.executeQuery("select * from company02 order by id");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                float salary = rs.getFloat("salary");
                System.out.println(id + "," + name + "," + age + "," + address.trim() + "," + salary);
            }

            rs.close();
            stmt.close();

            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

