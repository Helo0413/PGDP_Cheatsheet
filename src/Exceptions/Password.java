package Exceptions;

public class Password {
    private final int nrUpperShould, nrLowerShould, lengthShould;
    private final char[] illegalChars;

    private static boolean matchesIllegalCharacter(
            char[] illegalChars,
            char c
    ) {
        for (char illegalChar : illegalChars)
            if (c == illegalChar) {
                return true;
            }
        return false;
    }

    public Password(int nrUpperShould, int nrLowerShould, int lengthShould, char[] illegalChars) {
        this.nrUpperShould = nrUpperShould;
        this.nrLowerShould = nrLowerShould;
        this.lengthShould = lengthShould;
        this.illegalChars = illegalChars;
    }

    public void checkFormat(String pwd) throws IllegalCharException, NotEnoughException{

        //the checkFormat method checks if the format is how it should, if not, throws the necessary exceptions


        int nrUpper, nrLower, length;
        nrUpper = 0;
        nrLower = 0;
        length = pwd.length();

        if(pwd.length() < lengthShould){
            throw new NotLongEnoughException(lengthShould, length);
            //throws exception if the length is not big enough
        }

        for (int i = 0; i < length; i++) {
            if (matchesIllegalCharacter(illegalChars, pwd.charAt(i))) {

                //throws exception if the char is not an allowed char eg $%@...
                throw new IllegalCharException(pwd.charAt(i));

            } else if (pwd.charAt(i) >= 'a' && pwd.charAt(i) <= 'z') {
                nrLower++;
                //counts lower cases
            } else if (pwd.charAt(i) >= 'A' && pwd.charAt(i)<= 'Z') {
                //counts upper cases
                nrUpper++;
            }
        }

        if(nrUpper < nrUpperShould){
            //throws exception if the amount of upper cases is not enough
            throw new NotEnoughUpperCaseException(nrUpperShould, nrUpper);
        }

        if(nrLower < nrLowerShould){
            //throws exception if the amount of lower cases is not enough
            throw new NotEnoughLowerCaseException(nrLowerShould, nrLower);
        }

    }

    public void checkFormatWithLogging(String pwd) throws IllegalCharException,NotEnoughException{
        try{
            //tries to run the checkFormat method, if exceptions are thrown, stop and run catch block
            checkFormat(pwd);
        } catch (NotEnoughException | IllegalCharException e){
            //prints the toString method of the exception on the console
            System.out.println(e);

            //rethrows the caught exception -> exception will propagate up the call stack
            // if this method is called, the instance of the call will have to either handle the exception
            // or rethrow it further up the stack.
            throw e;
        }
    }
}
