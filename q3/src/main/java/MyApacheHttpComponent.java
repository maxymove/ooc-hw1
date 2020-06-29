import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;

public class MyApacheHttpComponent {

    void download(String url, String targetPath) {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = client.execute(request);
            HttpEntity entity = response.getEntity();
            int responseCode = response.getStatusLine().getStatusCode();
            //
            if (entity != null) {
                InputStream in = null;
                OutputStream out = null;
                try {
                    in = entity.getContent();
                    String target = targetPath + FilenameUtils.getName(url);
                    out = new FileOutputStream(target);
                    IOUtils.copy(in, out);
                } finally {
                    out.close();
                    in.close();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
