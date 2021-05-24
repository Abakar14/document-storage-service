/**
 * @author Mahamat
 * Date 19.10.2020 : 13:38:13
 */
package com.bytmasoft.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import com.bytmasoft.clientDomain.models.Student;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * @author Mahamat Date 19.10.2020 : 13:38:13
 */
@Component
public class DashboardController {

	@Autowired
	private ConfigurableApplicationContext applicationContext;

	@FXML
	private TableView<Student> tbData;

	@FXML
	private TableColumn<Student, Integer> studentId;

	@FXML
	private TableColumn<Student, String> firstName;

	@FXML
	private TableColumn<Student, String> lastName;

	@FXML
	private PieChart pieChart;

	@FXML
	private BarChart<String, Number> barChart;

	@FXML
	private LineChart<Student, ?> lineChart;

	@FXML
	private BubbleChart<Student, ?> bubbleChart;

	@FXML
	private CategoryAxis xAxis;

	@FXML
	private NumberAxis yAxis;
	private ObservableList<Student> studentsModels;

//	private ObservableList<Student> studentsModels = FXCollections.observableArrayList(
//			new Student(1, "Amos", "Chepchieng"), new Student(2, "Amos", "Mors"),
//			new Student(3, "Amos", "Chepchieng"), new Student(4, "Amos", "Mors"),
//			new Student(5, "Abakar", "Mahamat"), new Student(6, "Abakar", "Roushed"),
//			new Student(7, "Abakar", "Tasnim"), new Student(8, "Frikh", "Amal"));

	@FXML
	void initialize() {

		loadBarChart();
		loadPieChart();
//		loadStudents();

		assert studentId != null : "fx:id=\"studentId\" was not injected: check your FXML file 'dashboard.fxml'.";
		assert firstName != null : "fx:id=\"firstName\" was not injected: check your FXML file 'dashboard.fxml'.";
		assert lastName != null : "fx:id=\"lastName\" was not injected: check your FXML file 'dashboard.fxml'.";
		assert pieChart != null : "fx:id=\"pieChart\" was not injected: check your FXML file 'dashboard.fxml'.";
		assert barChart != null : "fx:id=\"barChart\" was not injected: check your FXML file 'dashboard.fxml'.";
		assert lineChart != null : "fx:id=\"lineChart\" was not injected: check your FXML file 'dashboard.fxml'.";
		assert bubbleChart != null : "fx:id=\"bubbleChart\" was not injected: check your FXML file 'dashboard.fxml'.";
		assert xAxis != null : "fx:id=\"xAxis\" was not injected: check your FXML file 'dashboard.fxml'.";
		assert yAxis != null : "fx:id=\"yAxis\" was not injected: check your FXML file 'dashboard.fxml'.";

	}

	/**
	 * 
	 */
	private void loadStudents() {

		studentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
		firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		tbData.setItems(studentsModels);

	}

	/**
	 * 
	 */
	private void loadPieChart() {

		PieChart.Data slice1 = new PieChart.Data("Classes", 213);
		PieChart.Data slice2 = new PieChart.Data("Attendance", 67);
		PieChart.Data slice3 = new PieChart.Data("Teachers", 36);

		pieChart.getData().add(slice1);
		pieChart.getData().add(slice2);
		pieChart.getData().add(slice3);

	}

	private void loadBarChart() {

		String ahmed = "Ahmed";
		String ali = "Ali";
		String adam = "Adam";
		String roushed = "Roushed";

		XYChart.Series series1 = new XYChart.Series();
		series1.setName("2018");
		series1.getData().add(new XYChart.Data(ahmed, 111.34));
		series1.getData().add(new XYChart.Data(ali, 123.34));
		series1.getData().add(new XYChart.Data(adam, 111.3));
		series1.getData().add(new XYChart.Data(roushed, 47.6));

		XYChart.Series series2 = new XYChart.Series();
		series2.setName("2019");
		series2.getData().add(new XYChart.Data(ahmed, 34.4));
		series2.getData().add(new XYChart.Data(ali, 65.44));
		series2.getData().add(new XYChart.Data(adam, 78.4));
		series2.getData().add(new XYChart.Data(roushed, 76.43));

		XYChart.Series series3 = new XYChart.Series();
		series3.setName("2020");
		series3.getData().add(new XYChart.Data(ahmed, 113.33));
		series3.getData().add(new XYChart.Data(ali, 119.33));
		series3.getData().add(new XYChart.Data(adam, 132.33));
		series3.getData().add(new XYChart.Data(roushed, 123.3));

		barChart.setBarGap(3);
		barChart.setCategoryGap(20);
		barChart.getData().addAll(series1, series2, series3);

	}

	private void loadLineChart() {

	}

	private void loadBubbleChart() {

	}

}
