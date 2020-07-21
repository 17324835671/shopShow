package com.tx.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @Author tx_li
 * @Date 2020/7/20 22:47
 * @Version 1.0
 */
@RestController
public class FileUploadController {

    @RequestMapping("/uoloadNIO")
    public String uploadNIO(@RequestParam(value = "file", required = true) MultipartFile file) throws IOException {
        String filePath = null;
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1);
        // 设置文件存储路径
        filePath = "D://test/" + UUID.randomUUID() + "." + suffixName;
        byte[] bytes = file.getBytes();
        Path path = Paths.get(filePath);
        //保存在本地
        Files.write(path, bytes);
        return "success";
    }

    @RequestMapping("/uoloadBIO")
    public String uploadBIO(@RequestParam(value = "file", required = true) MultipartFile file) throws IOException {
        String filePath = null;
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1);
        // 设置文件存储路径
        filePath = "D://test/" + UUID.randomUUID() + "." + suffixName;
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(filePath));
        return "success";
    }
}
