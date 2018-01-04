package com.minh.nguyen.Util.Compiler;

import com.google.common.util.concurrent.SimpleTimeLimiter;
import com.google.common.util.concurrent.TimeLimiter;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author Mr.Minh
 * @since 04/01/2018
 * Purpose:
 */
public abstract class CommonCompiler {
    private int timeLimit, memoryLimit;
    private boolean judgeAll;
    public void solve(){
        TimeLimiter limiter = new SimpleTimeLimiter();
        boolean compile = compile();
        if (!compile){

        }
        else{
            try {
                String result = limiter.callWithTimeout(new Callable<String>() {
                    public String call() {

                        return "ok";
                    }
                }, timeLimit, TimeUnit.SECONDS, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public abstract boolean compile();
    public abstract void run();
}
