package kr.or.kosta.shoppingmall.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import kr.or.kosta.shoppingmall.common.db.DaoFactory;
import kr.or.kosta.shoppingmall.common.exception.MallException;
import kr.or.kosta.shoppingmall.common.web.Params;
import kr.or.kosta.shoppingmall.user.domain.User;

/**
 * JDBC API를 이용한 사용자(users) 테이블 관련 영속성 처리 DAO 클래스
 * DAO 패턴 적용
 * @author 김기정
 */
public class JdbcUserDao implements UserDao{
	
private DataSource dataSource;
	
	public JdbcUserDao() {}
	
	public JdbcUserDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	/** 신규 회원 등록 */
	public void create(User user){
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = " INSERT INTO users " +
		             "             (id," +
				     "              name," +
		             "              passwd," +
				     "              email," +
		             "              telephone," +
				     "              job," +
		             "              message)" +
		             " VALUES      (?," +
				     "              ?," +
		             "              ?," +
		             "              ?," +
		             "              ?," +
		             "              ?," +
		             "              ?)";
		
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPasswd());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getTelephone());
			pstmt.setString(6, user.getJob());
			pstmt.setString(7, user.getMessage());
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {}
			throw new MallException("JdbcUserDao.crate(user) 실행 중 예외발생", e);
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close(); // 커넥션풀에 반납
			} catch (Exception e) {}
		}
	}
	
	
	/** 사용자아이디를 이용한 사용자 상세 정보 조회 */
	public User read(String id){
		User user = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT id, " + 
				     "       name, " + 
				     "       passwd, " + 
				     "       email, " + 
				     "       telephone, " + 
				     "       job, " + 
				     "       message, " + 
				     "       TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS') regdate " + 
				     "FROM   users " + 
				     "WHERE  id = ?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user = createUser(rs);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new MallException("JdbcUserDao.read(String id) 실행 중 예외발생", e);
		}finally {
			try {
				if(rs != null)    rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			} catch (Exception e) {}
		}
		return user;
	}
	
	
	/** 회원정보 수정 */
	public void update(User user) {	}
	
	
	/** 회원 여부 반환 */
	public User isMember(String id, String passwd){
		User user = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT id, " + 
				     "       name, " + 
				     "       passwd, " + 
				     "       email, " + 
				     "       telephone, " + 
				     "       job, " + 
				     "       message, " + 
				     "       TO_CHAR(regdate, 'YYYY/MM/DD') regdate " + 
				     "FROM   users " + 
				     "WHERE  id = ? AND passwd = ?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user = createUser(rs);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new MallException("JdbcUserDao.isMember(id, passwd) 실행 중 예외발생", e);
		}finally {
			try {
				if(rs != null)    rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			} catch (Exception e) {}
		}
		return user;
	}
	
	private User createUser(ResultSet rs) throws SQLException {
		String uid = rs.getString("id");		
		String name = rs.getString("name");		
		String passwd = rs.getString("passwd");		
		String email = rs.getString("email");		
		String telephone = rs.getString("telephone");		
		String job = rs.getString("job");		
		String message = rs.getString("message");		
		String regdate = rs.getString("regdate");
		
		User user = new User();
		user.setId(uid);
		user.setName(name);
		user.setPasswd(passwd);
		user.setEmail(email);
		user.setTelephone(telephone);
		user.setJob(job);
		user.setMessage(message);
		user.setRegdate(regdate);
		return user;
	}

	
	/** 전체 회원목록 반환 */
	public List<User> listAll() {
		List<User> list = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT id, " +
		             "        name," +
		             "        passwd," +
		             " 	      email," +
				     "        telephone," +
		             "        job," +
		             "        message," +
		             "        TO_CHAR(regdate, 'YYYY/MM/DD HH24:MI:SS') regdate" +
				     " FROM   users" + 
				     " ORDER BY regdate DESC";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			list = new ArrayList<User>();
			
			while(rs.next()){
				User user = createUser(rs);
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MallException("JdbcUserDao.listAll() 실행 중 예외발생", e);
		} finally {
			try {
				if(rs != null)    rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			} catch (Exception e) {}
		}
		return list;
	}
	
	/** 선택 페이지에 대한 회원목록 반환 */
	public List<User> listByPage(int page) {
		List<User> list = null;	
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT id,");
		sb.append("        name,");
		sb.append("        passwd,");
		sb.append("        email,");
		sb.append("        telephone,");
		sb.append("        job,");
		sb.append("        message,");
		sb.append("        regdate");
		sb.append(" FROM   (SELECT CEIL(rownum / 10) request_page,");
		sb.append("                id,");
		sb.append("                name,");
		sb.append("                passwd,");
		sb.append("                email,");
		sb.append("                telephone,");
		sb.append("                job,");
		sb.append("                message,");
		sb.append("                regdate");
		sb.append("         FROM   (SELECT id,");
		sb.append("                        name,");
		sb.append("                        passwd,");
		sb.append("                        email,");
		sb.append("                        telephone,");
		sb.append("                        job,");
		sb.append("                        message,");
		sb.append("                        TO_CHAR(regdate, 'YYYY/MM/DD HH24:MI:SS') regdate");
		sb.append("                 FROM   users");
		sb.append("                 ORDER BY regdate DESC))");
		sb.append(" WHERE  request_page = ?");
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, page);
			rs = pstmt.executeQuery();
			list = new ArrayList<User>();
			
			while(rs.next()){
				User user = createUser(rs);
				list.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new MallException("JdbcUserDao.listByPage(int page) 실행 중 예외발생", e);
		} finally {
			try {
				if(rs != null)    rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			} catch (Exception e) {}
		}
		return list;
	}
	
	
	/** {선택페이지, 검색유형, 검색값, 한페이지당 출력 행수}에 대한 회원목록 반환 */
	public List<User> listByParams(Params params) {
		List<User> list = null;		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT id,");
		sb.append("        name,");
		sb.append("        passwd,");
		sb.append("        email,");
		sb.append("        telephone,");
		sb.append("        job,");
		sb.append("        message,");
		sb.append("        regdate");
		sb.append(" FROM   (SELECT CEIL(rownum / ?) request_page,");
		sb.append("                id,");
		sb.append("                name,");
		sb.append("                passwd,");
		sb.append("                email,");
		sb.append("                telephone,");
		sb.append("                job,");
		sb.append("                message,");
		sb.append("                regdate");
		sb.append("         FROM   (SELECT id,");
		sb.append("                        name,");
		sb.append("                        passwd,");
		sb.append("                        email,");
		sb.append("                        telephone,");
		sb.append("                        job,");
		sb.append("                        message,");
		sb.append("                        TO_CHAR(regdate, 'YYYY/MM/DD HH24:MI:SS') regdate");
		sb.append("                 FROM   users");
		
		// 검색 유형별 WHERE 절 동적 추가
		String type = params.getType();
		String value = params.getValue();
		if(type != null){
			switch (params.getType()) {
				case "id":    
					sb.append(" WHERE  id  = ?");
					break;
				case "name":  
					sb.append(" WHERE  name LIKE ?");
					value = "%" + value + "%";
					break;
				case "job":   
					sb.append(" WHERE  job LIKE ?");
					value = "%" + value + "%";
					break;
			}
		}
		sb.append(" ORDER BY regdate DESC))");
		sb.append(" WHERE  request_page = ?");
		
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, params.getPageSize());
			
			// 전체검색이 아닌경우 경우
			if(type != null){
				pstmt.setString(2, value);
				pstmt.setInt(3, params.getPage());
			}else{// 전체검색인 경우
				pstmt.setInt(2, params.getPage());
			}
			
			rs = pstmt.executeQuery();
			list = new ArrayList<User>();
			
			while(rs.next()){
				User user = createUser(rs);
				list.add(user);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new MallException("JdbcUserDao.listByParams(Params params) 실행 중 예외발생", e);
		} finally {
			try {
				if(rs != null)    rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			} catch (Exception e) {}
		}
		return list;
	}

	/** 출력페이지 계산을 위한 {검색유형, 검색값}에 대한 행의 수 반환 */
	public int pageCount(Params params) {
		int count = 0;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT COUNT(id) count");
		sb.append(" FROM   users");
		
		// 검색 유형별 WHERE 절 동적 추가
		String type = params.getType();
		String value = params.getValue();
		if(type != null){
			switch (params.getType()) {
				case "id":
					sb.append(" WHERE  id  = ?");
					break;
				case "name":
					sb.append(" WHERE  name LIKE ?");
					value = "%" +value + "%";
					break;
				case "job":
					sb.append(" WHERE  job LIKE ?");
					value = "%" + value + "%";
					break;
			}
		}
				
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sb.toString());

			// 전체검색이 아닌경우 경우
			if(type != null){
				pstmt.setString(1, value);
			}

			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MallException("JdbcUserDao.pageCount(Params params) 실행 중 예외발생", e);
		} finally {
			try {
				if(rs != null)    rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			} catch (Exception e) {}
		}
		return count;
	}
	
	/** 단위 테스트 */
	public static void main(String[] args) {
		UserDao userDao = (UserDao) DaoFactory.getInstance().getDao(JdbcUserDao.class);
		
		// 회원가입 테스트
		/*
		User user = new User();
		user.setId("killer");
		user.setName("김킬러");
		user.setPasswd("1111");
		user.setEmail("killer@korea.com");
		user.setTelephone("010-1111-2222");
		user.setJob("기타");
		user.setMessage("I'm a killer!!!");
		
		userDao.create(user);
		System.out.println("회원가입 완료");
		*/
		
		User user = userDao.isMember("bangry", "1111");
		System.out.println(user);
		
		List<User> list =  userDao.listAll();
		System.out.println(list);
		
		list = userDao.listByPage(1);
		System.out.println(list);
		
		Params params = new Params(1, "id", "bangry", 10, 1);
		list = userDao.listByParams(params);
		System.out.println(list);
		
		
		
	}

	@Override
	public int deleteUser(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int deleteCheck=0;
		
		try{
			String sql="update users set name = '탈퇴' where id=?";	//게시글 삭제시 완전 삭제가 아닌 업데이트로 상태만 변경해준다.
			con=dataSource.getConnection();
			pstmt=con.prepareStatement(sql);
			con.setAutoCommit(false);
			pstmt.setString(1, id);
			deleteCheck=pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
			}
			throw new MallException("JdbcArticleDao.deleteArticle(int article_id) 실행 중 예외발생", e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
		}
		
		return deleteCheck;
	}
	
}












