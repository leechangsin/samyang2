package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class CartDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	String sql;
	
	public void insertCart(CartDataBean cart)throws Exception {
		try{
			conn = DBManager.getConnection();
			sql = "insert into cart(book_id, buyer, book_title, buy_price, buy_count, book_image)";
			sql += "values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, cart.getBook_id());
			pstmt.setString(2, cart.getBuyer());
			pstmt.setString(3, cart.getBook_title());
			pstmt.setInt(4, cart.getBuy_price());
			pstmt.setByte(5, cart.getBuy_count());
			pstmt.setString(6, cart.getBook_image());
			
			pstmt.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			DBManager.closeConnection(conn, pstmt, rs);
		}//end try
	}//end insertCart(CartDO cart)
	
	public int getListCount(String id)throws Exception {
		int x = 0;
		try{
			conn = DBManager.getConnection();
			sql = "select count(*) from cart where buyer=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				x = rs.getInt(1);
			
			
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			DBManager.closeConnection(conn, pstmt, rs);
		}
		
		return x;
	}//end getListCount(String id)
	
	public List<CartDataBean> getCart(String id, int count) throws Exception{
		CartDataBean cart = null;
		List<CartDataBean> lists = null;
		
		try{
			conn = DBManager.getConnection();
			sql = "select * from cart where buyer=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			lists = new ArrayList<>(count);
			
			while(rs.next()){
				cart = new CartDataBean();
				cart.setCart_id(rs.getInt("cart_id"));
				cart.setBook_id(rs.getInt("book_id"));
				cart.setBook_title(rs.getString("book_title"));
				cart.setBuy_price(rs.getInt("buy_price"));
				cart.setBuy_count(rs.getByte("buy_count"));
				cart.setBook_image(rs.getString("book_image"));
				
				lists.add(cart);
			}//end while(rs.next())
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			DBManager.closeConnection(conn, pstmt, rs);
		}//end try
		
		return lists;
	}//end getCart(String id, int count)
	
	//장바구니에서 수량 수정시 실행되는 메서드
	public void updateCount(int cart_id, byte count) throws Exception{
		try{
			conn = DBManager.getConnection();
			sql = "update cart set buy_count=? where cart_id=?";
			pstmt.setByte(1, count);
			pstmt.setInt(2, cart_id);
			pstmt.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			DBManager.closeConnection(conn, pstmt);
		}//end try
	}//end updateCount(int cart_id, byte count)
	
	//장바구니에서 cart_id에 대한 레코드를 삭제하는 메서드
	public void deleteList(int cart_id) throws Exception{
		try{
			conn = DBManager.getConnection();
			sql = "delete from cart where cart_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cart_id);
			pstmt.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			DBManager.closeConnection(conn, pstmt);
		}//end try
	}//end deleteList(int cart_id)
	
	//장바구니 비우기 버튼 클릭시 실행되는 메서드
	public void deleteAll(String id) throws Exception{
		try{
			conn = DBManager.getConnection();
			sql = "delete from cart where buyer=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			DBManager.closeConnection(conn, pstmt);
		}//end try
	}//end deleteAll(String id)
}