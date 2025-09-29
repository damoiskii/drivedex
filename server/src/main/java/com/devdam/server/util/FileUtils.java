package com.devdam.server.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtils {
    
    // This method will convert an url to bytes[]
    public static byte[] getFileBytesFromUrl(String url){
        byte[] response = null;

        // Source: https://stackoverflow.com/a/5882039/15978450
        try(InputStream in = new BufferedInputStream(new URL(url).openStream()); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            byte[] buf = new byte[1024];
            int n = 0;

            // Reading the file in bytes
            while (-1 != (n = in.read(buf))) {
                out.write(buf, 0, n);
            }

            out.close();
            in.close();

            response = out.toByteArray();
        }
        catch (MalformedURLException e) {
            log.error("Error [MalformedURLException]: {}", e.getMessage());
        }
        catch (IOException e) {
            log.error("Error [IOException]: {}", e.getMessage());
        }

        return response;
    }
}
