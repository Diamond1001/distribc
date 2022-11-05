package com.example;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ServiceImpl extends UnicastRemoteObject implements Service {
    private final BlockingQueue<Integer> queue;
    ArrayList<Integer> NumberOfPrimeNumbers = new ArrayList<Integer>();
    static long startTime = 0, endTime = 0;
    boolean firstProcessStarted = false;
    public ServiceImpl() throws RemoteException {
        super();
        this.queue = new LinkedBlockingQueue<Integer>();
    }
    @Override
    public Integer timer() throws RemoteException {
        if (!firstProcessStarted) {
            startTime = System.nanoTime();
        }
        firstProcessStarted = true;
        return this.queue.poll();
    }
    @Override
    public void addelement(int num) throws RemoteException {
        this.queue.add(num);
    }
    @Override
    public void storeCalculatedMessage(int num) throws RemoteException {
        System.out.println("Queue contain follow elements: " + queue);
        NumberOfPrimeNumbers.add(num);
        if (queue.isEmpty()) {
            try {
                Thread.sleep((long) 11.11);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finish(NumberOfPrimeNumbers);
        }
    }

    public static void finish(ArrayList<Integer> numberList) {
        int sum = 0;
        for (int numbers : numberList) {
            sum += numbers;
        }
        System.out.println("The final sum is equal to: "+sum);
        endTime = System.nanoTime();
        System.out.println("Time taken:" + (endTime - startTime) );
    }

}