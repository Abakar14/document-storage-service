/**
 * @author Mahamat
 * Date 19.10.2020 : 14:04:25
 */
package com.bytmasoft.sms.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

/**
 * @author Mahamat Date 19.10.2020 : 14:04:25
 */
@Component
public class TimetableController implements Initializable {

	@Autowired
	private ConfigurableApplicationContext applicationContext;
	
	@FXML
	DatePicker terminDatePicker;
	private final String pattern = "dd.MM.yyyy";

	private LocalDate localDate = LocalDate.now();

	private void initDatePicker() {

		terminDatePicker.setValue(localDate);
		terminDatePicker.setConverter(converter());
		terminDatePicker.setPromptText(pattern.toLowerCase());

		terminDatePicker.setOnAction(event -> {

			System.out.println("Selected Date is " + terminDatePicker.getValue());
		});

	}

	private StringConverter<LocalDate> converter() {

		return new StringConverter<LocalDate>() {

			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

			@Override
			public String toString(LocalDate date) {
				if (date != null) {
					return dateFormatter.format(date);
				} else {

					return "";
				}
			}

			@Override
			public LocalDate fromString(String string) {
				if (string != null && !string.isEmpty()) {
					return LocalDate.parse(string, dateFormatter);
				} else {
					return null;
				}
			}
		};
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initDatePicker();

	}

//	private LocalDate formateDate(String date, DateTimeFormatter formatter) {
//		return LocalDate.parse(date, formatter);
//
//	}
//
//	private String formatDateToString(LocalDate date) {
//
//		return formatter.format(date);
//	}

}
