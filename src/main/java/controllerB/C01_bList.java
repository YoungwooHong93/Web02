package controllerB;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import vo.BoardVO;

@WebServlet("/blist")
public class C01_bList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public C01_bList() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
		// => Service 준비
		String uri = "/board/boardList.jsp";
		BoardService service = new BoardService();

		// 2. Service 처리
		List<BoardVO> list = service.selectList();
		if ( list != null ) {
			request.setAttribute("banana", list);
		} else {
			request.setAttribute("message", "출력할 List 가 1건도 없습니다.");
		}

		// 3. 결과(View -> forward) 처리
		request.getRequestDispatcher(uri).forward(request, response);
	} //doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	} //doPost

} //class
