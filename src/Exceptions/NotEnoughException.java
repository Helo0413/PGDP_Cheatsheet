package Exceptions;

public class NotEnoughException extends Exception{
    private final int should;
    private final int is;
    public NotEnoughException(int should, int is){
        if (is >= should)
            throw new IllegalArgumentException();
        this.is = is;
        this.should = should;
    }
    @Override
    public String toString() {
        return "Should: " + should + ", but is " + is + ".";
    }
}
