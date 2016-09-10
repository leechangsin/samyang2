package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BuyDAO;
import bean.BuyDO;

public class BuyListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String buyer = request.getParameter("buyer");
		
		List<BuyDO> buyLists = null;
		int count = 0;
		
		BuyDAO buyDao = new BuyDAO();
		count = buyDao.getListCount(buyer);
		
		if(count > 0){
			buyLists = buyDao.getBuyList(buyer);
			request.setAttribute("buyLists", buyLists);
		}
		
		request.setAttribute("count", new Integer(count));
		request.setAttribute("type", new Integer(1));
		return "/buy/buyList.jsp";
	}

}
