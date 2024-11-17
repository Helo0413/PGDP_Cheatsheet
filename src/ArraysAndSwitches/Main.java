package ArraysAndSwitches;// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.Arrays;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        System.out.println("Wählen Sie eine Operation:\n1) +\n2) -");
        // \n for new line -> no need for multiple souts!

        //psvm for main!



        int a = 1;

        for(; a< 5; ++a){}
        System.out.println(a);

        // (number & (number - 1)) == 0 for testing if the number is a power  of two!


        //Calculates a to the power of 2
        System.out.println(Math.pow(a,2));

        //int[][] tester = new int[0][];
        int[][] tester = new int[][]{{1,2,3}, {2,4,5}};

        for (int i = 0; i < tester.length; i++) {
            for (int j = 0; j < tester[i].length; j++) {
                System.out.print(tester[i][j] + " ");
            }
            System.out.println();


        }

        int[][] rr = new int[][]{
                {0, 3, 4},
                {5, 7, 8}
        };

        System.out.println("x = 1, y = 0 rr[x][y] = " + rr[1][0] + " rr[y][x] = " + rr[0][1]);
        // rr[x][y] = 5, rr[y][x] = 3!!!
        // CONCLUSION: read arrays down and then right; Y FIRST THEN X!!!

        //Arrays.deepToString(array) delivers 2D array as String; For one dimensional arrays use Array.toString(tester)
        String s = Arrays.deepToString(tester);

       // super(value, new Node[2]);
        System.out.println(Arrays.toString(new int[]{112,12}));
        String s1 = Arrays.toString(new int[]{112,12});

        String s2 = Arrays.toString(new int[0]); // new int[0] and new int[]{}!!!

        String s3 = Arrays.toString(new int[12]);

        System.out.println(s1 + s2 + s3);

        Main main = new Main();
        main.readInput();
    }

    public static void readInput(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println("IN: " + input); //reads String as user input

        int integerInput = scanner.nextInt(); // reads Integer as user input
        System.out.println("IN :" + integerInput);

        //the next() designated methods from Scanner stops the program run and waits for/until an input is given

        // (!!!) requires: import.java.util.Scanner.* (!!!)
    }

    /*public static List mergeSort(List a, List b){
        if(b==null) return a;
        if(a==null) return b;
        if(b.info > a.info){
            a.next = mergeSort(a.next,b);
            System.out.println(a.info + "determined, a.next merging to " + a.next);
            return a;
        } else{
            b.next = mergeSort(a,b.next);
            System.out.println(b.info + "determined, b.next merging to " + b.next);
            return b;
        }
    }*/


    public static int[] resize(int[] a, int length) {
        if(length <= 0) return new int[0];
        int[] resized = new int[length];
        for (int i = 0; i < length; i++) {
            if(i < a.length){
                resized[i] = a[i];
            } else {
                resized[i] = 0;
            }
        }

        return resized;
    }

    public static int countIndexes(int[][] a){
        int indexes = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                indexes++;
            }
        }
        return indexes;
    }
    public static boolean alreadyContained(int[] a, int x, int count){
        for (int i = 0; i < count; i++) {
            if (a[i] == x) return true;
        }
        return false;
    }

    public static boolean isOrderedAscendingly(int[] a) {
        boolean isOrdered = true;
        for(int i = 0; i < a.length - 1; i++) {
            isOrdered &= a[i] <= a[i + 1];
            //bitwise AND operator, if a[i] <= a[i+1] AND && isOrdered then isOrdered = true;
            // if either isOrderd was previously assigned false, or a[i] > a[i+1] then isOrdered = false;
        }
        return isOrdered;
    }

    public static void minAndMax(int[] a) {
        if(a.length == 0) return;
        int min = a[0];
        int max = min;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < min){
                min = a[i];
            }
            if(a[i]> max) {
                max = a[i];
            }
        }
        System.out.println("Minimum = " + min + ", Maximum = " + max);
    }
    public static int[][] minsAndMaxs(int[][] a) {
        if(a.length == 0) return new int[0][0];

        int[][] miniMax = new int[a.length][2];
        for (int i = 0; i < a.length; i++) {
            miniMax[i] = miniMax(a[i]);

        }

        return miniMax;
    }
    public static int[] miniMax(int[] a){
        if(a.length == 0) return new int[0];
        int[] miniMax = new int[2];
        int min = a[0];
        int max = a[0];

        for(int i = 0; i < a.length; i++){
            if(a[i] < min){
                min = a[i];
            }
            if(a[i] > max){
                max = a[i];
            }
        }
        miniMax[0] = min;
        miniMax[1] = max;

        return miniMax;
    }

    public static void invert(int[] a) {
        for (int i = 0; i < a.length/2; i++) {
            int tmp = a[i];
            int invertedIndex = a.length - 1 - i;
            a[i] = a[invertedIndex];
            a[invertedIndex] = tmp;
        }
    }

    public static int quersumme(int penguin) {
        int sum = 0;
        while (penguin != 0) {
            sum += penguin % 10; // gives the last digit of penguin and adds it to sum
            penguin /= 10; //divides penguin by ten to move for next digit
        }
        return sum;
    }

    public static int recursiveQuersumme(int penguin) {
        if (penguin <= 9) {
            return penguin;
        } else{
            int sum = penguin % 10 + recursiveQuersumme(penguin/10);
            return sum;
        }
    }


    public static long permutation(long n, long k) {
        long permutations = 1;
        for (long i = 0; i < n-k ; i++) {
            permutations = permutations* (n-i);
        }

        return permutations;
    }

    public static int[] distinct(int[] a) {
        int[] distinct = new int[a.length];
        int count = 0;

        for (int i = 0; i < a.length; i++) {
            if(!alreadyContained(distinct,a[i], count)){
                distinct[count] = a[i];
                count++;
            }
        }

        return resize(distinct, count);
    }

    //SWITCH USAGE EXAMPLES
    public static int daysInMonth(int month) {
        int days;
        switch (month) {
            case 4, 6, 9, 11:
                days = 30;
                break;
            case 2:
                days = 28;
                break;
            default:
                days = 31;
                break;
        }
        String monthName = switch(month) {
            case 1 -> "Januar";
            case 2 -> "Februar";
            case 3 -> "März";
            case 4 -> "April";
            case 5 -> "Mai";
            case 6 -> "Juni";
            case 7 -> "Juli";
            case 8 -> "August";
            case 9 -> "September";
            case 10 -> "Oktober";
            case 11 -> "November";
            case 12 -> "Dezember";
            default -> {
                System.out.println("not a month!");
                yield "";

                //In Java, every code path in a switch expression must produce a value. The default case is no
                // exception. Even if in your specific logic, you know that the default case will set a variable and
                // not directly produce a value, Java syntax still requires that a value is produced.

            }
        };

        return days;
    }

    public static String locationOfLectureHall(String hall) {
        return switch(hall) {
            case "MI HS 2", "Interims I 1" -> "Informatik";
            case "MW0001", "MW2001" -> "Maschinenwesen";
            case "Interims II 2" -> "Chemie";
            case "Carl-von-Linde", "N1190" -> "Innenstadt";
            default -> "Unbekannter Hörsaal";
        };


    }

    public static int daysLeftInYearAfter(int day, int month, int year) {
        int days = 0;
        switch(month) {
            case 1: days += 31;
            case 2: days += 29;
            case 3: days += 31;
            case 4: days += 30;
            case 5: days += 31;
            case 6: days += 30;
            case 7: days += 31;
            case 8: days += 31;
            case 9: days += 30;
            case 10: days += 31;
            case 11: days += 30;
            case 12: days += 31 - day; break;
            default:
                days = -1;
        }

        //There is no break statement between the cases,
        // which means that once a case is matched, the control falls
        // through to subsequent cases. This leads to cumulative
        // addition of days for each subsequent month.
        return days;
    }
}
