package com.bytmasoft.sms.view;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;

import com.bytmasoft.sms.spring.config.SpringFXMLLoader;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class StageManager {

	private static final Logger LOG = getLogger(StageManager.class);

	private final Stage primaryStage;
	private final SpringFXMLLoader springFXMLLoader;

	public StageManager(SpringFXMLLoader loader, Stage stage) {
		this.primaryStage = stage;
//		this.primaryStage.initModality(Modality.NONE);
		this.springFXMLLoader = loader;
	}

	public void switchScene(final FxmlView view) {
		Parent viewRootNodeHierarchy = LoadViewNodeHierarchy(view.getFxmlFile());
		show(viewRootNodeHierarchy, view.getTitle());

	}
	
	public void switchAndWait(final FxmlView view) {
		Parent viewRootNodeHierarchy = LoadViewNodeHierarchy(view.getFxmlFile());
		Stage stage = new Stage();
		stage.setScene(new Scene(viewRootNodeHierarchy));
		stage.initModality(Modality.NONE);
		stage.show();
//		show(viewRootNodeHierarchy, view.getTitle());

	}

	private void show(final Parent rootNode, String title) {

		Scene scene = prepareScene(rootNode);
		this.primaryStage.setTitle(title);
		this.primaryStage.setScene(scene);
		this.primaryStage.sizeToScene();
		this.primaryStage.centerOnScreen();

		try {
			this.primaryStage.show();
//			this.primaryStage.showAndWait();

		} catch (Exception e) {
			logAndExit("Unable to show the scene for this title " + title, e);
		}

	}
	
	

	private Scene prepareScene(Parent rootNode) {

		Scene scene = primaryStage.getScene();
		if (scene == null) {

			scene = new Scene(rootNode);
		}
		scene.setRoot(rootNode);
		return scene;

	}

	private Parent LoadViewNodeHierarchy(String fxmlFilePath) {
		Parent rootNode = null;

		try {
			rootNode = springFXMLLoader.load(fxmlFilePath);
			Objects.requireNonNull(rootNode, "A Root Fxml node must not be null");

		} catch (Exception e) {
			logAndExit("Unable to load FXML view ", e);
		}
		return rootNode;
	}

	private void logAndExit(String errorMessage, Exception e) {

		LOG.error(errorMessage, e.getCause());
		Platform.exit();

	}

	public void logout() {		
		this.switchScene(FxmlView.LOGIN);
//		List <Window> opens = (List<Window>) Stage.getWindows().stream().filter(Window::isShowing);
//		
//		opens.forEach(a ->{
//			System.out.println(a.getWindows().get(0));
//		});
//		
		
	}
	
	

}
