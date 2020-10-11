package Chapter01_Solutions;

import java.io.File;
import java.io.FileFilter;

public class FilterHiddenFiles {

    public static void main(String... args) {

        // The old method
        printHiddenFiles(".");

        // The new Java 8 method
        File[] hiddenFiles = new File(".").listFiles(File::isHidden);

    }

    private static void printHiddenFiles(String pathname) {

        File[] hiddenFiles = new File(pathname).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isHidden();
            }
        });

        for (File file : hiddenFiles) {
            System.out.println(file.getName());
        }
    }
}
