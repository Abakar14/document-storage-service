/**
 * @author Mahamat
 * Date 17.10.2020 : 11:12:13
 */
package com.bytmasoft.sms.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import com.bytmasoft.sms.view.FxmlView;
import com.bytmasoft.sms.view.StageManager;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Mahamat Date 17.10.2020 : 11:12:13
 */
@Component
public class HomeController implements Initializable {

	@Autowired
	private ConfigurableApplicationContext applicationContext;

	private StageManager stageManager;

    @FXML
    private AnchorPane homeAnchorPane;
	@FXML
	private Menu helpMenu;

	@FXML
	private MenuItem checkForUpdateItem;

	@FXML
	private MenuItem aboutMenuItem;

	@FXML
	private Menu userProfileMenu;

	@FXML
	private MenuBar helpMenubar;

	@FXML
	private MenuItem logoutMenuItem;

	@FXML
	private Button btnTeachers;

	@FXML
	private Button btnClasses;

	@FXML
	private Button btnStudents;

	@FXML
	private Button btnTimetable;

	@FXML
	private Button btnExams;

	@FXML
	private Button btnSettings;

	@FXML
	private Button btnUpdate;

	@FXML
	private Button btnDashboard;

	@FXML
	void logout(ActionEvent event) {

		stageManager.logout();
		
	}

	@FXML
	protected void handelMenuItemClicks(ActionEvent event) throws IOException {
		if (event.getSource().equals(checkForUpdateItem)) {

		} else if (event.getSource().equals(aboutMenuItem)) {

		} else if (event.getSource().equals(logoutMenuItem)) {

			System.out.println(" Do something for logout ....");
		
		}

	}

	@FXML
	protected void handelButtonClicks(ActionEvent event) throws IOException {

		System.out.println("handelButtonClicks");
		System.out.println("Button clicks");

		if (event.getSource().equals(btnDashboard)) {
			System.out.println("Dashboard");

			stageManager.switchAndWait(FxmlView.DASHBOARD);

		} else if (event.getSource().equals(btnStudents)) {
			System.out.println("Students");
			stageManager.switchAndWait(FxmlView.STUDENT);

		} else if (event.getSource().equals(btnTeachers)) {
			System.out.println("Teachers");
			stageManager.switchAndWait(FxmlView.TEACHER);

		} else if (event.getSource().equals(btnClasses)) {
			System.out.println("Classes");

			stageManager.switchAndWait(FxmlView.CLASSES);
		} else if (event.getSource().equals(btnTimetable)) {
			System.out.println("timetable");

			stageManager.switchAndWait(FxmlView.TIMETABLE);
		} else if (event.getSource().equals(btnExams)) {
			System.out.println("Exams");

			stageManager.switchScene(FxmlView.EXAM);
		} else if (event.getSource().equals(btnSettings)) {
			System.out.println("Settings");
			stageManager.switchAndWait(FxmlView.SETTING);
		} else if (event.getSource().equals(btnUpdate)) {
			System.out.println("update");

			stageManager.switchAndWait(FxmlView.UPDATE);
		}

	}
	

//	private void loadStage(FXMLLoader loader) {
//		try {
//			Parent root = loader.load();
//			Stage stage = new Stage();
//			stage.setScene(new Scene(root));
//			stage.initModality(Modality.NONE);
//			stage.show();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public void close() {
		Stage stage = (Stage) this.homeAnchorPane.getScene().getWindow();
		stage.close();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		stageManager = applicationContext.getBean(StageManager.class);
		stageManager.addHomeController(this);

		aboutMenuItem.setOnAction(event -> {

			try {

				System.out.println("about menu Item  from Home ...");
				handelMenuItemClicks(event);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		checkForUpdateItem.setOnAction(event -> {

			try {

				System.out.println("check For Update from Home ...");
				handelMenuItemClicks(event);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

//		logoutMenuItem.setOnAction(event -> {
//
//			try {
//
//				
//				handelMenuItemClicks(event);
//
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		});

		btnUpdate.setOnAction((event -> {
			try {
				handelButtonClicks(event);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}));

		btnSettings.setOnAction((event -> {
			try {
				handelButtonClicks(event);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}));

		btnExams.setOnAction((event -> {
			try {
				handelButtonClicks(event);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}));

		btnTimetable.setOnAction((event -> {
			try {
				handelButtonClicks(event);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}));

		btnClasses.setOnAction((event -> {
			try {
				handelButtonClicks(event);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}));

		btnStudents.setOnAction((event -> {
			try {
				handelButtonClicks(event);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}));

		btnDashboard.setOnAction((event -> {
			try {
				handelButtonClicks(event);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}));

		btnTeachers.setOnAction((event -> {
			try {
				handelButtonClicks(event);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}));
	      assert homeAnchorPane != null : "fx:id=\"homeAnchorPane\" was not injected: check your FXML file 'home.fxml'.";
		assert helpMenubar != null : "fx:id=\"helpMenubar\" was not injected: check your FXML file 'home.fxml'.";
		assert helpMenu != null : "fx:id=\"helpMenu\" was not injected: check your FXML file 'home.fxml'.";
		assert checkForUpdateItem != null
				: "fx:id=\"checkForUpdateItem\" was not injected: check your FXML file 'home.fxml'.";
		assert aboutMenuItem != null : "fx:id=\"aboutMenuItem\" was not injected: check your FXML file 'home.fxml'.";
		assert userProfileMenu != null
				: "fx:id=\"userProfileMenu\" was not injected: check your FXML file 'home.fxml'.";
		assert logoutMenuItem != null : "fx:id=\"logoutMenuItem\" was not injected: check your FXML file 'home.fxml'.";
		assert btnStudents != null : "fx:id=\"btnStudents\" was not injected: check your FXML file 'home.fxml'.";
		assert btnTeachers != null : "fx:id=\"btnTeachers\" was not injected: check your FXML file 'home.fxml'.";
		assert btnClasses != null : "fx:id=\"btnClasses\" was not injected: check your FXML file 'home.fxml'.";
		assert btnTimetable != null : "fx:id=\"btnTimetable\" was not injected: check your FXML file 'home.fxml'.";
		assert btnExams != null : "fx:id=\"btnExams\" was not injected: check your FXML file 'home.fxml'.";
		assert btnSettings != null : "fx:id=\"btnSettings\" was not injected: check your FXML file 'home.fxml'.";
		assert btnUpdate != null : "fx:id=\"btnUpdate\" was not injected: check your FXML file 'home.fxml'.";
		assert btnDashboard != null : "fx:id=\"btnDashboard\" was not injected: check your FXML file 'home.fxml'.";

	}

}
