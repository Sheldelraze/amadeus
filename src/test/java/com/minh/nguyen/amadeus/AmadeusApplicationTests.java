package com.minh.nguyen.amadeus;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AmadeusApplicationTests {
    private static boolean stopRequested = false;
    public static synchronized  void doLoop(){
        int i = 0;
        if (!stopRequested) {
            while (true) {
                i++;
            }
        }
    }
    public static synchronized void updateStopRequest(){
        stopRequested = true;
    }
	public static  void main(String[] args) throws IOException, InterruptedException {
        Thread countingThread = new Thread(new Runnable() {
            @Override
            public   void run() {
                doLoop();
            }
        });
        countingThread.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Stop now");

    }

}
