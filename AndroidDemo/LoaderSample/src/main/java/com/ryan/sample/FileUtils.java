package com.ryan.sample;

import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

/**
 *
 */
public class FileUtils {

    /**
     * 获取文件所在目录
     * @param filePath
     * @return
     */
    public static String getFileParent(String filePath){
        String path = filePath;
        if(TextUtils.isEmpty(path)){
            return null;
        }

        if(path.endsWith("/")){
            int index = path.lastIndexOf("/");
            if(index >= 0 || index < path.length()){
                path = path.substring(0,index);
            }
        }

        int start = path.lastIndexOf("/");
        if(start == -1){
            return null;
        }
        String dir = path.substring(0, start);
        return dir;
    }

    /**
     * 获取文件名称
     * @return
     */
    public static String getFileName(String filepath){
        String path = filepath;
        if(TextUtils.isEmpty(path)){
            return "";
        }
        if(path.endsWith("/")){
            int index = path.lastIndexOf("/");
            if(index >= 0 || index < path.length()){
                path = path.substring(0,index);
            }
        }
        int start = path.lastIndexOf("/");
        if(start == -1){
            return path;
        }
        String fileName = path.substring(start+1);
        return fileName;
    }

    /**
     * 获取文件后缀
     * @param filepath
     */
    public static String getExtension(String filepath){
        if (TextUtils.isEmpty(filepath)) {
            return "";
        }
        String fileName = getFileName(filepath);
        int dotPosition = fileName.lastIndexOf(".");
        if ((dotPosition > 0) && (dotPosition != (fileName.length() - 1))) {
            return fileName.substring(dotPosition + 1);
        } else {
            // No extension.
            return "";
        }
    }

    /**
     * 获取文件名称 不包含extension
     * @param fileName
     * @return
     */
    public static String getFileNameWithoutExtension(String fileName){
        if (fileName == null) {
            return "";

        }
        String name = getFileName(fileName);
        int dotPosition = name.lastIndexOf(".");
        if (dotPosition > 0) {
            return name.substring(0, dotPosition);
        } else {
            return name;
        }
    }

    private static DecimalFormat mFormater = new DecimalFormat("#.##");
    /**
     *
     * @param length 文件长度
     * @return 带有合适单位名称的文件大小
     */
    public static String getSizeFormatText(long length) {
        if (length <= 0)
            return "0KB";

        String str = "B";
        double result = (double) length;
        if(length < 1024){
            return "1KB";
        }
        // 以1024为界，找到合适的文件大小单位
        if (result >= 1024) {
            str = "KB";
            result /= 1024;
            if (result >= 1024) {
                str = "MB";
                result /= 1024;
            }
            if (result >= 1024) {
                str = "G";
                result /= 1024;
            }
            if(result >= 1024){
                str = "T";
                result /= 1024;
            }
        }
        String sizeString = null;

        // 按照需求设定文件的精度
        // MB 和 GB 保留两位小数.#254107添加 T
        if (str.equals("MB") || str.equals("G")||str.equals("T")) {
            sizeString = mFormater.format(result);
        }
        // B 和 KB 保留到各位
        else
            sizeString = Integer.toString((int) result);
        return sizeString + str;
    }

    public static File createFile(String desFileDir,String filename) {
        File file = new File(desFileDir, filename);
        if (!file.exists()) {
            return file;
        }
        // Get the extension of the file, if any.
        int index = filename.lastIndexOf('.');
        String format;
        String name = "";
        String extension = "";
        if (index != -1) {
            name = filename.substring(0, index);
            extension = filename.substring(index);
        } else {
            name = filename;
            extension = "";
        }
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            String fName = name + String.format("-%d", i) + extension;
            file = new File(desFileDir, fName);
            if (!file.exists()) {
                return file;
            }
        }
        return null;
    }

    // 检查是否还有足够的空间
    public static boolean checkAvailableSpace(long size, String filePath) {
        if (false == isInSdCard(filePath)) {
            //判断外界盘的存储空间
            long availableSize = getAvailableSize(filePath);
            if (availableSize >= size && availableSize != 0) {
                return true;
            } else {
                return false;
            }
        }

        if (!(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))) {
            // can not be saved
            return false;
        }

        File path = Environment.getExternalStorageDirectory();
        StatFs statFs = new StatFs(path.getPath());
        long blockSize = statFs.getBlockSize();
        long availableBlocks = statFs.getAvailableBlocks();
        long availabSize = blockSize * availableBlocks;

        if (availabSize >= size && availabSize != 0)
            return true;
        else
            return false;
    }

    public static boolean isExsit(String filePath) {
        File file = new File(filePath);
        if(file != null && file.exists()) {
            return true;
        }
        return false;
    }

    public static boolean isInSdCard(String filePath){
        String sdCardPath = Environment.getExternalStorageDirectory().getPath();
        if(!TextUtils.isEmpty(filePath) && filePath.startsWith(sdCardPath)){
            return true;
        }
        return false;
    }

    // 删除存在的file
    public static boolean deleteExistentFile(File file) {
        if (!file.exists())
            return false;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if(files != null){
                for (File f : files) {
                    deleteExistentFile(f);
                }
            }
            file.delete();
        } else {
            file.delete();
        }
        return true;
    }

    public static boolean deleteExistentFile(String filePath) {
        if (!TextUtils.isEmpty(filePath)) {
            File file = new File(filePath);
            return deleteExistentFile(file);
        }
        return false;
    }

//    public static final String formatTimeNormal(Context context, long time){
//        return com.meizu.common.util.DateTimeUtils.formatTimeStampString(context, time, com.meizu.common.util.DateTimeUtils.FORMAT_TYPE_NORMAL, true);
//    }

    public static long getAvailableSize(String realPath){
        try{
            StatFs statFs = new StatFs(realPath);
            long blockSize = statFs.getBlockSize();
            long availableBlocks = statFs.getAvailableBlocks();
            long availableSize = blockSize * availableBlocks;
            return availableSize;
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public static long getTotalSize(String realPath){
        try{
            StatFs statFs = new StatFs(realPath);
            long blockSize = statFs.getBlockSize();
            long totalBlocks = statFs.getBlockCount();
            long totalSize = blockSize * totalBlocks;
            return totalSize;
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean isContainsHiddenFolderPath(String path){
        if(TextUtils.isEmpty(path)){
            return false;
        }
        if(path.contains("/.")){
            return true;
        }
        return false;
    }

    /**
     * Copy data from a source stream to destFile. Return true if succeed,
     * return false if failed.
     */
    public static boolean copyToFile(InputStream inputStream, File destFile) {
        try {
            if (destFile.exists()) {
                destFile.delete();
            }
            FileOutputStream out = new FileOutputStream(destFile);
            try {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) >= 0) {
                    out.write(buffer, 0, bytesRead);
                }
            } finally {
                out.flush();
                try {
                    out.getFD().sync();
                } catch (IOException e) {
                }
                out.close();
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 判断本地是否存在该文件
     * @param localPath
     * @return
     */
    public static boolean checkLocalExist(String localPath) {
        boolean isExist = false;
        if(!TextUtils.isEmpty(localPath)) {
            File file = new File(localPath);
            if(file.exists()) {
                isExist = true;
            }
        }
        return isExist;
    }

}
