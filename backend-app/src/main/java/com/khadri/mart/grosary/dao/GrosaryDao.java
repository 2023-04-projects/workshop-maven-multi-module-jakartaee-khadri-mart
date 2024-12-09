package com.khadri.mart.grosary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.khadri.mart.grosary.form.GrosaryForm;
import com.khadri.mart.util.DaoUtil;

public class GrosaryDao {
	private Connection con;
	PreparedStatement pstmt;
	private Statement stmt;
	private DaoUtil daoUtil;

	public GrosaryDao(DaoUtil daoUtil) {
		if (daoUtil == null) {
            throw new IllegalArgumentException("DaoUtil cannot be null");
        }
		this.daoUtil = daoUtil;
	}

	public int insertGrosary(GrosaryForm form) {
		System.out.println("GrosaryDao insertGrosary(-)");
		int result = 0;
		try {
			con = daoUtil.getNewConnection();

			pstmt = con.prepareStatement("insert into grosary values(?,?,?)");
			pstmt.setString(1, form.getGrosaryName());
			pstmt.setInt(2, form.getGrosaryQty());
			pstmt.setDouble(3, form.getGrosaryPrice());

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

	public List<GrosaryForm> selectGrosary(String Name) {
		System.out.println("GrosaryDao selectGrosary(-)");
		List<GrosaryForm> listOfData = new ArrayList<>();
		try {
			con = daoUtil.getNewConnection();
			stmt = con.createStatement();
			ResultSet resultSet = stmt.executeQuery("select * from grosary where name='" + Name + "'");

			while (resultSet.next()) {
				GrosaryForm form = new GrosaryForm(resultSet.getString(1), resultSet.getInt(2), resultSet.getDouble(3));
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

	public int updateGrosary(GrosaryForm form) {
		System.out.println("GrosaryDao updateGrosary(-)");
		int result = 0;

		try {
			con = daoUtil.getNewConnection();
			pstmt = con.prepareStatement("UPDATE grosary SET qty=?, price=? WHERE name=?");

			pstmt.setInt(1, form.getGrosaryQty());
			pstmt.setDouble(2, form.getGrosaryPrice());
			pstmt.setString(3, form.getGrosaryName());

			result = pstmt.executeUpdate();
			System.out.println(result + " record modified successfully!");

		} catch (Exception e) {
			System.out.println("Exception occured" + e.getMessage());
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

	public List<GrosaryForm> selectAllGrosary() {
		System.out.println("GrosaryDao selectAllGrosary(-)");
		List<GrosaryForm> listOfGrossary = new ArrayList<>();
		try {
			con = daoUtil.getNewConnection();

			stmt = con.createStatement();
			ResultSet resultSet = stmt.executeQuery("select * from grosary");

			while (resultSet.next()) {
				GrosaryForm form = new GrosaryForm(resultSet.getString(1), resultSet.getInt(2), resultSet.getDouble(3));
				listOfGrossary.add(form);
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
		return listOfGrossary;

	}

	public int deleteGrosary(String name) {
		System.out.println("GrosaryDao deleteGrosary(-)");
		int result = 0;
		try {
			con = daoUtil.getNewConnection();

			pstmt = con.prepareStatement("DELETE FROM grosary WHERE name = ?");
			pstmt.setString(1, name);
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("Exception occurred: " + e.getMessage());
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
