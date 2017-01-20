package bean;

import java.util.Date;

public class Movimento {
	
	private String tipoEvento;
//	private Date registrazione ;
	private int annoEvento;
	private int anno;
	private int mese;
	private int giorno;
	private int circoscrizioneProvenienza ;
	private int circoscrizioneDestinazione ;
	private int totaleEventi ;
	
	public Movimento(String tipoEvento, int annoEvento, int anno, int mese, int giorno, int circoscrizioneProvenienza,
			int circoscrizioneDestinazione, int totaleEventi) {
		super();
		this.tipoEvento = tipoEvento;
		this.annoEvento = annoEvento;
		this.anno = anno;
		this.mese = mese;
		this.giorno = giorno;
		this.circoscrizioneProvenienza = circoscrizioneProvenienza;
		this.circoscrizioneDestinazione = circoscrizioneDestinazione;
		this.totaleEventi = totaleEventi;
	}
	public String getTipoEvento() {
		return tipoEvento;
	}
	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
	public int getAnnoEvento() {
		return annoEvento;
	}
	public void setAnnoEvento(int annoEvento) {
		this.annoEvento = annoEvento;
	}
	public int getAnno() {
		return anno;
	}
	public void setAnno(int anno) {
		this.anno = anno;
	}
	public int getMese() {
		return mese;
	}
	public void setMese(int mese) {
		this.mese = mese;
	}
	public int getGiorno() {
		return giorno;
	}
	public void setGiorno(int giorno) {
		this.giorno = giorno;
	}
	public int getCircoscrizioneProvenienza() {
		return circoscrizioneProvenienza;
	}
	public void setCircoscrizioneProvenienza(int circoscrizioneProvenienza) {
		this.circoscrizioneProvenienza = circoscrizioneProvenienza;
	}
	public int getCircoscrizioneDestinazione() {
		return circoscrizioneDestinazione;
	}
	public void setCircoscrizioneDestinazione(int circoscrizioneDestinazione) {
		this.circoscrizioneDestinazione = circoscrizioneDestinazione;
	}
	public int getTotaleEventi() {
		return totaleEventi;
	}
	public void setTotaleEventi(int totaleEventi) {
		this.totaleEventi = totaleEventi;
	}

	
	public String toString(){
		String r ;
		r = tipoEvento+" "+annoEvento+" "+anno+" "+mese+" "+giorno+" "+circoscrizioneProvenienza+" "+circoscrizioneDestinazione+" "+totaleEventi+" \n";
		return r;
	}
}
