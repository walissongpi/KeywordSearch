package Graph;
import schemacrawler.schema.*;
//grafo sem pesos 
public class Grafo {
	
	UtilGraph grafo;
	Conexao conexao;
	
	public Grafo(String banco){
		conexao = new Conexao(banco);
	}
	
	public void construirGrafo(){		
		// calculando o grau do grafo
		Table [] tables = conexao.schema.getTables();
		int cont=tables.length;
		int contChaves = 0;
		
		for(Table table: tables){
			cont+= table.getColumns().length;
			ForeignKey [] chaves = table.getExportedForeignKeys();
			if(chaves!=null)
			contChaves +=chaves.length;	
		} 				
		grafo = new UtilGraph(cont,((cont-tables.length)+contChaves));
		 
		for(Table table: tables){
			//adicionar cada tabela como v�rtice

			Vertice v1 = new Vertice(table.getName(),true);
			grafo.addVertice(v1);
			
			for(Column c: table.getColumns()){ //adicionar cada coluna como v�rtice
				Vertice v2 = new Vertice(v1.getNome()+"."+c.getName(),false);
				grafo.addVertice(v2);				
				grafo.addAresta(new Aresta("",v1,v2));
			}
		}
	
		//fazer a baga�a aqui para saber qual tabela est� relacionada

		for(int i = 0; i<tables.length; i++){
			System.out.println("tabela: "+tables[i].getName());
			
			ForeignKey [] exp = tables[i].getExportedForeignKeys();
			
			for(int j= 0; j<tables.length; j++){
				ForeignKey [] imp = tables[j].getImportedForeignKeys();
				
				for(int x=0; x<exp.length; x++ ){
					for(int y=0; y<imp.length; y++ ){
						if(exp[x].getName().equals(imp[y].getName())){														
							//adicionar as arestas dos v�rtices rela��o
							//System.out.print(grafo.getVertice(tables[i].getName()).nome+"-->");
							//System.out.println(grafo.getVertice(tables[j].getName()).nome);			
							grafo.addAresta(new Aresta("", grafo.getVertice(tables[i].getName()), grafo.getVertice(tables[j].getName())));							
						}
					}
				}				
			}		
			System.out.println("--------------------");		
		}
		
		System.out.println("Vertices: "+grafo.vertices.size());
		System.out.println("Arestas: "+grafo.arestas.size());
				
		grafo.imprimeAdjacencia();
	}

	public UtilGraph getGrafo(){
		return grafo;
	}
	
	
	
	public static void main(String [] args){
		Grafo g= new Grafo("imdbpart");
		g.construirGrafo();
		
		System.out.println("+++++++++++++++");
		for(Vertice v: g.grafo.getAdjacentes(g.grafo.vertices.get(0))){
			System.out.print("-"+v.getNome());
		}
		
		System.out.println("\niniciando pelo vertice "+ g.grafo.vertices.get(0).getNome());
		
		g.grafo.buscaAmplitude(g.grafo, g.grafo.vertices.get(0));
		//g.grafo.buscaAmplitude(g.grafo);
	}

}
