package prework.controller.delete;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import prework.databaseservice.dao.DAOStudent;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

/**
 * Servlet delete student with specific name and family name
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
@Deprecated
public class DeleteStudent extends HttpServlet{

	private static final long serialVersionUID = 253765982137241L;

	private DAOStudent daoStudent;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{

		String name = req.getParameter("name");
		String familyName = req.getParameter("familyName");
		String mes = "";
		try{
			daoStudent.delete(name, familyName);
			mes = "Operation was success";
		}catch(SQLException e){
			mes = "Can't do this operation.";
			e.printStackTrace();
		}

		req.setAttribute("message", mes);
		
		RequestDispatcher reqDisp =  req.getRequestDispatcher("/jsp/delete/DeleteStudent.jsp");
		reqDisp.forward(req, res);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doGet(req, res);
	}
}
