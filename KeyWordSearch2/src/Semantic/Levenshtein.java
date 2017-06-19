package Semantic;

public class Levenshtein {
	
	/**
     * gets the similarity of the two strings using levenstein distance.
     *
     * @param string1
     * @param string2
     * @return a value between 0-1 of the similarity
     */
    public static float getSimilarity(final String string1, final String string2) {
        final float levensteinDistance = getUnNormalisedSimilarity(string1, string2);
        //convert into zero to one return

        //get the max possible levenstein distance score for string
        float maxLen = string1.length();
        if (maxLen < string2.length()) {
            maxLen = string2.length();
        }

        //check for 0 maxLen
        if (maxLen == 0) {
            return 1.0f; //as both strings identically zero length
        } else {
            //return actual / possible levenstein distance to get 0-1 range
            return 1.0f - (levensteinDistance / maxLen);
        }

    }

    /**
     * implements the levenstein distance function
     * <p/>
     * Copy character from string1 over to string2 (cost 0)
     * Delete a character in string1 (cost 1)
     * Insert a character in string2 (cost 1)
     * Substitute one character for another (cost 1)
     * <p/>
     * D(i-1,j-1) + d(si,tj) //subst/copy
     * D(i,j) = min D(i-1,j)+1 //insert
     * D(i,j-1)+1 //delete
     * <p/>
     * d(i,j) is a function whereby d(c,d)=0 if c=d, 1 else.
     *
     * @param s
     * @param t
     * @return the levenstein distance between given strings
     */
    public static float getUnNormalisedSimilarity(final String s, final String t) {
        final float[][] d; // matrix
        final int n; // length of s
        final int m; // length of t
        int i; // iterates through s
        int j; // iterates through t
        float cost; // cost

        // Step 1
        n = s.length();
        m = t.length();
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        d = new float[n + 1][m + 1];

        // Step 2
        for (i = 0; i <= n; i++) {
            d[i][0] = i;
        }
        for (j = 0; j <= m; j++) {
            d[0][j] = j;
        }

        // Step 3
        for (i = 1; i <= n; i++) {
            // Step 4
            for (j = 1; j <= m; j++) {
                // Step 5
                cost = getCost(s, i - 1, t, j - 1);

                // Step 6
                d[i][j] = min3(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + cost);
            }
        }

        // Step 7
        return d[n][m];
    }
    
    public static int min3(final float x, final float y, final float z) {
        return (int) Math.min(x, Math.min(y, z));
    }
    
    public final static float getCost(final String str1, final int string1Index, final String str2, final int string2Index) {
        if (str1.charAt(string1Index) == str2.charAt(string2Index)) {
            return 0.0f;
        } else {
            return 1.0f;
        }
    }
}


