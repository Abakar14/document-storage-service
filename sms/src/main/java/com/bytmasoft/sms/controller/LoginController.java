/**
 * @author Mahamat
 * Date 14.10.2020 : 11:16:54
 */
package com.bytmasoft.sms.controller;

import java.io.IOException;
import java.net.URL;
import java.net.http.HttpClient;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import com.bytmasoft.sms.view.FxmlView;
import com.bytmasoft.sms.view.StageManager;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * @author Mahamat Date 14.10.2020 : 11:16:54
 */
@Component
public class LoginController implements Initializable {


    @FXML
    private AnchorPane loginWindow;
    
	@FXML
	private ChoiceBox<String> languageChoiceBox;

	@FXML
	private Label school_title;

	@FXML
	private Label lang_label;
	@FXML
	private Label app_title;

	@FXML
	private Label greeting;

	@FXML
	private Button resetPasswordButton;

	@FXML
	private PasswordField password;

	@FXML
	private Label password_label;

	@FXML
	private Button passwordForgottenButton;

	@FXML
	private Button loginButton;

	@FXML
	private TextField username;
	@FXML
	private Label username_label;

	private StageManager stageManager;
	
	@Autowired
	private ConfigurableApplicationContext applicationContext;
	
	@Autowired
	private HttpClient client;
	
	
	@Value("${server.address}")
	private String url;
	


	protected void handleSubmitButtonAction(ActionEvent event) throws IOException, InterruptedException, ExecutionException {
		
		if (event.getSource().equals(loginButton)) {
			if (username.getText().equals("a") && password.getText().equals("a")) {
				System.out.println("Login successful...");
				System.out.println("Go to user's Modules");
				
						
				stageManager.switchScene(FxmlView.HOME);
				
			

			} else {
				System.out.println("Login failed...");
			}
		} else if (event.getSource().equals(resetPasswordButton)) {
			System.out.println("resetPasswordButton");
		} else if (event.getSource().equals(passwordForgottenButton)) {
			System.out.println("passwordForgottenButton");
		}

	}

	private void handelLanguageChoiceBox() {
		languageChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

//				if (newValue.equals("English")) {
//					I18N.setLocale(Locale.ENGLISH);
//
//				} else if (newValue.equals("Deutsch")) {
//					I18N.setLocale(Locale.GERMAN);
//
//				} else if (newValue.equals("French")) {
//					I18N.setLocale(Locale.FRENCH);
//
//				}

			}
		});

	}

	private void initBindingForTranslate() {
//		school_title.textProperty().bind(I18N.createStringBinding("school.title"));
//		app_title.textProperty().bind(I18N.createStringBinding("window.title"));
//		lang_label.textProperty().bind(I18N.createStringBinding("lang"));
//		greeting.textProperty().bind(I18N.createStringBinding("greeting"));
//		username_label.textProperty().bind(I18N.createStringBinding("username"));
//		password_label.textProperty().bind(I18N.createStringBinding("password"));
//		resetPasswordButton.textProperty().bind(I18N.createStringBinding("resetPassword"));
//		passwordForgottenButton.textProperty().bind(I18N.createStringBinding("passwordForgot"));
//		loginButton.textProperty().bind(I18N.createStringBinding("login"));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	 stageManager = applicationContext.getBean(StageManager.class);	
		
		assert languageChoiceBox != null : "fx:id=\"languageChoiceBox\" was not injected: check your FXML file 'login.fxml'.";
		assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'login.fxml'.";
		assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'login.fxml'.";
		assert passwordForgottenButton != null : "fx:id=\"passwordForgotten\" was not injected: check your FXML file 'login.fxml'.";
		assert loginButton != null : "fx:id=\"login\" was not injected: check your FXML file 'login.fxml'.";
		assert resetPasswordButton != null : "fx:id=\"resetPassword\" was not injected: check your FXML file 'login.fxml'.";
		assert greeting != null : "fx:id=\"greeting\" was not injected: check your FXML file 'login.fxml'.";
		assert school_title != null : "fx:id=\"school_title\" was not injected: check your FXML file 'login.fxml'.";
		assert app_title != null : "fx:id=\"app_title\" was not injected: check your FXML file 'login.fxml'.";
		assert languageChoiceBox != null : "fx:id=\"languageChoiceBox\" was not injected: check your FXML file 'login.fxml'.";
		assert lang_label != null : "fx:id=\"lang_label\" was not injected: check your FXML file 'login.fxml'.";
	      assert loginWindow != null : "fx:id=\"loginWindow\" was not injected: check your FXML file 'login.fxml'.";

	     stageManager.getPrimaryStage().setTitle(FxmlView.LOGIN.getTitle());
	    
	      
		loginButton.setOnAction(event -> {
			try {
				handleSubmitButtonAction(event);
			} catch (IOException | InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		handelLanguageChoiceBox();
		initBindingForTranslate();

	}

}
