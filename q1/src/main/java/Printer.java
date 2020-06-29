import java.util.Arrays;
import java.util.Map;

public class Printer {

    private MyDirectoryWalker myDirectoryWalker;

    public Printer(MyDirectoryWalker walker) {
        this.myDirectoryWalker = walker;
    }

    void printNumDir() {
        System.out.printf("Total number of directories: %d.\n", myDirectoryWalker.getNumDir());
    }

    void printNumFile() {
        System.out.printf("Total number of files: %d.\n", myDirectoryWalker.getNumFile());
    }

    void printUniqueExt() {
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

}
