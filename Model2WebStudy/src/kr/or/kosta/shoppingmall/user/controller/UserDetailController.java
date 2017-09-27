package kr.or.kosta.shoppingmall.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.shoppingmall.common.controller.Controller;
import kr.or.kosta.shoppingmall.common.controller.ModelAndView;
import kr.or.kosta.shoppingmall.common.web.PageBuilder;
import kr.or.kosta.shoppingmall.common.web.Params;
import kr.or.kosta.shoppingmall.user.domain.User;
import kr.or.kosta.shoppingmall.user.service.UserService;
import kr.or.kosta.shoppingmall.user.service.UserServiceImpl;

/**
 * 회원리스트 처리
 * 
 * /user/list 요청에 대한 세부 컨트롤러
 * @author 김기정
 *
 */
public class UserDetailController implements Controller {
	
	private UserService userService = new UserServiceImpl();
//	private UserService userService ;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)	throws ServletException {
		User user=null;
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter out=null;
		String id=request.getParameter("id");
		System.out.println("컨트롤러 아이디:"+id);
		try {
			user=userService.read(id);
			out = response.getWriter();
			out.println("{\"id\":\""+user.getId()+"\",\"name\":\""+user.getName()+"\",\"email\":\""+user.getEmail()+"\",\"telephone\":\""+user.getTelephone()+"\",\"job\":\""+user.getJob()+"\",\"message\":\""+user.getMessage()+"\"}");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

}




