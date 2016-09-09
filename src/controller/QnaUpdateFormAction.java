package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.QnaDAO;
import bean.QnaDO;

public class QnaUpdateFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		int qna_id = Integer.parseInt(request.getParameter("qna_id"));
		String book_kind = request.getParameter("book_kind");
		
		QnaDAO qnaDao = new QnaDAO();
		QnaDO qna = qnaDao.updateGetArticle(qna_id);
		
		request.setAttribute("qna", qna);
		request.setAttribute("qna_id", new Integer(qna_id));
		request.setAttribute("book_kind", book_kind);
		request.setAttribute("type", new Integer(1));
		return "/qna/qnaUpdateForm.jsp";
	}

}
