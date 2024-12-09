package com.khadri.mart.clothes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.khadri.mart.clothes.form.ClothesForm;
import com.khadri.mart.util.DaoUtil;

public class ClothesDao {
	private Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private DaoUtil daoUtil;

	public ClothesDao(DaoUtil daoUtil) {
		this.daoUtil = daoUtil;
	}

	public int insertClothes(ClothesForm form) {
		System.out.println("ClothesDao insertClothes(-)");
		int result = 0;
		try {
			con = daoUtil.getNewConnection();
			pstmt = con.prepareStatement("insert into clothes values(?,?,?)");
			pstmt.setString(1, form.getItemName());
			pstmt.setInt(2, form.getItemQty());
			pstmt.setDouble(3, form.getItemPrice());
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("Exception occured" + e.getMessage());
		} finally {
			System.out.println("Executed finally block");
			try {
				pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	public int updateClothes(ClothesForm form) {
		int result = 0;
		try {
			con = daoUtil.getNewConnection();
			pstmt = con.prepareStatement("UPDATE clothes SET qty = ?, price = ? WHERE name = ?");
			pstmt.setInt(1, form.getItemQty());
			pstmt.setDouble(2, form.getItemPrice());
			pstmt.setString(3, form.getItemName());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Executed finally block");
			try {
				pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public List<ClothesForm> selectClothes(String Item_name) {
		System.out.println("ClothesDao selectClothes(-)");
		List<ClothesForm> listOfData = new ArrayList<>();
		try {
			con = daoUtil.getNewConnection();

			stmt = con.createStatement();
			ResultSet resultSet = stmt.executeQuery("select * from clothes where name='" + Item_name + "'");

			while (resultSet.next()) {
				ClothesForm form = new ClothesForm(resultSet.getString(1), resultSet.getInt(2), resultSet.getDouble(3));
				listOfData.add(form);
			}

		} catch (Exception e) {
			System.out.println("Exception occured" + e.getMessage());
		} finally {
			System.out.println("Executed finally block");
			try {
				stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listOfData;

	}

	public List<ClothesForm> selectAllClothes() {
		System.out.println("ClothesDao selectAllClothes(-)");
		List<ClothesForm> listOfClothes = new ArrayList<>();
		try {
			con = daoUtil.getNewConnection();

			stmt = con.createStatement();
			ResultSet resultSet = stmt.executeQuery("select * from clothes");

			while (resultSet.next()) {
				ClothesForm form = new ClothesForm(resultSet.getString(1), resultSet.getInt(2), resultSet.getDouble(3));
				listOfClothes.add(form);
			}

		} catch (Exception e) {
			System.out.println("Exception occured" + e.getMessage());
		} finally {
			System.out.println("Executed finally block");
			try {
				stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listOfClothes;

	}

	public int deleteClothes(String name) {
		int result = 0;
		try {
			con = daoUtil.getNewConnection();
			pstmt = con.prepareStatement("delete from clothes where name=?");
			pstmt.setString(1, name);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Executed finally block");
			try {
				pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
