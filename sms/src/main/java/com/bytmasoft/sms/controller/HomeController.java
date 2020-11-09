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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
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
    private Menu helpMenu;

    @FXML
    private MenuItem checkForUpdateItem;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private Menu userProfileMenu;

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
	protected void handelMenuItemClicks(ActionEvent event) throws IOException {
		if(event.getSource().equals(checkForUpdateItem)) {
			
		}else if(event.getSource().equals(aboutMenuItem)) {
			
		}else if(event.getSource().equals(logoutMenuItem)) {
			
			System.out.println(" Do something for logout ....");
			stageManager.logout();
			
		}
		
	}

	@FXML
	protected void handelButtonClicks(ActionEvent event) throws IOException {
		System.out.println("handelButtonClicks");
		System.out.println("Button clicks");
		if (event.getSource().equals(btnDashboard)) {
			System.out.println("Dashboard");
		
//			stageManager.switchScene(FxmlView.DASHBOARD);
			stageManager.switchAndWait(FxmlView.DASHBOARD);
		} else if (event.getSource().equals(btnStudents)) {
			System.out.println("Students");
		
//			stageManager.switchScene(FxmlView.STUDENT);
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

	private void loadStage(FXMLLoader loader) {
		try {
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.NONE);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		stageManager = applicationContext.getBean(StageManager.class);	
		
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
		
		logoutMenuItem.setOnAction( event -> {
			
			try {
				
				System.out.println("Logout from Home ...");
				handelMenuItemClicks(event);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});

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

	}

//	@FXML // fx:id="itemAddStudent"
//	private MenuItem itemAddStudent; // Value injected by FXMLLoader
//
//	@FXML // fx:id="itemShowStudents"
//	private MenuItem itemShowStudents; // Value injected by FXMLLoader
//
//	@FXML // fx:id="itemAddTeacher"
//	private MenuItem itemAddTeacher; // Value injected by FXMLLoader
//
//	@FXML // fx:id="itemShowTeachers"
//	private MenuItem itemShowTeachers; // Value injected by FXMLLoader
//
//	@FXML // fx:id="itemAddManager"
//	private MenuItem itemAddManager; // Value injected by FXMLLoader
//
//	@FXML // fx:id="itemShowManagers"
//	private MenuItem itemShowManagers; // Value injected by FXMLLoader
//
//	@FXML // fx:id="itemAddClass"
//	private MenuItem itemAddClass; // Value injected by FXMLLoader
//
//	@FXML // fx:id="itemShowClasses"
//	private MenuItem itemShowClasses; // Value injected by FXMLLoader
//
//	@FXML // fx:id="itemAddExam"
//	private MenuItem itemAddExam; // Value injected by FXMLLoader
//
//	@FXML // fx:id="itemShowExams"
//	private MenuItem itemShowExams; // Value injected by FXMLLoader
//
//	@FXML // fx:id="itemLogout"
//	private MenuItem itemLogout; // Value injected by FXMLLoader
//
//	@FXML // fx:id="itemCurrentArticle"
//	private MenuItem itemCurrentArticle; // Value injected by FXMLLoader
//
//	@FXML // fx:id="itemCurrentNews"
//	private MenuItem itemCurrentNews; // Value injected by FXMLLoader
//
//	@FXML // fx:id="itemCurrentConferance"
//	private MenuItem itemCurrentConferance; // Value injected by FXMLLoader
//
//	@FXML // fx:id="itemJobs"
//	private MenuItem itemJobs; // Value injected by FXMLLoader
//
//	@FXML // fx:id="itemConferanceCalender"
//	private MenuItem itemConferanceCalender; // Value injected by FXMLLoader
//
//	@FXML // fx:id="itemSitemap"
//	private MenuItem itemSitemap; // Value injected by FXMLLoader
//
//	@FXML // fx:id="itemImpressum"
//	private MenuItem itemImpressum; // Value injected by FXMLLoader
//
//	@FXML // fx:id="itemAboutUs"
//	private MenuItem itemAboutUs; // Value injected by FXMLLoader
//
//	@FXML // fx:id="itemContactUs"
//	private MenuItem itemContactUs; // Value injected by FXMLLoader
//
//	@FXML // fx:id="bodyCenterId"
//	private AnchorPane bodyCenterId; // Value injected by FXMLLoader

//	@FXML
//	void handelMenuClicks(ActionEvent event) {
//		System.out.println("handelMenuClicks");
//		if (event.getSource().equals(itemAddStudent)) {
//			System.out.println("add Student...");
//		} else if (event.getSource().equals(itemAddTeacher)) {
//			System.out.println("add Teacher...");
//		} else if (event.getSource().equals(itemAddManager)) {
//			System.out.println("add Manager...");
//		} else if (event.getSource().equals(itemAddClass)) {
//			System.out.println("add Class...");
//		} else if (event.getSource().equals(itemAddExam)) {
//			System.out.println("add Exam...");
//		} else if (event.getSource().equals(itemShowStudents)) {
//			System.out.println("show Students...");
//		} else if (event.getSource().equals(itemShowTeachers)) {
//			System.out.println("show teachers...");
//		} else if (event.getSource().equals(itemShowManagers)) {
//			System.out.println("show Manager...");
//		} else if (event.getSource().equals(itemShowClasses)) {
//			System.out.println("show Classes...");
//		} else if (event.getSource().equals(itemShowExams)) {
//			System.out.println("show Exams...");
//		} else if (event.getSource().equals(itemAboutUs)) {
//			System.out.println("About us...");
//		} else if (event.getSource().equals(itemLogout)) {
//			System.out.println("logout ....");
//		} else if (event.getSource().equals(itemAboutUs)) {
//			System.out.println("About us ....");
//		} else if (event.getSource().equals(itemConferanceCalender)) {
//			System.out.println("conferance calendar ....");
//		} else if (event.getSource().equals(itemCurrentArticle)) {
//			System.out.println("CurrentArticle ....");
//		} else if (event.getSource().equals(itemCurrentConferance)) {
//			System.out.println("itemCurrentConferance ....");
//		} else if (event.getSource().equals(itemCurrentNews)) {
//			System.out.println("itemCurrentNews ....");
//		} else if (event.getSource().equals(itemImpressum)) {
//			System.out.println("itemImpressum ....");
//		} else if (event.getSource().equals(itemJobs)) {
//			System.out.println("itemJobs ....");
//		} else if (event.getSource().equals(itemSitemap)) {
//			System.out.println("itemSitemap ....");
//		} else if (event.getSource().equals(itemContactUs)) {
//			System.out.println("itemContactUs ....");
//		}
//	}

//	@FXML // This method is called by the FXMLLoader when initialization is complete
//	void initialize() {
//		assert itemAddStudent != null : "fx:id=\"itemAddStudent\" was not injected: check your FXML file 'main.fxml'.";
//		assert itemShowStudents != null : "fx:id=\"itemShowStudents\" was not injected: check your FXML file 'main.fxml'.";
//		assert itemAddTeacher != null : "fx:id=\"itemAddTeacher\" was not injected: check your FXML file 'main.fxml'.";
//		assert itemShowTeachers != null : "fx:id=\"itemShowTeachers\" was not injected: check your FXML file 'main.fxml'.";
//		assert itemAddManager != null : "fx:id=\"itemAddManager\" was not injected: check your FXML file 'main.fxml'.";
//		assert itemShowManagers != null : "fx:id=\"itemShowManagers\" was not injected: check your FXML file 'main.fxml'.";
//		assert itemAddClass != null : "fx:id=\"itemAddClass\" was not injected: check your FXML file 'main.fxml'.";
//		assert itemShowClasses != null : "fx:id=\"itemShowClasses\" was not injected: check your FXML file 'main.fxml'.";
//		assert itemAddExam != null : "fx:id=\"itemAddExam\" was not injected: check your FXML file 'main.fxml'.";
//		assert itemShowExams != null : "fx:id=\"itemShowExams\" was not injected: check your FXML file 'main.fxml'.";
//		assert itemAboutUs != null : "fx:id=\"itemAboutUs\" was not injected: check your FXML file 'main.fxml'.";
//		assert itemLogout != null : "fx:id=\"itemLogout\" was not injected: check your FXML file 'main.fxml'.";
//		assert itemCurrentArticle != null : "fx:id=\"itemCurrentArticle\" was not injected: check your FXML file 'main.fxml'.";
//		assert itemCurrentNews != null : "fx:id=\"itemCurrentNews\" was not injected: check your FXML file 'main.fxml'.";
//		assert itemCurrentConferance != null : "fx:id=\"itemCurrentConferance\" was not injected: check your FXML file 'main.fxml'.";
//		assert itemJobs != null : "fx:id=\"itemJobs\" was not injected: check your FXML file 'main.fxml'.";
//		assert itemConferanceCalender != null : "fx:id=\"itemConferanceCalender\" was not injected: check your FXML file 'main.fxml'.";
//		assert itemSitemap != null : "fx:id=\"itemSitemap\" was not injected: check your FXML file 'main.fxml'.";
//		assert itemImpressum != null : "fx:id=\"itemImpressum\" was not injected: check your FXML file 'main.fxml'.";
//		assert itemAboutUs != null : "fx:id=\"itemAboutUs\" was not injected: check your FXML file 'main.fxml'.";
//		assert itemContactUs != null : "fx:id=\"itemContactUs\" was not injected: check your FXML file 'main.fxml'.";
//		assert bodyCenterId != null : "fx:id=\"bodyCenterId\" was not injected: check your FXML file 'main.fxml'.";
//
//	}
}
