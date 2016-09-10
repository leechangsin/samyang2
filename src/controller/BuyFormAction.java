package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BuyDAO;
import bean.CartDAO;
import bean.CartDataBean;
import bean.MemberDAO;
import bean.MemberDO;

public class BuyFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String buyer = request.getParameter("buyer");
		
		List<CartDataBean> cartLists = null;
		List<String> accountLists = null;
		MemberDO member = null;
		int count = 0;
		
		CartDAO cartDao = new CartDAO();
		count = cartDao.getListCount(buyer);
		
		if(count > 0){
			cartLists = cartDao.getCart(buyer, count);
			request.setAttribute("cartLists", cartLists);
		}
		
		MemberDAO memberDao = new MemberDAO();
		member = memberDao.getMember(buyer);
		
		BuyDAO buyDao = new BuyDAO();
		accountLists = buyDao.getAccount();
		
		request.setAttribute("member", member);
		request.setAttribute("accountLists", accountLists);
		request.setAttribute("type", new Integer(1));
		return "/buy/buyForm.jsp";
	}
}