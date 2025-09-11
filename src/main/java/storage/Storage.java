package storage;

import tasks.TaskList;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

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

    /* Write to file */
    public void saveToFile(TaskList tasks) {
        try {
            assert !new File(STORAGE_PATH).exists() : "File not setup yet";
            FileWriter writer = new FileWriter(STORAGE_PATH, false);

            writer.write(tasks.toString());


            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

