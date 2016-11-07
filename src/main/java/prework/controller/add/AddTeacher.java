package prework.controller.add;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import prework.databaseservice.dao.DAOTeacher;

/**
 * Servlet add teacher with specific name and family name into database
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
@Deprecated
public class AddTeacher extends HttpServlet{

	private static final long serialVersionUID = 253444423311141L;

	private DAOTeacher daoTeacher;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{

		String name = req.getParameter("name");
		String familyName = req.getParameter("familyName");
		
		String message = "";
		
		try{
			daoTeacher.add(name, familyName);
			message = "Operation was success";
		}catch(SQLException e){
			message = "Can't do this operation.";
			e.printStackTrace();
		}
	
		req.setAttribute("message", message);
		
		RequestDispatcher reqDisp =  req.getRequestDispatcher("/jsp/add/AddTeacher.jsp");
		reqDisp.forward(req, res);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doGet(req, res);
	}
}
