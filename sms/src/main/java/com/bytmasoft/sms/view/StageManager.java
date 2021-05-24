package com.bytmasoft.sms.view;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.Objects;

import org.slf4j.Logger;

import com.bytmasoft.sms.classe.controller.ClasseController;
import com.bytmasoft.sms.controller.HomeController;
import com.bytmasoft.sms.controller.TimetableController;
import com.bytmasoft.sms.controller.UpdateController;
import com.bytmasoft.sms.spring.config.SpringFXMLLoader;
import com.bytmasoft.sms.student.controller.AddStudentController;
import com.bytmasoft.sms.student.controller.ShowStudentDetailsController;
import com.bytmasoft.sms.teacher.controller.AddTeacherController;
import com.bytmasoft.sms.teacher.controller.ShowTeacherDetailsController;
import com.bytmasoft.sms.teacher.controller.TeacherController;
import com.jfoenix.controls.pannable.PannableScrollPane;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StageManager {

	private static final Logger LOG = getLogger(StageManager.class);

	private final Stage primaryStage;
	private final SpringFXMLLoader springFXMLLoader;

	private ShowStudentDetailsController editStudentController;

	private AddStudentController addStudentController;

	private HomeController homeController;

	private ClasseController classeController;

	private UpdateController updateController;

	private TimetableController timetableController;

	private TeacherController teacherController;

	private AnchorPane studentRootContent;

	private PannableScrollPane rootContentScrollpane;

	public StageManager(SpringFXMLLoader loader, Stage stage) {
		this.primaryStage = stage;
//		this.primaryStage.initModality(Modality.NONE);		
		this.springFXMLLoader = loader;

		this.primaryStage.setOnCloseRequest(e -> Platform.exit());
	}

	public void switchScene(final FxmlView view) {
		Parent viewRootNodeHierarchy = loadViewNodeHierarchy(view.getFxmlFile());
		show(viewRootNodeHierarchy, view.getTitle());

	}

	/**
	 * @return the primaryStage
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void switchAndWait(final FxmlView view) {
		Parent viewRootNodeHierarchy = loadViewNodeHierarchy(view.getFxmlFile());
		Stage stage = new Stage();
		stage.setTitle(view.getTitle());
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

	public Parent loadViewNodeHierarchy(String fxmlFilePath) {
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

	public void cancelWindow(Button cancelButton) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}

	public void cancelWindow(MenuItem item) {

		ContextMenu cm = item.getParentPopup();
		Scene scene = cm.getScene();

		Stage stage = (Stage) scene.getWindow();
	}

	public Boolean logout() {

//		Window.getWindows().forEach(w -> {

//		for (Window w : Window.getWindows()) {		
//		
//			Stage stage  = (Stage) w.getScene().getWindow();
//			System.out.println("stage tilte ...."+stage.getTitle());
//			
//			if(stage.getTitle().equals(FxmlView.ADDSTUDENT.getTitle())) {
//				addStudentController.close();
//			}
//			
//			if(stage.getTitle().equals(FxmlView.EDITSTUDENT.getTitle())) {
//				editStudentController.close();
//			}
//			
//			if(stage.getTitle().equals(FxmlView.HOME.getTitle())) {
//				homeController.close();
//			}
//			
//			if(stage.getTitle().equals(FxmlView.UPDATE.getTitle())) {
//				updateController.close();
//			}
//			
//			if(stage.getTitle().equals(FxmlView.TIMETABLE.getTitle())) {
//				timetableController.close();
//			}
//			if(stage.getTitle().equals(FxmlView.TEACHER.getTitle())) {
//				teacherController.close();
//			}
//			
//		}

		switchScene(FxmlView.LOGIN);

		return true;

	}

	/**
	 * @param editStudentController
	 */
	public void addEditController(ShowStudentDetailsController editStudentController) {
		this.editStudentController = editStudentController;

	}

	/**
	 * @param addStudentController
	 */
	public void addAddStudentController(AddStudentController addStudentController) {
		this.addStudentController = addStudentController;

	}

	/**
	 * @param homeController
	 */
	public void addHomeController(HomeController homeController) {
		this.homeController = homeController;
	}

	/**
	 * @param updateController
	 */
	public void addUpdateController(UpdateController updateController) {
		this.updateController = updateController;

	}

	/**
	 * @param timetableController
	 */
	public void addTimetableController(TimetableController timetableController) {
		this.timetableController = timetableController;

	}

	/**
	 * @param teacherController
	 */
	public void addTeacherController(TeacherController teacherController) {
		this.teacherController = teacherController;

	}

	public void addClasseController(ClasseController classeController) {
		this.classeController = classeController;

	}

	/**
	 * @param filename
	 * @param mainContent
	 */
	public void addFileToMainContent(String filename) {

		Parent parent = loadViewNodeHierarchy(filename);

		AnchorPane.setBottomAnchor(parent, 50.0);
		AnchorPane.setLeftAnchor(parent, 50.0);
		AnchorPane.setRightAnchor(parent, 50.0);
		AnchorPane.setTopAnchor(parent, 50.0);

		parent.toFront();

		if (!studentRootContent.getChildren().isEmpty()) {
			studentRootContent.getChildren().forEach(n -> {
				n.toBack();
			});
		}

		studentRootContent.getChildren().setAll(parent);
	}

	/**
	 * @param rootContent
	 */
	public void setMainContent(AnchorPane rootContent) {
		this.studentRootContent = rootContent;

	}

	/**
	 * @param addTeacherController
	 */
	public void addAddTeacherController(AddTeacherController addTeacherController) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param showTeacherDetailsController
	 */
	public void addShowTeacherController(ShowTeacherDetailsController showTeacherDetailsController) {
		// TODO Auto-generated method stub

	}

}
