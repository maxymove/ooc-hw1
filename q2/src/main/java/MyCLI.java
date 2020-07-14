import org.apache.commons.cli.*;

import java.io.File;

public class MyCLI {

    private CommandLineParser parser = new DefaultParser();
    private CommandLine cmd;
    private MyOptions myOptions = new MyOptions();

    public MyCLI(String[] args) {
        try {
            this.cmd = parser.parse(myOptions.getOptions(), args);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    boolean hasOption(char option) {
        return cmd.hasOption(option);
    }

    String getOptionValue(char option) {
        return cmd.getOptionValue(option);
    }
}
