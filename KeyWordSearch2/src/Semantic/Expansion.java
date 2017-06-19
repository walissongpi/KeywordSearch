/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Semantic;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Walisson
 */
public class Expansion {
    public static ArrayList<String> expandQuery(String[] palavrasChave){
	ArrayList<String> consultaExpandida = new ArrayList<String>();
	Ontologia dictionary=null;
        String[] sinonimos;
        try{
            dictionary = new Ontologia();
	
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e);
        }
		/* Expande a consulta original com sinônimos */
	for(String palavra: palavrasChave){
            if(!(palavra.equalsIgnoreCase("valor"))){
		sinonimos = dictionary.getSynsets(palavra);	
		for(String sin: sinonimos){
                    //System.out.println(sin);
                    consultaExpandida.add(sin);
		}		
            }						
	}
            return consultaExpandida;
    }
       
    public static ArrayList<String[]> expandQuery(ArrayList<String> keywords){
	ArrayList<String[]> expansion = new ArrayList<String[]>();
	Ontologia dictionary=null;
        String[] sinonimos;
        try{
            dictionary = new Ontologia();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e);
        }

        for(int i=0;i<keywords.size();i++){
            sinonimos = dictionary.getSynsets(keywords.get(i));
            expansion.add(sinonimos);
        }
        return expansion;
    }
    
   /* public static void main(String[]as){
        ArrayList<String> v = new ArrayList<String>();
        v.add("role");
        v.add("worker");
        for(String [] s: expandQuery(v))
            for(String a: s)
                System.out.println(a);
    }*/
}
