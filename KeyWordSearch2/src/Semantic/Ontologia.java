package Semantic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.Set;

import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.WordNetDatabase;



public class Ontologia {
	Hashtable<String, Set<String>> dicionario = new Hashtable<String, Set<String>>();
	static WordNetDatabase database;
	String caminhoWordnet = "C:/Program Files (x86)/WordNet/2.1/dict";//caminho do wordnet
	String caminhoOntologia = "C:\\Users\\UFG\\Documents\\consulta.txt";
	
	public Ontologia() throws IOException{
		//Instancia Wordnet
		System.setProperty("wordnet.database.dir", caminhoWordnet);
		database = WordNetDatabase.getFileInstance();	
				
		Set<String> sinonimos = null;
		StringBuilder key =  new StringBuilder();
		StringBuilder sinonimo =  new StringBuilder();
		
		
		File file = new File(caminhoOntologia);
		//Se o arquivo de ontologia existir, cria um dicion�rio de sin�nimos a partir da ontologia
		if (file.exists()){
			FileInputStream stream = new FileInputStream(caminhoOntologia);
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader br = new BufferedReader(reader);
			String linha = br.readLine();
			int posicaoCaracter = 0;
		
			if (linha != null) {
				while (posicaoCaracter < linha.length()) {
					sinonimos = new LinkedHashSet<String>();
					while (linha.charAt(posicaoCaracter) != ' ') {
						key.append(linha.charAt(posicaoCaracter));
						posicaoCaracter++;
						if (posicaoCaracter == linha.length()) {
							break;
						}
					}
					sinonimos.add(key.toString().toLowerCase());
					posicaoCaracter++;
					if (posicaoCaracter < linha.length()) {
						if (linha.charAt(posicaoCaracter) == '[') {
							posicaoCaracter++;
							while (linha.charAt(posicaoCaracter) != ']') {
								sinonimo.append(linha.charAt(posicaoCaracter));
								posicaoCaracter++;
								if (linha.charAt(posicaoCaracter) == ' ') {
									sinonimos.add(sinonimo.toString()
											.toLowerCase());
									sinonimo.replace(0, sinonimo.length(), "");
									posicaoCaracter++;
								}
							}
							sinonimos.add(sinonimo.toString().toLowerCase());
							sinonimo.replace(0, sinonimo.length(), "");
							dicionario.put(key.toString().toLowerCase(),
									sinonimos);
							posicaoCaracter += 2;
						} else {
							dicionario.put(key.toString().toLowerCase(),
									sinonimos);
						}
						key.replace(0, key.length(), "");
					}
				}
			}
		}
	}
			
	public String[] getSynsets(String word){	
		//Se o arquivo de ontologia n�o existir, utiliza WordNet
		File file = new File(caminhoOntologia);
		if(!file.exists()){
			Synset[] synsets = database.getSynsets(word);
			String[] wordForm;
			Set<String> sinonimos = new LinkedHashSet<String>();
			sinonimos.add(word);
			for(Synset s: synsets){
				wordForm = s.getWordForms();
				for(String st :wordForm){
					if(sinonimos.size() >= 4){
						break;
					}else{
					   sinonimos.add(st);
					}
									
				}
				
			}
			return sinonimos.toArray(new String[sinonimos.size()]);			
		}else{
			return 	dicionario.get(word.toLowerCase()).toArray(new String[dicionario.get(word.toLowerCase()).size()]);
		}		
	}
}
