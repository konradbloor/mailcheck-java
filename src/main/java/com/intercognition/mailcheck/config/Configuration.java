package com.intercognition.mailcheck.config;

import com.intercognition.mailcheck.stringdistance.DistanceAlgorithm;

import java.util.Collection;

public interface Configuration {
    public int getThreshold();
    public Collection<String> getDomains();
    public Collection<String> getTopLevelDomains();
    public DistanceAlgorithm getDistanceAlgorithm();
}
