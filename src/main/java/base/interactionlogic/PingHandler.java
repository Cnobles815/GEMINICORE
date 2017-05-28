package base.interactionlogic;

/**
 * Created by nosec_000 on 5/27/2017.
 */
public class PingHandler {
    private long pings;

    public long incrementPings(){
        pings += 1;
        return pings;
    }

    public long getPings() {
        return pings;
    }
}
