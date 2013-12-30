package com.intercognition.mailcheck.stringdistance;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Sift3Test {

    DistanceAlgorithm sift3 = new Sift3(5);

    @Test
    public void distanceBetweenNullFirstStringAndSecondStringIsLengthOfSecondString() {
        assertEquals(6D, sift3.getDistance(null, "abcdef"), 0D);
    }

    @Test
    public void distanceBetweenEmptyFirstStringAndSecondStringIsLengthOfSecondString() {
        assertEquals(6D, sift3.getDistance("", "abcdef"), 0D);
    }

    @Test
    public void distanceBetweenFirstStringAndNullSecondStringIsLengthOfFirstString() {
        assertEquals(5D, sift3.getDistance("abcde", null), 0D);
    }

    @Test
    public void distanceBetweenFirstStringAndEmptySecondStringIsLengthOfFirstString() {
        assertEquals(5D, sift3.getDistance("abcde", ""), 0D);
    }

    @Test
    public void distanceBetweenIdenticalStringsIsZero() {
        assertEquals(0D, sift3.getDistance("abcdef", "abcdef"), 0D);
    }

    @Test
    public void oneCharacterDifferenceBetweenStringsGivesDistanceOfOne() {
        assertEquals(1D, sift3.getDistance("abcdef", "abcdeZ"), 0D);
    }

    @Test
    public void twoCharacterDifferenceBetweenStringsIs2() {
        assertEquals(2D, sift3.getDistance("abcdef", "ZbcdeZ"), 0D);
    }

    @Test
    public void differenceFurtherThanMaximumOffsetBetweenStringsIsCountedAsDistance() {
        assertEquals(0D, sift3.getDistance("abcdef", "abcdefg"), 0.5f);
    }

    @Test
    public void differenceCloserThanMaximumOffsetBetweenStringsGivesDistanceOfOne() {
        assertEquals(1D, sift3.getDistance("abc", "abZ"), 0D);
    }

    @Test
    public void additionalCharacterWithinOffsetRangeIsCountedAsDistance() {
        assertEquals(0D, sift3.getDistance("abc", "abcd"), 0.5f);
    }

}
