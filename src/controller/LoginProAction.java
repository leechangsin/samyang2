package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.MemberDAO;

public class LoginProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		MemberDAO memberDao = new MemberDAO();
		int check = memberDao.userCheck(id, passwd);
		
		request.setAttribute("id", id);
		request.setAttribute("check", new Integer(check));
		return "/meember/loginPro.jsp";
	}
}