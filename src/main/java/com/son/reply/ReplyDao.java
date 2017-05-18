package com.son.reply;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kiost on 2017-05-17.
 */
@Repository
public class ReplyDao {
    @Autowired
    private SqlSession sqlSession;


    public List<ReplyVo> selectReplyListByBrdNo(ReplyVo replyVo) {
        return sqlSession.selectList("reply.selectReplyByBrdNo", replyVo);
    }

    public ReplyVo selectParentReply(String reParent) {
        return sqlSession.selectOne("reply.selectParentReply", reParent);
    }

    public void updateReplyOrder(ReplyVo parentReply) {
        sqlSession.update("reply.updateReplyOrder", parentReply);
    }

    public Integer selectReplyMaxOrder(String brdNo) {
        return sqlSession.selectOne("reply.selectMaxOrder", brdNo);
    }

    public void insertReply(ReplyVo replyVo) {
        sqlSession.insert("reply.insertReply", replyVo);
    }

    public int selectChildReply(ReplyVo replyVo) {
        return sqlSession.selectOne("reply.selectChildReply", replyVo);
    }

    public void deleteReply(ReplyVo replyVo) {
        sqlSession.update("reply.updateReplyOrder4Delete", replyVo);
        sqlSession.update("reply.deleteReply", replyVo);
    }

    public void updateReply(ReplyVo replyVo) {
        sqlSession.update("reply.updateReply", replyVo);
    }
}
