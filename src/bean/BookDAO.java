package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.BCrypt;
import util.DBManager;
import util.SHA256;

public class BookDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	SHA256 sha = null;
	String sql = null;
	int x = -1;

	// 관리자 인증 메서드
	public int userCheck(String id, String passwd) {
		try {
			sha = SHA256.getInsatnce();
			conn = DBManager.getConnection();

			String orgPasswd = passwd;
			String shaPasswd = sha.getSha256(orgPasswd.getBytes());

			sql = "select managerPasswd from manager where managerId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String dbPasswd = rs.getString("managerPasswd");
				if (BCrypt.checkpw(shaPasswd, dbPasswd))
					x = 1; // 인증성공
				else
					x = 0;// 비밀번호 틀림
			} else {
				x = -1;// 아이디 없음
			} // end if(rs.next())
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.closeConnection(conn, pstmt, rs);
		} // end try

		return x;
	}// userCheck(String id, String passwd)
	
	// 책 등록 메서드
	public void insertBook(BookDO book) throws Exception {
		try {
			conn = DBManager.getConnection();
			sql = "insert into book(book_kind, book_title, book_price, book_count, author, publishing_com, ";
			sql += "publishing_date, book_image, book_content, discount_rate, reg_date)";
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.closeConnection(conn, pstmt);
		} // end try
	}// end insertBook(BookDO book)
	
	// 이미 등록된 책인지 검사하는 메서드
	public int registedBookconfim(String book_kind, String book_name, String author) throws Exception {
		try {
			conn = DBManager.getConnection();
			sql = "select book_name from book where book_kind=? and book_name=? and author=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book_kind);
			pstmt.setString(2, book_name);
			pstmt.setString(3, author);

			rs = pstmt.executeQuery();

			if (rs.next())
				x = 1; // 해당 책이 이미 등록되어 있음
			else
				x = -1;// 해당 책이 등록되어있지 않음
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.closeConnection(conn, pstmt, rs);
		}

		return x;
	}// end registedBookconfim(String kind, String bookName, String author)
	
	// 전체 등록된 책의 수를 얻어내는 메소드
	public int getBookCount() throws Exception {
		x = 0;

		try {
			conn = DBManager.getConnection();
			sql = "select count(*) from book";

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
	}// end getBookCount()
	
	// 해당 종류의 책의 수를 얻어내는 메서드
	public int getBookCount(String book_kind) throws Exception {
		x = 0;
		int kind = Integer.parseInt(book_kind);

		try {
			conn = DBManager.getConnection();
			sql = "select count(*) from book where book_kind=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, kind);
			rs = pstmt.executeQuery();

			if (rs.next())
				x = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.closeConnection(conn, pstmt, rs);
		} // end try

		return x;
	}// end getBookCount(String book_kind)
	
	// 책의 제목을 얻어내는 메서드
	public String getBookTitle(int book_id) {
		String book_title = null;

		try {
			conn = DBManager.getConnection();
			sql = "select book_title from book where book_id=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, book_id);
			rs = pstmt.executeQuery();

			if (rs.next())
				book_title = rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.closeConnection(conn, pstmt, rs);
		} // end try

		return book_title;
	}// end getBookTitle(int book_id)
	
	// 분류별 또는 전체 등록된 책의 정보를 얻어내는 메서드
	public List<BookDO> getBooks(String book_kind) throws Exception {
		List<BookDO> bookList = null;

		try {
			conn = DBManager.getConnection();

			if (book_kind.equals("all") || book_kind.equals("")) {
				sql = "select * from book";
				pstmt = conn.prepareStatement(sql);
			} else {
				sql = "select * from book where book_kind=? order by reg_date desc";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, book_kind);
			}

			rs = pstmt.executeQuery();

			if (rs.next()) {
				bookList = new ArrayList<>();
				do {
					BookDO book = new BookDO();
					book.setBook_id(rs.getInt("book_id"));
					book.setBook_kind(rs.getString("book_kind"));
					book.setBook_title(rs.getString("book_title"));
					book.setBook_price(rs.getInt("book_price"));
					book.setBook_count(rs.getShort("book_count"));
					book.setAuthor(rs.getString("author"));
					book.setPublishing_com(rs.getString("publishing_com"));
					book.setPublishing_date(rs.getString("publishing_date"));
					book.setBook_image(rs.getString("book_image"));
					book.setDiscount_rate(rs.getByte("discount_rate"));
					book.setReg_date(rs.getTimestamp("reg_date"));

					bookList.add(book);
				} while (rs.next());
			} // end if(rs.next())
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.closeConnection(conn, pstmt, rs);
		} // end try

		return bookList;
	}// end getBooks(String book_kind)
	
	// 쇼핑몰 메인에 표시하기 위해 사용하는 메서드, 분류별 신간책 목록을 얻어내는 메서드
	public BookDO[] getBooks(String book_kind, int count) throws Exception {
		BookDO[] bookList = null;
		int i = 0;

		try {
			conn = DBManager.getConnection();
			sql = "select * from book where book_kind=? order by reg_date desc limit ?,?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book_kind);
			pstmt.setInt(2, 0);
			pstmt.setInt(3, count);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				bookList = new BookDO[count];
				do {
					BookDO book = new BookDO();
					book.setBook_id(rs.getInt("book_id"));
					book.setBook_kind(rs.getString("book_kind"));
					book.setBook_title(rs.getString("book_title"));
					book.setBook_price(rs.getInt("book_price"));
					book.setBook_count(rs.getShort("book_count"));
					book.setAuthor(rs.getString("author"));
					book.setPublishing_com(rs.getString("publishing_com"));
					book.setPublishing_date(rs.getString("publishing_date"));
					book.setBook_image(rs.getString("book_image"));
					book.setDiscount_rate(rs.getByte("discount_rate"));
					book.setReg_date(rs.getTimestamp("reg_date"));

					bookList[i++] = book;
				} while (rs.next());
			} // end if(rs.next())
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.closeConnection(conn, pstmt, rs);
		} // end try

		return bookList;
	}// end getBooks(String book_kind, int count)
	
	// 책의 정보를 수정하기 위해 book_id값으로 책의 정보를 얻어오는 메서드
	public BookDO getBook(int book_id) throws Exception {
		BookDO book = null;

		try {
			conn = DBManager.getConnection();
			sql = "select * from book where book_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, book_id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				book = new BookDO();
				book.setBook_kind(rs.getString("book_kind"));
				book.setBook_title(rs.getString("book_title"));
				book.setBook_price(rs.getInt("book_price"));
				book.setBook_count(rs.getShort("book_count"));
				book.setAuthor(rs.getString("author"));
				book.setPublishing_com(rs.getString("publishing_com"));
				book.setPublishing_date(rs.getString("publishing_date"));
				book.setBook_image(rs.getString("book_image"));
				book.setBook_content(rs.getString("book_content"));
				book.setDiscount_rate(rs.getByte("discount_rate"));
				book.setReg_date(rs.getTimestamp("reg_date"));
			} // end if(rs.next())
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.closeConnection(conn, pstmt, rs);
		} // end try

		return book;
	}// end getBook(int book_id)
	
	// 책의 정보를 수정하는 메서드
	public void updateBook(BookDO book, int book_id) throws Exception {
		try {
			conn = DBManager.getConnection();

			sql = "update book set book_kind=?, book_title=?, book_price=?, book_count=?, ";
			sql += "author=?, publishing_com=?, publishing_date=?, book_image=?, book_content=?, ";
			sql += "discount_rate=? where book_id=?";
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
			pstmt.setInt(11, book_id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.closeConnection(conn, pstmt);
		} // end try
	}// end updateBook(BookDO book, int book_id)
	
	// 책을 삭제하는 메서드
	public void deleteBook(int book_id) throws Exception {
		try {
			conn = DBManager.getConnection();
			sql = "delete from book where book_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, book_id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.closeConnection(conn, pstmt);
		} // end try
	}// end deleteBook(int book_id)
}