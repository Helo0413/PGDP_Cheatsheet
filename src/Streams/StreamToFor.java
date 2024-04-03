package Streams;

public class StreamToFor {

    // TODO 1.1
    public static void printSquares() {
        byte[] a = {3,1,-4};
        for(byte i : a){
            i = (byte) (i*i);
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        printSquares();
    }
}
