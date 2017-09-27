package kr.or.kosta.shoppingmall.common.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * ServletContext 생명주기(생성/소멸) 관련 이벤트 리스너
 * @author 김기정
 */
public class ServletContextLoadListener implements ServletContextListener {
	
	/**
	 * ServletContext생성 이벤트 처리
     * ServletContext가 생성되면(서블릿컨테이너 초기화) 웹 애플리케이션내의
     * 모든 Servlet, JSP, Filter 등이 공유할 수 있는 객체 또는 리소스 생성 및 등록(초기화)
	 */
	public void contextInitialized(ServletContextEvent event)  {
//		System.out.println("[Debug] : ServletContext 생성됨 >>>");
//		DaoFactory2 daoFactory = new DaoFactory2();
		// 모든 서블릿, JSP들이 공유할 수 있도록 ServletContext에 DaoFactory 저장
//		ServletContext servletContext = event.getServletContext();
	}
	
	
	public void contextDestroyed(ServletContextEvent event)  {
//		System.out.println("[Debug] : ServletContext(서블릿컨테이너) 종료됨 >>>");
    }
}
