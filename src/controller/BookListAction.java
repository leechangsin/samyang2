package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BookDAO;
import bean.BookDO;

public class BookListAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		List<BookDO> bookList = null;
		String book_kind = request.getParameter("book_kind");
		int count = 0;
		
		//전체 상품의 수를 얻어냄
		BookDAO bookDao = new BookDAO();
		count = bookDao.getBookCount();
		
		//상품이 있으면
		if(count > 0){
			//상품 전체를 테이블에서 얻어내서 bookList에 저장
			bookList = bookDao.getBooks(book_kind);
			//bookList를 뷰에서 사용할 수 있도록 request속성에 저장
			request.setAttribute("bookList", bookList);
		}
		//뷰에서 사용할 속성
		request.setAttribute("count", new Integer(count));
		request.setAttribute("book_kind", book_kind);
		request.setAttribute("type", new Integer(0));
		return "/mngr/productProcess/bookList.jsp";
	}
}