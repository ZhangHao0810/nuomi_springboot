package com.yiran.nuomi.service.impl;

import com.yiran.nuomi.service.FileService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * @author Super-Zhang
 * @date 2022/1/10 20:11
 */
@Service
public class FileServiceImpl implements FileService {

    @Override
    public boolean fileDelet(String filePath) {
        return true;
    }

    @Override
    public boolean fileMove(String filePath) {
        return true;
    }

    @Override
    public void fileReName(String filePath,String newName) throws IOException {
    }
}
