package com.minh.nguyen.Util;

import com.google.common.util.concurrent.SimpleTimeLimiter;
import com.google.common.util.concurrent.TimeLimiter;
import com.google.common.util.concurrent.UncheckedTimeoutException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Mr.Minh
 * @since 04/01/2018
 * Purpose:
 */
public class CommonCompiler {
    private int timeLimit, memoryLimit;
    private boolean judgeAll;
    public  void compile(){
        for(int i = 1;i <= 2e9;i++){

        }
    }
    public void solve(){
        TimeLimiter timeLimiter = new SimpleTimeLimiter();
        String a = "132";

        try {
            timeLimiter.callWithTimeout(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    compile();
                    return null;
                }
            },1,TimeUnit.SECONDS,true);
        } catch (TimeoutException | UncheckedTimeoutException e) {
            System.out.print("haha");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void run(){

    }
}
