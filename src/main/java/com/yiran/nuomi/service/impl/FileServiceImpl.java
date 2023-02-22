package com.yiran.nuomi.service.impl;

import com.yiran.nuomi.service.FileService;
import com.yiran.nuomi.utils.FileUtils;
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
        return FileUtils.moveFileToRecycleBin(new File(filePath));
    }

    @Override
    public boolean fileMove(String filePath) {
        return FileUtils.moveFileToRecycleBin(new File(filePath));
    }

    @Override
    public void fileReName(String filePath,String newName) throws IOException {
        FileUtils.renameFile(filePath,newName);
    }
}
