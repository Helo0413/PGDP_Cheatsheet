package Streams.Homeworks;

import java.util.Random;

public class Teacher extends  Person {
    private Subject subject;
    private Person[] students;

    // Note that the tests will set this field to a different value
    private int randomSeed = 0;
    private Random random;

    public Teacher(String name, int age, Subject subject, Person[] students) {
        super(name, age);
        this.subject = subject;
        this.students = students;
        this.random = new Random(randomSeed);
    }

    public Subject getSubject() {
        return subject;
    }

    public Person[] getStudents() {
        return students;
    }

    public void setRandomSeed(int seed) {
        this.randomSeed = seed;
        this.random = new Random(seed);
    }

    public Homework createHomework() {
        return new Homework(random.nextInt(10), subject);
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Teacher))
            return false;
        Teacher other = (Teacher) o;
        return super.equals(other) && subject == other.subject;
    }
}
