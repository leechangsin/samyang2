package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BuyDAO;
import bean.BuyDO;

public class OrderListAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		List<BuyDO> buyLists = null;
		int count = 0;
		
		BuyDAO buyDao = new BuyDAO();
		count = buyDao.getListCount();
		
		if(count > 0){
			buyLists = buyDao.getBuyList();
			request.setAttribute("buyLists", buyLists);
		}
		
		request.setAttribute("count", new Integer(count));
		request.setAttribute("type", new Integer(0));
		return "/mngr/orderedProduct/orderList.jsp";
	}
}