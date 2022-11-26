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

@WebServlet("/mdelete")
public class C07_mDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public C07_mDelete() {
		super();
	}

	//  ** 삭제 컨트롤러 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
		// => Service 준비
		// => 요청 id 가 존재해야됨 (login 정보를 이용 또는 Parameter 이용)
		String id="";
		HttpSession session = request.getSession(false);
		// => 탈퇴 완료 후 session 삭제를 위해 필요하기 때문에 전역으로 선언해야함.
		
		if (request.getParameter("id") != null) 
			id = request.getParameter("id");
		else {
			// => session, getAttribute
			if ( session != null && session.getAttribute("loginID") != null ) {
				id = (String)session.getAttribute("loginID");
			} else {
				request.setAttribute("message", "=> session 정보 없음. 로그인 후 이용하십시오.");
			}
		} //getParameter_else
		
		String uri = "index.jsp";
		MemberService service = new MemberService();
		MemberVO vo = new MemberVO();
		
		// 2. Service 처리
		vo.setId(id);
		if ( service.delete(vo) > 0 ) {
			// 삭제 성공 
			// => message : 탈퇴 완료 !
			// => session 삭제(무효화)
			if ( session != null ) session.invalidate();
			request.setAttribute("message", "탈퇴 완료, 1개월 후 재가입 가능합니다.");
		} else { // 삭제 실패
			request.setAttribute("message", "탈퇴 실패, 오류 발생시 관리자에게 문의 주세요.");
		}

		// 3. 결과(View -> redirect) 처리
		// => url 을 깔끔하게 하기 위함.
		// response.sendRedirect(uri); // -> 단점 : message 가 나오지 않음.
		// => index.jsp 사용하지 않고 afterDelete.jsp 를 사용.
		uri = "/member/afterDelete.jsp";
		request.getRequestDispatcher(uri).forward(request, response);
		
	} //doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	} //doPost

} //class
