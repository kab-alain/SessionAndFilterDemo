package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns="/login")
public class AuthenticationServlet extends HttpServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		
		RequestDispatcher dispatcher;
		 PrintWriter out = res.getWriter();
		
		if((userName.equalsIgnoreCase("admin") && password.equalsIgnoreCase("1234")) || (userName.equalsIgnoreCase("student") && password.equalsIgnoreCase("1234"))) {
			
		HttpSession session = req.getSession();
		session.setMaxInactiveInterval(60*2);
		session.setAttribute("userName", userName);
		session.setAttribute("password", password);
		//session.setAttribute("role", "admin");
		
		dispatcher = req.getRequestDispatcher("home");
		dispatcher.forward(req, res);
		}else {
			out.println("<h1> Invalid User Name or Password </h1>");
			dispatcher = req.getRequestDispatcher("/login.html");
			dispatcher.include(req, res);
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		
		if(session !=null) {
			//clean the session and destroy that session
			session.invalidate();
			res.sendRedirect("login.html");
		}else {
			res.sendRedirect("login.html");
		}
		
	}
}
