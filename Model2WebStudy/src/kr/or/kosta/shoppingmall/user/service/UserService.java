package kr.or.kosta.shoppingmall.user.service;

import java.util.List;

import kr.or.kosta.shoppingmall.common.web.Params;
import kr.or.kosta.shoppingmall.user.domain.User;

/**
 * Domain(업무영역)별 고객의 요구사항을 반영하는 비즈니스 메소드 선언
 * 
 * @author 김기정
 *
 */
public interface UserService {
	
	/** 회원 목록 반환 */
	public List<User> listAll();
	
	/** 신규 사용자 등록 */
	public void regist(User user);
	
	/** 회원 여부 반환 */
	public User isMember(String id, String passwd);
	
	/** {선택페이지, 검색유형, 검색값, 한페이지당 출력 행수}에 대한 회원목록 반환 */
	public List<User> listByParams(Params params);
	
	/** 출력페이지 계산을 위한 {검색유형, 검색값}에 대한 행의 수 반환 */
	public int pageCount(Params params);
	
	public User read(String id);

	public int deleteUser(String id);
}
