package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BuyDAO;
import bean.CartDAO;
import bean.CartDataBean;

public class BuyProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String account = request.getParameter("account");
		String deliveryName = request.getParameter("deliveryName");
		String deliveryTel = request.getParameter("deliveryTel");
		String deliveryAddress = request.getParameter("deliveryAddress");
		String buyer = request.getParameter("buyer");
		int count = 0;
		
		CartDAO cartDao = new CartDAO();
		count = cartDao.getListCount(buyer);
		List<CartDataBean> cartLists = cartDao.getCart(buyer, count);
		
		BuyDAO buyDao = new BuyDAO();
		buyDao.insertBuy(cartLists, buyer, account, deliveryName, deliveryTel, deliveryAddress);
		
		request.setAttribute("orderStatus", "주문완료");
		request.setAttribute("type", new Integer(1));
		return "/buy/buyPro.jsp";
	}

}
