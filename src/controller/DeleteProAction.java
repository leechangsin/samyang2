package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.MemberDAO;

public class DeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		MemberDAO memberDao = new MemberDAO();
		int check = memberDao.deleteMember(id, passwd);
		
		request.setAttribute("check", new Integer(check));
		return "/member/deletePro.jsp";
	}

}
