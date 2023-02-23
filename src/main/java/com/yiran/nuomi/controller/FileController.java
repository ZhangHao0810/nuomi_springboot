package com.yiran.nuomi.controller;

import cn.hutool.core.io.FileUtil;
import com.yiran.nuomi.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author zh
 * @description 用于文件处理的Controller
 * @data 2021-09-16 20:24
 */
@Controller
@RequestMapping("file")
public class FileController {

    @Autowired
    FileService fileService;

    @Value("${root-path}")
    private String rootPath;

    /**
     * 删除指定文件
     * @param path 文件的相对路径
     * @return
     */
    @RequestMapping("/delet")
    public String fileDelet(String path) throws Exception {

        String filepath = rootPath + "/" + path;
        System.out.println("文件夹 删除路径：" + filepath);

        if (!filepath.equals("G:/过程蓝图设计方法学/")) {
            FileUtil.move(new File(filepath),new File(rootPath+"/回收站"),false);
//            FileUtils.moveFileToRecycleBin(filepath,rootPath+"/回收站");
        } else {
            throw new Exception("不能删除根目录！！！");
        }

        System.out.println("即将重定向到：" + path.substring(0, path.lastIndexOf("/")));
//        return new RedirectView("pageNext?filepath=" + URLEncoder.encode(path.substring(0, path.lastIndexOf("/")), "UTF-8"));
        return "redirect:/pageNext?filepath=" + URLEncoder.encode(path.substring(0, path.lastIndexOf("/")));

    }

    /**
     * 文件或文件夹的重命名
     *
     * @param path
     * @return
     */
    @RequestMapping("/rename")
    public RedirectView fileRename( String path, String newName) throws IOException {

        String filepath = rootPath + "/" + path;
        System.out.println("文件重命名：" + filepath);
        FileUtil.rename(new File(filepath), newName, false);
//        fileService.fileReName(filepath, newName);

        String newpath = path.substring(0, path.lastIndexOf("/")) + "/" + newName;
        System.out.println("即将重定向到：" + newpath);
        return new RedirectView("pageNext?filepath=" + URLEncoder.encode(newpath, "UTF-8"));

    }
}
