package kr.or.kosta.shoppingmall.user.controller;

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
public class UserListController implements Controller {
	
	private UserService userService = new UserServiceImpl();
//	private UserService userService ;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)	throws ServletException {
		ModelAndView mav = new ModelAndView();
		
		int pageSize = 10;
		int pageNum = 10;
		
		String page = request.getParameter("page");
		if(page == null)  page = "1";
		int pageCount = Integer.parseInt(page);
		String type = request.getParameter("type");
		String value = request.getParameter("value");
		
		Params params = new Params();
		params.setPage(pageCount);
		params.setPageSize(pageSize);
		params.setPageNum(pageNum);
		params.setType(type);
		params.setValue(value);
		
		
//		List<User> list = userService.listAll();
		
		// 페이징 및 검색처리
		List<User> list = userService.listByParams(params);
		int rowCount = userService.pageCount(params);
		
		// 페이징 계산 유틸리티 생성 및 실행
		PageBuilder pageBuilder = new PageBuilder(params, rowCount);
	    pageBuilder.build();
		
		mav.addObject("list", list);
		mav.addObject("params", params);
		mav.addObject("pageBuilder", pageBuilder);
		
//		mav.setView("/user/list_v1.jsp");
		mav.setView("/user/list_v2.jsp");
		
		return mav;
	}

}




