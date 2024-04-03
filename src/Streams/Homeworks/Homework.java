package Streams.Homeworks;

public class Homework {
    private int difficulty;
    private Subject subject;

    public Homework(int difficulty, Subject subject) {
        this.difficulty = difficulty;
        this.subject = subject;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public Subject getSubject() {
        return subject;
    }
}
