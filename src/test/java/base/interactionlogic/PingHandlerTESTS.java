package base.interactionlogic;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by nosec_000 on 5/28/2017.
 */
public class PingHandlerTESTS {

    @Test
    public void incrementPingsTEST(){
        PingHandler pingTest = new PingHandler();
        long expected = 1;
        long actual = pingTest.incrementPings();
        assertEquals("The value should be 1", expected, actual);
    }
}
