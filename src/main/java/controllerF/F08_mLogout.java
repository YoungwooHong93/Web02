package controllerF;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberService;
import vo.MemberVO;

public class F08_mLogout implements F04_Controller {
	
	@Override
	public String doUser(HttpServletRequest request, HttpServletResponse response) {
		// ** session 인스턴스 정의 후 삭제하기
    	// => 매개변수: 없거나, true, false
    	// => false : session 이 없을때 null 을 return;
		HttpSession session = request.getSession(false);
    	if (session!=null) session.invalidate();
    	String uri="/index.jsp";
    	request.setAttribute("message", "~~ 로그아웃 되었습니다 ~~"); 
		return uri;
	} //doUser 

} //class
