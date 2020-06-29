import java.io.File;

public class Main {

    public static void main(String[] args) {
        File startDir = new File("/home/max/work/ooc-2019-t3/docs");
        MyDirectoryWalker myDirectoryWalker = new MyDirectoryWalker(startDir);
        Printer printer = new Printer(myDirectoryWalker);
        printer.printNumFile();
        printer.printNumDir();
        printer.printUniqueExt();
        printer.listUniqueExt(false);
        printer.listUniqueExt(true);
    }
}
