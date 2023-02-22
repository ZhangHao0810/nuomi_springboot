package com.yiran.nuomi.controller;

import com.yiran.nuomi.common.Toolbox;
import com.yiran.nuomi.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author Super-Zhang
 * @date 2021-09-16 20:24
 */
@Controller
public class FileController {

    @Autowired
    FileService fileService;

    /**
     * 删除指定文件
     * @param path
     * @param fileName
     * @return
     */
    @RequestMapping("/fileDelet")
    public RedirectView fileDelet(@RequestParam String path, @RequestParam String fileName) throws UnsupportedEncodingException {

        String filepath=Toolbox.rootPath+"/"+path+"/"+fileName;

        System.out.println("文件删除路径："+filepath);

        fileService.fileDelet(filepath);

        System.out.println("即将重定向到："+path);

        return new RedirectView("pageNext?filepath="+URLEncoder.encode(path,"UTF-8"));

    }

    /**
     * 删除指定文件夹
     * @param path
     * @return
     */
    @RequestMapping("/folderDelet")
    public RedirectView folderDelet(@RequestParam String path) throws Exception {

        String filepath=Toolbox.rootPath+"/"+path;

        System.out.println("文件夹 删除路径："+filepath);

        if(!filepath.equals("G:/过程蓝图设计方法学/"))
            fileService.fileDelet(filepath);
        else throw new Exception("不能删除根目录！！！");

        System.out.println("即将重定向到："+path.substring(0,path.lastIndexOf("/")));

        return new RedirectView("pageNext?filepath="+URLEncoder.encode(path.substring(0,path.lastIndexOf("/")),"UTF-8"));

    }

    /**
     * 移动文件到特定文件夹（回收站）
     * @param path
     * @return
     */
    @RequestMapping("/fileDeletToBin")
    public RedirectView fileDeletToBin(@RequestParam String path) throws UnsupportedEncodingException {

        String filepath=Toolbox.rootPath+"/"+path;

        System.out.println("文件夹 移动路径："+filepath);

        String bin="回收站";

        fileService.fileDelet(filepath);

        System.out.println("即将重定向到："+path.substring(0,path.lastIndexOf("/")));

        return new RedirectView("pageNext?filepath="+URLEncoder.encode(path.substring(0,path.lastIndexOf("/")),"UTF-8"));

    }


    /**
     * 文件或文件夹的重命名
     * @param path
     * @return
     */
    @RequestMapping("/fileReName")
    public RedirectView fileReName(@RequestParam String path,@RequestParam String newName) throws IOException {

        String filepath=Toolbox.rootPath+"/"+path;

        System.out.println("文件重命名："+filepath);

        fileService.fileReName(filepath,newName);

        String newpath=path.substring(0,path.lastIndexOf("/"))+"/"+newName;

        System.out.println("即将重定向到："+newpath);

        return new RedirectView("pageNext?filepath="+URLEncoder.encode(newpath,"UTF-8"));

    }
}
