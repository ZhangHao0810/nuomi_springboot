package com.yiran.nuomi.utils;

import java.io.File;
import java.io.IOException;

/**
 * @author Super-Zhang
 * @date 2022/1/11 15:06
 */
public class FileUtils {

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     * If a deletion fails, the method stops attempting to
     * delete and returns "false".
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
        //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    /**
     * 移动文件到回收站
     *
     * @param dir
     * @return
     */
    public static boolean moveFileToRecycleBin(File dir) {
        return true;
    }

    /**
     * 文件重命名
     *
     * @param filePath
     * @param newFileName
     */
    public static void renameFile(String filePath, String newFileName) throws IOException {
        System.out.println("文件重命名： " + filePath + "  TO  " + filePath.substring(0, filePath.lastIndexOf("/")) + "/" + newFileName);

        // 旧的文件或目录
        File oldName = new File(filePath);
        // 新的文件或目录
        File newName = new File(filePath.substring(0, filePath.lastIndexOf("/")) + "/" + newFileName);
        if (newName.exists()) {  //  确保新的文件名不存在
            throw new IOException("file exists");
        }
        if (oldName.renameTo(newName)) {
            System.out.println("已重命名");
        } else {
            System.out.println("Error");
        }
    }
}
