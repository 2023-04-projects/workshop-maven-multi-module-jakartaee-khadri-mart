package com.khadri.mart.fruits.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import com.khadri.mart.fruits.dao.FruitsDao;
import com.khadri.mart.util.DaoUtil;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FruitsModifyServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	 private FruitsDao dao;
		private  DaoUtil utilDao;

		@Override
		public void init() {
			utilDao = new DaoUtil();
			dao = new FruitsDao(utilDao);
		}

	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	        System.out.println("Entered into ModifyFruitsServlet doPost(-,-)");

	        String name = req.getParameter("item_name");
	        String qtyStr = req.getParameter("item_qty");
	        String priceStr = req.getParameter("item_price");
	        
	        
	        System.out.println("Request parameters: ");
	        for (Enumeration<String> en = req.getParameterNames(); en.hasMoreElements();) {
	            String paramName = en.nextElement();
	            System.out.println(paramName + ": " + req.getParameter(paramName));
	        }
	        PrintWriter pw = resp.getWriter();
	        
	        if (name == null || name.trim().isEmpty()) {
	            pw.println("@@@@@ Item name is missing @@@@@");
	            return;
	        }
	        if (qtyStr == null || qtyStr.trim().isEmpty()) {
	            pw.println("@@@@@ Quantity is missing @@@@@");
	            return;
	        }
	        if (priceStr == null || priceStr.trim().isEmpty()) {
	            pw.println("@@@@@ Price is missing @@@@@");
	            return;
	        }
	        try {
	            int qty = Integer.parseInt(qtyStr);
	            double price = Double.parseDouble(priceStr);

	            int result = dao.updateFruitsItem(name, qty, price);

	            if (result >0) {
	                pw.println(result + " Fruits Item Updated Successfully!!!!!");
	            } else {
	                pw.println("@@@@@ Something went wrong while updating the item @@@@@");
	            }
	        } catch (NumberFormatException e) {
	            pw.println("@@@@@ Invalid input: Quantity and Price must be numeric @@@@@");
	            e.printStackTrace();
	        } catch (Exception e) {
	            pw.println("@@@@@ Something went wrong: " + e.getMessage() + " @@@@@");
	            e.printStackTrace();
	        }
	    }
	}
