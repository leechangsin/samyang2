package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CartDAO;

public class DeleteCartAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String list = request.getParameter("list");
		String msg = "";
		
		CartDAO cartDao = new CartDAO();
		
		//장바구니의 목록을 모두 삭제라면
		if(list.equals("all")){
			String buyer = request.getParameter("buyer");
			cartDao.deleteAll(buyer);
			msg = "장바구니가 모두 비워졌습니다.";
		} else{//장바구니에서 특정 항목만 삭제하는 거라면
			cartDao.deleteList(Integer.parseInt(list));
			msg = "지정한 항목이 삭제되었습니다.";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("type", new Integer(1));
		return "/cart/deleteCart.jsp";
	}

}
