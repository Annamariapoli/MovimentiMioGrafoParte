package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SampleController {
	
	private Model m = new Model();
	
	public void setModel(Model m){
		this.m=m;
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Integer> combo;

    @FXML
    private Button btnElenca;

    @FXML
    private TextField txtLung;

    @FXML
    private Button btnRicerca;

    @FXML
    private TextArea txtResulot;

    @FXML
    private ProgressBar progress;

    @FXML
    void doElenca(ActionEvent event) {
    	txtResulot.clear();
    	int circoscrizione = combo.getValue();
    	if(combo.getValue()==0){
    		txtResulot.appendText("Seleziona una circoscrizione!\n");
    		return;
    	}

    	List<String> cambi = m.getNumCambiEDest(circoscrizione);
    	for(String s : cambi){
    		txtResulot.appendText(s +" \n");
    	}
    	

    }

    @FXML
    void doRicerca(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert combo != null : "fx:id=\"combo\" was not injected: check your FXML file 'Sample.fxml'.";
        assert btnElenca != null : "fx:id=\"btnElenca\" was not injected: check your FXML file 'Sample.fxml'.";
        assert txtLung != null : "fx:id=\"txtLung\" was not injected: check your FXML file 'Sample.fxml'.";
        assert btnRicerca != null : "fx:id=\"btnRicerca\" was not injected: check your FXML file 'Sample.fxml'.";
        assert txtResulot != null : "fx:id=\"txtResulot\" was not injected: check your FXML file 'Sample.fxml'.";
        assert progress != null : "fx:id=\"progress\" was not injected: check your FXML file 'Sample.fxml'.";

        combo.getItems().addAll(m.getCircoscrizioni());
    }
}
