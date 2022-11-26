package controllerB;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import vo.BoardVO;

@WebServlet("/binsert")
public class C03_bInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public C03_bInsert() {
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
		String uri = "/blist";
		
		vo.setId(request.getParameter("id"));
		vo.setTitle(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));
		
		// 2. Service 처리
		if ( service.insert(vo) > 0 ) {
			// 0보다 크면 글 등록 성공 : blist
			request.setAttribute("message", "새 게시물 작성 완료.");
		} else {
			// 0보다 작으면 글 등록 실패 : binsertForm
			request.setAttribute("message", "게시물 작성 실패. 다시 작성하세요.");
			uri = "/board/bInsertForm.jsp";
		}
		
		// 3. 결과(View -> forward) 처리
		request.getRequestDispatcher(uri).forward(request, response);
	} //doPost

} //class
