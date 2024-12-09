package com.khadri.mart.clothes.servlet;

import java.io.IOException;

import com.khadri.mart.clothes.dao.ClothesDao;
import com.khadri.mart.util.DaoUtil;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteClothesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ClothesDao dao;
	private DaoUtil daoUtil;

	public void init() {
		daoUtil= new DaoUtil(); 
		dao = new ClothesDao(daoUtil);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String name = req.getParameter("item_name");
		System.out.println("Received 'name' parameter: " + name);

		if (name != null && !name.isEmpty()) {
			int result = dao.deleteClothes(name);

			if (result > 0) {
				resp.getWriter().println("Clothes item deleted successfully.");
			} else {
				resp.getWriter().println("Failed to delete Clothes item.");
			}
		} else {
			resp.getWriter().println("No Clothes items withthis name.");
		}
	}
}
