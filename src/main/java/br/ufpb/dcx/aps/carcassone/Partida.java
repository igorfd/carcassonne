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
	
	Partida(BolsaDeTiles tiles, Cor ...sequencia) {
		this.tiles = tiles;
		pegarProximoTile();
		jogadores = carregaJogadores(sequencia);
		setEstadoPartida("Em_Andamento");
		tabuleiro.adicionarPrimeiroTile(proximoTile);
		

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

	public void verificaPartidaFinalizada() {
		if (estadoPartida.equals("Partida_Finalizada")) 
			throw new ExcecaoJogo("Partida finalizada");
	}
	
	public String montaRelatorioTurno() {
		return "Jogador: " + jogadores[jogadorAtual].getCor() +"\nTile: " + proximoTile + "\nStatus: "+ getEstadoTurno();
	}                                                                      
	
	public String relatorioTurno() {
		this.verificaPartidaFinalizada();
		if (TilePosicionado) 
			setEstadoTurno("Tile_Posicionado");
		return montaRelatorioTurno();
	}

	public Partida girarTile() {
		if(TilePosicionado) {
			throw new ExcecaoJogo("Não pode girar tile já posicionado");
		}
		if(estadoPartida.equals("Partida_Finalizada")) {
			throw new ExcecaoJogo("Não pode girar tiles com a partida finalizada");
		}
		proximoTile.girar();
		return this;
	}
	
	private void pegarProximoTile() {
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
		if(TilePosicionado) {
			throw new ExcecaoJogo("Não pode reposicionar tile já posicionado");
		}
		tabuleiro.posicionar(tileReferencia, ladoTileReferencia, proximoTile);
		setEstadoTurno("Tile_Posicionado");
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
	
	public boolean isPrimeiroTurno() {
		return countTurno==1;
	}
	
	public String relatorioTabuleiro() {
		return tabuleiro.toString();
		/*if(isPrimeiroTurno()){
			return proximoTile.toString();
		}
		if(getEstadoTurno().equals("Tile_Posicionado")){
			return tileAnterior.toString()+proximoTile.toString(); 
		}
		if(!TilePosicionado) {			
			return tileAnterior.toString(); 
		}
		System.out.println(tileAnterior.toString());
		return tileAnterior.toString();*/
		
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
