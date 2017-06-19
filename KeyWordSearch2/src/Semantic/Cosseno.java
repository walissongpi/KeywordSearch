package Semantic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Cosseno {
	
	
	 /**
     * gets the similarity of the two strings using CosineSimilarity.
     *
     * @param string1
     * @param string2
     * @return a value between 0-1 of the similarity
     */
    public static float getSimilarity(final String string1, final String string2) {
        final ArrayList<String> str1Tokens = new ArrayList<String>();
        final ArrayList<String> str2Tokens = new ArrayList<String>();
        String token;
        int j = 0;
        for(int i = 0; i < string1.length(); i++ ){
        	j = i + 1;
        	token = string1.substring(i, j);
        	str1Tokens.add(token);
        	
        }
        
        for(int i = 0; i < string2.length(); i++ ){
        	j = i + 1;
        	token = string2.substring(i, j);
        	str2Tokens.add(token);
        	
        }

        final Set<String> allTokens = new HashSet<String>();
        allTokens.addAll(str1Tokens);
        final int termsInString1 = allTokens.size();
        final Set<String> secondStringTokens = new HashSet<String>();
        secondStringTokens.addAll(str2Tokens);
        final int termsInString2 = secondStringTokens.size();

        //now combine the sets
        allTokens.addAll(secondStringTokens);
        final int commonTerms = (termsInString1 + termsInString2) - allTokens.size();

        //return CosineSimilarity
        return (float) (commonTerms) / (float) (Math.pow((float) termsInString1, 0.5f) * Math.pow((float) termsInString2, 0.5f));
    }

  /*  public static void main(String[]args){
        String s1 = "department";
        String s2 = "departamento";
        
        System.out.println(getSimilarity(s1,s2));
        System.out.println(Levenshtein.getSimilarity(s1,s2));
        System.out.println(Jaccard.getSimilarity(s1,s2));
        
        
    }
    
*/
}
