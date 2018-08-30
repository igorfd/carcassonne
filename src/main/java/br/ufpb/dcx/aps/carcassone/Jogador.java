package br.ufpb.dcx.aps.carcassone;

public class Jogador {
	private String cor;
	private int pontuacao;
	private int miples;
	
	Jogador(String cor){
		this.cor = cor;
		this.setPontuacao(0);
		this.setMiples(7);		
	}

	public String getCor() {
		return cor;
	}
	
	public int getPontuacao() {
		return pontuacao;
	}

	public int getMiples() {
		return miples;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	public void setMiples(int miples) {
		this.miples = miples;
	}
	
	public String toString() {
		return getCor() + "("+ getPontuacao() + "," + getMiples() +")";
	}
}
