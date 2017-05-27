import pro.beam.api.BeamAPI;

import java.util.concurrent.ExecutionException;



/**
 * Created by nosec_000 on 5/27/2017.
 */
public class GeminiApp {
    public static BeamAPI beam;
    public static GeminiCore Gem;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        beam = new BeamAPI("wZJC9iUaNdzAGhw9nCVkHgNz9w6uLogRXdCIMOdXksZCaswSbu6PU88ka36gSu1T");
        Gem = new GeminiCore();

        int pings = 0;

        Gem.establishConnection(pings);

        Gem.monitorChat();





    }
}
