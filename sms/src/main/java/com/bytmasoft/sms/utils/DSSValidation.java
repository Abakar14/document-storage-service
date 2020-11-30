/**
 * 
 */
package com.bytmasoft.sms.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.core.io.ClassPathResource;

import com.bytmasoft.clientDomain.models.BaseUser;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import lombok.extern.log4j.Log4j2;

/**
 * @author Mahamat Abakar created on 17.11.2020 at 22:02:09
 */
@Log4j2
public class DSSValidation {

	private static String title = "Validate Fields";

	private String iconsUrl = "com/bytmasoft/school/sms/images/icons/";

	private ClassLoader loader = getClass().getClassLoader();

	/**
	 * 
	 */
	public DSSValidation() {

	}

	private Image getImageIcon(String filename) {
		ClassPathResource resource = new ClassPathResource(filename);

		InputStream is;
		try {
			is = resource.getInputStream();
			return new Image(is);
		} catch (IOException e) {

			log.error(e.getMessage());
		}
		return null;

	}

	public RequiredFieldValidator getRequiredFieldValidator(JFXTextField textField, String message) {

		RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator();
		ImageView iv = new ImageView(getImageIcon(iconsUrl + "icon.gif"));
		iv.setFitWidth(25);
		iv.setFitHeight(25);
		requiredFieldValidator.setIcon(iv);

		textField.getValidators().add(requiredFieldValidator);

		requiredFieldValidator.setMessage(message);

		textField.focusedProperty().addListener((observable, oldValue, newValue) -> {

			if (!newValue) {

				textField.validate();

			}
		});

		return requiredFieldValidator;
	}

	public static void noBlanks(final TextField textField) {

		textField.focusedProperty().addListener((observable, oldValue, newValue) -> {

			if (!newValue) {

				textField.setStyle("-fx-background-color: red;");

				System.out.println("first name invalid");
			}
		});
	}

	/**
	 * @return
	 */
	private static EventHandler<KeyEvent> createNoBlanksInputHandler() {

		return (event -> {

			if (event.getSource() instanceof TextField) {
				if (" ".equals(event.getCharacter())) {
					event.consume();
				}
			}
		});

	}

	public static boolean validateEmail(TextField email) {

		Pattern pattern = Pattern.compile("");
		Matcher matcher = pattern.matcher(email.getText());

		if (matcher.find() && matcher.group().equals(email)) {
			return true;

		} else {
			warringAlert(title, "E-Mail is not Valid please insert valid E-mail ...!");
			return false;
		}

	}

	/**
	 * 1- must contains one digit from 0-9 2- must contains one lowercase characters
	 * 3- must contains one uppercase characters 4- must contains one special symbol
	 * in the list "@#$%" 5- match anything with previous conditions 6- Length at
	 * least 6 characters and max 15
	 * 
	 * 
	 * @param password
	 * @return
	 */

	public static boolean isPasswordValid(TextField password) {

		boolean isValid = false;

		if (isTextFieldsValid(Arrays.asList(password))) {

			Pattern pattern = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,15}");
			Matcher matcher = pattern.matcher(password.getText());

			if (matcher.matches()) {
				isValid = true;

			} else {

				warringAlert(title,
						"Password must contain at least one(Digit, Lowercase char, \nuppercase char and special char, \nand lenth must be between 6 and 15 ...)");

				isValid = false;
			}

		}
		return isValid;

	}

	public static boolean isDatePickerValid(DatePicker picker) {

		boolean isValid = true;

		return isValid;

	}

	public static boolean isTextFieldsValid(List<TextField> textFields) {

		boolean isValid = true;
		for (TextField textField : textFields) {
			if (textField == null || textField.getText().trim().isEmpty()) {
				warringAlert(title, warrningMessage(textField));
				isValid = false;
			} else {
				isValid = true;
			}
		}
		return isValid;
	}

	public static boolean isTextFieldsValidWithoutWarning(List<TextField> textFields) {

		boolean isValid = true;
		for (TextField textField : textFields) {
			if (textField == null || textField.getText().trim().isEmpty()) {
				isValid = false;
			} else {
				isValid = true;
			}
		}
		return isValid;
	}

	private static String warrningMessage(TextField textField) {

		String message = "";
		if (textField.getId().equals("passwordTextField")) {
			message = " Please insert a password for the student ...!";
		}
		if (textField.getId().equals("emailTextField")) {
			message = " Please insert a E-mail for the student ...!";
		}

		if (textField.getId().equals("firstNameTextField")) {
			message = " Please insert a First name for the student ...!";
		}

		if (textField.getId().equals("lastNameTextField")) {
			message = " Please insert a Last Name for the student ...!";
		}
		if (textField.getId().equals("mobileTextField")) {
			message = " Please insert a Mobile phone number for the student ...!";
		}

		if (textField.getId().equals("cityTextField")) {
			message = " Please insert a City for the student ...!";
		}
		if (textField.getId().equals("streetTextField")) {
			message = " Please insert a Street for the student ...!";

		}
		if (textField.getId().equals("HauseNrTextField")) {
			message = " Please insert a Hause Nr for the student ...!";
		}
		if (textField.getId().equals("ZipcodeTextField")) {
			message = " Please insert a Zipcode for the student ...!";
		}

		if (textField.getId().equals("contactPersonfirstNameTextField")) {
			message = " Please insert a Contact Person First Name for the student ...!";
		}
		if (textField.getId().equals("contactPersonLastNameTextField")) {
			message = " Please insert a Contact Person Last Name for the student ...!";
		}

		if (textField.getId().equals("ContactPersonMobileTextField")) {
			message = " Please insert a E-mail for the student ...!";
		}

		if (textField.getId().equals("contactPersonTelefonTextField")) {
			message = " Please insert a Contact Person Telefon for the student ...!";
		}

		if (textField.getId().equals("contactPersonEmailTextField")) {
			message = " Please insert Contact Person E-Mail for the student ...!";
		}
		return message;
	}

	public static boolean isToggleGroupValid(List<ToggleGroup> tooglegroup) {

		boolean isValid = true;

		for (ToggleGroup toggleGroup : tooglegroup) {
			if (toggleGroup.getSelectedToggle() == null) {
				isValid = false;
				warringAlert(title, "Please check gender Field ...!");
			} else {
				isValid = true;
			}
		}

		return isValid;
	}

	private static Alert warringAlert(String title, String content) {
		Alert alert = new Alert(AlertType.WARNING);

		alert.setTitle(title);
		alert.setContentText(content);
//		alert.showAndWait();
		return alert;
	}

	public static Alert infoAlert(String title, String content) {

		Alert alert = new Alert(AlertType.INFORMATION);

		alert.setTitle(title);
		alert.setContentText(content);
//		alert.showAndWait();

		return alert;

	}

	public static Alert confirmAlert(String title, String content) {

		Alert alert = new Alert(AlertType.CONFIRMATION);

		alert.setTitle(title);
		alert.setContentText(content);
//		alert.showAndWait();
		return alert;

	}

	public static Alert errorAlert(String title, String content) {

		Alert alert = new Alert(AlertType.ERROR);

		alert.setTitle(title);
		alert.setContentText(content);
//		alert.showAndWait();
		return alert;

	}

	public static boolean isObjectNull(Object obj) {
		if (null == obj)
			return true;
		else
			return false;

	}

	public boolean isListsEqual(List<BaseUser> firstList, List<BaseUser> secondList) {

		boolean isEqual = true;

		if (firstList.size() != secondList.size()) {
			isEqual = false;
		} else {
			for (BaseUser user : firstList) {
				if (!secondList.contains(user)) {
					isEqual = false;
				}
			}
		}

		return isEqual;

	}

}
