public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(){
        super();
    }
    public InvalidPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

}
