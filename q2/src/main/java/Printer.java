import java.util.Arrays;
import java.util.Map;

public class Printer {

    private MyDirectoryWalker myDirectoryWalker;
    private MyCLI myCLI;

    public Printer(MyDirectoryWalker walker, MyCLI myCLI) {
        this.myDirectoryWalker = walker;
        this.myCLI = myCLI;
    }

    void printNumDir() {
        System.out.printf("Total number of directories: %d.\n", myDirectoryWalker.getNumDir());
    }

    void printNumFile() {
        System.out.printf("Total number of files: %d.\n", myDirectoryWalker.getNumFile());
    }

    void printNumUniqueExt() {
        System.out.printf("Total number of unique file extensions: %d.\n", myDirectoryWalker.getExtMap().size());
    }

    void listUniqueExt(boolean flag) {
        if (flag) {
            for (Map.Entry<String, Integer> entry : myDirectoryWalker.getExtMap().entrySet()) {
                String key = entry.getKey();
                int val = entry.getValue();
                System.out.printf("%s:%d\n", key, val);
            }
        } else {
            System.out.println(Arrays.toString(myDirectoryWalker.getExtMap().keySet().toArray()));
        }
    }

    void printSpecifiedExt(String ext) {
        System.out.printf("%s: %d.\n", ext, myDirectoryWalker.getExtMap().get(ext));
    }

    void printResults() {
        if (myCLI.hasOption('a')) {
            printNumFile();
        }
        if (myCLI.hasOption('b')) {
            printNumDir();
        }
        if (myCLI.hasOption('c')) {
            printNumUniqueExt();
        }
        if (myCLI.hasOption('d')) {
            listUniqueExt(false);
        }
        if (myCLI.hasOption('e')) {
            printSpecifiedExt(myCLI.getOptionValue('e'));
        }
    }

}
