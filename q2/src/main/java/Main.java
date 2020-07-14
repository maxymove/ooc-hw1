import java.io.File;

public class Main {

    public static void main(String[] args) {
        // parse arg
        // get options
        // parse available options into walker
        // start walking
        String path = "/home/max/work/ooc/hw/docs";
        File startDir = new File(path);
        String[] args2 = {"-f", startDir.toString(), "-b", "-a", "-c", "-d", "-e", "pdf"};
        MyCLI myCLI = new MyCLI(args2);
        MyDirectoryWalker myDirectoryWalker = new MyDirectoryWalker(myCLI.getOptionValue('f'), myCLI);
        new Printer(myDirectoryWalker, myCLI).printResults();
    }
}
