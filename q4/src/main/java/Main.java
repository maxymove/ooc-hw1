public class Main {

    public static void main(String[] args) {
        String url = "https://ooc.cs.muzoo.io/docs/index.html";
        String path = "src/main/";
        SimpleCrawler simpleCrawler = new SimpleCrawler(url, path);
        simpleCrawler.testDownload();
//        simpleCrawler.bfs();
    }
}
