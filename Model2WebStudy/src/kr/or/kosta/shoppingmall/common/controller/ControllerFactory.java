package kr.or.kosta.shoppingmall.common.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 * Factory 패턴 적용 ControllerFactory
 * 
 * @author 김기정
 */
public class ControllerFactory {

	// 요청에 대한 세부 컨트롤러 클래스 생성 및 관리
	private HashMap<String, Controller> controllerMap;

/*	public ControllerFactory(String controllerMapperLocation) {
		controllerMap = new HashMap<String, Controller>();

		// 매핑정보를 저장할 Properties 객체 생성
		Properties prop = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(controllerMapperLocation);
			prop.load(fis);
			Iterator keyIter = prop.keySet().iterator();
			System.out.println("--- 컨트롤러 생성 목록 ---");
			while (keyIter.hasNext()) {
				String uri = (String) keyIter.next();
				String controllerClass = prop.getProperty(uri);
				// 컨트롤러 생성
				Controller controllerObject = (Controller) Class.forName(controllerClass).newInstance();
				controllerMap.put(uri, controllerObject);
				System.out.println(uri + "=" + controllerObject);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Controller getController(String uri) {
		return controllerMap.get(uri);
	}*/
	
	public ControllerFactory(String controllerMapperLocation){
		controllerMap = new HashMap<String, Controller>();
		
		try {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		DocumentBuilder parser = factory.newDocumentBuilder();

		String xmlPath = "D:\\KOSTA164\\workspace\\Model2WebStudy\\WebContent\\WEB-INF\\controller-mapper.xml";
		Document document = parser.parse(xmlPath);
		
		// 루트 엘리먼트 취득
		// 특정 엘리먼트 이름으로 엘리먼트 검색
		NodeList beanList = document.getElementsByTagName("bean");
		System.out.println("[디버깅]: book 엘리먼트 갯수: " + beanList.getLength());
		for (int i = 0; i < beanList.getLength(); i++) {
			Element bean = (Element) beanList.item(i);
			String name=bean.getAttribute("name");
			String className=bean.getAttribute("class");
			Controller controllerObject = (Controller) Class.forName(className).newInstance();
			controllerMap.put(name, controllerObject);
			System.out.println(name + "=" + controllerObject);
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public Controller getController(String uri) {
		return controllerMap.get(uri);
	}
}
