package com.son.board;

import com.son.file.FileVO;
import com.son.util.Search;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by kiost on 2017-05-11.
 */
@Repository
public class BoardDao {
    @Autowired
    private SqlSession sqlSession;

    public List<BoardVo> selectBoardList(Search search) {
        return sqlSession.selectList("board.selectBoardList", search);
    }

    public BoardVo selectBoardOne(String brdNo) {
        return sqlSession.selectOne("board.selectBoardOne", brdNo);
    }

    public void hitUpBoard(String brdNo) {
        sqlSession.update("board.hitUpBoard", brdNo);
    }

    public void insertBoard(BoardVo boardVo) {
        sqlSession.insert("board.insertBoard", boardVo);
    }

    public void updateBoard(BoardVo boardVo) {
        sqlSession.update("board.updateBoard", boardVo);
    }

    public void deleteBoard(String brdNo) {
        sqlSession.update("board.deleteBoard", brdNo);
    }

    public void deleteFile(Map<String, String[]> fparam) {
        sqlSession.delete("board.deleteFile", fparam);
    }

    public void insertFile(FileVO f) {
        sqlSession.insert("board.insertFile", f);
    }

    public List<FileVO> selectFileList(String brdNo) {
        return sqlSession.selectList("board.selectFileList", brdNo);
    }

    public int selectBoardCount(Search search) {
        return sqlSession.selectOne("board.selectBoardCount", search);
    }
}