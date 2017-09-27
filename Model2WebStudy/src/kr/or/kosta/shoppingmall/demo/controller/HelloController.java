package kr.or.kosta.shoppingmall.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.shoppingmall.common.controller.Controller;
import kr.or.kosta.shoppingmall.common.controller.ModelAndView;

/**
 * FrontController에서 일관된(표준화된) 실행메소드 호출이 가능토록 커맨드 패턴 적용한 세부 컨트롤러
 * /hello 요청에 대한 컨트롤러
 * @author 김기정
 *
 */
public class HelloController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)	throws ServletException {
		ModelAndView mav = new ModelAndView();
		//String message = xxxService.bizMethod();
		String message = "모델2 기반 웹애플리케이션 개발";
		
		List<String> list = new ArrayList<String>();
		list.add("Doosan 타이거즈");
		list.add("LG 베어즈");
		list.add("Samsung 트윈즈");
		
		mav.addObject("message", message);
		mav.addObject("list", list);
		mav.setView("/demo/hello.jsp");
//		mav.setView("redirect:http://www.naver.com");
//		mav.setView("redirect:"+request.getContextPath()+"/demo/hello.jsp");
//		mav.setView("redirect:"+request.getContextPath()+"/demo/today.mall");
		return mav;
	}

}




