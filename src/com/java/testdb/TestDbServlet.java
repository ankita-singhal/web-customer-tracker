package com.java.testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestDbServlet
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//setup connection variable
		String user = "springstudent";
		String pass = "springstudent";
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?userSSL=false";
		String driver = "com.mysql.jdbc.Driver";
		
		//get connection to database
		try {
			PrintWriter out = response.getWriter();
			
			out.println("Connecig to datatbase "+ jdbcUrl);
			
			Class.forName(driver);
			Connection con = DriverManager.getConnection(jdbcUrl, user,pass);
			
			out.println("SUCCESS!!");
			con.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ServletException(ex);
		}
		
		
	}

}
