public class Main {

    public static void main(String[] args) {
        String fileURL = "https://corretto.aws/downloads/latest/amazon-corretto-8-x64-linux-jdk.deb";
        String outPath = "/home/max/Downloads/tmp/";

        MyURLConnection myURLConnection = new MyURLConnection();
        myURLConnection.download(fileURL, outPath);

        MyApacheCommonsIO myApacheCommonsIO = new MyApacheCommonsIO();
        myApacheCommonsIO.download(fileURL, outPath);

        MyApacheHttpComponent myApacheHttpComponent = new MyApacheHttpComponent();
        myApacheHttpComponent.download(fileURL, outPath);
    }
}
