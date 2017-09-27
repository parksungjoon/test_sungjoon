package test.kr.or.kosta.shoppingmall;

/** 
 * 테스트를 위한 Application 클래스 정의
 */
public class SomeServiceTest {
	
	SomeServiceImpl service = new SomeServiceImpl();
	
	public void testService(){
		int sum = service.sum(10, 20);
		System.out.println(sum);
	}

	public static void main(String[] args) {
		/*
		SomeServiceImpl service = new SomeServiceImpl();
		int sum = service.sum(10, 20);
		System.out.println(sum);
		*/
		SomeServiceTest test = new SomeServiceTest();
		test.testService();

	}

}
