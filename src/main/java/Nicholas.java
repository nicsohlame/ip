public class Nicholas {
    public static String line = "--------------------";
    public static void main(String[] args) {
        System.out.println(line);
        greet();
        exit();
    }

    public static void greet() {
        System.out.println("Hello! I'm Nicholas");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
