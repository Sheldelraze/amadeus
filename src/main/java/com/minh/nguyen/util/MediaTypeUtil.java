package com.minh.nguyen.util;

import org.springframework.http.MediaType;

import javax.servlet.ServletContext;

/**
 * @author Mr.Minh
 * @since 17/03/2018
 * Purpose:
 */
public class MediaTypeUtil {

    // abc.zip
    // abc.pdf,..
    public static MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {
        // application/pdf
        // application/xml
        // image/gif, ...
        String mineType = servletContext.getMimeType(fileName);
        try {
            MediaType mediaType = MediaType.parseMediaType(mineType);
            return mediaType;
        } catch (Exception e) {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

}