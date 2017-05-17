package com.son.reply;

import com.son.board.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;

/**
 * Created by kiost on 2017-05-17.
 */
@Service
public class ReplyService {
    @Autowired
    private ReplyDao replyDao;

    @Autowired
    private DataSourceTransactionManager txManager;

    public ArrayList<ReplyVo> selectReplyListByBrdNo(ReplyVo replyVo) {
        return (ArrayList<ReplyVo>) replyDao.selectReplyListByBrdNo(replyVo);
    }

    public ArrayList<ReplyVo> insertReply(ReplyVo replyVo) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(def);

        try {
            if (replyVo.getReNo() == null || "".equals(replyVo.getReNo())) {
                if (replyVo.getReParent() != null) {
                    ReplyVo parentReply = replyDao.selectParentReply(replyVo.getReParent());
                    replyVo.setReDepth(parentReply.getReDepth() + 1);
                    replyVo.setReOrder(parentReply.getReOrder());
                    replyDao.updateReplyOrder(parentReply);
                }
            } else {
                Integer reOrder = replyDao.selectReplyMaxOrder(replyVo.getBrdNo());
                replyVo.setReOrder(reOrder);
            }
            replyDao.insertReply(replyVo);

            txManager.commit(status);
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            txManager.rollback(status);
        }


        return (ArrayList<ReplyVo>) replyDao.selectReplyListByBrdNo(replyVo);
    }

    public ArrayList<ReplyVo> deleteReply(ReplyVo replyVo) {
        if(replyDao.selectChildReply(replyVo) <= 0)
            replyDao.deleteReply(replyVo);

        return (ArrayList<ReplyVo>) replyDao.selectReplyListByBrdNo(replyVo);
    }
}
