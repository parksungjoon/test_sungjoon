package kr.or.kosta.shoppingmall.common.db;

import java.lang.reflect.Method;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import kr.or.kosta.shoppingmall.common.exception.MallException;

/**
 * #1. Dao 인스턴스 생성
 * #2. DataSource 인스턴스 생성
 * #3. Dao에 DataSource 설정
 * #4. DataSource가 설정된 Dao 반환
 * 서블릿컨텍스트로드리스너에 의해 ServletContext에 생성 및 공유
 * 
 * @author 김기정
 */
public class DaoFactory2 {
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521";
	private static final String USER = "blog";
	private static final String PASSWORD = "blog";
	private static final int INIT_SIZE = 5;
	private static final int MAX_SIZE = 10;
	
	private BasicDataSource dataSource;
	
	public DaoFactory2() {
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName(DRIVER);
		dataSource.setUrl(URL);
		dataSource.setUsername(USER);
		dataSource.setPassword(PASSWORD);
		dataSource.setInitialSize(INIT_SIZE);
		dataSource.setMaxTotal(MAX_SIZE);
	}
	
	public BasicDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(BasicDataSource dataSource) {
		this.dataSource = dataSource;
	}

	/*
	public UserDao getUserDao() {
		UserDao dao = new JdbcUserDao(dataSource);
		return dao;
	}
	
	public ArticleDao getArticleDao() {
		ArticleDao dao = new JdbcArticleDao(createDataSource());
		return dao;
	}
	.
	.
	.
	*/
	
	/**
	 * 단일화된 Dao 요청 창구
	 */
	public Object getDao(String className) {
		Object dao = null;
		try {
			Class cls = Class.forName(className);
			dao = cls.newInstance();
			
			//JdbcXXXDao dao = (JdbcXXXDao)dao;
			//dao.setDataSource(dataSource);
			
			// 동적 메소드호출
			Method method =  cls.getMethod("setDataSource", DataSource.class);
			method.invoke(dao, dataSource);
			
		} catch (Exception e) {
			throw new MallException("DaoFactory.getDao(String className) 실행 중 예외발생", e);
		}
		return dao;
	}
	
	public Object getDao(Class cls) {
		return getDao(cls.getName());
	}
}
