package bean;

public class MovNum {
	
	private int circoscrizioneDestinazione;
	private int numeroEventi;
	public MovNum(int circoscrizioneDestinazione, int numeroEventi) {
		super();
		this.circoscrizioneDestinazione = circoscrizioneDestinazione;
		this.numeroEventi = numeroEventi;
	}
	public int getCircoscrizioneDestinazione() {
		return circoscrizioneDestinazione;
	}
	public void setCircoscrizioneDestinazione(int circoscrizioneDestinazione) {
		this.circoscrizioneDestinazione = circoscrizioneDestinazione;
	}
	public int getNumeroEventi() {
		return numeroEventi;
	}
	public void setNumeroEventi(int numeroEventi) {
		this.numeroEventi = numeroEventi;
	}
	
	public String toString(){
		String r ;
		r = circoscrizioneDestinazione+" "+numeroEventi+" \n";
		return r;
	}

}
