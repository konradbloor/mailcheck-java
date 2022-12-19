package com.intercognition.mailcheck.config;

import com.intercognition.mailcheck.stringdistance.DistanceAlgorithm;

import java.util.Collection;

public interface Configuration {
	@SuppressWarnings("checkstyle:MissingJavadocMethod")
	int getThreshold();

	@SuppressWarnings("checkstyle:MissingJavadocMethod")
	Collection<String> getDomains();

	@SuppressWarnings("checkstyle:MissingJavadocMethod")
	Collection<String> getTopLevelDomains();

	@SuppressWarnings("checkstyle:MissingJavadocMethod")
	DistanceAlgorithm getDistanceAlgorithm();
}
