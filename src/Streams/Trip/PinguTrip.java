package Streams.Trip;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

final public class PinguTrip {

    // To hide constructor in utility class.
    private PinguTrip() {}

    public static Stream<WayPoint> readWayPoints(String pathToWayPoints) {

        try {
            //takes the lines of the file pathToWayPoints references to. Those lines are a stream of strings 1:line
            return Files.lines(Path.of(pathToWayPoints))
                    .takeWhile(line -> !line.equals("---"))
                    .filter(line -> !line.startsWith("//"))
                    //uses the method of WayPoint that changes a one-line String of two coordinates to create a new
                    //WayPoint Object which is then returned by the method. So in the end we have a Stream of points
                    .map(WayPoint::ofString);
        } catch (IOException ioe) {
            //catches the exception if the pathway to the file is incorrect/there is a problem along the way.
            return Stream.empty();
        }
    }

    public static Stream<OneWay> transformToWays(List<WayPoint> wayPoints) {
        //  IntStream.Range gives you a Stream within the range of the list indexes -1,
        //  so that one new OneWay is created for each of the indexes with list.get(index i) as a start point
        //  and with get(i+1) as an endpoint - meaning the next element in the list.
        return IntStream.range(0, wayPoints.size()-1).mapToObj((x) -> new OneWay(wayPoints.get(x), wayPoints.get(x+1)));

    }

    public static double pathLength(Stream<OneWay> oneWays) {
        return oneWays.mapToDouble(OneWay::getLength).reduce(0, Double::sum);
    }

    public static List<OneWay> kidFriendlyTrip(List<OneWay> oneWays) {
        // The ways that are not longer than avg length in the entire route
        double average = oneWays.stream().mapToDouble(OneWay::getLength).average().orElse(0.0);
        //REMEMBER!!! .toList or .collect(Collectors.toList()) only work if the stream has not been mapToInt/Double!!!
        // takeWhile instead of filter since we stop as soon as the length becomes too big!!!
        return oneWays.stream().takeWhile(x -> x.getLength() <= average).collect(Collectors.toList());


    }

    public static WayPoint furthestAwayFromHome(Stream<WayPoint> wayPoints, WayPoint home) {
        return wayPoints.max(Comparator.comparingDouble(wayOne -> wayOne.distanceTo(home))).orElse(home);
    }

    public static boolean onTheWay(Stream<OneWay> oneWays, WayPoint visit) {
        return oneWays.anyMatch(way -> way.isOnPath(visit));
    }

    public static String prettyDirections(Stream<OneWay> oneWays) {
        return oneWays.map(OneWay::prettyPrint).collect(Collectors.joining("\n"));
    }

    public static void main(String[] args) {
        List<WayPoint> wayPoints = readWayPoints("test_paths/path.txt").toList();
        // List.of(new WayPoint(4.0, 11.5), new WayPoint(19.1, 3.2));

        List<OneWay> oneWays = transformToWays(wayPoints).toList();
        // List.of(new OneWay(new WayPoint(4.0, 11.5), new WayPoint(19.1, 3.2)));

        double length = pathLength(oneWays.stream());
        // 17.230 ...

        List<OneWay> kidFriendly = kidFriendlyTrip(oneWays);
        // List.of(new OneWay(new WayPoint(4.0, 11.5), new WayPoint(19.1, 3.2)));

        WayPoint furthest = furthestAwayFromHome(wayPoints.stream(), wayPoints.get(0));
        System.out.println(furthest);
        // new WayPoint(19.1, 3.2);

        boolean onTheWay = onTheWay(oneWays.stream(), new WayPoint(0.0, 0.0));
        // false

        onTheWay = onTheWay(oneWays.stream(), new WayPoint(19.1, 3.2));
        // true

        String directions = prettyDirections(Stream.of(new OneWay(new WayPoint(0.0, 0.0), new WayPoint(1.0, 0.0)),
                new OneWay(new WayPoint(1.0, 0.0), new WayPoint(3.0, 0.0)),
                new OneWay(new WayPoint(3.0, 0.0), new WayPoint(4.0, 1.0))));
        // "25 Schritte Richtung 331 Grad."
    }

}
