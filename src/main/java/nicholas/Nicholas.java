package nicholas;

import exception.NicholasException;
import parsers.Parser;
import tasks.TaskList;
import storage.Storage;
import ui.Ui;

import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner; //Import the Scanner Class




public class Nicholas {
    public static final Scanner scanner = new Scanner(System.in); // Creating new Scanner Object
    public static final String exitCommand = "bye";
    public static final TaskList task = new TaskList();
    public static final Storage defaultStorage = new Storage();
    public static final Parser parser = new Parser();
    public static final Ui ui = new Ui();


    public static void main(String[] args) {
        ui.showGreetingMessage();
        defaultStorage.fileSetup();

        while(true) {
            String userInput = scanner.nextLine();
            ui.showDivider();
            if (Objects.equals(userInput, exitCommand)) {
                ui.showGoodbyeMessage();;
                break;
            }
            try {
                parser.parseCommand(userInput, task);
                ui.showDivider();
                defaultStorage.saveToFile(task);
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
