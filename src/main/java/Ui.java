/**
 * Handles displays shown to user
 */
public class Ui {

    /* Divider */
    private static final String LINE = "--------------------";

    /* Goodbye Message */
    private static final String EXIT = "Goodbye :)";


    /* Shows message to user */
    public void showUser(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }

    /* Shows goodbye message */
    public void showGoodbyeMessage() {
        showUser(EXIT, LINE);
    }

    /* Show error message */
    public void showErrorMessage(String message) {
        String error = "[Nicholas]: " + message;
        showUser(error, LINE);
    }
}
