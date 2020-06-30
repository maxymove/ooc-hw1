import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class SimpleCrawler {

    private CloseableHttpClient client = HttpClientBuilder.create().build();

    private Queue<String> q;
    private Set<String> visited;

    private String rootUrl;
    private String rootDir;

    private String domainName = "https://ooc.cs.muzoo.io/";

    private int numFile = 0;

    public SimpleCrawler(String rootUrl, String rootDir) {
        this.q = new LinkedList<>();
        this.visited = new HashSet<>();
        this.rootUrl = rootUrl;
        this.rootDir = rootDir;
    }

    void bfs() {
        q.add(rootUrl);
        visited.add(rootUrl);
        while (!q.isEmpty()) {
            String currentUrl = q.poll();

            // download this html page.
            download(currentUrl);

            // get all html pages in this page.
            Document doc = null;
            try {
                doc = Jsoup.connect(currentUrl).ignoreContentType(true).ignoreHttpErrors(true).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements links = doc.select("a[href]");
            addNeighbors(links);

            // get all imports ie. .css, .ico
            Elements imports = doc.select("link[href]");
            for (Element elt : imports) {
                String tmpUrl = elt.attr("abs:href");
                if (!visited.contains(tmpUrl) && tmpUrl.contains(domainName)) {
                    visited.add(tmpUrl);
                    download(tmpUrl);
                }
            }

            // get all media in this page.
            Elements media = doc.select("[src]");
            for (Element elt : media) {
                String tmpUrl = elt.attr("abs:src");
                if (!visited.contains(tmpUrl) && tmpUrl.contains(domainName)) {
                    visited.add(tmpUrl);
                    download(tmpUrl);
                }
            }
        }
    }

    void addNeighbors(Elements links) {
        for (Element link : links) {
            String tmpUrl = link.attr("abs:href");
            String parentName = getParentName(tmpUrl);
            if (!visited.contains(parentName) && parentName.contains(domainName)) {
                q.add(parentName);
                visited.add(parentName);
            }
        }
    }

    String filterDomainName(String url) {
        return url.replaceAll(domainName, "");
    }

    String getParentName(String url) {
        return url.replaceAll("#.*", "");
    }

    void testDownload() {
        download("https://ooc.cs.muzoo.io/docs/index.html");
    }

    void download(String url) {
        if (!url.endsWith("/")) {
            HttpGet request = new HttpGet(url);
            HttpResponse response = null;
            try {
                response = client.execute(request);
                HttpEntity entity = response.getEntity();
                InputStream in = entity.getContent();
                String targetPath = FilenameUtils.getPath(filterDomainName(url));
                FileUtils.forceMkdir(new File(rootDir + targetPath));
                String filename = getParentName(FilenameUtils.getName(url));
                FileOutputStream fos = new FileOutputStream(filename);
                int buffer;
                while((buffer = in.read()) != -1) {
                    fos.write(buffer);
                }
                in.close();
                fos.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
