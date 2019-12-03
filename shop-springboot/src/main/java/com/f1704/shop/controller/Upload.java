package com.f1704.shop.controller;

import com.hxx.common.utils.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/file/")
public class Upload {
  @Value("${file.path}")
  private String filePath;
  @Value("${file.domain}")
  private String fileDomain;
  @RequestMapping("upload")
  public Object upload(@RequestParam("file")MultipartFile file) throws IOException {
    String fileName= UUID.randomUUID()+ FileUtil.getExtendName(file.getOriginalFilename());
    File saveFile=new File(filePath+fileName);
    file.transferTo(saveFile);
    return fileDomain+fileName;
  }
}
