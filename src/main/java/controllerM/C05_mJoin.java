package controllerM;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;
import vo.MemberVO;

@WebServlet("/mjoin")
public class C05_mJoin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public C05_mJoin() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
		// => Service 준비
		// => request, 한글처리
		//	  한글처리 (utf-8 이면 get 방식은 안해도 되지만, post 방식은 반드시 해야함_getParameter 하기전에)
		//	  Parameter -> vo에 set
		
		request.setCharacterEncoding("UTF-8");
		MemberVO vo = new MemberVO();
		MemberService service = new MemberService();
		String uri = "/member/loginForm.jsp";
		
		vo.setId(request.getParameter("id"));
		vo.setPassword(request.getParameter("password"));
		vo.setName(request.getParameter("name"));
		vo.setInfo(request.getParameter("info"));
		vo.setBirthday(request.getParameter("birthday"));
		vo.setJno(Integer.parseInt(request.getParameter("jno")));
		vo.setAge(Integer.parseInt(request.getParameter("age")));
		vo.setPoint(Double.parseDouble(request.getParameter("point")));
		
		// 2. Service 처리
		if ( service.insert(vo) > 0 ) {
			// 0보다 크면 join 성공 : 로그인 유도 loginForm
			request.setAttribute("message", "회원가입 성공. 로그인 후 이용하세요.");
			
		} else {
			// 0보다 작으면 join 실패 : joinForm
			request.setAttribute("message", "회원가입 실패. 다시 시도하세요.");
			uri = "/member/joinForm.jsp";
		}

		// 3. 결과(View -> forward) 처리
		request.getRequestDispatcher(uri).forward(request, response);
	} //doPost

} //class
