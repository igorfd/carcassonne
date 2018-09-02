package br.ufpb.dcx.aps.carcassone;

import br.ufpb.dcx.aps.carcassone.tabuleiro.TabuleiroFlexivel;
import br.ufpb.dcx.aps.carcassone.tabuleiro.Tile;

public class Partida {

	private BolsaDeTiles tiles;
	private Tile proximoTile;
	private TabuleiroFlexivel tabuleiro = new TabuleiroFlexivel("  ");
	
	private String estadoPartida = "Em_Andamento";
	

	private String estadoTurno = "Início_Turno";
	private boolean TilePosicionado = true;
	private Jogador [] jogadores;
	private int jogadorAtual = 0;
	
	private int countTurno = 1;
	private Tile tileAnterior;
	

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
		if (estadoPartida == "Partida_Finalizada") {
			throw new ExcecaoJogo("Partida finalizada");
		}
		if (TilePosicionado) 
			setEstadoTurno("Tile_Posicionado");
		return "Jogador: " + jogadores[jogadorAtual].getCor() +"\nTile: " + proximoTile + "\nStatus: "+ getEstadoTurno();
	}

	public Partida girarTile() {
		if(TilePosicionado) {
			throw new ExcecaoJogo("Não pode girar tile já posicionado");
		}
		if(proximoTile == null) {
			estadoPartida = "Partida_Finalizada";
			throw new ExcecaoJogo("Não pode girar tiles com a partida finalizada");
		}
		proximoTile.girar();
		return this;
	}
	
	private void pegarProximoTile() {
		tileAnterior = proximoTile;
		proximoTile = tiles.pegar();
		if(proximoTile != null) {
			proximoTile.reset();
		}else {
			setEstadoPartida("Partida_Finalizada");
		}
	}

	
	public Partida finalizarTurno() {
		setEstadoPartida("Em_Andamento");
		TilePosicionado = false;
		pegarProximoTile();
		setCountTurno(countTurno+=1);
		jogadorAtual++;
		
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
		if(countTurno==1){
			return proximoTile.toString();
		}
		return tileAnterior.toString();
		
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
		return (TilePosicionado) ? "Tile_Posicionado": "Tile_Não_Posicionado";
	}

	public void setEstadoTile(boolean estadoTile) {
		this.TilePosicionado = estadoTile;
	}
	public int getCountTurno() {
		return countTurno;
	}

	public void setCountTurno(int countTurno) {
		this.countTurno = countTurno;
	}
}
