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

@WebServlet("/mupdate")
public class C06_mUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public C06_mUpdate() {
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
		String uri = "/member/memberDetail.jsp";
		
		vo.setId(request.getParameter("id"));
		vo.setPassword(request.getParameter("password"));
		vo.setName(request.getParameter("name"));
		vo.setInfo(request.getParameter("info"));
		vo.setBirthday(request.getParameter("birthday"));
		vo.setJno(Integer.parseInt(request.getParameter("jno")));
		vo.setAge(Integer.parseInt(request.getParameter("age")));
		vo.setPoint(Double.parseDouble(request.getParameter("point")));
		
		request.setAttribute("apple", vo);
		// => Update 성공 / 실패 모두 출력시 필요하므로
		
		// 2. Service 처리
		// -> 성공 : 내정보 표시 ( memberDetail.jsp )
		// -> 실패 : 재수정 유도 ( updateForm.jsp )
		
		if ( service.update(vo) > 0 ) {
			// 0보다 크면 update 성공 : 내정보 표시 -> memberDetail.jsp 
			// => "/mdetail" 로 요청 보내기 또는 apple을 setAttribute 후 jsp로 forward 하기
			request.setAttribute("message", "회원정보 수정 성공. 로그인 후 이용하세요.");
			
		} else {
			// 0보다 작으면 update 실패 : updateForm.jsp
			request.setAttribute("message", "회원정보 수정 실패. 다시 시도하세요.");
			uri = "/member/updateForm.jsp";
		}

		// 3. 결과(View -> forward) 처리
		request.getRequestDispatcher(uri).forward(request, response);
	} //doPost

} //class
