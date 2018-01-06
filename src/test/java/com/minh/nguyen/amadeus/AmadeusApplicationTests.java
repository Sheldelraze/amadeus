package com.minh.nguyen.amadeus;

import com.google.common.util.concurrent.SimpleTimeLimiter;
import com.google.common.util.concurrent.TimeLimiter;
import com.minh.nguyen.Util.CommonCompiler;
import com.minh.nguyen.Util.Runner.Outcome;
import com.minh.nguyen.Util.Runner.Params;
import com.minh.nguyen.Util.Runner.ProcessRunner;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AmadeusApplicationTests {

	static Logger logger = LoggerFactory.getLogger(AmadeusApplicationTests.class);
	public static void main(String[] args) throws IOException {
        CommonCompiler commonCompiler = new CommonCompiler();
        commonCompiler.solve();
        TimeLimiter timeLimiter = new SimpleTimeLimiter();
        try {
            timeLimiter.callWithTimeout(new Callable<Void>() {

                @Override
                public Void call() throws Exception {
                    return null;
                }
            },3,TimeUnit.SECONDS,true);
        } catch (Exception e) {

        }
        Params.Builder builder = new Params.Builder();
        //builder.setDirectory(new File("E:\\C++\\test"));
        //builder.setRedirectErrorFile(new File("E:\\C++\\test\\error.txt"));
        builder.setTimeLimit(10);
        //builder.setRedirectInputFile(new File("E:\\C++\\test\\in.txt"));
        //builder.setRedirectOutputFile(new File(("E:\\C++\\test\\out.txt")));
        Params params = builder.newInstance();
        String command = "E:\\C++\\test\\main.exe";
        Outcome outcome =  ProcessRunner.run(command,params);
        System.out.println("Errors: " + outcome.getError());
        System.out.println("Output: " + outcome.getOutput());
        System.out.println("Exit code: " + outcome.getExitCode());
        System.out.println("Comments: " + outcome.getComment());
    }

}
