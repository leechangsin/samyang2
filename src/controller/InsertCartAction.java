package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CartDAO;
import bean.CartDataBean;

public class InsertCartAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		byte buy_count = Byte.parseByte(request.getParameter("buy_count"));
		int book_id = Integer.parseInt(request.getParameter("book_id"));
		String book_title = request.getParameter("book_title");
		String book_image = request.getParameter("book_image");
		int buy_price = (int)Float.parseFloat(request.getParameter("buy_price"));
		String buyer = request.getParameter("buyer");
		
		CartDataBean cart = new CartDataBean();
		cart.setBook_id(book_id);
		cart.setBook_image(book_image);
		cart.setBook_title(book_title);
		cart.setBuy_count(buy_count);
		cart.setBuy_price(buy_price);
		cart.setBuyer(buyer);
		
		CartDAO cartDao = new CartDAO();
		cartDao.insertCart(cart);
		return "/cart/insertCart.jsp";
	}

}
