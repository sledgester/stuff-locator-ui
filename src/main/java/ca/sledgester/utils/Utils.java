package ca.sledgester.utils;

import com.google.common.io.ByteStreams;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public class Utils {

    public String imageToBase64String(MultipartFile file) {

        byte[] filecontent = null;
        String imageString = "";

        try
        {
            InputStream inputStream = file.getInputStream();

            if(inputStream == null) {

                System.out.println("Null inputstream");

            } else {

                filecontent = ByteStreams.toByteArray(inputStream);

            }

            imageString =  Base64.encodeBase64String(filecontent);

        } catch(IOException ex) {

            System.out.println("IOException");

        }

        return imageString;

    }

}
