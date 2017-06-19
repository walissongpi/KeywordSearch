package Graph;

public class Aresta {
	String label;
	int peso; // posso tamb�m representar o peso na pr�pria matriz de adjac�ncia
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
