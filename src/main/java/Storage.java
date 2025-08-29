import java.io.File;
import java.io.IOException;

/**
 * Handles file storage and writing
 */
public class Storage {

    /* Default file path */
    private static final String STORAGE_PATH = "./tasks/Nicholas.txt";

    /* Handles folder and file creation */
    public void fileSetup() {
        File file = new File(STORAGE_PATH);

        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}

