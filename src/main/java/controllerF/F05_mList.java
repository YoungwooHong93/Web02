package controllerF;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;
import vo.MemberVO;

public class F05_mList implements F04_Controller {

	@Override
	public String doUser(HttpServletRequest request, HttpServletResponse response) {
		// MemberList
		MemberService service= new MemberService();
		List<MemberVO> list = new ArrayList<MemberVO>();
    	list = service.selectList();
    	if ( list!=null ) {
    		request.setAttribute("banana", list);
    	}else {
    		request.setAttribute("message", "~~ 출력 자료가 없습니다 ~~");
    	}
		return "/member/memberList.jsp";
	}
	
} //F05_mList 
