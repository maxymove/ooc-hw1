public class Main {

    public static void main(String[] args) {
        String url = "https://ooc.cs.muzoo.io/docs/index.html";
        String path = "/home/max/Downloads/tmp/";
        SimpleCrawler simpleCrawler = new SimpleCrawler(url, path);
        simpleCrawler.bfs();
    }
}
