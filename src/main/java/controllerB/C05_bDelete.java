package controllerB;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import vo.BoardVO;

@WebServlet("/bdelete")
public class C05_bDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public C05_bDelete() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
		// => Service 준비
		//	  삭제성공 : /blist , 	삭제실패 : /bdetail
		String uri = "/blist";
		BoardService service = new BoardService();
		BoardVO vo = new BoardVO();
		
		// 2. Service 처리
		// => Service 에서 selectOne
		vo.setSeq(Integer.parseInt(request.getParameter("seq")));
		
		if ( service.delete(vo) > 0 ) {
			// 삭제 성공
			request.setAttribute("message", "=> 게시물 삭제를 완료하였습니다.");
		} else { // 삭제 실패
			request.setAttribute("message", "=> 게시물 삭제를 실패하였습니다. 다시 시도하세요.");
			uri = "/bdetail?seq="+vo.getSeq();
		}

		// 3. 결과(View -> forward) 처리
		request.getRequestDispatcher(uri).forward(request, response);
	} //doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	} //doPost

} //class
