/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class SocialNetworkTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */

    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-17T12:00:00Z");

    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk @about rivest @sasha so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest in @sanjar 30  @about minutes #hype @sasha", d2);
    private static final Tweet tweet3 = new Tweet(3, "beta", "hello there! general kenobi @sasha", d3);


    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGuessFollowsGraphEmpty() {
        Map<String, Set<String>> follows = SocialNetwork.guessFollowsGraph(new ArrayList<>());
        List<Tweet> tweets = new ArrayList<>();
        tweets.add(tweet1);
        tweets.add(tweet2);
        tweets.add(tweet3);
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);
        Map<String, Set<String>> testMap = new HashMap<>();
        testMap.put("alyssa", Set.of("@about", "@sasha"));
        testMap.put("bbitdiddle", Set.of("@sanjar", "@about", "@sasha"));
        testMap.put("beta", Set.of("@sasha"));
        assertTrue("expected empty graph", follows.isEmpty());
        assertEquals("Implementation wrong!", testMap, followsGraph);
    }
    
    @Test
    public void testInfluencersEmpty() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertTrue("expected empty list", influencers.isEmpty());

        List<Tweet> tweets = new ArrayList<>();
        tweets.add(tweet1);
        tweets.add(tweet2);
        tweets.add(tweet3);
        List<String> influencers2 = new ArrayList<>();
        influencers2.add("@sasha");
        influencers2.add("@about");
        influencers2.add("@sanjar");

        Map<String, Set<String>> followsGraph2 = SocialNetwork.guessFollowsGraph(tweets);
        List<String> actualInfluencers = SocialNetwork.influencers(followsGraph2);

        assertEquals("Implementation wrong", influencers2, actualInfluencers);

    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version.
     * DO NOT strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in SocialNetwork, because that means you're testing a
     * stronger spec than SocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */

}
