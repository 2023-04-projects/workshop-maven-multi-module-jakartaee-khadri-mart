package com.khadri.mart.vegetable.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.khadri.mart.util.DaoUtil;
import com.khadri.mart.vegetable.form.VegetableForm;

public class VegetableDao {

	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private DaoUtil daoUtil;

	public VegetableDao(DaoUtil daoUtil) {
		 this.daoUtil = daoUtil;
	}

	public int insertVegetables(VegetableForm form) {
		System.out.println("VegetableDao insertVegetables(-)");
		int result = 0;
		try {
			 con = daoUtil.getNewConnection();
			pstmt = con.prepareStatement("insert into vegetable values(?,?,?)");
			pstmt.setString(1, form.getVegName());
			pstmt.setInt(2, form.getVegQty());
			pstmt.setDouble(3, form.getVegPrice());
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("Exception occured" + e.getMessage());
		} finally {
			System.out.println("Executed finally block");
            closeResources();   
		}
		return result;
	}

	public int updateVegetables(VegetableForm form) {
		System.out.println("VegetableDao updateVegetables(-,-)");

		int result = 0;
		try {
			 con = daoUtil.getNewConnection();
			pstmt = con.prepareStatement("UPDATE vegetable SET qty = ?, price = ? WHERE name = ?");
			pstmt.setInt(1, form.getVegQty());
			pstmt.setDouble(2, form.getVegPrice());
			pstmt.setString(3, form.getVegName());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Executed finally block");
			closeResources();
		}
		return result;
	}

	public List<VegetableForm> selectVegetables(String veg_name) {
		System.out.println("VegetableDao selectVegetables(-)");
		List<VegetableForm> listOfData = new ArrayList<>();
		try {
			 con = daoUtil.getNewConnection();

			stmt = con.createStatement();
			ResultSet resultSet = stmt.executeQuery("select * from vegetable where name='" + veg_name + "'");

			while (resultSet.next()) {
				VegetableForm form = new VegetableForm(resultSet.getString(1), resultSet.getInt(2),
						resultSet.getDouble(3));
				listOfData.add(form);
			}

		} catch (Exception e) {
			System.out.println("Exception occured" + e.getMessage());
		} finally {
			System.out.println("Executed finally block");
			closeResources();
		}
		return listOfData;

	}

	public List<VegetableForm> selectAllVegetables() {
		System.out.println("VegetableDao selectAllVegetables(-)");
		List<VegetableForm> listOfvegetables = new ArrayList<>();
		try {
			 con = daoUtil.getNewConnection();

			stmt = con.createStatement();
			ResultSet resultSet = stmt.executeQuery("select * from vegetable");

			while (resultSet.next()) {
				VegetableForm form = new VegetableForm(resultSet.getString(1), resultSet.getInt(2),
						resultSet.getDouble(3));
				listOfvegetables.add(form);
			}

		} catch (Exception e) {
			System.out.println("Exception occured" + e.getMessage());
		} finally {
			System.out.println("Executed finally block");
			closeResources();
		}
		return listOfvegetables;

	}

	public int deleteVegetables(String name) {
		System.out.println("VegetableDao deleteVegetable(-,-)");
		int result = 0;
		try {
			 con = daoUtil.getNewConnection();

			pstmt = con.prepareStatement("DELETE FROM vegetable WHERE name = ?");
			pstmt.setString(1, name);
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("Exception occurred: " + e.getMessage());
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
