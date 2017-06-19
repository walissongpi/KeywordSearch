package Graph;

public class Aresta {
	String label;
	int peso; // posso também representar o peso na própria matriz de adjacência
	Vertice origem, destino;
	
	Aresta(){
		
	}
	
	public Aresta(Vertice origem, Vertice destino){
		this.label="";
		this.peso=0;
		this.origem=origem;
		this.destino=destino;
	}
	
	public Aresta(String label, Vertice origem, Vertice destino){
		this.label=label;
		this.peso=0;
		this.origem=origem;
		this.destino=destino;
	}
	
	public Aresta(String label, int peso, Vertice origem, Vertice destino){
		this.label=label;
		this.peso=peso;
		this.origem=origem;
		this.destino=destino;
	}

}
