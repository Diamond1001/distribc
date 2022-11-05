package com.example;

import java.rmi.Naming;

public class RMIClient {
    public static void main(String[] args) {
        String hostName = "localhost";
        int port = 8080;
        String RMI_HOSTNAME = "java.rmi.server.hostname";
        String SERVICE_PATH = "//" + hostName + ":" + port + "/Service";
        try {
            System.setProperty(RMI_HOSTNAME, hostName);
            Service service = (Service) Naming.lookup(SERVICE_PATH);
            while (true) {
                Integer num = service.timer();
                if (num == null) {
                    System.out.println("Received none!");
                    break;
                } else {
                    System.out.println("Received: " + num);
                    service.storeCalculatedMessage(calculateNumber(num));
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static int calculateNumber(int num) {
        int countNumbers = 0;
        for (int i = 1; i < num; i++) {
            if (checkPrime(i)) {
                countNumbers++;
            }
    }
        return countNumbers;
    }
    public static boolean checkPrime(int num) {
        boolean flag = false;
        for (int i = 2; i <= num / 2; ++i) {
            if (num % i == 0) {
                flag = true;
                break;
            }
        }
        if (!flag) {return true;}
        else{return false;}
    }
}
