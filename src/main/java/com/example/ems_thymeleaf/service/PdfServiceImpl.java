package com.example.ems_thymeleaf.service;

import com.example.ems_thymeleaf.entity.Employee;
import com.example.ems_thymeleaf.utils.PdfUtil;
import com.itextpdf.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Service
@Slf4j
public class PdfServiceImpl implements PdfService{
    @Override
    public void exportPdf(HttpServletResponse response,Employee employee) {
        log.info("进入到方法exportPdf()");
        String fileName = "test.pdf";
        try {
            // 1.生成pdf文件
            PdfUtil.createPdf(fileName,employee);
            // 2.下载pdf文件
            downLoadFile(fileName, response);
            // 3.删除临时生成的pdf文件
            deleteFile(new File(fileName));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("生成pdf文件完毕");
    }
    public boolean downLoadFile(String fileName, HttpServletResponse response) {
        boolean flag = false;
        log.info("【下载文件】文件名为：{}", fileName);
        File file = new File(fileName);
        if (!file.exists()) {
            log.error("【下载文件】文件{}不存在", fileName);
            return flag;
        }
        try {
            InputStream in = new FileInputStream(fileName);
            OutputStream outputStream = response.getOutputStream();
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.addHeader("Content-Length", "" + file.length());
            response.setContentType("application/pdf");
            IOUtils.copy(in, outputStream);
            flag = true;
            in.close();
            outputStream.close();
        } catch (Exception e) {
            log.error("【下载文件】下载文件失败，失败信息为{}", e.getMessage());
            return flag;
        }
        return flag;
    }
        public static boolean deleteFile(File file) {
            if (!file.exists()) {
                return false;
            }
            if (file.isDirectory()) {
                String[] children = file.list();
                //递归删除目录中的子目录下
                for (int i=0; i<children.length; i++) {
                    boolean success = deleteFile(new File(file, children[i]));
                    if (!success) {
                        return false;
                    }
                }
            }
            // 目录此时为空，可以删除
            return file.delete();
        }
}
