package com.copy.utils;

import com.copy.exception.AppException;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

/**
 * 文件操作工具类
 *
 * @author zhongyong
 * @date 20180817
 * @since v1.0
 */
@Slf4j
public class FileUtils {

    /**
     * 工具类构造方法私有，禁止实例化
     * 直接调用该类的static方法
     */
    private FileUtils() {
    }

    /**
     * 生成文件
     *
     * @param path     文件目录
     * @param fileName 文件名称
     * @return 返回 file
     * @author zhongyong
     * @date 20180817
     * @since v1.0
     */
    public static File createNewFile(String path, String fileName) {
        // 生成目录
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 生成文件
        File file = new File(dir, fileName);

        // 同名文件存在先删除
        if (file.exists()) {
            file.delete();
        }

        // 生成新文件
        try {
            file.createNewFile();
        } catch (IOException e) {
            log.error("createNewFile异常", e);
            throw new AppException("创建文件异常");
        }
        return file;
    }

    public static File createDir(String path) {
        // 生成目录
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    public static File createNewFile(String filePath) {
        File f = new File(filePath);
        if (f.exists()) {
            f.delete();
        }
        //判断父目录路径是否存在
        if (!f.getParentFile().exists()) {
            //不存在则创建父目录
            f.getParentFile().mkdirs();
        }
        try {
            f.createNewFile();
            return f;
        } catch (IOException e) {
            log.error("createNewFile异常", e);
            throw new AppException("创建文件异常");
        }
    }

    public static void createFile(String path, String fileContent) throws IOException {
        //判断文件是否存在，如果存在则删除
        File fileOld = new File(path);
        if (fileOld.exists()) {
            fileOld.delete();
        }
        File file = fileOld.getParentFile();
        if (!file.exists()) {
            file.mkdirs();
        }
        @Cleanup FileWriter fw = new FileWriter(path, true);
        @Cleanup BufferedWriter bw = new BufferedWriter(fw);
        bw.write(fileContent);
    }

    public static void deleteFile(File file) {
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File childrenFile : Objects.requireNonNull(files)) {
                deleteFile(childrenFile);
            }
        }
        if (!file.delete()) {
            log.error("{}删除失败", file.getName());
        }
    }


}
