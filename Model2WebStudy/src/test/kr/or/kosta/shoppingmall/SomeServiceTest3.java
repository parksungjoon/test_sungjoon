package test.kr.or.kosta.shoppingmall;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit 4에서 Annotation을 이용하여 Test 클래스를 정의하는 방법
 */
public class SomeServiceTest3 {
	
	SomeServiceImpl service = new SomeServiceImpl();
	
	
	@Before
	public void setUp() throws Exception {
		// 선행 작업(자원할당 등)
		System.out.println("선행작업 수행됨");
	}

	@After
	public void tearDown() throws Exception {
		// 후행 작업(자원해제 등)
		System.out.println("후행작업 수행됨");
	}

	@Test
	public void sumTest() {
		//Assert.assertEquals(20, calculator.sum(10, 10)); //assertEquals(expected, actual);
		assertEquals(20, service.sum(10, 10)); //static import 활용
		System.out.println(service.sum(30, 10));
	}
	
	@Test
	public void getMessageTest() {
		assertNotNull(service.getMessage());
	}

}
