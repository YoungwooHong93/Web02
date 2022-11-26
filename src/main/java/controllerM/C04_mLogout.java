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

@WebServlet("/mlogout")
public class C04_mLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public C04_mLogout() {
		super();
	}
	
	// ** Logout
	// => session 삭제(무효화) 후 index(첫화면) 로
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ** session 정의 후 삭제(무효화)
		// => 매개변수 : 없거나, true, false
		// => false : session 이 없을때 null 을 return;
		
		HttpSession session = request.getSession();
		session.invalidate();
		String uri = "/index.jsp";
		request.setAttribute("message", " 로그아웃 되었습니다. ");
		
		request.getRequestDispatcher(uri).forward(request, response);

	} //doGet

} //class
