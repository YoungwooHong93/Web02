package controllerM;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.taglibs.standard.tag.common.fmt.RequestEncodingSupport;

import service.MemberService;
import vo.MemberVO;

@WebServlet("/mdetail")
public class C02_mDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public C02_mDetail() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
		// => Service 준비
		// => 요청 id 가 존재해야됨 (login 정보를 이용 또는 Parameter 이용)
		// => 요청 구분 (내정보 보기 : session.getAttribute... or memberList : getParameter...
		String id="";
		if (request.getParameter("id") != null) 
			id = request.getParameter("id");
		else {
			// session 정의, getAttribute
			// => 반드시 존재하는 session 에서 전달 받아야함
			//	  그러므로 이 시점에 session 이 존재하지 않으면 null 을 return 해야함.
			//	  -> request.getSession() 와 getSession(true) 는 없으면 생성 후 return
			//		 그러므로 null 을 return 하지않음.
			//	  -> request.getSession(false) 는 없으면 null return
			//		 session 이 null 인지 반드시 확인 후 사용해야함. 
			HttpSession session = request.getSession(false);
			if ( session != null && session.getAttribute("loginID") != null ) {
				id = (String)session.getAttribute("loginID");
			} else {
				request.setAttribute("message", "=> 출력할 id 가 없음. 로그인 후 이용하십시오.");
			}
		} //getParameter_else
		
		String uri = "/member/memberDetail.jsp";
		MemberService service = new MemberService();
		MemberVO vo = new MemberVO();
		// 2. Service 처리
		// => Service 에서 selectOne
		vo.setId(id);
		vo = service.selectOne(vo);
		if (vo != null ) {
			request.setAttribute("apple", vo);
			// ** Update 요청이면 updateForm.jsp 로
			// => 주의사항 : Parameter "jCode" 가 없는 경우에는 null return
			//	  NullPointerException 발생하지 않도록 주의
			if ( "U".equals(request.getParameter("jCode")))
				uri = "member/updateForm.jsp";
			
		} else { // 없는 ID
			request.setAttribute("message", "=> 없는 iD, 정확한 ID를 요청하세요.");
		}

		// 3. 결과(View -> forward) 처리
		request.getRequestDispatcher(uri).forward(request, response);
	} //doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	} //doPost

} //class
