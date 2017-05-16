package com.son.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(BoardService.class);

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
            LOGGER.info("brdno : " + brdNo);
            boardVo = boardDao.selectBoardOne(brdNo);
            boardDao.hitUpBoard(brdNo);
            txManager.commit(status);
        } catch (Exception e) {
            LOGGER.error("조회오류 : " + e.toString());
            txManager.rollback(status);
        }
        return boardVo;
    }
}
