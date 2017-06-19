package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UtilGraph {
	int matrizA[][], matrizI[][];
	ArrayList<Vertice> vertices;
	ArrayList<Aresta> arestas;
	
	ArrayList<ArrayList<Vertice>> adjacencia;
	
	UtilGraph(int qtdV, int qtdA){
		
		System.out.println("vertices> "+qtdV);
		System.out.println("arestas> "+qtdA);
		
		matrizA=new int[qtdV][qtdV];//adjcencia
		matrizI=new int[qtdV][qtdA];//incidencia
		
		//inicializar a matriz com -1
		for(int i = 0; i< qtdV; i++){
			for(int j = 0; j< qtdV; j++){
				matrizA[i][j]=-1;
			}
		}
		
		for(int i = 0; i< qtdV; i++){
			for(int j = 0; j< qtdA; j++){
				matrizI[i][j]=-1;
			}
		}

		vertices = new ArrayList<Vertice>(qtdV);
		arestas = new ArrayList<Aresta>(qtdA);		
	}
		
	public void addVertice(Vertice v){
		vertices.add(v);
	}
	
	public void addAresta(Aresta a){
		arestas.add(a);
		
		int indiceOrigem = vertices.indexOf(a.origem);
		int indiceDestino = vertices.indexOf(a.destino);
		
		matrizA[indiceOrigem][indiceDestino] = 0;
		matrizA[indiceDestino][indiceOrigem] = 0;
		
		matrizI[indiceOrigem][arestas.size()-1] = 0;
		matrizI[indiceDestino][arestas.size()-1] = 0;
	}
	
	
	void imprimirGrafo(){
		System.out.println();
		for(int i=0;i<matrizA.length;i++){
			//System.out.println("V�rtice: "+ vertices.get(i).nome);	
			for(int j=0;j<matrizA.length;j++){
				if(matrizA[i][j]==0){
					System.out.print("="+vertices.get(j).getNome());
				}
			}			
			System.out.println("\n----------");
		}		
	}
	
	
	void gerarAdjacencia(){
		adjacencia = new ArrayList<ArrayList<Vertice>>(vertices.size());
		for(int i=0;i<matrizA.length;i++){
			adjacencia.add(new ArrayList<Vertice>());
			
			for(int j=0;j<matrizA.length;j++){
				if(matrizA[i][j]==0){
					adjacencia.get(adjacencia.size()-1).add(vertices.get(j));
				}
			}
		}		
	}
	
	public ArrayList <Vertice> getAdjacentes(Vertice v){
		int i = vertices.indexOf(v);
		if(i!=-1)
			return adjacencia.get(i);
		
		return null;
	}
	
	
	public Vertice getVertice(String s){
		for(Vertice v: vertices){
			if(v.getNome().equals(s) && v.isRelacao()){
				return v;
			}		
		}
		return null;
	}
	

	void imprimeAdjacencia(){
		gerarAdjacencia();
		
		System.out.println("Adjac�ncia");
		for(int i=0;i<adjacencia.size();i++){
			System.out.print(vertices.get(i).getNome()+"---->");
			for(int j=0;j<adjacencia.get(i).size();j++){
				System.out.print(adjacencia.get(i).get(j).getNome()+"->");
			}
			System.out.println();
		}
	}
	
	void buscaAmplitude(UtilGraph g, Vertice verticeInicial){
		LinkedList<Vertice> fila = new LinkedList<Vertice>();
		ArrayList<Vertice> visitado = new ArrayList<Vertice>(vertices.size());
		
		fila.add(verticeInicial);
				
		System.out.println();

		while(fila.size()>0){
			Vertice v = fila.pop(); //retorna e retira o elemento da fila
			ArrayList<Vertice> adjacentes = this.getAdjacentes(v);	//recupera os v�rtices adjacentes ao v�rtice em quest�o		
			//System.out.println("\nVertice "+v.nome+" possui "+adjacentes.size()+" +vertices adjacentes");
			
			for(int i=0;i<adjacentes.size();i++){			
				//Vertice v
				if(!visitado.contains(adjacentes.get(i)) && !fila.contains(adjacentes.get(i)) ){//preciso verificar se o v�rtice adjacente n�o se encontra na fila
					fila.add(adjacentes.get(i));	
					//verificar aqui se ocorreu a incid�ncia com o v�rtice
					
					//adjacentes.get(i).flag=true;
				}					
			}
			visitado.add(v);
			//devo processar o v�rtice logo ap�s eu terminar
			System.out.println();
			System.out.print(" -> " +v.getNome());
			
		}	
	}
}
