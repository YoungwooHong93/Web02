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


//** FrontController 1.
//=> Basic 
//=> 대표 컨트롤러 1개만 서블릿으로 작성
//   나머지 컨트롤러는 일반 클래스로 작성
//=> 모든 요청을 이 대표컨트롤러(FrontController) 가 받도록 함.

//=> 요청에 대한 서비스를 if문으로 서블릿내에서 모두 처리
//   코드가독성, 모듈의 재사용성 떨어짐 
//=> Factory 패턴
//   각각의 서비스를 일반클래스(컨트롤러)로 작성 Factory로부터 제공받음 

//=> @ 방식 또는 web.xml 방식  
//@WebServlet(urlPatterns={"*.do"})
public class F01_FrontBasic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public F01_FrontBasic() {
        super();
    }
    
    MemberService service= new MemberService();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
		// => url 또는 uri 분석해서 요청명 확인
    	// => 한글처리 
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		// url : http://localhost:8080/Web02/mlist.do
    	// uri : /Web02/mlist.do
		// mappingName : /mlist.do
		String mappingName = uri.substring(uri.lastIndexOf("/")) ;
		System.out.println("** URL => "+ request.getRequestURL());
		System.out.println("** URI => "+ uri);
		System.out.println("** mappingName => "+ mappingName);
		
		String message = "~~ FrontController Basic Test ~~" ; 
		uri = "index.jsp" ;   // viewName
		// 2. 해당서비스 실행
		if ("/mlist.do".equals(mappingName)) {
			// MemberList
			List<MemberVO> list = new ArrayList<MemberVO>();
	    	list = service.selectList();
	    	if ( list!=null ) {
	    		request.setAttribute("banana", list);
	    	}else {
	    		request.setAttribute("message", "~~ 출력 자료가 없습니다 ~~");
	    	}
	    	uri = "/member/memberList.jsp";
		}else if ("/mdetail.do".equals(mappingName)) {
			// MemberDetail
			MemberVO vo = new MemberVO();
			vo.setId(request.getParameter("id"));
			vo=service.selectOne(vo);
			if (vo != null) {
				request.setAttribute("apple", vo);
			}else {
				message="~~ "+request.getParameter("id")+"님의 자료는 존재하지 않습니다 ~~";
				request.setAttribute("message", message); 
			}	
			uri = "/member/memberDetail.jsp";
		}
		
		// 3. 결과(View -> forward) 처리 
    	request.getRequestDispatcher(uri).forward(request, response);
		
	} //doGet 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
} // class
