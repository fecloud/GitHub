package com.inspurworld.server.util;

/**
 * @(#) ImportFile.java Created on 2012-7-4
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class <code>ImportFile</code> 以文件流的方式将文件导入到SD卡
 * 
 * @author likai
 * @version 1.0
 */
public class FileUtil {

    /**
     * 以文件流的方式复制文件
     * 
     * @param src
     *            文件源目录
     * @param dest
     *            文件目的目录
     * @throws IOException
     */
    public void copyFile(String src, String dest) throws IOException {
        FileInputStream in = new FileInputStream(src);
        File file = new File(dest);
        if (!file.exists())
            file.createNewFile();
        FileOutputStream out = new FileOutputStream(file);
        int s;
        byte buffer[] = new byte[1024];
        while ((s = in.read(buffer)) != -1) {
            for (int i = 0; i < s; i++)
                out.write(buffer[i]);
        }

        in.close();
        out.close();

    }

    /**
     * read file to byte array
     * 
     * @param path
     * @return
     * @throws IOException
     */
    public static byte[] readFile(String path) throws IOException {
        final File file = new File(path);
        if (!file.exists())
            throw new IOException("the path" + path + " is not exists");
        final FileInputStream templateImageData = new FileInputStream(file);
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] cache = new byte[255];
        int len = 0;
        while (-1 != (len = templateImageData.read(cache))) {
            outputStream.write(cache, 0, len);
        }
        outputStream.flush();
        outputStream.close();
        templateImageData.close();
        return outputStream.toByteArray();
    }

    /**
     * write bytes to file
     * 
     * @param filename
     * @param bs
     * @throws IOException
     */
    public static void writeFile(String filename, byte[] bs) throws Exception {
        try {
            final File file = new File(filename).getParentFile();
            if (!file.exists()) {
                file.mkdirs();
            }
            final FileOutputStream out = new FileOutputStream(filename);
            out.write(bs);
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new Exception("writeFile error", e);
        }

    }

    /**
     * 删除文件夹及子文件
     * 
     * @param file
     *            要删除的文件夹
     * @return
     */
    public static boolean deleteFile(File file) {
        if (file.exists()) { // 判断文件是否存在
            if (file.isFile()) { // 判断是否是文件
                if (!file.delete())
                    return false; // delete()方法 你应该知道 是删除的意思;
            } else if (file.isDirectory()) { // 否则如果它是一个目录
                File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
                for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
                    deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
                }
            }
            return file.delete();
        }
        return false;
    }

    /**
     * 取当前文件的所有文件
     * 
     * @param dir
     * @return
     */
    public static List<String> getDirFileName(String dir) {
        final File d = new File(dir);
        if (d.exists() && d.isDirectory()) {
            final List<String> list = new ArrayList<String>();
            for (String s : d.list()) {
                list.add(s);
            }
            return list;
        } else {
            return null;
        }
    }
}
