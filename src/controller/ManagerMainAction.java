package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagerMainAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		//관리자와 일반사용자를 구분할때 사용
		request.setAttribute("type", new Integer(0));
		//응답페이지
		return "/mngr/managerMain.jsp";
	}

}
