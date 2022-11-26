package controllerF;

import java.util.HashMap;
import java.util.Map;

//** ServiceFactory
//=> FrontController 가 요청한 서비스 컨트롤러 클래스를 생성후 제공

//** Map 적용
//=> HashMap 객체 정의 : 전역변수 (맴버)
//=> 자료 put : 생성자에 정의

//** 싱글톤 패턴 : 인스턴스를 단 1개만 허용
//=> 일반적인 경우 : 복수의 인스턴스 가능 
//    Student s1 = new Student();
//    Student s2 = new Student();
//=> 클래스 외부에서 인스턴스를 생성할 수 없도록 제한  

//** 방법
//=> 생성자를 private 으로 내부에서만 사용가능하도록하고
//=> 내부에서 getInstance() 메서드로 생성 제공해줌 
//=> 외부에서는 getInstance() 메서드를 통해서만 사용

public class F03_ServiceFactory {
	
	// ** Map 적용하기
	Map<String, F04_Controller> mappings;
	
	// ** 생성자를 private 으로 내부에서만 사용가능
	private F03_ServiceFactory() {
		mappings = new HashMap<String, F04_Controller>();
		mappings.put("/mlist.do", new F05_mList());
		mappings.put("/mdetail.do", new F06_mDetail());
		mappings.put("/login.do", new F07_mLogin());
		mappings.put("/logout.do", new F08_mLogout());
	} //생성자
	
	// ** 싱글톤 적용
	private static F03_ServiceFactory instance = new F03_ServiceFactory();
	public static F03_ServiceFactory getInstance() { return instance; }
									// return new F03_ServiceFactory();
									//  => 싱글톤 적용되지않음 : 메서드 호출시마다 생성됨
	
	public F04_Controller getController(String mappingName) {
	// => 요청명(mappingName)에 해당되는 서비스를 바로 사용가능하도록 생성후 전달	
	
	// ** ver01
	/*	if ("/mlist.do".equals(mappingName)) {
			return new F05_mList();
		}else if ("/mdetail.do".equals(mappingName)) {
			return new F06_mDetail();
		}else if ("/login.do".equals(mappingName)) {
			return new F07_mLogin();
		}else if ("/logout.do".equals(mappingName)) {
			return new F08_mLogout();
		}
		return null;
		*/
	// ** ver02_Map 적용			
		return mappings.get(mappingName);
	} //getController
	

} //F03_ServiceFactory 
