package Exceptions.CalculatorExceptions;

import java.util.function.Function;

public class Main {

    public static void main(String[] args) {

    }

    public static void showTableWithExceptionHandling(double start, double end, double step, Function<Double, Double> func) {
        try {
            showTable(start, end, step, func);
        } catch (InvalidRangeException e){
            System.out.println("Error: " + e.toString());

        }
    }

    public static void showTable(double start, double end, double step, Function<Double, Double> func) throws InvalidRangeException {
        if(! isValidRange(start, end, step)){
            throw new InvalidRangeException(start, end, step);
        } else {
            double x = start;
            for (double i = 0; i < Math.abs(end-start); i+=step) {
                System.out.println(x + "\t" + func.apply(x));
                x+= step;
            }

        }



    }

    protected static boolean isValidRange(double start, double end, double step){
        if (step == 0) {
            return false;
        }
        if (step > 0) {
            return start < end;
        }
        else {
            return start > end;
        }
    }
}
