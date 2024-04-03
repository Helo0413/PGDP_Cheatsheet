package Threads.BufferKlausur;

import java.util.concurrent.Semaphore;

public class Buffer {
	private Exam[] data;
	private Semaphore free;
	private Semaphore occupied;
	int first = 0, last = 0;

	public Buffer(int maxSize) {
		if (maxSize < 0)
			throw new IllegalArgumentException(
					"Buffer must have positive size!");
		data = new Exam[maxSize];
		//free Semaphore stands for the amount of free slots in the buffer data-array a thread can add an exam to
		free = new Semaphore(maxSize);
		// while the occupied Semaphore refers to the amount of exams already in the buffer data
		occupied = new Semaphore(0);

	}

	public Exam consume() throws InterruptedException {
		Exam consume;

		//if a tutor wants to take an exam from the buffer to correct it, thy have to acquire a "permit" from the
		// occupied buffer, in order to get the exam. If the buffer is zero, there are no elements/exams to be acquired
		//and the thread must wait for the buffer to be loaded again.
		occupied.acquire();
		synchronized (this){
			consume = data[first];
			first = (first+1)% data.length;
		}

		//after acquiring an exam, the tutor must "add" a free slot in the buffer data array, since the slot of the
		// taken exam is now free for others to use/add exams to. Basically releasing the permit taken from occupied
		// to the free Semaphore
		free.release();
		return consume;
	}

	public void produce(Exam e) throws InterruptedException {

		//here the principle is similar. If a tutor wants to add an exam to the buffer, they must first acquire a free
		// slot through the free Semaphore, or wait until they can.
		free.acquire();
		synchronized (this){
			data[last] = e;
			last = (last+1) % data.length;
		}
		//after acquiring a free slot and adding the necessary data in to it, they must then warn the occupied
		// Semaphore that there is now another exam in a slot, which can now be taken by another tutorThread
		// for correcting.
		occupied.release();
	}
}