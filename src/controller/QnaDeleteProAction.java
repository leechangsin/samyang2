package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.QnaDAO;

public class QnaDeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		int qna_id = Integer.parseInt(request.getParameter("qna_id"));
		
		QnaDAO qnaDao = new QnaDAO();
		int check = qnaDao.deleteArticle(qna_id);
		
		request.setAttribute("check", new Integer(check));
		return "/qna/qnaDeletePro.jsp";
	}

}
