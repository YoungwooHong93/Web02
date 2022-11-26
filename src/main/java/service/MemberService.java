package service;

import java.util.List;

import util.MemberDAO;
import vo.MemberVO;

public class MemberService {
	MemberDAO dao = new MemberDAO();
	
	// ** selectList
	public List<MemberVO> selectList() {
		return dao.selectList();
	}
	// ** selectOne
	public MemberVO selectOne(MemberVO vo) {
		return dao.selectOne(vo);
	}
	
	// ** Insert
	public int insert(MemberVO vo) {
		return dao.insert(vo);
	}
	
	// ** Update
	public int update(MemberVO vo) {
		return dao.update(vo);
	}
	
	// ** Delete
	public int delete(MemberVO vo) {
		return dao.delete(vo);
	}
	
} //class
