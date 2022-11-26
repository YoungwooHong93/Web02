package service;

import java.util.List;

import util.BoardDAO;
import vo.BoardVO;

public class BoardService {
	BoardDAO dao = new BoardDAO();
	
	// ** selectList
	public List<BoardVO> selectList() {
		return dao.selectList();
	}
	// ** selectOne
	public BoardVO selectOne(BoardVO vo) {
		return dao.selectOne(vo);
	}
	
	// ** Insert
	public int insert(BoardVO vo) {
		return dao.insert(vo);
	}
	
	// ** Update
	public int update(BoardVO vo) {
		return dao.update(vo);
	}
	
	// ** Delete
	public int delete(BoardVO vo) {
		return dao.delete(vo);
	}
	
	// ** 조회수 증가
	public int countUp(BoardVO vo) {
		return dao.countUp(vo);
	}
	
	// ** 답글 등록
	public int rinsert(BoardVO vo) {
		return dao.rinsert(vo);
	}
} //class
