package org.leanpoker.player;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlayerTest {

    @Test
    public void testBetRequest() throws Exception {

        String json = "{\"key1\": \"value1\", \"key2\": \"value2\"}";

        assertEquals(0, PlayerBot.betRequest(json));

    }
}
