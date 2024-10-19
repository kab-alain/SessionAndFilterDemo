package view;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns="/home")
public class HomeFilter implements Filter{


	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	
	//@SuppressWarnings("unused")
	@Override
	public void doFilter(ServletRequest sreq, ServletResponse sres, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) sreq;
		HttpServletResponse res = (HttpServletResponse) sres;
		
		HttpSession session = req.getSession(false);
		
		//String role = (String) session.getAttribute("role");
		
		RequestDispatcher dispatcher;
		
		if(session != null) {
			
			
				chain.doFilter(req, res);
//			}else {
//				dispatcher = req.getRequestDispatcher("/login.html");
//				dispatcher.forward(req, res);
//			}
			
		}else {
			dispatcher = req.getRequestDispatcher("/login.html");
			dispatcher.forward(req, res);
		}
		
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
