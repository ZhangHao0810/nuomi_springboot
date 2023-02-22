package com.yiran.nuomi.controller;

import com.yiran.nuomi.common.Toolbox;
import com.yiran.nuomi.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Super-Zhang
 * @date 2021-09-16 20:23
 */
@Controller
public class PageController {

    private String path = Toolbox.rootPath;

    @Autowired
    PageService pageService;

    @RequestMapping(path = "pageIndex")
    public String register(Model model) throws Exception {

        String prefix = "";
        model.addAttribute("Path", prefix);

        model.addAttribute("File", pageService.getFileNames(path));
        model.addAttribute("Video", pageService.getMp4Names(path));
        model.addAttribute("Gif", pageService.getPicNames(path).get(0));
        model.addAttribute("Pic", pageService.getPicNames(path).get(1));
        model.addAttribute("Txt", pageService.getTextNames(path));

        return "page";
    }

    @RequestMapping(path = "pageNext")
    public String register(@RequestParam String filepath, Model model) throws Exception {
        //保存当前目录。
        model.addAttribute("Path", filepath);

//        //面包屑导航
//        String[] prefixs = filepath.split("/");
//        ArrayList<String> list = new ArrayList<>(Arrays.asList(prefixs));
//        model.addAttribute("Breadcrumb", list);

        //获得资源传给前端
        String filepathReal = path +"/"+ filepath;
        System.out.println("pageNext filepathReal 文件访问路径"+filepathReal);

        model.addAttribute("File", pageService.getFileNames(filepathReal));
        model.addAttribute("Video", pageService.getMp4Names(filepathReal));
        model.addAttribute("Gif", pageService.getPicNames(filepathReal).get(0));
        model.addAttribute("Pic", pageService.getPicNames(filepathReal).get(1));
//        model.addAttribute("Txt",pageService.getTextNames(filepathReal));


        return "page";
    }

}
