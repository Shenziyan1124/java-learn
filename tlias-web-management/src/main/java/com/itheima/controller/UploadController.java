package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    /**
     * 本地文件上传
     * @param name
     * @param age
     * @param file
     * @return
     */
    //    @PostMapping("/upload")
    //    public Result upload(String  name, Integer  age, MultipartFile  file) throws IOException {
    //        log.info("接受参数,{},{},{}", name, age, file);
    //
    //        String originalFilename = file.getOriginalFilename();
    //        String extension = null;
    //        if (originalFilename != null) {
    //            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
    //        }
    //        String newFileName = UUID.randomUUID().toString() + extension;
    //
    //        file.transferTo(new java.io.File("D:\\SHEN\\projects\\java\\web-project\\springboot-web-02\\" + newFileName));
    //
    //       return   Result.success();
    //    }

    /**
     * OSS文件上传
     * */
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;
    @PostMapping("/upload")
    public Result upload(MultipartFile  file) throws Exception {
        log.info("接受参数,{}", file);
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("文件上传成功,{}", url);

        return  Result.success(url);
    }
}
