package com.minh.nguyen.util;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Mr.Minh
 * @since 07/01/2018
 * Purpose:
 */
@Service
public class FileUtil {
    public static void writeToFile(String text,final File file){
        try {
            PrintWriter out = new PrintWriter(file);
            out.println(text);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void writeToFile(String[] text, File file){
        try {
            PrintWriter out = new PrintWriter(file);
            for(int i = 0;i < text.length;i++) {
                out.println(text[i]);
            }
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static File createFile(String location, String fileName,String extension){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(location);
        if (location.charAt(location.length() - 1) != '\\') {
            stringBuilder.append("\\");
        }
        stringBuilder.append(fileName);
        stringBuilder.append(".");
        stringBuilder.append(extension);
        File file = new File(stringBuilder.toString());
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
