package controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.QnaDAO;
import bean.QnaDO;

public class QnaProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		String qna_writer = request.getParameter("qna_writer");
		String book_title = request.getParameter("book_title");
		String qna_content = request.getParameter("qna_content");
		int book_id = Integer.parseInt(request.getParameter("book_id"));
		Byte qora = Byte.parseByte(request.getParameter("qora"));
		//답변 여부 - 미답변
		byte reply = 0;
		
		QnaDO qna = new QnaDO();
		qna.setBook_id(book_id);
		qna.setBook_title(book_title);
		qna.setQna_content(qna_content);
		qna.setQna_writer(qna_writer);
		qna.setReply(reply);
		qna.setReg_date(new Timestamp(System.currentTimeMillis()));
		qna.setQora(qora);
		
		QnaDAO qnaDao = new QnaDAO();
		int check = qnaDao.insertArticle(qna);
		
		request.setAttribute("check", new Integer(check));
		return "/qna/qnaPro.jsp";
	}
}