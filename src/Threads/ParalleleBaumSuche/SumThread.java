package Threads.ParalleleBaumSuche;

import java.util.Optional;

public class SumThread extends Thread {
    private Optional<Integer> sum;

    private Node node;

    private int remainingThreads;

    SumThread leftThread;
    SumThread rightThread;


    public SumThread(Node node, int remainingThreads) {
        this.remainingThreads = remainingThreads;
        this.node = node;
        sum = Optional.empty();
    }

    public Optional<Integer> getSum() {
        return sum;
    }

    public void run() {
        //initiate calculatingSum with the current node's value
        int calculatingSum = node.getValue();
        //start all child-threads
        startChildThreads();

        //if left node does not have its own thread
        if (leftThread == null){
            //test this separately so if the node does not have a left node the leftThread.join() is not called
            if (node.getLeft().isPresent()) {
                //if there is indeed a left node, add the result of the sum method of said node to the calculatingSum
                calculatingSum += node.getLeft().get().sum();
            }
        }
        else {
            try{
                //if leftThread is not null, meaning left node has its own thread, wait here until left thread is done.
                leftThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //and then once leftThread has fully run its method and calculated its own sum either based on sum()
            // method or other threads, add the sum of leftThread to the current calculatingSum
            calculatingSum += leftThread.getSum().get();
        }

        //analog for right side now.
        if (rightThread == null) {
            if (node.getRight().isPresent()) {
                calculatingSum += node.getRight().get().sum();
            }
        }
        else {
            try {
                rightThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            calculatingSum += rightThread.getSum().get();
        }

        //set this SumThread's sum attribute equals calculatingSum, so the value may be returned/accessed when
        // called/referred to by other threads or in the main method
        sum = Optional.of(calculatingSum);
    }

    protected void startChildThreads() {
        int leftThreadCount = leftThreadCount();
        int rightThreadCount = remainingThreads - leftThreadCount - 1;

        if (leftThreadCount > 0 && node.getLeft().isPresent()) {
            leftThread = new SumThread(node.getLeft().get(), leftThreadCount);
            leftThread.start();
        }

        if (rightThreadCount > 0 && node.getRight().isPresent()) {
            rightThread = new SumThread(node.getRight().get(), rightThreadCount);
            rightThread.start();
        }


    }

    protected int leftThreadCount() {
        int threadsForChildren = remainingThreads - 1;
        if (node.getLeft().isEmpty()) {
            return 0;
        }
        if (node.getRight().isPresent()) {
            return threadsForChildren / 2;
        }
        return threadsForChildren;
    }

}
