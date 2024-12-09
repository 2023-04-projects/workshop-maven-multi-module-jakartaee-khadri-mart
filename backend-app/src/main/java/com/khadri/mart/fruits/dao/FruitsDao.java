package com.khadri.mart.fruits.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.khadri.mart.fruits.form.FruitsForm;
import com.khadri.mart.util.DaoUtil;

public class FruitsDao {

    private Connection con;
    private PreparedStatement pstmt;
    private Statement stmt;
    private DaoUtil daoUtil;

    public FruitsDao(DaoUtil daoUtil) {
        this.daoUtil = daoUtil;
    }

    public int insertFruits(FruitsForm form) {
        System.out.println("FruitsDao insertFruits(-)");
        int result = 0;
        try {
            con = daoUtil.getNewConnection();    
            pstmt = con.prepareStatement("insert into fruits values(?,?,?)");
            pstmt.setString(1, form.getItemName());
            pstmt.setInt(2, form.getItemQty());
            pstmt.setDouble(3, form.getItemPrice());
            result = pstmt.executeUpdate();   
        } catch (Exception e) {
            System.out.println("Exception occured: " + e.getMessage());
        } finally {
            System.out.println("Executed finally block");
            closeResources();   
        }
        return result;
    }

    public int updateFruitsItem(String name, int qty, double price) {
        int result = 0;
        try {
            con = daoUtil.getNewConnection();    
            pstmt = con.prepareStatement("UPDATE Fruits SET qty = ?, price = ? WHERE name = ?");
            pstmt.setInt(1, qty);
            pstmt.setDouble(2, price);
            pstmt.setString(3, name);
            result = pstmt.executeUpdate();   
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Executed finally block");
            closeResources();  
        }
        return result;
    }

    public List<FruitsForm> selectFruits(String itemName) {
        System.out.println("FruitsDao selectFruits(-)");
        List<FruitsForm> listOfData = new ArrayList<>();
        try {
            con = daoUtil.getNewConnection();  
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from Fruits where name='" + itemName + "'");

            while (resultSet.next()) {
                FruitsForm form = new FruitsForm(resultSet.getString(1), resultSet.getInt(2), resultSet.getDouble(3));
                listOfData.add(form);
            }
        } catch (Exception e) {
            System.out.println("Exception occured: " + e.getMessage());
        } finally {
            System.out.println("Executed finally block");
            closeResources();   
        }
        return listOfData;
    }

    public List<FruitsForm> selectAllFruits() {
        System.out.println("FruitsDao selectAllFruits(-)");
        List<FruitsForm> listOfFruits = new ArrayList<>();
        try {
            con = daoUtil.getNewConnection();   
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from fruits");

            while (resultSet.next()) {
                FruitsForm form = new FruitsForm(resultSet.getString(1), resultSet.getInt(2), resultSet.getDouble(3));
                listOfFruits.add(form);
            }
        } catch (Exception e) {
            System.out.println("Exception occured: " + e.getMessage());
        } finally {
            System.out.println("Executed finally block");
            closeResources();  
        }
        return listOfFruits;
    }

    public int deleteFruits(String name) {
        int result = 0;
        try {
            con = daoUtil.getNewConnection();   
            pstmt = con.prepareStatement("delete from fruits where name=?");
            pstmt.setString(1, name);
            result = pstmt.executeUpdate();   
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Executed finally block");
            closeResources();   
        }
        return result;
    }

    private void closeResources() {
        try {
            if (stmt != null) {
                stmt.close();   
            }
            if (pstmt != null) {
                pstmt.close();   
            }
            if (con != null && !con.isClosed()) {
                con.close();  
            }
        } catch (SQLException e) {
            e.printStackTrace();           }
    }
}

