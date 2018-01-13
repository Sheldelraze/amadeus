package com.minh.nguyen.amadeus;

import com.google.common.util.concurrent.SimpleTimeLimiter;
import com.google.common.util.concurrent.TimeLimiter;
import com.minh.nguyen.util.CompileUtil;
import com.minh.nguyen.util.Runner.Outcome;
import com.minh.nguyen.util.Runner.Params;
import com.minh.nguyen.util.Runner.ProcessRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AmadeusApplicationTests {

    private static ModelMapper modelMapper = new ModelMapper();
	static Logger logger = LoggerFactory.getLogger(AmadeusApplicationTests.class);
	public static class A{
	    String a;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }
    }
    public static class B{
        public int a;

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }
    }
	public static void main(String[] args) throws IOException {
	    A a = new A();
	    a.setA("123");
	    B b = new B();
	    modelMapper.map(a,b);
        b.getA();
//        CompileUtil compileUtil = new CompileUtil();
//        compileUtil.solve();
//        TimeLimiter timeLimiter = new SimpleTimeLimiter();
//        try {
//            timeLimiter.callWithTimeout(new Callable<Void>() {
//
//                @Override
//                public Void call() throws Exception {
//                    return null;
//                }
//            },3,TimeUnit.SECONDS,true);
//        } catch (Exception e) {
//
//        }
//        Params.Builder builder = new Params.Builder();
//        //builder.setDirectory(new File("E:\\C++\\test"));
//        //builder.setRedirectErrorFile(new File("E:\\C++\\test\\error.txt"));
//        builder.setTimeLimit(10);
//        //builder.setRedirectInputFile(new File("E:\\C++\\test\\in.txt"));
//        //builder.setRedirectOutputFile(new File(("E:\\C++\\test\\out.txt")));
//        Params params = builder.newInstance();
//        String command = "E:\\C++\\test\\main.exe";
//        Outcome outcome =  ProcessRunner.run(command,params);
//        System.out.println("Errors: " + outcome.getError());
//        System.out.println("Output: " + outcome.getOutput());
//        System.out.println("Exit code: " + outcome.getExitCode());
//        System.out.println("Comments: " + outcome.getComment());
    }

}
