package com.intercognition.mailcheck.stringdistance;

/**
 * Enables us to use different distance algorithms than Sift3.
 */
public interface DistanceAlgorithm {
	@SuppressWarnings("checkstyle:MissingJavadocMethod")
	float getDistance(String firstString, String secondString);
}
