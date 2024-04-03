package GenericCollections.Iterators.JobManager;

import java.util.*;

public class JobIterator implements Iterator<Job> {

    protected int[] sortedPriorityLevels;
    protected boolean hasItems;
    private int currentPriorityLevelIndex;
    private Map<Integer, List<Job>> priorityGroups;
    protected Iterator<Job> currentGroupIterator;

    public JobIterator(Map<Integer, List<Job>> priorityGroups) {
        // The constructor implies a certain solution strategy.
        // You can change it if you want to, but you are recommended to keep it as it is.
        // However, you MUST keep the first line and the signature of the constructor.
        this.priorityGroups = priorityGroups;
        if (priorityGroups.isEmpty()) {
            hasItems = false;
            return;
        }
        hasItems = true;
        sortedPriorityLevels = getSortedPriorities();

        currentPriorityLevelIndex = sortedPriorityLevels.length-1;
        int highestPriority = sortedPriorityLevels[currentPriorityLevelIndex];
        currentGroupIterator = priorityGroups.get(highestPriority).iterator();
    }

    // TODO The following methods are not implemented correctly.
    // You can use them for testing the other methods.
    // Without any modification, the iterator will
    // iterate over all jobs with the priority 3.


    protected int[] getSortedPriorities() {
        int[] priorities =new int[priorityGroups.keySet().size()];

        int counter = 0;

        for (int i : priorityGroups.keySet()){
            priorities[counter++] = i;
        }
        Arrays.sort(priorities);
        // If you are stuck, you can test the other methods with a fixed return value.
        return priorities;
    }


    @Override
    public boolean hasNext() {

        return currentGroupIterator != null && (currentGroupIterator.hasNext() || currentPriorityLevelIndex>0);
    }

    @Override
    public Job next() {
        if(!hasNext()) {
            return null;
        }
        if(currentGroupIterator.hasNext()){
            return currentGroupIterator.next();
        }else{
            currentPriorityLevelIndex--;
            int highestPriority = sortedPriorityLevels[currentPriorityLevelIndex];
            currentGroupIterator = priorityGroups.get(highestPriority).iterator();
        }
        return currentGroupIterator.next();
    }
}
