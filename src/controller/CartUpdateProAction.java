package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CartDAO;

public class CartUpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int cart_id = Integer.parseInt(request.getParameter("cart_id"));
		byte buy_count = Byte.parseByte(request.getParameter("buy_count"));
		
		CartDAO cartDao = new CartDAO();
		cartDao.updateCount(cart_id, buy_count);
		
		request.setAttribute("type", new Integer(1));
		return "/shoppingmall/cartList.do";
	}
}