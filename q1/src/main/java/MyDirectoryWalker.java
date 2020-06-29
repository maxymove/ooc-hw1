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

    public MyDirectoryWalker(File root) {
        super();
        try {
            walk(root, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected boolean handleDirectory(File directory, int depth, Collection results) throws IOException {
        numDir++;
        return super.handleDirectory(directory, depth, results);
    }

    @Override
    protected void handleFile(File file, int depth, Collection results) {
        numFile++;
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
