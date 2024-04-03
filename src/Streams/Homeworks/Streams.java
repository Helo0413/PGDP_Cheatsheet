package Streams.Homeworks;

import pgdp.streams.models.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams {

    public static void main(String[] args) {

        Person[] st = {
                new Person("A", 2),
                new Person("A", 2),
                new Person("E", 2),
                new Person("R", 2),
                new Person("R", 2),
                new Person("D", 2),
                new Person("C", 2),

        };
        Person[] ts = {
                new Person("A", 2),
                new Person("g", 2),
                new Person("k", 2),
                new Person("c", 2),
                new Person("d", 2),
                new Person("o", 2),
                new Person("p", 2),

        };
        Teacher t = new Teacher("Ternando", 22, Subject.ART, st);
        Teacher p = new Teacher("Ternando", 22, Subject.HISTORY, ts);



    }

    /**
     * Teilaufgabe 1
     * Implementiere die Methode `averageAge(Person[] people)`, die das durchschnittliche Alter aller Personen in `people` zurückgibt.
     * Du kannst davon ausgehen, dass `people` mindestens ein Element enthält.
     * Hinweis: Rückgabewert der Methode ist `double`, nicht `OptionalDouble`!
     */
    public double averageAge(Person[] people) {
        return Arrays.stream(people).mapToDouble(Person::getAge).average().orElse(0);
    }

    /**
     * Teilaufgabe 2
     * Implementiere die Methode `createHomeworks(Teacher teacher, int numberOfHomeworks)`, die einen Stream von
     * `numberOfHomeworks`-vielen Hausaufgaben erstellt. Eine Hausaufgabe wird
     * vom `teacher` mittels `teacher.createHomework()` erstellt.
     */
    public Stream<Homework> createHomeworks(Teacher teacher, int numberOfHomeworks) {
        Stream<Homework> homeworks = Stream.generate(() -> teacher.createHomework()).limit(numberOfHomeworks);
        return homeworks;
    }

    /**
     * Teilaufgabe 3
     * Implementiere die Methode `mostDifficultHomework(Stream<Homework> homeworkStream)`, welche die schwierigste Hausaufgabe
     * aus dem Stream `homeworkStream` zurückgibt. Falls `homeworkStream` leer ist, soll `Optional.empty()` zurückgegeben werden.
     * Eine Hausaufgabe gilt als schwieriger als eine andere, wenn ihre `getDifficulty`-Methode einen höheren Wert zurückgibt.
     */
    public Optional<Homework> mostDifficultHomework(Stream<Homework> homeworkStream) {
        return homeworkStream.max(Comparator.comparingInt(Homework::getDifficulty));
    }

    /**
     * Teilaufgabe 4
     * Implementiere die Methode `mostDifficultHomework(Teacher teacher, int numberOfHomeworks)`,
     * die zunächst `numberOfHomeworks`-viele Hausaufgaben mithilfe des `teacher`s erstellt und von diesen die
     * schwierigste Hausaufgabe wie in den Teilaufgaben 2 und 3 zurückgibt.
     */
    public Optional<Homework> mostDifficultHomework(Teacher teacher, int numberOfHomeworks) {
        return mostDifficultHomework(createHomeworks(teacher, numberOfHomeworks));
    }

    /**
     * Teilaufgabe 5
     * Implementiere die Methode `doableHomework(Teacher teacher, int numberOfHomeworks, int thresholdDifficulty)`, die
     * einen Stream von Hausaufgaben wie in Teilaufgabe 2 erstellt, aber nur die Hausaufgaben zurückgibt, deren Schwierigkeit
     * strikt kleiner als `thresholdDifficulty` ist.
     */
    public Stream<Homework> doableHomework(Teacher teacher, int numberOfHomeworks, int thresholdDifficulty) {
        return createHomeworks(teacher, numberOfHomeworks).filter(hw -> hw.getDifficulty() < thresholdDifficulty);
    }

    /**
     * Teilaufgabe 6
     * Implementiere die Methode `doHomeworkUntilTooDifficult(Stream<Homework> homework, int thresholdDifficulty)`. Sie
     * soll alle Hausaufgaben aus `homework` bearbeiten, bis eine Hausaufgabe erreicht wird, die zu schwer (Schwierigkeit größer 
     * oder gleich `thresholdDifficulty`) ist. Die Hausaufgaben sollen dann in einen Stream von `Solution`s umgewandelt werden.
     * Das Fach einer Lösung ist natürlich das gleiche wie das Fach der Aufgabe. Für eine Hausaufgabe mit Schwierigkeit 0 werden 
     * 10 Minuten benötigt und die Dauer einer Hausaufgabe steigt pro Schwierigkeitsstufe um 10 Minuten.
     */
    public Stream<Solution> doHomeworkUntilTooDifficult(Stream<Homework> homework, int thresholdDifficulty) {
        return homework.takeWhile(x -> x.getDifficulty() < thresholdDifficulty)
                //+1 be because there is the difficulty 0, which should take 10 minutes too!
                // Difficulty 1 takes 20 mins ;)

                .map(hw -> new Solution(hw.getSubject(), 10*(hw.getDifficulty()+1)));
    }


    /**
     * Teilaufgabe 7
     * Implementiere die Methode `timeTakenForSubject(Stream<Solution> solutions, Subject subject)`. Sie soll die Zeit berechnen,
     * die für alle Lösungen in `solutions` aus dem Fach `subject` benötigt wird.
     */
    public long timeTakenForSubject(Stream<Solution> solutions, Subject subject) {
        return solutions.filter(x -> x.getSubject().equals(subject)).mapToLong(Solution::getTimeTaken).sum();
    }

    /**
     * Teilaufgabe 8
     * Implementiere die Methode `allStudents(Teacher[] teachers)`. Sie soll einen Stream bestehend aus allen Schülern von jedem
     * Lehrer in `teachers` zurückgeben. Die Schüler sollen dabei im Stream nur einmal vorkommen.
     */
    public Stream<Person> allStudents(Teacher[] teachers) {
        return Arrays.stream(teachers).flatMap(teacher -> Arrays.stream(teacher.getStudents())).distinct();
    }

    /**
     * Teilaufgabe 9
     * Implementiere die Methode `numberOfStudents(Teacher[] teachers)`. Sie soll die Anzahl der Schüler aller Lehrer in `teachers`
     * wie in Teilaufabe 8 zurückgeben.
     */
    public long numberOfStudents(Teacher[] teachers) {
        return allStudents(teachers).count();
    }

    /**
     * Teilaufgabe 10
     * Implementiere als letztes die Methode `allPeopleAlphabeticallyList(Teacher[] teachers)`. Sie soll eine Liste bestehend aus
     * allen Lehrern aus `teachers` und ihren Schülern zurückgeben. Wie in Teilaufgabe 8 sollen die Personen im Stream nur einmal
     * vorkommen. Ferner sollen die Personen in der Liste alphabetisch nach ihrem Namen sortiert sein.
     */
    public List<Person> allPeopleAlphabeticallyList(Teacher[] teachers) {
        //concat joins two different streams into one
        // Comparator.comparing gives you a´Comparator which compares values according to the diven function/predicate
        return Stream.concat(Arrays.stream(teachers), allStudents(teachers)).sorted(Comparator.comparing(Person::getName)).collect(Collectors.toList());
    }
}
