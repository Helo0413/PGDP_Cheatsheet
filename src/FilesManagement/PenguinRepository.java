package FilesManagement;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class PenguinRepository implements Repository<Penguin> {
    @Override
    public void save(Penguin p, String filename) {

        // BufferedWriter in a try with source, ensures the bw will be closed after the execution of the try-block
        // regardless of whether the code come to abrupt completion due to throwing an exception. Closing the writer
        // ensures that any buffered data is properly written to the file before the resources are released
        // -> data would be lost with sudden termination. Ensures data integrity.
        // Avoid memory leaks and stops resource exhaustion if program opens too many files without closing them.
        // The BufferedWriter receives as a parameter a new FileWriter which creates a new file with name filename
        // In which information shall be stored/file we want to write on.
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))){
            bw.write(p.name() + "\n");
            bw.write(p.age() + "\n");
            bw.write(p.weight() + "\n");

        } catch(IOException e){

            //Exception handling: System.err.println for printing error messages or diagnostics.
            // System.in is for reading input from the console
            // System.out is for printing output to the console
            System.err.println("Error while saving penguin to file");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Penguin load(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))){

            //br reads a new line of the file with br.readLine, starting from the first line.
            //We assign the data to individual variables for better code readability.
            // With Integer.parseInt(String) & Double.parseDouble(String) we transform text into the desired data types.

            String name = br.readLine();
            int age = Integer.parseInt(br.readLine());
            double weight = Double.parseDouble(br.readLine());

            return new Penguin(name, age, weight);
            // return new Penguin(br.readLine(), Integer.parseInt(br.readLine()), Double.parseDouble(br.readLine()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
