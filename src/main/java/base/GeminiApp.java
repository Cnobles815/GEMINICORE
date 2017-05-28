package base;

import base.chatmonitoring.GeminiCore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutionException;



/**
 * Created by nosec_000 on 5/27/2017.
 */
@SpringBootApplication
public class GeminiApp {
    public static GeminiCore Gem;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SpringApplication.run(GeminiApp.class, args);
        Gem = new GeminiCore();

        Gem.establishConnection();
        Gem.monitorChat();
        Gem.monitorPingCount();


    }
}
