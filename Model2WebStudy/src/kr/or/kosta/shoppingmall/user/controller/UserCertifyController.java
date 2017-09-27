package kr.or.kosta.shoppingmall.user.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.shoppingmall.common.controller.Controller;
import kr.or.kosta.shoppingmall.common.controller.ModelAndView;
import kr.or.kosta.shoppingmall.user.domain.User;
import kr.or.kosta.shoppingmall.user.service.UserService;
import kr.or.kosta.shoppingmall.user.service.UserServiceImpl;

/**
 * 회원 로그인/로그아웃 처리
 * 
 * /user/certify 요청에 대한 세부 컨트롤러
 * @author 김기정
 *
 */
public class UserCertifyController implements Controller {
	
	private UserService userService = new UserServiceImpl();

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)	throws ServletException {
		ModelAndView mav = new ModelAndView();
		String location = "/index.mall";
		
		// 로그인
		if(request.getMethod().equalsIgnoreCase("post")) {
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");
			String referer = request.getParameter("referer");

			User user = userService.isMember(id, passwd);
			if(user != null){
			    String userInfo = null;
				try {
					userInfo = URLEncoder.encode(user.getId() + "," + user.getName(), "utf-8");
				} catch (UnsupportedEncodingException e) {
					throw new ServletException("UserAuthController.handleRequest() 실행중 예외 발생", e);
				}
				Cookie loginCookie = new Cookie("user", userInfo);
				loginCookie.setPath("/");
				response.addCookie(loginCookie);
			    if(referer != null)  location = referer;
			}else{
			  location = "/user/login.mall";
			}
		}else {// 로그아웃
			String user = null;
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equalsIgnoreCase("user")) {
						cookie.setMaxAge(0);
						cookie.setPath("/");
						response.addCookie(cookie);
						break;
					}
				}
			}
		}
		mav.setView("redirect:"+request.getContextPath() + location);
		return mav;
	}
	
}




