package Streams.Homeworks;

public class Solution {
    private Subject subject;
    private int timeTaken;

    public Solution(Subject subject, int timeTaken) {
        this.subject = subject;
        this.timeTaken = timeTaken;
    }

    public Subject getSubject() {
        return subject;
    }

    public int getTimeTaken() {
        return timeTaken;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Solution)) {
            return false;
        }
        Solution other = (Solution) o;
        return other.subject.equals(subject) && other.timeTaken == timeTaken;
    }
}
