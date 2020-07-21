package com.tx.controller;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.tx.mapper.StuMapper;
import com.tx.mapper.UsersMapper;
import com.tx.pojo.Users;
import com.tx.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * @Author tx_li
 * @Date 2020/4/19 11:57
 * @Version 1.0
 */
@RestController
public class HelloController {

    @Resource
    UsersMapper usersMapper;
    @Resource
    StuMapper stuMapper;

    @Resource
    UserService userService;

    @GetMapping("/hello")
    public Object hello() {
        Users users = new Users();
        users.setNickname("ltx");
        Users users1 = usersMapper.selectOne(users);
        System.out.println(users1.getBirthday());
        return "helloWorld!";
    }

    @GetMapping("/getStu")
    public Object getStu(int id) {
        return stuMapper.selectByPrimaryKey(id).getName();
    }

    @PostMapping("/uploadPic")
    public Object uploadPic(MultipartFile file) throws Exception {
        outPut(file.getInputStream());
        return "helloWorld!";
    }

    public void outPut(InputStream jpegFile) throws Exception {
        Metadata metadata = ImageMetadataReader.readMetadata(jpegFile);
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                //格式化输出[directory.getName()] - tag.getTagName() = tag.getDescription()
                System.out.format("[%s] - %s = %s\n",
                        directory.getName()+"----", tag.getTagName()+"----", tag.getDescription());
            }
            if (directory.hasErrors()) {
                for (String error : directory.getErrors()) {
                    System.err.format("ERROR: %s", error);
                }
            }
        }
    }



}
