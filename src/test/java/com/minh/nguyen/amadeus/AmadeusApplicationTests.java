package com.minh.nguyen.amadeus;

import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class AmadeusApplicationTests {

    private static ModelMapper modelMapper = new ModelMapper();
//	static Logger logger = LoggerFactory.getLogger(AmadeusApplicationTests.class);
	public static class A{
	    String a;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }
    }
    public static class B extends A{
        public int a;

        public int getB() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }
    }
    public static class TestRunnable implements Runnable{

        @Override
        public void run() {
            int[] a = new int[123123133];
            a[0] = 123;
            for(int i = 1;i < a.length;i++){
                a[i] = a[i - 1] + 2;
            }
            try {
                Thread.sleep(1182);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("huha");
        }

    }
	public static void main(String[] args) throws IOException {
        System.out.print("haa");
        Thread thread = new Thread(null, new TestRunnable(), "test thread", 1);
        thread.setDaemon(false);
        thread.start();
        System.out.print("hehe");
        return;
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
