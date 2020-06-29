import org.apache.commons.cli.Options;
import org.apache.commons.io.DirectoryWalker;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MyDirectoryWalker extends DirectoryWalker {

    private int numFile;
    private int numDir;
    private Map<String, Integer> extMap = new HashMap<>();

    private File startDir;
    private MyCLI myCLI;

    public MyDirectoryWalker(String root, MyCLI myCLI) {
        super();
        this.startDir = new File(root);
        this.myCLI = myCLI;
        walk();
    }

    void walk() {
        try {
            this.walk(startDir, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected boolean handleDirectory(File directory, int depth, Collection results) throws IOException {
        if (myCLI.hasOption('b')) {
            numDir++;
        }
        return super.handleDirectory(directory, depth, results);
    }

    @Override
    protected void handleFile(File file, int depth, Collection results) {
        if (myCLI.hasOption('a')) {
            numFile++;
        }
        if (myCLI.hasOption('c') || myCLI.hasOption('d') || myCLI.hasOption('e')) {
            String ext = FilenameUtils.getExtension(file.getName());
            if (ext != "") {
                if (extMap.containsKey(ext)) {
                    int tmp = extMap.get(ext);
                    extMap.replace(ext, tmp+1);
                } else {
                    extMap.put(ext, 1);
                }
            }
        }
    }

    public int getNumFile() {
        return numFile;
    }

    public int getNumDir() {
        return numDir;
    }

    public Map<String, Integer> getExtMap() {
        return extMap;
    }

}
