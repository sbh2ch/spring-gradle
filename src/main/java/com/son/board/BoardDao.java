package com.son.board;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kiost on 2017-05-11.
 */
@Repository
public class BoardDao {
    @Autowired
    private SqlSession sqlSession;

    public List<BoardVo> selectBoardList() {
        return sqlSession.selectList("board.selectBoardList");
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
}
