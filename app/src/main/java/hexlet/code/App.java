package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "display this help message")
    boolean usageHelpRequested;

    @Option(names = {"-V", "--version"}, versionHelp = true, description = "display version info")
    boolean versionInfoRequested;

    @Override
    public Integer call() throws Exception {

        return 0;
    }

    public static void main(String[] args) {
        App app = CommandLine.populateCommand(new App(), args);
        if (app.usageHelpRequested) {
            CommandLine.usage(new App(), System.out);
        } else if (app.versionInfoRequested) {
            System.out.println("1.0");
        }
    }
}
