package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BookDAO;
import bean.BookDO;
import bean.QnaDAO;
import bean.QnaDO;

public class BookContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		List<QnaDO> qnaLists;
		String book_kind = request.getParameter("book_kind");
		int book_id = Integer.parseInt(request.getParameter("book_id"));
		
		BookDAO bookDao = new BookDAO();
		BookDO book = bookDao.getBook(book_id);
		
		QnaDAO qnaDao = new QnaDAO();
		int count = qnaDao.getArticleCount(book_id);
		
		if(count > 0){
			qnaLists = qnaDao.getArticles(count, book_id);
			request.setAttribute("qnaLists", qnaLists);
		}
		
		request.setAttribute("book", book);
		request.setAttribute("book_id", book_kind);
		request.setAttribute("book_kind", book_kind);
		request.setAttribute("count", count);
		request.setAttribute("type", new Integer(1));
		return "/shop/bookContent.jsp";
	}

}
