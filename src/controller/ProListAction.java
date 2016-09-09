package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BookDAO;
import bean.BookDO;

public class ProListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		List<BookDO> bookList = null;
		int count = 0;
		String book_kind = request.getParameter("book_kind");
		
		BookDAO bookDao = new BookDAO();
		//kind값이 all이면 전체 상품의 수를 얻음 
		if(book_kind.equals("all"))
			count = bookDao.getBookCount();
		else// 아니라면 특정 카테고리의 상품의 수를 얻음
			count = bookDao.getBookCount(book_kind);
		
		if(count > 0){
			bookList = bookDao.getBooks(book_kind);
			request.setAttribute("bookList", bookList);
		}
		
		request.setAttribute("count", new Integer(count));
		request.setAttribute("book_kind", book_kind);
		request.setAttribute("type", new Integer(1));
		return "/shop/showList.jsp";
	}

}
