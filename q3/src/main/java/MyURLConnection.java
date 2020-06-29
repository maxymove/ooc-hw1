import org.apache.commons.io.FilenameUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyURLConnection {

    private static final int BUFFER_SIZE = 4096;

    void download(String url, String targetPath) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            int responseCode = connection.getResponseCode();
            //
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream in = connection.getInputStream();
                String target = targetPath + FilenameUtils.getName(url);
                FileOutputStream out = new FileOutputStream(target);
                int bytesRead = -1;
                byte[] buffer = new byte[BUFFER_SIZE];
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
                out.close();
                in.close();

                connection.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
