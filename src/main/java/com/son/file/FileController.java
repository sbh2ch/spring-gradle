package com.son.file;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * Created by kiost on 2017-05-16.
 */
@Controller
@RequestMapping("/board")
public class FileController {
    @RequestMapping("/fileDownload")
    public void fileDownload(HttpServletRequest req, HttpServletResponse res) {
        String path = "f:\\fileupload\\";
        String fileName = req.getParameter("fileName");
        String downName = req.getParameter("downName");
        String realPath;
        System.out.println("fileName : " + fileName);

        if (fileName == null || fileName.equals(""))
            fileName = downName;

        try {
            fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
        } catch (Exception e) {
            e.printStackTrace();
        }

        realPath = path + downName.substring(0, 4) + "\\" + downName;
        File file1 = new File(realPath);

        if (!file1.exists())
            return;

        res.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        try {
            OutputStream os = res.getOutputStream();
            FileInputStream fis = new FileInputStream(file1);

            int ncount;
            byte[] bytes = new byte[1024];

            while ((ncount = fis.read(bytes)) != -1) {
                os.write(bytes, 0, ncount);
            }
            fis.close();
            os.close();
        } catch (Exception e) {

        }
    }
}
