import java.io.File;

public class Main {

    public static void main(String[] args) {
        String path = "/home/max/work/ooc/hw/docs";
        File startDir = new File(path);
        MyDirectoryWalker myDirectoryWalker = new MyDirectoryWalker(startDir);
        Printer printer = new Printer(myDirectoryWalker);
        printer.printNumFile();
        printer.printNumDir();
        printer.printUniqueExt();
        printer.listUniqueExt(false);
        printer.listUniqueExt(true);
    }
}
