package Threads.SynchroneListen;

public class RW {

    private int readerCount = 0;
    public synchronized void startRead() throws InterruptedException {
        //makes sure that no one is writing at the moment -> symbolized by negative reader number
        while(readerCount < 0){
            wait();
        }

        //afterward increase reader count -> more than one Thread may then read at the same time
        readerCount++;
    }

    public synchronized void endRead() {
        //when you are done with reading, decrease the counter -> once al of the readerThreads are done, the count = 0
        readerCount--;
        if(readerCount == 0){
            //notify so that countWriter knows it must not wait any longer, since no one is reading anymore
            //notifies ONLY ONE thread so ONLY ONE writer thread may alter the list -> other ReaderThreads are
            // already awake and working!
            notify();
        }
    }

    public synchronized void startWrite() throws InterruptedException {
        //readerCount must be 0 if a Thread wants to alter something within the list
        while(readerCount != 0){
            wait();
        }

        readerCount = -1;
    }


    public synchronized void endWrite() {

        //notifyAll since while writing, everyone waits until they can do their thing
        readerCount = 0;
        //NotifyAll since ALL Threads are waiting -> MANY reader threads may now READ the list
        // eventually another Writer Thread may now take over the spot if scheduler runs it first!!!
        notifyAll();

    }
}