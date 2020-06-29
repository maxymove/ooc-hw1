import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MyApacheCommonsIO {

    void download(String url, String targetPath) {
        String target = targetPath + FilenameUtils.getName(url);
        try {
            FileUtils.copyURLToFile(new URL(url), new File(target));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
