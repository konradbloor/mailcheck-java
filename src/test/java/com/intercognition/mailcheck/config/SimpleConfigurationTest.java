package com.intercognition.mailcheck.config;

import com.intercognition.mailcheck.stringdistance.Sift3;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SimpleConfigurationTest {

    private Configuration configuration;

    @Before
    public void setUp() {
        configuration = new SimpleConfiguration(5,
                Arrays.asList("hotmail.com"),
                Arrays.asList("com"),
                new Sift3(5));
    }

    @Test
    public void thresholdAccessor() {
        assertEquals(5,configuration.getThreshold());
    }

    @Test
    public void domainAccessor() {
        assertEquals(Arrays.asList("hotmail.com"),configuration.getDomains());
    }

    @Test
    public void topLevelDomains() {
        assertEquals(Arrays.asList("com"),configuration.getTopLevelDomains());
    }

    @Test
    public void distanceAlgorithm() {
        assertTrue(configuration.getDistanceAlgorithm() instanceof Sift3);
    }
}
