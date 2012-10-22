package com.intercognition.mailcheck.stringdistance;

/**
 * Ported from C# version
 * @see <a href="http://siderite.blogspot.com/2007/04/super-fast-and-accurate-string-distance.html">authors page on algorithm</a>
 *
 */
public class Sift3 implements DistanceAlgorithm {

    private int maxOffset;

    /**
     * Calculates string distance - broadly how many characters are different in one string to another
     *
     * @param maxOffset How far away a different character has to be from another to not count
     *                  as distance.  For example, if your max offset is 5, abcde and abcdef should
     *                  have a difference of 0.
     */
    public Sift3(int maxOffset) {
        this.maxOffset = maxOffset;
    }

    @Override
    public float getDistance(String firstString, String secondString) {
        if (isNullOrEmpty(firstString))
            return isNullOrEmpty(secondString) ? 0 : secondString.length();
        if (isNullOrEmpty(secondString))
            return firstString.length();
        
        int c = 0;
        int offset1 = 0;
        int offset2 = 0;
        int lcs = 0;

        while (c + offset1 < firstString.length() && (c + offset2 < secondString.length())) {
            if(firstString.charAt(c + offset1) == secondString.charAt(c + offset2))
                lcs++;
            else {
                offset1 = 0;
                offset2 = 0;
                for (int i = 0; i < maxOffset; i++)
                {
                    if ((c + i < firstString.length())
                            && (firstString.charAt(c + i) == secondString.charAt(c)))
                    {
                        offset1 = i;
                        break;
                    }
                    if ((c + i < secondString.length())
                            && (firstString.charAt(c) == secondString.charAt(c + i)))
                    {
                        offset2 = i;
                        break;
                    }
                }
            }
            c++;
        }
        return (firstString.length() + secondString.length())/2 - lcs;
    }

    private boolean isNullOrEmpty(String candidate) {
        return candidate == null || candidate.isEmpty();
    }
}
