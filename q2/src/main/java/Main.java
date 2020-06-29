import java.io.File;

public class Main {

    public static void main(String[] args) {
        // parse arg
        // get options
        // parse available options into walker
        // start walking
        File startDir = new File("/home/max/work/ooc-2019-t3/docs");
        String[] args2 = {"-f", startDir.toString(), "-b", "-a", "-c", "-d", "-e", "pdf"};
        MyCLI myCLI = new MyCLI(args2);
        MyDirectoryWalker myDirectoryWalker = new MyDirectoryWalker(myCLI.getOptionValue('f'), myCLI);
        new Printer(myDirectoryWalker, myCLI).printResults();
    }
}
