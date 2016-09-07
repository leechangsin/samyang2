package controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.QnaDAO;
import bean.QnaDO;

public class QnaReplyProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		//상품 Qna답변글 관련 내용
		int qna_id = Integer.parseInt(request.getParameter("qna_id"));
		int book_id = Integer.parseInt(request.getParameter("book_id"));
		String qna_writer = request.getParameter("qna_writer");
		String book_title = request.getParameter("book_title");
		String qna_content = "[답변] : " + request.getParameter("qna_contet");
		byte qora = Byte.parseByte(request.getParameter("qora"));
		//답변 여부, 1 = 답변함
		byte reply = 1;
		
		//상품 Qna답변글 저장을 위한 정보 설정
		QnaDO qna = new QnaDO();
		qna.setQna_id(qna_id);
		qna.setBook_id(book_id);
		qna.setBook_title(book_title);
		qna.setQna_content(qna_content);
		qna.setQna_writer(qna_writer);
		qna.setGroup_id(qna_id);
		qna.setReply(reply);
		qna.setReg_date(new Timestamp(System.currentTimeMillis()));
		qna.setQora(qora);
		
		//테이블에 상품 Qna 답변글 추가
		QnaDAO qnaDao = new QnaDAO();
		int check = qnaDao.insertArcitle(qna, qna_id);
		
		request.setAttribute("check", new Integer(check));
		return "/mngr/qnaProcess/qnaReplyPro.js";
	}

}
