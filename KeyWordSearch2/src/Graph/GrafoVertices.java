package Graph;

import schemacrawler.schema.Column;
import schemacrawler.schema.ForeignKey;
import schemacrawler.schema.Table;
//grafo com v�rtices ponderados
public class GrafoVertices {

	UtilGraph grafo;
	Conexao conexao;
	
	public GrafoVertices(String banco){
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
			int peso = table.getExportedForeignKeys().length+table.getImportedForeignKeys().length+table.getColumns().length;
			//prest�gio do v�rtice rela��o calculado de acordo com o grau
			Vertice v1 = new Vertice(table.getName(),peso,true);
			grafo.addVertice(v1);
			
			for(Column c: table.getColumns()){ //adicionar cada coluna como v�rtice
				Vertice v2 = new Vertice(c.getName(),1,false);
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

	public static void main(String [] args){
		GrafoVertices grafo = new GrafoVertices("imdbpart");
		grafo.construirGrafo();
		System.out.println();
		for(Vertice v: grafo.grafo.vertices){
			System.out.println("vertice: " + v.getNome() + "  peso: "+v.getPeso());
		}	
	}
}

