package com.son.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by kiost on 2017-05-16.
 */
public class FileUtil {
    public List<FileVO> saveAllFiles(List<MultipartFile> upfiles) {
        String filePath = "f:\\fileupload\\";
        List<FileVO> fileList = new ArrayList<>();

        for (MultipartFile file : upfiles) {
            if (file.getSize() == 0)
                continue;

            String newName = getNewName();

            saveFile(file, filePath, "/" + newName.substring(0, 4) + "/" + newName);

            FileVO fileVO = new FileVO();
            fileVO.setFileName(file.getOriginalFilename());
            fileVO.setRealName(newName);
            fileVO.setFileSize(file.getSize());
            fileList.add(fileVO);
        }

        return fileList;
    }

    public void saveFile(MultipartFile file, String basePath, String fileName) {
        if (file == null || file.getName().equals("") || file.getSize() < 1) {
            return;
        }

        makeBasePath(basePath);

        String serverFullPath = basePath + fileName;
        File saveFile = new File(serverFullPath);

        try {
            file.transferTo(saveFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void makeBasePath(String path) {
        File dir = new File(path);
        if (!dir.exists())
            dir.mkdirs();
    }

    public String getNewName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
        return sdf.format(new Date()) + new Random().nextInt(10);
    }
}
