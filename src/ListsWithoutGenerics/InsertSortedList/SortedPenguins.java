package ListsWithoutGenerics.InsertSortedList;

public class SortedPenguins {

    public static void main(String[] args) {
        System.out.println(sortPenguins(new int[] {8, 3, 2, 1, 7}));
    }

    public static List sortPenguins(int[] ages) {
        List sortedPenguins = new List();
        for (int age: ages) {
            sortedPenguins.insertIntoSorted(age);
        }
        return sortedPenguins;
    }
}
