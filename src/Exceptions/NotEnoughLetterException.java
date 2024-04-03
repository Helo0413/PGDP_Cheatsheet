package Exceptions;

public class NotEnoughLetterException extends NotEnoughException{
    public NotEnoughLetterException(int should, int is) {
        super(should, is);
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
