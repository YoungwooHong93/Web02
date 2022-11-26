package controllerB;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import vo.BoardVO;

@WebServlet("/bdetail")
public class C02_bDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public C02_bDetail() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
		// => Service 준비
		
		String uri = "/board/boardDetail.jsp";
		BoardService service = new BoardService();
		BoardVO vo = new BoardVO();
		
		// 2. Service 처리
		// => Service 에서 selectOne
		// => 조회수 증가 : 테이블의 cnt=cnt+1
		// => 조회수 증가조건 
		//	  - 글쓴이와 보는이(loginID)가 다를 때 
		//	  - jCode != U (수정(update)요청이 아닌경우)
		// => 증가 메서드 : DAO, Service 에 countUp 메서드 추가가 필요
		// => 증가시점 : selectOne 성공 후
		
		vo.setSeq(Integer.parseInt(request.getParameter("seq")));
		vo = service.selectOne(vo);
		if (vo != null ) {
			// ** 조회수 증가 추가
			// => 로그인 id 확인을 해야함.
			String loginID = (String)request.getSession().getAttribute("loginID");
			if ( !vo.getId().equals(loginID) && !"U".equals(request.getParameter("jCode")) ) {
				// => 조회수 증가
				if ( service.countUp(vo) > 0 ) vo.setCnt(vo.getCnt()+1);
			} //if_증가조건
			
			request.setAttribute("apple", vo);
			// ** Update 요청이면 updateForm.jsp 로
			// => 주의사항 : Parameter "jCode" 가 없는 경우에는 null return
			//	  NullPointerException 발생하지 않도록 주의
			if ( "U".equals(request.getParameter("jCode")))
				uri = "/board/bupdateForm.jsp";
		} else { // 없는 ID
			request.setAttribute("message", "=> 글 번호에 해당하는 게시물이 없습니다.");
		}

		// 3. 결과(View -> forward) 처리
		request.getRequestDispatcher(uri).forward(request, response);
	} //doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	} //doPost

} //class
