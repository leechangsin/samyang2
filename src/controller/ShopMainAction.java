package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BookDAO;
import bean.BookDO;

public class ShopMainAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		BookDO bookList[] = null;
		List<BookDO[]> bookLists = new ArrayList<>();
		
		BookDAO bookDao = new BookDAO();
		
		//카테고리별 최신상품 3개씩 얻어내서 List에 저장
		for(int i=1; i<=3; i++){
			bookList = bookDao.getBooks(i+"00", 3);
			bookLists.add(bookList);
		}
		
		request.setAttribute("bookLists", bookLists);
		return "/shop/shopMain.jsp";
	}
}