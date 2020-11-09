/**
 * @author Mahamat
 * Date 14.10.2020 : 11:16:54
 */
package com.bytmasoft.sms.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import com.bytmasoft.sms.model.Privilege;
import com.bytmasoft.sms.utils.JSONUtils;
import com.bytmasoft.sms.utils.PropertiesUtils;
import com.bytmasoft.sms.view.FxmlView;
import com.bytmasoft.sms.view.StageManager;
import com.fasterxml.jackson.core.type.TypeReference;

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

/**
 * @author Mahamat Date 14.10.2020 : 11:16:54
 */
@Component
public class LoginController implements Initializable {


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

		System.out.println("Url : " +url + "/privileges" );
		
		HttpRequest req = HttpRequest.newBuilder(URI.create(url + "/privileges"))
				.header("Content-Type", "application/json")
				.GET().build();
		
		CompletableFuture<HttpResponse<String>> response = this.client.sendAsync(req, BodyHandlers.ofString());
		
		response.thenAccept(res -> { 
							System.out.println("response : " +res.toString());
		}
		);
		
		System.out.println("response Body : " +response.get().body());
		
		List<Privilege> privileges = 
				JSONUtils.convertFromJsonToList(response.get().body(), new TypeReference<List<Privilege>>() {});
		
		privileges.forEach(p -> {
			System.out.println("id " + p.getId() + " Name " +p.getName() );
		});
		
		
		Privilege p1 = new Privilege();
		
	
		p1.setName("Test");
		p1.setStatus("A");
		
		HttpRequest req2 = HttpRequest.newBuilder(URI.create(url + "/privileges"))
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(JSONUtils.convertFromObjectToJson(p1))).build();
		
		CompletableFuture<HttpResponse<String>> response1 = client.sendAsync(req2, HttpResponse.BodyHandlers.ofString());
		System.out.println("Response body : " +response1.get().body());
		
		
		
		
		if (event.getSource().equals(loginButton)) {
			if (username.getText().equals("a") && password.getText().equals("a")) {
				System.out.println("Login successful...");
				System.out.println("Go to user's Modules");
				
				StageManager stageManager = applicationContext.getBean(StageManager.class);				
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
