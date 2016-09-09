package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.BCrypt;
import util.DBManager;
import util.SHA256;

public class MemberDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	
	public void insertMember(MemberDO member){
		SHA256 sha = SHA256.getInsatnce();
		try{
			conn = DBManager.getConnection();
			String orgPasswd = member.getPasswd();
			String shaPasswd = sha.getSha256(orgPasswd.getBytes());
			String bcPasswd = BCrypt.hashpw(shaPasswd, BCrypt.gensalt());
			
			sql = "insert into member values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, bcPasswd);
			pstmt.setString(3, member.getName());
			pstmt.setTimestamp(4, member.getReg_date());
			pstmt.setString(5, member.getAddress());
			pstmt.setString(6, member.getTel());
			
			pstmt.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			DBManager.closeConnection(conn, pstmt);
		}//end try
	}//end insertMember(MemberDO member)
	//로그인 폼 처리의 사용자 인증처리에서 사용하는 메서드
	public int userCheck(String id, String passwd){
		int x = -1;
		SHA256 sha = SHA256.getInsatnce();
		
		try{
			conn = DBManager.getConnection();
			String orgPasswd = passwd;
			String shaPasswd = sha.getSha256(orgPasswd.getBytes());
			
			sql = "select passwd from member where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				String dbPasswd = rs.getString("passwd");
				if(BCrypt.checkpw(shaPasswd, dbPasswd))
					x = 1; //로그인 성공
				else
					x = 0; //비밀번호 틀림
			} else{
				x = -1;//아이디 없음
			}//end if(rs.next())
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			DBManager.closeConnection(conn, pstmt, rs);
		}//end try
		
		return x;
	}//end userCheck(String id, String passwd)
	
	//아이디 중복여부 체크 메서드
	public int confirmId(String id){
		int x = -1;
		
		try{
			conn = DBManager.getConnection();
			sql = "seelct id from member where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				x = 1; //같은 아이디 존재 
			else 
				x = -1; //같은아이디 없음
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			DBManager.closeConnection(conn, pstmt, rs);
		}//end try
		
		return x;
	}//end confirmId(String id)
	
	//회원정보 얻어오는 메서드
	public MemberDO getMember(String id){
		MemberDO member = null;
		
		try{
			conn = DBManager.getConnection();
			sql = "select * from member where id=?";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				member = new MemberDO();
				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
				member.setReg_date(rs.getTimestamp("reg_date"));
				member.setAddress(rs.getString("address"));
				member.setTel(rs.getString("tel"));
			}//end if(rs.next())
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			DBManager.closeConnection(conn, pstmt, rs);
		}//end try
		
		return member;
	}//end getMember(String id)
	
	//주어진 id, passwd에 해당하는 회원정보를 얻어오는 메서드
	public MemberDO getMember(String id, String passwd){
		MemberDO member = null;
		SHA256 sha = SHA256.getInsatnce();
		
		try{
			conn = DBManager.getConnection();
			String orgPasswd = passwd;
			String shaPasswd = sha.getSha256(orgPasswd.getBytes());
			sql = "select * from member where id=?";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				String dbPasswd = rs.getString("passwd");
				if(BCrypt.checkpw(shaPasswd, dbPasswd)){
					member = new MemberDO();
					member.setId(rs.getString("id"));
					member.setName(rs.getString("name"));
					member.setReg_date(rs.getTimestamp("reg_date"));
					member.setAddress(rs.getString("address"));
					member.setTel(rs.getString("tel"));
				}//end if(BCrypt.checkpw(shaPasswd, dbPasswd))
			}//end if(rs.next())
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			DBManager.closeConnection(conn, pstmt, rs);
		}//end try
		
		return member;
	}//end getMember(String id, String passwd)
	
	public int updateMember(MemberDO member){
		int x = -1;
		SHA256 sha = SHA256.getInsatnce();
		
		try{
			conn = DBManager.getConnection();
			String orgPasswd = member.getPasswd();
			String shaPasswd = sha.getSha256(orgPasswd.getBytes());
			
			sql = "select passwd from member where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				String dbPasswd = rs.getString("passwd");
				if(BCrypt.checkpw(shaPasswd, dbPasswd)){
					sql = "update member set name=?, address=?, tel=? where id=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, member.getName());
					pstmt.setString(2, member.getAddress());
					pstmt.setString(3, member.getTel());
					pstmt.setString(4, member.getId());
					pstmt.executeUpdate();
					x = 1;
				} else{
					x = 0;
				}//end if(BCrypt.checkpw(shaPasswd, dbPasswd))
			}//end rs.next()
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			DBManager.closeConnection(conn, pstmt, rs);
		}//end try
		
		return x;
	}//end updateMember(MemberDO member)
	
	public int deleteMember(String id, String passwd){
		int x = -1;
		SHA256 sha = SHA256.getInsatnce();
		
		try{
			conn = DBManager.getConnection();
			String orgPasswd = passwd;
			String shaPasswd = sha.getSha256(orgPasswd.getBytes());
			sql = "select passwd from member where id=?";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				String dbPasswd = rs.getString("passwd");
				if(BCrypt.checkpw(shaPasswd, dbPasswd)){
					sql = "delete from member where id=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					x = 1;//회원탈퇴처리 성공
				} else{
					x = 0;//회원탈퇴 처리 실패
				}//end if(BCrypt.checkpw(shaPasswd, dbPasswd))
			}//end if(rs.next())
		} catch(Exception e){
			DBManager.closeConnection(conn, pstmt, rs);
		}//end try
		
		return x;
	}//end deleteMember(String id, String passwd)
}