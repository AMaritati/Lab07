package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Nerc> comboNerc;

    @FXML
    private TextField txtYears;

    @FXML
    private TextField txtHours;

    @FXML
    private Button btnWorst;

    @FXML
    private TextArea txtResult;

    @FXML
    void doWorst(ActionEvent event) {
    	int years = 0;
    	int hours = 0;
    	Nerc nerc = comboNerc.getValue();

    	// controllo sulle stringhe se sono numeri interi  	
    	if (this.model.controllaTxt(txtYears.getText())) {
    		years = Integer.parseInt(txtYears.getText());
    	}
    	else {
    		txtResult.setText("NON HAI INSERITO UN NUMERO VALIDO DI ANNI");
    		return;
    	}
    	
    	if (this.model.controllaTxt(txtHours.getText())) {
    		hours = Integer.parseInt(txtHours.getText());
    	}
    	else {
    		txtResult.setText("NON HAI INSERITO UN NUMERO VALIDO DI MESI");
    		return;
    	}

    }

    void addNerc() {
    	comboNerc.getItems().addAll(this.model.getNercList());
    }
    
    @FXML
    void initialize() {
        assert comboNerc != null : "fx:id=\"comboNerc\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnWorst != null : "fx:id=\"btnWorst\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

        model = new Model();
        addNerc();
    }
    
    public void setModel(Model model) {
    	this.model=model;
    	}
}

