package main.java.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.java.util.BCrypt;
import main.java.util.DBManager;
import main.java.util.SHA256;

public class BookDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	SHA256 sha = null;
	String sql = null;
	int x = -1;

	//관리자 인증 메서드
	public int userCheck(String id, String passwd){
		try{
			sha = SHA256.getInsatnce();
			conn = DBManager.getConnection();
			
			String orgPasswd = passwd;
			String shaPasswd = sha.getSha256(orgPasswd.getBytes());
			
			sql = "select managerPasswd from manager where managerId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				String dbPasswd = rs.getString("managerPasswd");
				if(BCrypt.checkpw(shaPasswd, dbPasswd))
					x = 1; //인증성공
				else
					x = 0;//비밀번호 틀림
			} else{
				x = -1;//아이디 없음
			}//end if(rs.next())
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			DBManager.closeConnection(conn, pstmt, rs);
		}//end try
		
		return x;
	}//userCheck(String id, String passwd)
	
	//책 등록 메서드
	public void insertBook(BookDO book) throws Exception{
		try{
			conn = DBManager.getConnection();
			sql = "insert into book(book_kind, book_title, book_price, book_count, author, publishing_com, ";
			sql += "publishing_date, book_image, book_content, discount_rate, reg_date ";
			sql += "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getBook_kind());
			pstmt.setString(2, book.getBook_title());
			pstmt.setInt(3, book.getBook_price());
			pstmt.setShort(4, book.getBook_count());
			pstmt.setString(5, book.getAuthor());
			pstmt.setString(6, book.getPublishing_com());
			pstmt.setString(7, book.getPublishing_date());
			pstmt.setString(8, book.getBook_image());
			pstmt.setString(9, book.getBook_content());
			pstmt.setByte(10, book.getDiscount_rate());
			pstmt.setTimestamp(11, book.getReg_date());
			
			pstmt.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			DBManager.closeConnection(conn, pstmt);
		}//end try
	}//end insertBook(BookDO book)
	
	//이미 등록된 책인지 검사하는 메서드
	public int registedBookconfim(String kind, String bookName, String author) throws Exception{
		try{
			conn = DBManager.getConnection();
			sql = "select book_name from book where book_kind=? and book_name=? and author=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kind);
			pstmt.setString(2, bookName);
			pstmt.setString(3, author);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
				x = 1; //해당 책이 이미 등록되어 있음
			else
				x = -1;//해당 책이 등록되어있지 않음
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			DBManager.closeConnection(conn, pstmt, rs);
		}
		
		return x;
	}//end registedBookconfim(String kind, String bookName, String author)
	
	//전체 등록된 책의 수를 얻어내는 메소드
	public int getBookCount() throws Exception{
		x = 0;
		
		try{
			conn = DBManager.getConnection();
			sql = "select count(*) from book";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				x = rs.getInt(1);
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			DBManager.closeConnection(conn, pstmt, rs);
		}//end try
		
		return x;
	}//end getBookCount()
	
	//해당 종류의 책의 수를 얻어내는 메서드
	public int getBookCount(String book_kind) throws Exception{
		x = 0;
		int kind = Integer.parseInt(book_kind);
		
		try{
			conn = DBManager.getConnection();
			sql = "select count(*) from book where book_kind=?";
			pstmt.setInt(1, kind);
			
			if(rs.next())
				x = rs.getInt(1);
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			DBManager.closeConnection(conn, pstmt, rs);
		}//end try
		
		return x;
	}//end getBookCount(String book_kind)
}