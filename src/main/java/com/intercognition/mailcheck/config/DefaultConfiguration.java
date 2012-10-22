package com.intercognition.mailcheck.config;

import com.intercognition.mailcheck.stringdistance.Sift3;

import java.util.Arrays;

/**
 * A default configuration that you can use to get started quickly.
 */
public class DefaultConfiguration extends SimpleConfiguration {

    public DefaultConfiguration() {
        super(
                3,
                Arrays.asList(
                        "aol.com",
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
                        "yahoo.com"
                        ),
                Arrays.asList(
                        "co.uk",
                        "com",
                        "edu",
                        "gov",
                        "info",
                        "mil",
                        "net",
                        "org"
                ),
                new Sift3(5)
        );
    }
}
