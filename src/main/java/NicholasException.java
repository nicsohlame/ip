public class NicholasException extends Exception{
    public NicholasException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return  getMessage();
    }
}
