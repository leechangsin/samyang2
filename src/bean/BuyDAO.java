package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class BuyDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	String sql;
	
	public List<String> getAccount(){
		List<String> accountList = null;
		try{
			conn = DBManager.getConnection();
			sql = "select * from bank";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			accountList = new ArrayList<>();
			while(rs.next()){
				String account = new String(rs.getString("account") + "" + 
						rs.getString("bank") + "" + rs.getString("name"));
				accountList.add(account);
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			DBManager.closeConnection(conn, pstmt, rs);
		}//end try
		
		return accountList;
	}//end getAccount()
	
	public void insertBuy(List<CartDataBean> lists, String id, String account, String deliveryName, String deliveryTel, String deliveryAddress) throws Exception{
		Timestamp reg_date = null;
		String maxDate = "";
		String number = "";
		String todayDate = "";
		String compareDate = "";
		long buyId = 0;
		short nowCount;
		
		try{
			conn = DBManager.getConnection();
			reg_date = new Timestamp(System.currentTimeMillis());
			todayDate = reg_date.toString();
			compareDate = todayDate.substring(0, 4) + todayDate.substring(5, 7) + todayDate.substring(8, 10);
			todayDate.substring(8, 10);
			
			sql = "select max(buy_id) from buy";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			if(rs.getLong(1) > 0){
				Long val = new Long(rs.getLong(1));
				maxDate = val.toString().substring(0, 8);
				number = val.toString().substring(8);
				if(compareDate.equals(maxDate)){
					if(Integer.parseInt(number) + 1 < 10000)
						buyId = Long.parseLong(maxDate + (Integer.parseInt(number) + 1 + 10000));
					else
						buyId = Long.parseLong(maxDate + (Integer.parseInt(number) + 1));
				} else{
					compareDate += "00001";
					buyId = Long.parseLong(compareDate);
				}
			} else {
				compareDate += "00001";
				buyId = Long.parseLong(compareDate);
			}
			
			conn.setAutoCommit(false);
			for(int i=0; i<lists.size(); i++){
				CartDataBean cart = lists.get(i);
				
				sql = "insert into buy(buy_id, buyer, book_id, book_title, buy_price, buy_count, ";
				sql += "book_image, buy_date, account, deliveryName, deliveryTel, deliveryAddress)";
				sql += "values(?,?,?,?,?,?,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setLong(1, buyId);
				pstmt.setString(2, id);
				pstmt.setInt(3, cart.getBook_id());
				pstmt.setString(4, cart.getBook_title());
				pstmt.setInt(5, cart.getBuy_price());
				pstmt.setByte(6, cart.getBuy_count());
				pstmt.setString(7, cart.getBook_image());
				pstmt.setTimestamp(8, reg_date);
				pstmt.setString(9, account);
				pstmt.setString(10, deliveryName);
				pstmt.setString(11, deliveryTel);
				pstmt.setString(12, deliveryAddress);
				pstmt.executeUpdate();
				
				sql = "select book_count from book where book_id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cart.getBook_id());
				rs = pstmt.executeQuery();
				rs.next();
				
				//나중에 활용할땐 구매수량을 뺄것 지금은 하나만 구매한다는가정
				nowCount = (short)(rs.getShort(1) - 1);
				
				sql = "update book set book_count=? where book_id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setShort(1, nowCount);
				pstmt.setInt(2, cart.getBook_id());
				pstmt.executeUpdate();
			}//end for(int i=0; i<lists.size(); i++)
			
			sql = "delete from cart where buyer=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
			conn.commit();
			conn.setAutoCommit(true);
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			DBManager.closeConnection(conn, pstmt, rs);
		}//end try
	}//end insertBuy
	
	public int getListCount(String id) throws Exception{
		int x = 0;
		try{
			conn = DBManager.getConnection();
			sql = "select count(*) from buy where buyer=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				x = rs.getInt(1);
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			DBManager.closeConnection(conn, pstmt, rs);
		}//end try
		
		return x;
	}//end getListCount(String id)
	
	public int getListCount() throws Exception {
		int x = 0;
		try{
			conn = DBManager.getConnection();
			sql = "select count(*) from buy";
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
	}//end getListCount()
	
	public List<BuyDO> getBuyList(String id) throws Exception{
		BuyDO buy = null;
		List<BuyDO> lists = null;
		
		try{
			conn = DBManager.getConnection();
			sql = "select * from buy where buyer=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			lists = new ArrayList<BuyDO>();
			
			while(rs.next()){
				buy = new BuyDO();
				buy.setBuy_id(rs.getLong("buy_id"));
				buy.setBook_id(rs.getInt("book_id"));
				buy.setBook_title(rs.getString("book_title"));
				buy.setBuy_price(rs.getInt("buy_price"));
				buy.setBuy_count(rs.getByte("buy_count"));
				buy.setBook_image(rs.getString("book_image"));
				buy.setSanction(rs.getString("sanction"));
			
				lists.add(buy);
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			DBManager.closeConnection(conn, pstmt, rs);
		}//end try
		
		return lists;
	}//end getBuyList(String id)
	
	public List<BuyDO> getBuyList() throws Exception {
		BuyDO buy = null;
		List<BuyDO> lists = null;
		
		try{
			conn = DBManager.getConnection();
			sql = "select * from buy";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			lists = new ArrayList<>();
			while(rs.next()){
				buy = new BuyDO();
				buy.setBuy_id(rs.getLong("buy_id"));
				buy.setBuyer(rs.getString("buyer"));
				buy.setBook_id(rs.getInt("book_id"));
				buy.setBook_title(rs.getString("book_title"));
				buy.setBuy_price(rs.getInt("buy_price"));
				buy.setBuy_count(rs.getByte("buy_count"));
				buy.setBook_image(rs.getString("book_image"));
				buy.setBuy_date(rs.getTimestamp("buy_date"));
				buy.setAccount(rs.getString("account"));
				buy.setDeliveryName(rs.getString("deliveryName"));
				buy.setDeliveryTel(rs.getString("deliveryTel"));
				buy.setDeliveryAddress(rs.getString("deliveryAddress"));
				buy.setSanction(rs.getString("sanction"));
				
				lists.add(buy);
			}//end while(rs.next())
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			DBManager.closeConnection(conn, pstmt, rs);
		}//end try
	
		return lists;
	}//end getBuyList()
}