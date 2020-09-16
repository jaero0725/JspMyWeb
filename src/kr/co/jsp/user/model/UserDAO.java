package kr.co.jsp.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserDAO implements IUserDAO {
	
	DataSource ds;
	
	private UserDAO() {
		try {
			InitialContext ct = new InitialContext();
			ds = (DataSource) ct.lookup("java:comp/env/jdbc/mysql");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static UserDAO dao = new UserDAO();
	
	public static UserDAO getInstance() {
		if(dao == null) {
			dao = new UserDAO();
		}
		return dao;
	}
	
	
	
	/////////////////////////////////////////////////////////////

	@Override
	public boolean confirmId(String id) {
		boolean flag = false;
		String sql = "SELECT * FROM user WHERE user_id=?";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) flag = true; //아이디가 존재
			else flag = false;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	
	
	@Override
	public boolean insertUser(UserVO vo) {
		boolean flag = false;
		String sql = "INSERT INTO user VALUES(?,?,?,?,?)";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getAddress());
			
			if(pstmt.executeUpdate() == 1) flag = true;
			else flag = false;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	
	
	

	@Override
	public int userCheck(String id, String pw) {
		int check = 0;
		//아이디가 있는지를 조회하여 그 아이디에 같이 맵핑되어 있는
		//비밀번호를 얻은 후, 매개값으로 받은 비밀번호가 같은지 대조하여
		//return값을 조정해 주시면 되겠습니다.
		//아이디x -> -1, 비밀번호 다른 경우 = 0, 로그인 성공 -> 1
		
		String sql = "SELECT * FROM user WHERE user_id=?";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) { //아이디가 존재함.
				String dbPw = rs.getString("user_pw");// DB에 저장된 비밀번호 얻음.
				if(dbPw.equals(pw)) { //로그인 성공
					check = 1;
				} else { //비밀번호가 다른 경우.
					check = 0;
				}
			} else { //아이디가 존재하지 않음.
				check = -1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return check;
	}
	
	
	@Override
	public UserVO getUserInfo(String id) {
		UserVO vo = null;
		String sql = "SELECT * FROM user WHERE user_id=?";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new UserVO(
						rs.getString("user_id"),
						rs.getString("user_pw"),
						rs.getString("user_name"),
						rs.getString("user_email"),
						rs.getString("user_address")
						);		
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}



	@Override
	public boolean changePassword(String id, String pw) {
		boolean flag = false;
		String sql = "UPDATE user SET user_pw=? WHERE user_id=?";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, pw);
			pstmt.setString(2, id);
			
			if(pstmt.executeUpdate() == 1) flag = true;
			else flag = false;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}



	@Override
	public boolean updateUser(UserVO vo) {
		boolean flag = false;
		String sql = "UPDATE user "
				+ "SET user_name=?, user_email=?, user_address=? "
				+ "WHERE user_id=?";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getAddress());
			pstmt.setString(4, vo.getId());
			
			if(pstmt.executeUpdate() == 1) flag = true;
			else flag = false;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}



	@Override
	public boolean deleteUser(String id) {
		boolean flag = false;
		
		String sql = "DELETE FROM user WHERE user_id=?";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			
			if(pstmt.executeUpdate() == 1) flag = true;
			else flag = false;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
