package Graph;

import schemacrawler.schema.Column;
import schemacrawler.schema.ForeignKey;
import schemacrawler.schema.Table;
//grafo com v�rtices ponderados
public class GrafoArestas {
//Grafo com vertices e arestas ponderadas. Preciso rever o c�lculo dos pesos das arestas. Eles devem ser inversamente proporcionais.
	//talvez eu deva mudar a forma de como os pesos s�o atribu�dos. E que tal utilizar somente os v�rtices rela��es na pondera��o das arestas?
	//Isso � algo que terei que estudar...
	UtilGraph grafo;
	Conexao conexao;
	
	public GrafoArestas(String banco){
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
				Vertice v2 = new Vertice(c.getName(),1,false); //vertices atributos t�m peso 1
				grafo.addVertice(v2);	
				int pesoAresta=v1.getPeso()/v2.getPeso();
				grafo.addAresta(new Aresta("",pesoAresta,v1,v2));
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
							int pesoAresta=grafo.getVertice(tables[i].getName()).getPeso()/grafo.getVertice(tables[j].getName()).getPeso();
							grafo.addAresta(new Aresta("", pesoAresta,grafo.getVertice(tables[i].getName()), grafo.getVertice(tables[j].getName())));						
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

	
	public void zerarPesosVertices(){// utilizar essa fun��o para trabalhar somente com os pesos das arestas
		for(int i=0; i<grafo.vertices.size(); i++){
			grafo.vertices.get(i).setPeso(0);
		}
	}
	
	public static void main(String [] args){
		GrafoArestas grafo = new GrafoArestas("mondial");
		grafo.construirGrafo();
		
		System.out.println();
		for(Aresta a: grafo.grafo.arestas){
			System.out.println("aresta:" + a.origem.getNome() + "->"+a.destino.getNome()+" peso: "+a.peso);
		}
	
	}

}

