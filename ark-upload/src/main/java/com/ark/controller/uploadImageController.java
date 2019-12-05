package com.ark.controller;

import com.ark.config.OssConfig;
import com.ark.utils.OssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class uploadImageController {

    @Autowired
    private OssUtil ossUtil;
    @Autowired
    private OssConfig ossConfig;

    /**
     * url:
     * 请求方式：post
     * 请求参数: File 上传文件
     * 返回参数: String字符串（图片地址）
     * 图片文件上传
     */
    @PostMapping("/image")
    public String uploadImage(@RequestParam("file") MultipartFile file, Model model) {
        try {
            if (file != null) {
                //p1.jpg
                String filename = file.getOriginalFilename();
                if (!"".equals(filename.trim())) {
                    File newFile = new File(filename);

                    //注意:要对新建的文件对象,关联一个输出流对象 *****
                    FileOutputStream os = new FileOutputStream(newFile);
                    os.write(file.getBytes());
                    os.close();

                    //把源文件里的内容复制到目标文件中
                    file.transferTo(newFile);
                    //返回云服务器中,图片的完整地址
                    String path = ossUtil.upload(newFile);

                    //拼接完整的图片url地址,进行页面回显
                    String prefix = ossConfig.getUrlPrefix();
                    String imgUrl = prefix + path;
                    model.addAttribute("url", imgUrl);
                    return imgUrl;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
