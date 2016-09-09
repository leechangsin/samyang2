package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.QnaDAO;
import bean.QnaDO;

public class QnaUpdateProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		int qna_id = Integer.parseInt(request.getParameter("qna_id"));
		String qna_content = request.getParameter("qna_content");
		
		QnaDO qna = new QnaDO();
		qna.setQna_id(qna_id);
		qna.setQna_content(qna_content);
		
		QnaDAO qnaDao = new QnaDAO();
		int check = qnaDao.updateArticle(qna);
		
		request.setAttribute("check", new Integer(check));
		return "/qna/qnaUpdatePro.jsp";
	}
}