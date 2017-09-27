package kr.or.kosta.shoppingmall.user.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.shoppingmall.common.controller.Controller;
import kr.or.kosta.shoppingmall.common.controller.ModelAndView;
import kr.or.kosta.shoppingmall.user.domain.User;
import kr.or.kosta.shoppingmall.user.service.UserService;
import kr.or.kosta.shoppingmall.user.service.UserServiceImpl;

/**
 * 회원 가입 처리
 * 
 * /user/join 요청에 대한 세부 컨트롤러
 * @author 김기정
 *
 */
public class UserJoinController implements Controller {
	
	private UserService userService = new UserServiceImpl();

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)	throws ServletException {
		ModelAndView mav = new ModelAndView();
		System.out.println("실행됨...");
		
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String job = request.getParameter("job");
		String message = request.getParameter("message");
		
		// JavaBean 생성
		User user = new User();
		user.setId(id);
		user.setPasswd(passwd);
		user.setName(name);
		user.setEmail(email);
		user.setTelephone(telephone);
		user.setJob(job);
		user.setMessage(message);

		userService.regist(user);
		
		mav.addObject("user", user);
		mav.setView("/user/regist_result.jsp");
		
		return mav;
	}

}




