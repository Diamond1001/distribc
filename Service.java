package com.example;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote {
    Integer timer() throws RemoteException;
    void addelement(int num) throws RemoteException;
    void storeCalculatedMessage(int num) throws RemoteException;
}
