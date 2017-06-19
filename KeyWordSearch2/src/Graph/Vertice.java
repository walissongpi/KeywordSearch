package Graph;

public class Vertice {
	private String nome;
	private int peso;
	private boolean relacao;//true: vértice relação
	private boolean flag;
	//enum tipo;				//false: vértice atributo
	
	
	Vertice(String nome, boolean relacao){
		this.setNome(nome);
		this.setPeso(0);
		this.setRelacao(relacao);
		this.setFlag(false);
		
	}
	
	Vertice(String nome, int peso, boolean relacao){
		this.setNome(nome);
		this.setPeso(peso);
		this.setRelacao(relacao);
		this.setFlag(false);
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isRelacao() {
		return relacao;
	}

	public void setRelacao(boolean relacao) {
		this.relacao = relacao;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
}
