package com.yiran.nuomi.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Super-Zhang
 * @date 2022/1/11 15:06
 */
@Component
public class FileUtils {

    /**
     * 复制文件
     * 从源路径到目标文件夹路径，文件名保持一致
     * 如果目标文件夹不存在则自动创建
     * 如果文件已经存在则自动编号-copy n
     *
     * @param srcFile 源文件绝对路径
     * @param dstDir  目标文件夹绝对路径
     * @return 是否成功复制文件
     */
    public static boolean copyFile(File srcFile, File dstDir) {
        if (!srcFile.exists() || srcFile.isDirectory()) {
            return false;
        }
        if (!dstDir.exists()) {
            dstDir.mkdirs();
        }
        String oldFileName = srcFile.getName();
        Pattern suffixPattern = Pattern.compile("\\.\\w+");
        Matcher matcher = suffixPattern.matcher(oldFileName);
        String nameBody;
        String suffix;
        if (matcher.find()) {
            nameBody = oldFileName.substring(0, matcher.start());
            suffix = oldFileName.substring(matcher.start());
        } else {
            nameBody = oldFileName;
            suffix = "";
        }
        int fileNumber = 0;
        File newFile = new File(dstDir, oldFileName);
        while (newFile.exists()) {
            fileNumber++;
            String newFileName = nameBody + "-copy" + fileNumber + suffix;
            newFile = new File(dstDir, newFileName);
        }
        try {
            FileChannel fileIn = new FileInputStream(srcFile).getChannel();
            FileChannel fileOut = new FileOutputStream(newFile).getChannel();
            fileIn.transferTo(0, fileIn.size(), fileOut);
            fileIn.close();
            fileOut.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * 复制文件或文件夹
     * 如果目标文件夹不存在则自动创建
     * 如果文件或文件夹已经存在则自动编号-copy n
     *
     * @param src    源文件或文件夹绝对路径
     * @param dstDir 目标文件夹绝对路径
     * @return 是否成功复制文件或文件夹
     */
    public static boolean copy(File src, File dstDir) {
        if (!src.exists()) {
            return false;
        }
        if (!dstDir.exists()) {
            dstDir.mkdirs();
        }
        if (src.isFile()) {// 文件
            copyFile(src, dstDir);
        } else {// 文件夹
            String oldSrcName = src.getName();
            int srcNumber = 0;
            File newSrcDir = new File(dstDir, oldSrcName);
            while (newSrcDir.exists()) {
                srcNumber++;
                String newSrcName = oldSrcName + "-copy" + srcNumber;
                newSrcDir = new File(dstDir, newSrcName);
            }
            newSrcDir.mkdirs();
            for (File srcSub : src.listFiles()) {
                copy(srcSub, newSrcDir);// 递归复制源文件夹下子文件和文件夹
            }
        }
        return true;
    }

    /**
     * 移动(剪切)文件
     *
     * @param srcFile
     * @param dstDir
     * @return
     */
    public static boolean moveFile(File srcFile, File dstDir) {
        if (!srcFile.exists() || srcFile.isDirectory()) {
            return false;
        }
        if (!dstDir.exists()) {
            dstDir.mkdirs();
        }
        String oldFileName = srcFile.getName();
        File dstFile = new File(dstDir, oldFileName);
        if (srcFile.renameTo(dstFile)) {// 直接重命名绝对路径速度更快
            return true;
        } else {// 文件已经存在，需要自动编号复制再删除源文件
            copyFile(srcFile, dstDir);
            srcFile.delete();
        }
        return true;
    }

    /**
     * 移动文件或文件夹
     * 如果目标文件夹不存在则自动创建
     * 如果文件或文件夹已经存在则自动编号-copy n
     *
     * @param src    源文件或文件夹绝对路径
     * @param dstDir 目标文件夹绝对路径
     * @return 是否成功移动文件或文件夹
     */
    public static boolean move(File src, File dstDir) {
        if (!src.exists()) {
            return false;
        }
        if (!dstDir.exists()) {
            dstDir.mkdirs();
        }
        if (src.isFile()) {// 文件
            moveFile(src, dstDir);
        } else {// 文件夹
            String oldSrcName = src.getName();
            int srcNumber = 0;
            File newSrcDir = new File(dstDir, oldSrcName);
            while (newSrcDir.exists()) {
                srcNumber++;
                String newSrcName = oldSrcName + "-copy" + srcNumber;
                newSrcDir = new File(dstDir, newSrcName);
            }
            newSrcDir.mkdirs();
            for (File srcSub : src.listFiles()) {
                move(srcSub, newSrcDir);// 递归移动源文件夹下子文件和文件夹
            }
            src.delete();
        }
        return true;
    }

    /**
     * 删除文件或文件夹
     *
     * @param src 源文件或文件夹绝对路径
     * @return 是否成功删除文件或文件夹
     */
    public static boolean delete(File src) {
        if (!src.exists()) {
            return false;
        }
        if (src.isFile()) {
            src.delete();
        } else {
            for (File srcSub : src.listFiles()) {
                delete(srcSub);// 递归删除源文件夹下子文件和文件夹
            }
            src.delete();
        }
        return true;
    }


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
     * @param src 要删除的文件
     * @return
     */
    public static boolean moveFileToRecycleBin(String src,String bin) {
        File binFile = new File(bin);
        File srcFile = new File(src);
        if (!binFile.exists()) {
            binFile.mkdirs();
        }
        return move(srcFile,binFile);
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
