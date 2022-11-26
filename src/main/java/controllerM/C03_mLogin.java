package controllerM;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.taglibs.standard.tag.common.fmt.RequestEncodingSupport;

import service.MemberService;
import vo.MemberVO;

@WebServlet("/mlogin")
public class C03_mLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public C03_mLogin() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
		// => request 처리, Service 준비

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String uri = "/member/loginForm.jsp";
		MemberService service = new MemberService();
		MemberVO vo = new MemberVO();

		// 2. Service 처리
		// => Service 에서 selectOne
		//	  => id, password 오류 구분 가능
		//	  => DAO, Service 에 메서드 추가하지 않아도 됨.
		//	  => password 암호화 적용된 경우에도 비교 가능함.
		vo.setId(id);
		vo = service.selectOne(vo);
		if (vo != null ) {
			// ID는 일치, Password 확인
			if( vo.getPassword().equals(password) ) {
				// 로그인 성공 : 로그인 정보 session 보관(로그인 상태를 유지시켜주기 위함), index로
				request.getSession().setAttribute("loginID", id);
				request.getSession().setAttribute("loginName", vo.getName());
				
				uri = "/index.jsp";
			} else {
				request.setAttribute("message", "=> Password 오류, 정확한 Password를 입력하세요.");
			}
		} else { // 없는 ID
			request.setAttribute("message", "=> ID 오류, 정확한 ID를 입력하세요.");
		}
		
		// 3. 결과(View -> forward) 처리
		request.getRequestDispatcher(uri).forward(request, response);


	} //doPost

} //class
