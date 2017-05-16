package com.son.board;

import com.son.file.FileUtil;
import com.son.file.FileVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.HashMap;
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


    public void writeBoard(BoardVo boardVo, String[] fileNo) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(def);
        String brdNo = boardVo.getBrdNo();

        List<FileVO> fileList = new FileUtil().saveAllFiles(boardVo.getUploadFiles());

        try {
            if (brdNo == null || brdNo.equals("")) {
                boardDao.insertBoard(boardVo);
            } else {
                boardDao.updateBoard(boardVo);
            }

            if (fileNo != null) {
                HashMap<String, String[]> fparam = new HashMap<>();
                fparam.put("fileNo", fileNo);
                boardDao.deleteFile(fparam);
            }

            for (FileVO f : fileList) {
                f.setParentPK(boardVo.getBrdNo());
                boardDao.insertFile(f);
            }

            txManager.commit(status);
        } catch (Exception e) {
            txManager.rollback(status);
        }
    }

    public void deleteBoard(BoardVo boardVo) {
        boardDao.deleteBoard(boardVo.getBrdNo());
    }

    public List<FileVO> selectFileList(String brdNo) {
        return boardDao.selectFileList(brdNo);
    }
}
