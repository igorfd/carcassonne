package br.ufpb.dcx.aps.carcassone;

import br.ufpb.dcx.aps.carcassone.tabuleiro.TabuleiroFlexivel;
import br.ufpb.dcx.aps.carcassone.tabuleiro.Tile;

public class Partida {

	private BolsaDeTiles tiles;
	private Tile proximoTile;
	private TabuleiroFlexivel tabuleiro = new TabuleiroFlexivel("  ");
	
	private String estadoPartida = "Em_Andamento";
	

	private String estadoTurno = "Início_Turno";
	private String estadoTile = "Tile_Posicionado";
	private Jogador [] jogadores;
	private int jogadorAtual = 0;
	
	
	Partida(BolsaDeTiles tiles, Cor ...sequencia) {
		this.tiles = tiles;
		pegarProximoTile();
		jogadores = carregaJogadores(sequencia);
	}

	public Jogador [] carregaJogadores(Cor... sequencia){
		Jogador [] temp = new Jogador[sequencia.length];
		for(int i =0; i < sequencia.length; i ++) {
			temp[i] = new Jogador(sequencia[i].name()); 
		}
		return temp;
	}
	
	
	
	public String relatorioPartida() {
		String relatorio = "Status: " + this.getEstadoPartida() + "\nJogadores: ";
		for(int j = 0; j < jogadores.length; j++) {
			if(j ==  jogadores.length-1) {
				relatorio +=  jogadores[j].toString();
			}else
				relatorio += jogadores[j].toString() + "; ";
			}
		return relatorio;
	}

	public String relatorioTurno() {
		return "Jogador: " + jogadores[jogadorAtual].getCor() +"\nTile: " + proximoTile + "\nStatus: "+ getEstadoTile();
	}

	public Partida girarTile() {
		proximoTile.girar();
		return this;
	}

	private void pegarProximoTile() {
		proximoTile = tiles.pegar();
		proximoTile.reset();
	}

	public Partida finalizarTurno() {
		pegarProximoTile();
		return this;
	}

	public Partida posicionarTile(Tile tileReferencia, Lado ladoTileReferencia) {
		tabuleiro.posicionar(tileReferencia, ladoTileReferencia, proximoTile);
		return this;
	}

	public Partida posicionarMeepleEstrada(Lado lado) {
		return this;
	}

	public Partida posicionarMeepleCampo(Vertice vertice) {
		return this;
	}

	public Partida posicionarMeepleCidade(Lado lado) {
		return this;
	}

	public Partida posicionarMeepleMosteiro() {
		return this;
	}

	public String getEstradas() {
		return null;
	}

	public String getCampos() {
		return null;
	}

	public String getCidades() {
		return null;
	}

	public String getMosteiros() {
		return null;
	}

	public String relatorioTabuleiro() {
		return proximoTile.toString();
	}
	
	public String getEstadoPartida() {
		return estadoPartida;
	}

	public void setEstadoPartida(String estadoPartida) {
		this.estadoPartida = estadoPartida;
	}

	public String getEstadoTurno() {
		return estadoTurno;
	}

	public void setEstadoTurno(String estadoTurno) {
		this.estadoTurno = estadoTurno;
	}

	public String getEstadoTile() {
		return estadoTile;
	}

	public void setEstadoTile(String estadoTile) {
		this.estadoTile = estadoTile;
	}
}
