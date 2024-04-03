package Exceptions;

public class NotEnoughLowerCaseException extends NotEnoughLetterException{
    public NotEnoughLowerCaseException(int should, int is) {
        super(should, is);
    }
    @Override
    public String toString() {
        return "NotEnoughLowerCaseException: not enough lower case letters!\n"
                + super.toString();
    }
}
