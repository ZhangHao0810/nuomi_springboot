package com.yiran.nuomi.service;

import java.io.IOException;

/**
 * @author Super-Zhang
 * @date 2022/1/10 20:09
 */
public interface FileService {

    boolean fileDelet(String filePath);

    boolean fileMove(String filePath);

    void fileReName(String filePath,String newName) throws IOException;
}
