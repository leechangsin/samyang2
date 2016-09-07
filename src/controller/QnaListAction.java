package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.QnaDAO;
import bean.QnaDO;

public class QnaListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		List<QnaDO> qnaList;
		
		QnaDAO qnaDao = new QnaDAO();
		//qna의 수를 얻어냄
		int count = qnaDao.getArticleCount();
		
		//qna가 있다면
		if(count > 0){
			//qna의 수만큼 qna를 얻어냄
			qnaList = qnaDao.getArticles(count);
			request.setAttribute("qnaList", qnaList);
		}
		
		request.setAttribute("count", new Integer(count));
		request.setAttribute("type", new Integer(0));
		return "/mngr/qnaProcess/qnaList.jsp";
	}
}