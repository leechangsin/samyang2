package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BookDAO;

public class BookDeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		int book_id = Integer.parseInt(request.getParameter("book_id"));
		String book_kind = request.getParameter("book_kind");
		
		BookDAO bookDao = new BookDAO();
		bookDao.deleteBook(book_id);
		
		request.setAttribute("book_kind", book_kind);
		return "/mngr/productProcess/bookDeletePro.jsp";
	}

}
