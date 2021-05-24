/**
 * @author Mahamat
 * Date 19.10.2020 : 14:05:31
 */
package com.bytmasoft.sms.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import com.bytmasoft.sms.view.StageManager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Mahamat
 * Date 19.10.2020 : 14:05:31
 */
@Component
public class UpdateController implements Initializable{

	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane updateAnchorPane;

    
	@Autowired
	private ConfigurableApplicationContext applicationContext;

	private StageManager stageManager;

	public void close() {
		Stage stage = (Stage) this.updateAnchorPane.getScene().getWindow();
		stage.close();
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		stageManager = applicationContext.getBean(StageManager.class);
		stageManager.addUpdateController(this);
		
	     assert updateAnchorPane != null : "fx:id=\"updateAnchorPane\" was not injected: check your FXML file 'update.fxml'.";

		
	}
}
