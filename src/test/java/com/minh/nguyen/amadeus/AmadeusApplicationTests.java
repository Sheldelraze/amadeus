package com.minh.nguyen.amadeus;

import java.io.File;
import java.io.IOException;


public class AmadeusApplicationTests {

	public static void main(String[] args) throws IOException {
        boolean createFile = new File("/haha/kek").mkdirs();
        if (createFile){
            System.out.println("ook");
        }
        else{
            System.out.println("no sir!");
        }
    }

}
