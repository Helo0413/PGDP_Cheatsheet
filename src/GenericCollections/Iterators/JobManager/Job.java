package GenericCollections.Iterators.JobManager;

import java.util.Objects;

/**
 * This class has been implemented for you.
 * You should not need to change anything here.
 */
public class Job {
    private int id;
    private int durationInMinutes;

    public Job(int id, int durationInMinutes) {
        this.id = id;
        this.durationInMinutes = durationInMinutes;
    }

    public int getId() {
        return id;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public String toString() {
        return "Job " + id + " (" + durationInMinutes + " minutes)";
    }

    public boolean equals(Object o) {
        if (o instanceof Job other) {
            return id == other.id && durationInMinutes == other.durationInMinutes;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(id, durationInMinutes);
    }
}
