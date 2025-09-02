package nicholas;

import exception.NicholasException;
import parsers.Parser;
import tasks.TaskList;
import storage.Storage;
import ui.Ui;

import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner; //Import the Scanner Class

/**
 * Nicholas chatbot application
 * <p>
 * The core of the application where input is retrieved and displayed for users
 * to interact with.
 */
public class Nicholas {
    private static final Scanner scanner = new Scanner(System.in); // Creating new Scanner Object
    private static final String EXIT_COMMAND = "bye";
    private static final TaskList taskList = new TaskList();
    private static final Storage defaultStorage = new Storage();
    private static final Parser parser = new Parser();
    private static final Ui ui = new Ui();


    public static void main(String[] args) {
        ui.showGreetingMessage();
        defaultStorage.fileSetup();

        while (true) {
            String userInput = scanner.nextLine();
            ui.showDivider();
            if (Objects.equals(userInput, EXIT_COMMAND)) {
                ui.showGoodbyeMessage();;
                break;
            }
            try {
                parser.parseCommand(userInput, taskList);
                ui.showDivider();
                defaultStorage.saveToFile(taskList);
            } catch (NicholasException e){
                ui.showErrorMessage(e.getMessage());
            } catch (DateTimeParseException e) {
                ui.showErrorMessage("Please input the datetime format in (yyyy-mm-dd HH:mm):");
            } catch (NumberFormatException e) {
                ui.showErrorMessage("Invalid number! Please enter in a valid number.");
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showErrorMessage("Please indicate the task number to unmark.");
            }
        }
    }
}
