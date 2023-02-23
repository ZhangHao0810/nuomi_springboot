package com.yiran.nuomi.controller;

import com.yiran.nuomi.common.Toolbox;
import com.yiran.nuomi.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${root-path}")
    private String path;

    @Autowired
    PageService pageService;


    @RequestMapping(path = "pageIndex")
    public String register(Model model) throws Exception {
        // web浏览，不应将 F:/xx 写入。应直接写相对路径。只有在操作文件的时候才用绝对路径。
        model.addAttribute("Path", "");
        model.addAllAttributes(pageService.getViewMap(path));
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

        String fileRealPath = path + "/" + filepath;

        //获得资源传给前端
        System.out.println("pageNext filepathReal 文件访问路径" + fileRealPath);
        model.addAllAttributes(pageService.getViewMap(fileRealPath));

        return "page";
    }

}
