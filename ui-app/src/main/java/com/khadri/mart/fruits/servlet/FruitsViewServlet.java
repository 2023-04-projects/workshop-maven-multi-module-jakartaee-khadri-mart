package com.khadri.mart.fruits.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.khadri.mart.fruits.dao.FruitsDao;
import com.khadri.mart.fruits.form.FruitsForm;
import com.khadri.mart.util.DaoUtil;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FruitsViewServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private FruitsDao dao;
	private DaoUtil utilDao;

	@Override
	public void init() {
		utilDao = new DaoUtil();
		dao = new FruitsDao(utilDao);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("Entered into AddFruitsServlet doPost(-,-)");
		String name = req.getParameter("item_name");
		String qty = req.getParameter("item_qty");
		String price = req.getParameter("item_price");
		FruitsForm form = new FruitsForm(name, Integer.parseInt(qty), Double.parseDouble(price));
		List<FruitsForm> result = dao.selectFruits(name);
		PrintWriter pw = resp.getWriter();

	}
}
