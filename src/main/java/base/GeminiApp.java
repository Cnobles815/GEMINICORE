package base;

import base.chatmonitoring.GeminiCore;
import base.destinyconnection.BungieAccountAccess;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.concurrent.ExecutionException;



/**
 * Created by nosec_000 on 5/27/2017.
 */
@SpringBootApplication
public class GeminiApp {
    public static GeminiCore Gem;

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        SpringApplication.run(GeminiApp.class, args);
        BungieAccountAccess bungieAccountAccess = new BungieAccountAccess();
        Gem = new GeminiCore();

        bungieAccountAccess.getBungieStuff();
        Gem.activate();


    }
}
