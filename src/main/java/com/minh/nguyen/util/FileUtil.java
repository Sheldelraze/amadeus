package com.minh.nguyen.util;

import com.minh.nguyen.dto.LanguageDTO;
import com.minh.nguyen.dto.ProblemDTO;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Mr.Minh
 * @since 07/01/2018
 * Purpose:
 */
public class FileUtil {
    public static void store(MultipartFile file, String location) {
        try {
            Path path = Paths.get(location);
            File directory = new File(location);
            if (!directory.exists()) {
                directory.mkdir();
            }
            Files.copy(file.getInputStream(), path.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public static Resource loadFile(String filename, String location) {
        try {
            Path file = Paths.get(location).resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }

    private static void writeToFile(String text, final File file) {
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

    private static File createFile(String location, String fileName, String extension) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(location);
        if (location.charAt(location.length() - 1) != '\\') {
            stringBuilder.append("\\");
        }
        stringBuilder.append(fileName);
        stringBuilder.append(".");
        stringBuilder.append(extension);
        File file = new File(stringBuilder.toString()).getAbsoluteFile();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
    public static File createFileWithContent(ProblemDTO problemDTO, LanguageDTO languageDTO, String location, String filename){
        return createFileWithContent(location,filename,languageDTO.getExtension(),problemDTO.getSourceCode());
    }
    public static File createFileWithContent(String location,String filename,String extension,String content){
        File file = FileUtil.createFile(location,filename,extension);
        FileUtil.writeToFile(content, file);
        return file;
    }
}
