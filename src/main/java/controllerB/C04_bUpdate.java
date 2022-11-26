package controllerB;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import vo.BoardVO;

@WebServlet("/bupdate")
public class C04_bUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public C04_bUpdate() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
		// => Service 준비
		// => request, 한글처리
		//	  한글처리 (utf-8 이면 get 방식은 안해도 되지만, post 방식은 반드시 해야함_getParameter 하기전에)
		//	  Parameter -> vo에 set
		request.setCharacterEncoding("UTF-8");
		BoardVO vo = new BoardVO();
		BoardService service = new BoardService();
		String uri = "/board/boardDetail.jsp";
		
		vo.setSeq(Integer.parseInt(request.getParameter("seq")));
		vo.setId(request.getParameter("id"));
		vo.setTitle(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));
		vo.setRegdate(request.getParameter("regdate"));
		vo.setCnt(Integer.parseInt(request.getParameter("cnt")));
		
		request.setAttribute("apple", vo);
		// => 수정 후 결과 View 출력시 사용하기 위함.
		//	  수정하지 않는 값들도 전달받아 setAttribute
		
		// 2. Service 처리
		if ( service.update(vo) > 0 ) {
			// 0보다 크면 글 수정 성공 : boardDetail.jsp
			request.setAttribute("message", "게시물 수정 완료.");
		} else {
			// 0보다 작으면 글 수정 실패 : bupdateForm.jsp
			request.setAttribute("message", "게시물 수정 실패. 다시 작성하세요.");
			uri = "/board/bupdateForm.jsp";
		}
		
		// 3. 결과(View -> forward) 처리
		request.getRequestDispatcher(uri).forward(request, response);
	} //doPost

} //class
