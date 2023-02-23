package com.yiran.nuomi.service;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author Super-Zhang
 * @date 2021-09-26 14:59
 */
public interface PageService {

    Map<String,ArrayList<String>> getViewMap(String path) throws Exception;

    /**
     * 返回子文件夹
     */
    ArrayList<String> getFileNames(String path);

    /**
     * 返回 视频，暂为mp4
     */
    ArrayList<String> getMp4Names(String path);

    /**
     * 返回 gif 和 各种类型的静态图片
     */
    ArrayList<ArrayList<String>> getPicNames(String path) throws Exception;

    /**
     * 返回文本内容
     */
    ArrayList<String> getTextNames(String path);
}
