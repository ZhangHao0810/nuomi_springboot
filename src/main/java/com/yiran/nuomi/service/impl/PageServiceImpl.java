package com.yiran.nuomi.service.impl;

import com.yiran.nuomi.service.PageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Super-Zhang
 * @date 2021-09-26 15:00
 */
@Service
public class PageServiceImpl implements PageService {

    private Logger logger = LoggerFactory.getLogger(PageServiceImpl.class);

    /**
     * 获得所有子文件夹目录
     *
     * @param path
     * @return
     */
    @Override
    public ArrayList<String> getFileNames(String path) {
        File f = new File(path);
        if (!f.exists()) {
            logger.info(path + " not exists");
            return null;
        }

        ArrayList<String> arrayList = new ArrayList<>();

        File[] fa = f.listFiles();
        for (File fs : fa) {
            if (fs.isDirectory()) {
                arrayList.add(fs.getName());
                logger.info(" [目录]" + fs.getName());
            } else {
                System.out.println(fs.getName());
            }
        }

        return arrayList;
    }

    @Override
    public ArrayList<String> getMp4Names(String path) {
        File f = new File(path);
        if (!f.exists()) {
            logger.info(path + " not exists");
            return null;
        }

        ArrayList<String> arrayList = new ArrayList<>();

        File[] fa = f.listFiles();
        for (File fs : fa) {

            if (fs.getName().contains("mp4")) {
                arrayList.add(fs.getName());
                logger.info("Get mp4 :" + fs.getName());
            }
        }

        return arrayList;
    }

    @Override
    public ArrayList<ArrayList<String>> getPicNames(String path) throws Exception {
        File f = new File(path);
        if (!f.exists()) {
            System.out.println(path);
            logger.info(path + " not exists");
            throw new Exception("getPicNames 路径：" + path + "不存在");
        }

        ArrayList<String> arrayListPic = new ArrayList<>();
        ArrayList<String> arrayListGif = new ArrayList<>();
        ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();

        File[] fa = f.listFiles();
        for (File fs : fa) {

            if (fs.getName().contains("gif")) {
                arrayListGif.add(fs.getName());
                logger.info("Get gif :" + fs.getName());
            }

            if (fs.getName().contains("jpg")
                    || fs.getName().contains("jpeg") || fs.getName().contains("webp")
                    || fs.getName().contains("png") || fs.getName().contains("bmp")) {
                arrayListPic.add(fs.getName());
                logger.info("Get pic :" + fs.getName());
            }
        }
        arrayLists.add(arrayListGif);
        arrayLists.add(arrayListPic);

        return arrayLists;
    }

    @Override
    public ArrayList<String> getTextNames(String path) {
        File f = new File(path);
        if (!f.exists()) {
            logger.info(path + " not exists");
            return null;
        }

        ArrayList<String> arrayList = new ArrayList<>();

        File[] fa = f.listFiles();
        for (File fs : fa) {
            if (fs.getName().contains("txt")) {
                arrayList.add(fs.getName());
                logger.info("Get Text :" + fs.getName());
            }
        }

        return arrayList;
    }
}
