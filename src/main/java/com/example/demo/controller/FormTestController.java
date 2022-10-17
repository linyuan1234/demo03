package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/*
* 文件上传
* 自动配置好了StandardServletMultipartResolver[文件上传解析器]
*原理步骤：
*   1.请求进来使用文件上传解析器判断(isMultipart)并封装(resolveMultipart返回MultipartHttpServletRequest)文件上传请求
*   2.参数解析器解析请求中的文件内容封装成MultipartFile
*   3.将request中文件信息封装为一个Map: MultiValueMap<String,MultipartFile>
*    FileCopyUtils.实现文件流的拷贝
*
* */
@Slf4j
@Controller
public class FormTestController {
    @GetMapping("/form_layouts")
    public String form_layouts(){

        return "form/form_layouts";
    }
/*
* MultipartFile 自动封装上传过来的文件
* */
@PostMapping("/upload")
    public String upload(@RequestParam("email") String email,
                         @RequestParam("username") String username,
                         @RequestPart("headerImg") MultipartFile headerImg,
                         @RequestPart("photos") MultipartFile[] photos) throws IOException {



log.info("上传的信息；email={},username={},headerImg={},photos={}",email,username,headerImg.getSize(),photos.length);


if (!headerImg.isEmpty()){

    String originalFilename = headerImg.getOriginalFilename();
    headerImg.transferTo(new File("C:\\Users\\nantian\\Desktop\\PC玩家\\" + originalFilename));
}

if (photos.length>0){
    for (MultipartFile photo:photos
         ) {
        if (!photo.isEmpty()){
            photo.transferTo(new File("C:\\Users\\nantian\\Desktop\\PC玩家\\"+photo.getOriginalFilename()));
        }
    }
}
        return "index";
    }




}
