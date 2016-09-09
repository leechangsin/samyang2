package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;
import util.SHA256;

public class QnaDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	SHA256 sha;
	String sql;
	int x;
	
	// 사용자가 작성한 qna를 등록하는 메서드
	public int insertArticle(QnaDO article) {
		int group_id = 1;

		try {
			conn = DBManager.getConnection();
			sql = "select max(qna_id) from qna";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next())
				x = rs.getInt(1);
			if (x > 0)
				group_id = x + 1;

			sql = "insert into qna(book_id, book_title, qna_writer, qna_content, group_id, qora, reply, reg_date) ";
			sql += "values(?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, article.getBook_id());
			pstmt.setString(2, article.getBook_title());
			pstmt.setString(3, article.getQna_writer());
			pstmt.setString(4, article.getQna_content());
			pstmt.setInt(5, group_id);
			pstmt.setInt(6, article.getQora());
			pstmt.setInt(7, article.getReply());
			pstmt.setTimestamp(8, article.getReg_date());
			pstmt.executeUpdate();

			x = 1;// 레코드 추가 성공
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.closeConnection(conn, pstmt, rs);
		}

		return x;
	}// end insertArticle(QnaDO article)
	
	// 관리자가 작성한 qna(답변)을 등록하는 메서드
	public int insertArcitle(QnaDO article, int qna_id) {
		try {
			conn = DBManager.getConnection();
			sql = "insert into qna(book_id, book_title, qna_writer, qna_content, group_id, qora, reply, reg_date) ";
			sql += "values(?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, article.getBook_id());
			pstmt.setString(2, article.getBook_title());
			pstmt.setString(3, article.getQna_writer());
			pstmt.setString(4, article.getQna_content());
			pstmt.setInt(5, article.getGroup_id());
			pstmt.setInt(6, article.getQora());
			pstmt.setInt(7, article.getReply());
			pstmt.setTimestamp(8, article.getReg_date());
			pstmt.executeUpdate();

			sql = "update qna set reply=? where qna_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setInt(2, qna_id);
			pstmt.executeUpdate();

			x = 1;// 레코드 추가 성공
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.closeConnection(conn, pstmt);
		} // end try

		return x;
	}// end insertArcitle(QnaDO article, int qna_id)
	
	// qna테이블에 저장된 전체글의 갯수를 얻어내는 메서드
	public int getArticleCount() {
		try {
			conn = DBManager.getConnection();
			sql = "select count(*) from qna";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next())
				x = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.closeConnection(conn, pstmt, rs);
		} // end try

		return x;
	}// end getArticleCount()
	
	// book_id에 해당하는 상품의 QnA수를 얻어냄
	public int getArticleCount(int book_id){
		int x = 0;
		
		try{
			conn = DBManager.getConnection();
			sql = "select count(*) from qna where book_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, book_id);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				x = rs.getInt(1);
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			DBManager.closeConnection(conn, pstmt, rs);
		}//end try
		
		return x;
	}
	
	// 지정한 수만큼의 qna글을 얻어내는 메서드
	public List<QnaDO> getArticles(int count) {
		List<QnaDO> articleList = null;

		try {
			conn = DBManager.getConnection();
			sql = "select * from qna order by group_id desc, qora asc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				articleList = new ArrayList<>(count);
				do {
					QnaDO article = new QnaDO();
					article.setQna_id(rs.getInt("qna_id"));
					article.setBook_id(rs.getInt("book_id"));
					article.setBook_title(rs.getString("book_title"));
					article.setQna_writer(rs.getString("qna_writer"));
					article.setQna_content(rs.getString("qna_content"));
					article.setGroup_id(rs.getInt("group_id"));
					article.setQora(rs.getByte("qora"));
					article.setReply(rs.getByte("reply"));
					article.setReg_date(rs.getTimestamp("reg_date"));

					articleList.add(article);
				} while (rs.next());
			} // end if(rs.next())
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.closeConnection(conn, pstmt, rs);
		} // end try

		return articleList;
	}// end getArticles(int count)
	
	// 특정 책에 대해 작성된 qna글을 지정한 수만큼 얻어내는 메서드
	public List<QnaDO> getArticles(int count, int book_id) {
		List<QnaDO> articleList = null;

		try {
			conn = DBManager.getConnection();
			sql = "select * from qna where book_id=? order by group_id desc, qora asc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, book_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				articleList = new ArrayList<>(count);
				do {
					QnaDO article = new QnaDO();
					article.setQna_id(rs.getInt("qna_id"));
					article.setBook_id(rs.getInt("book_id"));
					article.setBook_title(rs.getString("book_title"));
					article.setQna_writer(rs.getString("qna_writer"));
					article.setQna_content(rs.getString("qna_content"));
					article.setGroup_id(rs.getInt("group_id"));
					article.setQora(rs.getByte("qora"));
					article.setReply(rs.getByte("reply"));
					article.setReg_date(rs.getTimestamp("reg_date"));

					articleList.add(article);
				} while (rs.next());
			} // end if(rs.next())
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.closeConnection(conn, pstmt, rs);
		} // end try

		return articleList;
	}// end getArticles(int count, int book_id)
	
	// QnA 글수정 폼에서 사용할 글의 내용을 얻어오는 메서드
	public QnaDO updateGetArticle(int qna_id) {
		QnaDO article = null;

		try {
			conn = DBManager.getConnection();
			sql = "select * from qna where qna_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qna_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				article = new QnaDO();
				article.setQna_id(rs.getInt("qna_id"));
				article.setBook_id(rs.getInt("book_id"));
				article.setBook_title(rs.getString("book_title"));
				article.setQna_writer(rs.getString("qna_writer"));
				article.setQna_content(rs.getString("qna_content"));
				article.setGroup_id(rs.getInt("group_id"));
				article.setQora(rs.getByte("qora"));
				article.setReply(rs.getByte("reply"));
				article.setReg_date(rs.getTimestamp("reg_date"));
			} // end if(rs.next())
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.closeConnection(conn, pstmt, rs);
		} // end try

		return article;
	}// end updateGetArticle(int qna_id)
	
	// Qna글 수정 메서드
	public int updateArticle(QnaDO article) {
		x = -1;

		try {
			conn = DBManager.getConnection();
			sql = "update qna set qna_content=? where qna_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getQna_content());
			pstmt.setInt(2, article.getQna_id());
			pstmt.executeUpdate();
			x = 1; // 수정 성공
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.closeConnection(conn, pstmt);
		} // end try

		return x;
	}// end updateArticle(QnaDO article)
	
	// Qna글 삭제 메서드
	public int deleteArticle(int qna_id) {
		x = -1;

		try {
			conn = DBManager.getConnection();
			sql = "delete from qna where qna_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qna_id);
			pstmt.executeUpdate();
			x = 1;// 글 삭제 성공
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.closeConnection(conn, pstmt, rs);
		} // end try

		return x;
	}// end deleteArticle(int qna_id)
}