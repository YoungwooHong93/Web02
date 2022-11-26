package controllerB;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import vo.BoardVO;

@WebServlet("/rinsert")
public class C06_rInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public C06_rInsert() {
		super();
	}
	
	// ** 답글 등록
	// => 성공 : blist
	//	  실패 : 재입력 유도 (rinsertForm)
	// => set vo
	//		- root: 부모와 동일
	//		- step: 부모(step) + 1
	//		- indent: 부모 indent + 1
	//		- 그러므로 rinsertForm 에 부모값을 보관(hidden으로)해서 전달받음
	//		  이를 위해 boardDetail 에서 요청시 쿼리스트링으로 전달 -> rinsertf

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
		// ** root, step, indent 처리
		vo.setRoot(Integer.parseInt(request.getParameter("root")));
		vo.setStep(Integer.parseInt(request.getParameter("step"))+1);
		vo.setIndent(Integer.parseInt(request.getParameter("indent"))+1);
		
		
		// 2. Service 처리
		if ( service.rinsert(vo) > 0 ) {
			// 0보다 크면 글 등록 성공 : blist
			request.setAttribute("message", "답글 작성 완료.");
		} else {
			// 0보다 작으면 글 등록 실패 : rinsertForm
			request.setAttribute("message", "답글 작성 실패. 다시 작성하세요.");
			uri = "/board/rinsertForm.jsp";
		}
		
		// 3. 결과(View -> forward) 처리
		request.getRequestDispatcher(uri).forward(request, response);
	} //doPost

} //class
