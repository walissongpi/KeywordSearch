package Semantic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Jaccard{
	
	
    /**
     * constructor - default.
     */
  	public static float getSimilarity(final String string1, final String string2) {
		/*
		Each instance is represented as a Jaccard vector similarity function. The Jaccard between two vectors X and Y is

		(X*Y) / (|X||Y|-(X*Y))

		where (X*Y) is the inner product of X and Y, and |X| = (X*X)^1/2, i.e. the Euclidean norm of X.

		This can more easily be described as ( |X & Y| ) / ( | X or Y | )
		*/
				
		        //todo this needs checking
		        final ArrayList<String> str1Tokens = new ArrayList<String>();
		        final ArrayList<String> str2Tokens = new ArrayList<String>();
		        int j = 0;
		        String token;
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

		        //return JaccardSimilarity
		        return (float) (commonTerms) / (float) (allTokens.size());
		    }

}
