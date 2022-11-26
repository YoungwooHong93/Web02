package controllerF;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;
import vo.MemberVO;


//** FrontController 2.
//=> Factory 패턴 적용
// - ServiceFactory
// - 개별컨트롤러(일반클래스) : 일관성을 위해 강제성 부여 ( interface 사용 )

//=> @ 방식 또는 web.xml 방식  
//@WebServlet(urlPatterns={"*.do"})
public class F02_FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public F02_FrontController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
		// => url 또는 uri 분석해서 요청명 확인
    	// => 한글처리 
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String mappingName = uri.substring(uri.lastIndexOf("/")) ;
		uri = "index.jsp" ;   // viewName
		
		// 2. 해당서비스 실행
		// => 요청명에 해당하는 서비스를 ServiceFactory 에 요청
		// => 서비스
		//	-> 요청내용을 실행하고 viewName 을 return 해 주어야함. 
		//	-> 다형성을 적용한 일괄처리를 위해 모든 서비스는 동일조상(interface) 으로 묶여있어야 함 
		
		//F03_ServiceFactory sf = new F03_ServiceFactory() ;
		// ** 싱글톤 적용
		F03_ServiceFactory sf = F03_ServiceFactory.getInstance();
		// ** 싱글톤 Test
		F03_ServiceFactory sf1 = F03_ServiceFactory.getInstance();
		F03_ServiceFactory sf2 = F03_ServiceFactory.getInstance();
		System.out.println("** sf => "+sf);
		System.out.println("** sf1 => "+sf1);
		System.out.println("** sf2 => "+sf2);
				
		F04_Controller controller = sf.getController(mappingName); // 요청명에 해당하는 서비스컨트롤러를 return 
		
		if (controller != null) uri = controller.doUser(request, response) ;
		else request.setAttribute("message", "** "+mappingName+" 은 없는 요청 입니다 ~~") ;
		
		// 3. 결과(View -> forward) 처리 
    	request.getRequestDispatcher(uri).forward(request, response);
		
	} //doGet 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
} // class
