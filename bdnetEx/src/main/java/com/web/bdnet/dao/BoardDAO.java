package com.web.bdnet.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.bdnet.vo.BoardVO;
import com.web.bdnet.vo.FileVO;

@Service
public class BoardDAO {
	@Autowired
	@Resource(name="sqlSession")
	private SqlSession sqlSession = null;
	
	// 마지막 식별번호를 조회한다.
	public int selectBoardMaxNo() {
		return sqlSession.selectOne("Board.selectBoardMaxNo");
	}
	
	// 하나의 게시글을 입력한다.
	public int insertBoardOne(BoardVO vo) {
		return sqlSession.insert("Board.insertBoardOne", vo);
	}
	
	// 게시글의 파일들을 입력한다.
	public int insertBoardFile(FileVO fVO) {
		return sqlSession.insert("Board.insertBoardFile", fVO);
	}
	
	// 전체 게시글 개수를 조회한다.
	public int selectBoardTotPage(Map<String, Object> map) {
		return sqlSession.selectOne("Board.selectBoardTotPage", map);
	}
	
	// 해당 페이지의 게시글을 조회한다.
	public List<BoardVO> selectBoardList(Map<String, Object> map) {
		return sqlSession.selectList("Board.selectBoardList", map);
	}
	
	// 하나의 게시글 정보를 조회한다.
	public BoardVO selectBoardOne(int no) {
		return sqlSession.selectOne("Board.selectBoardOne", no);
	}
	
	// 이전 게시글 번호를 조회한다.
	public int selectBoardPrev(int no) {
		return sqlSession.selectOne("Board.selectBoardPrev", no);
	}
	
	// 다음 게시글 번호를 조회한다.
	public int selectBoardNext(int no) {
		return sqlSession.selectOne("Board.selectBoardNext", no);
	}
	
	// 하나의 게시글 정보를 수정한다.
	public int updateBoardOne(BoardVO vo) {
		return sqlSession.update("Board.updateBoardOne", vo);
	}
	
	// 조회수를 증가한다.
	public int updateBoardHit(int no) {
		return sqlSession.update("Board.updateBoardHit", no);
	}
	
	// 게시글의 파일들을 조회한다.
	public FileVO selectBoardFile(int no) {
		return sqlSession.selectOne("Board.selectBoardFile", no);
	}
}
