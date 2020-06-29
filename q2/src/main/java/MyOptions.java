import org.apache.commons.cli.Options;

public class MyOptions {

    private Options options = new Options();

    public MyOptions() {
        makeOptions();
    }

    void makeOptions() {
        this.options.addOption("a", "--total-num-files", false, "Total number of files.");
        this.options.addOption("b", "--total-num-dirs", false, "Total number of directory.");
        this.options.addOption("c", "--total-unique-exts", false, "Total number of unique file extensions.");
        this.options.addOption("d", "--list-exts", false, "List all unique file extensions. Do not list duplicates.");
        this.options.addOption("e", "--num-ext=EXT", true, "List total number of file for specified extension EXT.");
        this.options.addOption("f", true, "path");
        this.options.addOption("h", "help");
    }

    public Options getOptions() {
        return options;
    }

}
