package com.son.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

/**
 * Created by kiost on 2017-05-11.
 */
@Service
public class BoardService {
    @Autowired
    private BoardDao boardDao;

    @Autowired
    private DataSourceTransactionManager txManager;

    public List<BoardVo> selectBoardList() {
        return boardDao.selectBoardList();
    }

    public BoardVo readBoardOne(String brdNo) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(def);
        BoardVo boardVo = null;

        try {
            boardVo = boardDao.selectBoardOne(brdNo);
            if (boardVo == null) {
                txManager.rollback(status);
            }
            boardDao.hitUpBoard(brdNo);

            txManager.commit(status);
        } catch (Exception e) {
            System.out.println("조회 오류 : " + e.toString());
            txManager.rollback(status);
        }
        return boardVo;
    }
}
