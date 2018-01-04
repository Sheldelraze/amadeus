package com.minh.nguyen.amadeus;

import com.google.common.util.concurrent.SimpleTimeLimiter;
import com.google.common.util.concurrent.TimeLimiter;
import com.google.common.util.concurrent.UncheckedTimeoutException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AmadeusApplicationTests {
	static Logger logger = LoggerFactory.getLogger(AmadeusApplicationTests.class);
	public static void main(String[] args) throws IOException {
//
//        ProcessBuilder builder = new ProcessBuilder("g++","tarzan.cpp","-std=gnu++14","-o","b.exe");
//        builder.directory(new File("E:\\JAVA\\Amadeus\\src\\test\\java\\com\\minh\\nguyen\\amadeus"));
//        builder.redirectErrorStream(true);
//        Process p = builder.start();
//        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
//        String line;
//        while (true) {
//            line = r.readLine();
//            if (line == null) { break; }
//            logger.info(line);
//        }
        TimeLimiter limiter = new SimpleTimeLimiter();
        try {
            String result = limiter.callWithTimeout(new Callable<String>() {
                public String call() {
                    for(int i = 1;i <= 2e9;i++){}
                    return "a";
                }
            }, 1, TimeUnit.SECONDS, false);
        } catch (UncheckedTimeoutException | TimeoutException e) {
            logger.info("excep");
        }catch(Exception e){
            e.printStackTrace();
        }
        logger.info("hello");
    }

}
