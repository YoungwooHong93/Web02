package controllerF;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//** 서비스 컨트롤러들의 메서드명의 일관성(강제성)과 다형성적용을 위해 작성
//=> 모든 서비스 컨트롤러들은 반드시 구현해야함.

public interface F04_Controller {

	public String doUser(HttpServletRequest request, HttpServletResponse response) ;
	
} // Controller_interface
