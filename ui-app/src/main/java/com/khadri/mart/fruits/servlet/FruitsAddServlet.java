package com.khadri.mart.fruits.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.khadri.mart.fruits.dao.FruitsDao;
import com.khadri.mart.fruits.form.FruitsForm;
import com.khadri.mart.util.DaoUtil;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FruitsAddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private FruitsDao dao;
	private DaoUtil utilDao;

	public void init(){
		utilDao = new DaoUtil();
		dao = new FruitsDao(utilDao);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("Entered into AddFruitsServlet doPost(-,-)");
		String name = req.getParameter("item_name");
		String qty = req.getParameter("item_qty");
		String price = req.getParameter("item_price");
		FruitsForm form = new FruitsForm(name, Integer.parseInt(qty), Double.parseDouble(price));
		int result = dao.insertFruits(form);
		PrintWriter pw = resp.getWriter();
		if (result == 1) {
			pw.println(result + " Inserted Successfully!!!!!");
		} else {
			pw.println("@@@@@Something went wrong@@@@@");
		}
	}

}
