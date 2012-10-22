package com.intercognition.mailcheck.config;

import com.intercognition.mailcheck.stringdistance.Sift3;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DefaultConfigurationTest {


    private Configuration configuration;

    @Before
    public void setUp() {
        configuration = new DefaultConfiguration();
    }

    @Test
    public void thresholdAccessor() {
        assertEquals(3,configuration.getThreshold());
    }

    @Test
    public void domainAccessor() {
        assertEquals(Arrays.asList("aol.com",
                "att.net",
                "comcast.net",
                "facebook.com",
                "gmail.com",
                "gmx.com",
                "google.com",
                "googlemail.com",
                "hotmail.co.uk",
                "hotmail.com",
                "mac.com",
                "mail.com",
                "me.com",
                "live.com",
                "msn.com",
                "sbcglobal.net",
                "verizon.net",
                "yahoo.co.uk",
                "yahoo.com"),
                configuration.getDomains());
    }

    @Test
    public void topLevelDomains() {
        assertEquals(Arrays.asList(
                "co.uk",
                "com",
                "edu",
                "gov",
                "info",
                "mil",
                "net",
                "org"),
                configuration.getTopLevelDomains());
    }

    @Test
    public void distanceAlgorithm() {
        assertTrue(configuration.getDistanceAlgorithm() instanceof Sift3);
    }

}
