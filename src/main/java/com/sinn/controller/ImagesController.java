package com.sinn.controller;

import com.sinn.pojo.Picture;
import com.sinn.service.PictureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/5/13
 */
@RestController
@Slf4j
public class ImagesController {

    @Value("${spring.servlet.multipart.location}")
    private String uploadRootPath; //注入路径

    @Autowired
    PictureService pictureService;

    /*http://localhost:8080/release/images/29*/
    @PostMapping("/release/images/{blogId}")
    public String upLoadImages(@RequestParam("files") MultipartFile[] files, @PathVariable Integer blogId){
        log.info("进入增加图片方法");
        log.info("传递的参数是："+files +"   Id是：  "+blogId);
        StringBuilder sb=new StringBuilder();
        File desFolder = new File(uploadRootPath);
        if(!desFolder.exists()){
            desFolder.mkdir();
        }
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String orgName = file.getOriginalFilename();
                String extName = orgName.substring(orgName.lastIndexOf('.'));
                String destName = UUID.randomUUID().toString().toUpperCase() + extName;
                try {
                    file.transferTo(new File(uploadRootPath, destName));
                    Picture picture = new Picture();
                    picture.setBlogId(blogId);
                    picture.setName(destName);
                    picture.setLocation("/image/"+destName);
                    pictureService.save(picture);
                    sb.append(orgName + " 上传成功!"+"\n");
                } catch (IllegalStateException | IOException e) {
                    e.printStackTrace();
                    sb.append(orgName + " 上传失败: " + e.getMessage());
                }
            }
        }
        log.info("完成图片添加");
        return sb.toString();
    }
}
