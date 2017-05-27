/**
 * Created by nosec_000 on 5/27/2017.
 */
public class PingHandler {
    private int pings = 0;

    public void incrementPings(){
        pings += 1;
    }

    public int getPings() {
        return pings;
    }
}
