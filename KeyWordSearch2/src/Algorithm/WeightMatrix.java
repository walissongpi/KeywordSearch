/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithm;

import SchemaCrawler.DatabaseInfo;
import Semantic.Cosseno;
import Semantic.Expansion;
import Semantic.Jaccard;
import Semantic.Levenshtein;
import java.util.ArrayList;

/**
 *
 * @author Walisson
 */
public class WeightMatrix {
    ArrayList<String> atributes;
    ArrayList<String> keywords;
    int matrixSW[][];
    DatabaseInfo databaseInfo;
    ArrayList<String> databaseAttributes;//atributos com nome da tabela
    ArrayList<String> databaseOnlyAttributes;//atributos sem nome da tabela
    ArrayList<String []> keywordsExpansions;
    
    WeightMatrix(ArrayList<String> keywords, String databaseName){
        databaseInfo=new DatabaseInfo(databaseName);
        databaseAttributes=databaseInfo.getDatabaseAttributes();
        databaseOnlyAttributes = databaseInfo.getDatabaseOnlyAttributes();
        matrixSW = new int [keywords.size()][databaseAttributes.size()];// keywords X database attributes
        keywordsExpansions = Expansion.expandQuery(keywords);
    }
    
    void fillMatrixSW(){
         for(int i=0;i<matrixSW.length;i++){
            for(int j=0;j<matrixSW[i].length;j++){
                //verificar os pesos das palavras aqui, juntamente com as palavras expandidas...
                //preciso marcar qual vai ser a palavra-chave usada   
            }
        }
    }
    
    void selectBestExpandedTerms(String databaseTerm){
        for(String [] ss:keywordsExpansions){
            String term="", term2="";
            double d=0;
            for(String s: ss){
                term = s;
                for(String a: databaseOnlyAttributes){
                   double x=mediaSemanticDistance(s,a);
                    if(x>d){
                        term=s;
                        term2=a;
                        d=x;
                    }
                } 
            }
            System.out.println("termo: "+term+ "-> "+term2 +" d: "+d);
            
           // regex(term);
//adicionar o termo aqui
            //term
        }
    }
    
 
    double mediaSemanticDistance(String s1, String s2){ // calcula a media das distâncias semânticas dos termos
        return (Cosseno.getSimilarity(s1,s2)+Levenshtein.getSimilarity(s1,s2)+Jaccard.getSimilarity(s1,s2))/3;
    }
    
    public static void main(String[]a){
        ArrayList<String> vetor = new ArrayList<>();
        
        vetor.add("actors");
        vetor.add("name");
        vetor.add("movies");
        WeightMatrix w = new WeightMatrix(vetor,"imdbpart");
        
        for(String q: w.databaseOnlyAttributes){
            System.out.println(q);
        }
        w.selectBestExpandedTerms("");
    }
}
