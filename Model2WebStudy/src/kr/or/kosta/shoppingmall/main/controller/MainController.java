package kr.or.kosta.shoppingmall.main.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.shoppingmall.common.controller.Controller;
import kr.or.kosta.shoppingmall.common.controller.ModelAndView;

/**
 * 메인컨트롤러
 * 
 * /index 요청에 대한 세부 컨트롤러
 * @author 김기정
 *
 */
public class MainController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)	throws ServletException {
		ModelAndView mav = new ModelAndView();
		String title = "모델2 기반 코스타 쇼핑몰";
		mav.addObject("title", title);
		mav.setView("/index.jsp");
		return mav;
	}

}




