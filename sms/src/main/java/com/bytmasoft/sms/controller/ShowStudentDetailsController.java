/**
 * @author Mahamat
 * Date 19.10.2020 : 14:04:07
 */
package com.bytmasoft.sms.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import com.bytmasoft.StudentClient;
import com.bytmasoft.clientDomain.enums.GenderType;
import com.bytmasoft.clientDomain.models.Address;
import com.bytmasoft.clientDomain.models.ContactPerson;
import com.bytmasoft.clientDomain.models.Student;
import com.bytmasoft.common.utils.DSSFormat;
import com.bytmasoft.sms.utils.DSSValidation;
import com.bytmasoft.sms.utils.DateTimePicker;
import com.bytmasoft.sms.view.FxmlView;
import com.bytmasoft.sms.view.StageManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import lombok.extern.log4j.Log4j2;

/**
 * @author Mahamat Date 19.10.2020 : 14:04:07
 */
@Log4j2
@Component
public class ShowStudentDetailsController implements Initializable {

	@Autowired
	private ConfigurableApplicationContext applicationContext;

	@Value("${student.service.address}")
	private String surl;

	@Value("${dss.dateTime.format}")
	private String dateTimeFormat;

	private Student student;

	@Autowired
	private StudentClient studentClient;

	DSSValidation dssValidation;

	@Autowired
	ShowAllStudentsController showAllStudentsController;

	@Autowired
	private StudentController studentController;

	private StageManager stageManager;

	private String lastNameError = "Last Name is Required ...";

	private String firstNameError = "First Name is Requird";
	private String emailError = "E-Mail Name is Requird";

	@FXML
	private AnchorPane showStudentDetailsAnchorPane;

	@FXML
	private JFXTextField registerdOnTextField;

	@FXML
	private JFXTextField lastLoginTextField;

	private DateTimePicker lastLoginTimePicker = new DateTimePicker();

	private DateTimePicker registerdOnTimePicker = new DateTimePicker();

	private ToggleGroup genderToggleGroup;

	private ToggleGroup contactPersonGenderToggleGroup;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TitledPane editStudentPane;

	@FXML
	private JFXTextField firstNameFieldText;

	@FXML
	private JFXTextField lastNameFieldText;

	@FXML
	private JFXTextField passwordTextField;

	@FXML
	private ImageView profilePictureImageView;

	@FXML
	private JFXTextField emailFieldText;

	@FXML
	private JFXTextField telefonTextField;

	@FXML
	private JFXTextField statusTextField;

//    @FXML
//    private JFXTextField registerdOnTimePickerId;
//
//    @FXML
//    private JFXTextField lastLoginTimePickerId;

	@FXML
	private JFXDatePicker birthdayDatePicker;

	@FXML
	private JFXToggleButton maleToggleButton;

	@FXML
	private JFXToggleButton femaleToggleButton;

	@FXML
	private JFXButton uploadProfilePictureButton;

	@FXML
	private TitledPane editAddressPane;

	@FXML
	private JFXTextField cityTextField;

	@FXML
	private JFXTextField zipcodeTextField;

	@FXML
	private JFXTextField streetTextField;

	@FXML
	private JFXTextField hauseNrTextField;

	@FXML
	private TitledPane editContactPersonPane;

	@FXML
	private JFXTextField contactPersonfirstNameTextField;

	@FXML
	private JFXTextField contactPersonLastNameTextField;

	@FXML
	private JFXTextField contactPersonTelefonTextField;

	@FXML
	private JFXTextField contactPersonEmailTextField;
	@FXML
	private JFXToggleButton contactPersonMaleToggleButton;

	@FXML
	private JFXToggleButton contactPersonFemaleToggleButton;

	@FXML
	private JFXButton backToMainContent;

	@FXML
	private JFXButton cancelButton;

	@FXML
	private JFXButton saveButton;

	@FXML
	void goBackToMainContent(ActionEvent event) {

		System.out.println("go back to maincontent");
		stageManager.addFileToMainContent(FxmlView.SHOWALLSTUDENTS.getFxmlFile());
	}

	@FXML
	void Save(ActionEvent event) {
		System.out.println("Save Button ..");

		updateStudent();

	}

	@FXML
	void cancel(ActionEvent event) {
		System.out.println("Cancel Button ...");
		stageManager.cancelWindow(cancelButton);

	}

	@FXML
	void uploadImage(ActionEvent event) {

		FileChooser fileChooser = new FileChooser();

		// setExtension filter
		FileChooser.ExtensionFilter extensionFilterJPG = new ExtensionFilter("JPG files (.JPG)", "*.JPG");
		FileChooser.ExtensionFilter extensionFilterjpg = new ExtensionFilter("jpg files (.jpg)", "*.jpg");
		FileChooser.ExtensionFilter extensionFilterPNG = new ExtensionFilter("PNG files (.PNG)", "*.PNG");
		FileChooser.ExtensionFilter extensionFilterpng = new ExtensionFilter("png files (.png)", "*.png");

		fileChooser.getExtensionFilters().addAll(extensionFilterJPG, extensionFilterjpg, extensionFilterPNG,
				extensionFilterpng);

		// show open file dialog
		File imageFile = fileChooser.showOpenDialog(new Stage());

		if (imageFile != null) {
			try {
				BufferedImage bufferedImage = ImageIO.read(imageFile);

				Image image = SwingFXUtils.toFXImage(bufferedImage, null);
				profilePictureImageView.setFitWidth(100);
				profilePictureImageView.setFitHeight(150);
				profilePictureImageView.setPreserveRatio(true);

				profilePictureImageView.setImage(image);

				FileInputStream is = new FileInputStream(imageFile);
				this.student.setProfile_picture(is.readAllBytes());

			} catch (IOException e) {
				log.error(e);
			}

		}
	}

	/**
	 * @param student the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;

	}

	public void initStudentDetails() {

		if (student != null) {
			try {
				/******************** student data ******************************/

				firstNameFieldText.setText(student.getFirstName());
				lastNameFieldText.setText(student.getLastName());
				emailFieldText.setText(student.getEmail());
				statusTextField.setText(student.getStatus());
				birthdayDatePicker.setValue(student.getBirthday());

				if (!DSSValidation.isObjectNull(student.getLastLogin())) {

					lastLoginTimePicker.setDateTimeValue(student.getLastLogin());
				}
				lastLoginTimePicker.setEditable(false);
				lastLoginTimePicker.setPrefWidth(134);

				lastLoginTextField.setText(
						DSSFormat.formateLocalDateTimeToString(lastLoginTimePicker.getDateTimeValue(), dateTimeFormat));

				if (!DSSValidation.isObjectNull(student.getCreatedOn())) {

					registerdOnTimePicker.setDateTimeValue(student.getCreatedOn());
				}

				registerdOnTimePicker.setEditable(false);
				registerdOnTimePicker.setPrefWidth(134);
				registerdOnTextField.setText(DSSFormat
						.formateLocalDateTimeToString(registerdOnTimePicker.getDateTimeValue(), dateTimeFormat));

				if (!DSSValidation.isObjectNull(student.getMobilePhoneNr())) {

					telefonTextField.setText(student.getMobilePhoneNr());
				}

				if (student.getGender().toString().equals("Mr")) {
					maleToggleButton.setSelected(true);

				} else if (student.getGender().toString().equals("Mrs")) {
					femaleToggleButton.setSelected(true);
				}

				if (student.getProfile_picture() != null) {

					InputStream input = new ByteArrayInputStream(student.getProfile_picture());
					BufferedImage bufferedImage = ImageIO.read(input);
					Image image = SwingFXUtils.toFXImage(bufferedImage, null);
					this.profilePictureImageView.setImage(image);

				}

				/******************** student address ******************************/

				if (student.getAddresses() != null && !student.getAddresses().isEmpty()) {
					Set<Address> addresses = student.getAddresses();
					Address address = addresses.stream().findFirst().get();

					cityTextField.setText(address.getCity());
					streetTextField.setText(address.getStreet());
					hauseNrTextField.setText(address.getHauseNumber());
					zipcodeTextField.setText(address.getPostalCode());

				}

				/******************** student contact Person ******************************/

				if (student.getContactPersons() != null && !student.getContactPersons().isEmpty()) {

					Set<ContactPerson> contacts = student.getContactPersons();
					ContactPerson person = contacts.stream().findFirst().get();

					this.contactPersonfirstNameTextField.setText(person.getFirstName());
					this.contactPersonLastNameTextField.setText(person.getLastName());
					this.contactPersonTelefonTextField.setText(person.getMobilePhoneNr());
					this.contactPersonEmailTextField.setText(person.getEmail());

					if (person.getGender().toString().equals("Mr")) {
						contactPersonMaleToggleButton.setSelected(true);

					} else if (person.getGender().toString().equals("Mrs")) {
						contactPersonFemaleToggleButton.setSelected(true);
					}

				}

			} catch (IOException e) {
				log.error("Some problem ocurred while loading students ...: " + e.getMessage());
			}

		} else {
			System.out.println("Student is null ...");
		}
	}

	@SuppressWarnings("unlikely-arg-type")
	public void updateStudent() {

//			if (DSSValidation.isTextFieldsValid(
//				Arrays.asList(firstNameFieldText, lastNameFieldText, emailFieldText, telefonTextField))
//				&&DSSValidation.isDatePickerValid(birthdayDatePicker) && DSSValidation.isToggleGroupValid(Arrays.asList(genderToggleGroup))) {

		student.setFirstName(firstNameFieldText.getText());
		student.setLastName(lastNameFieldText.getText());
		student.setEmail(emailFieldText.getText());
		student.setMobilePhoneNr(telefonTextField.getText());
		student.setBirthday(birthdayDatePicker.getValue());

		if (DSSValidation.isTextFieldsValidWithoutWarning(Arrays.asList(passwordTextField))) {
			student.setPassword(passwordTextField.getText());

		}

		if (genderToggleGroup.getSelectedToggle().equals(femaleToggleButton)) {

			student.setGender(GenderType.Mrs);
		} else {
			student.setGender(GenderType.Mr);
		}

		/**
		 * Add Address to Student
		 */
		if (DSSValidation.isTextFieldsValidWithoutWarning(
				Arrays.asList(streetTextField, cityTextField, zipcodeTextField, hauseNrTextField))) {

			Address address = new Address();

			address.setCity(cityTextField.getText());
			address.setStreet(streetTextField.getText());
			address.setPostalCode(zipcodeTextField.getText());
			address.setHauseNumber(hauseNrTextField.getText());

			student.getAddresses().add(address);
		}

		/**
		 * Add Contact Person to Student
		 */
		if (DSSValidation.isTextFieldsValidWithoutWarning(Arrays.asList(contactPersonfirstNameTextField,
				contactPersonLastNameTextField, contactPersonTelefonTextField, contactPersonEmailTextField))) {

			ContactPerson person = new ContactPerson();
			person.setFirstName(contactPersonfirstNameTextField.getText());
			person.setLastName(contactPersonLastNameTextField.getText());
			person.setMobilePhoneNr(contactPersonTelefonTextField.getText());
			person.setPhoneNr(contactPersonTelefonTextField.getText());
			person.setEmail(contactPersonEmailTextField.getText());

			if (contactPersonGenderToggleGroup.getSelectedToggle().equals(contactPersonFemaleToggleButton)) {

				person.setGender(GenderType.Mrs);
			} else {
				person.setGender(GenderType.Mr);
			}

			student.getContactPersons().add(person);

		}

		Optional<ButtonType> response = DSSValidation
				.infoAlert("Update Student", "Wollen Sie die Student-Daten aktualisiern!...").showAndWait();

		if (response.get().equals(ButtonType.OK)) {

			studentClient.update("/students/" + student.getId(), student).subscribe(e -> {

				showAllStudentsController.refreshStudentsTableView();

			});

		}

	}

//	}

	public void datePickerconverter() {

		birthdayDatePicker.setConverter(new StringConverter<LocalDate>() {
			String pattern = "dd.MM.yyyy";
			DateTimeFormatter dateTimeFormater = DateTimeFormatter.ofPattern(pattern);

			{
				birthdayDatePicker.setPromptText(pattern.toLowerCase());
			}

			@Override
			public String toString(LocalDate date) {

				if (date != null) {
					return dateTimeFormater.format(date);
				}
				return "";
			}

			@Override
			public LocalDate fromString(String string) {
				if (string != null && !string.isEmpty()) {
					return LocalDate.parse(string, dateTimeFormater);
				} else

				{

					return null;
				}
			}
		});
	}

	public void close() {
		Stage stage = (Stage) this.showStudentDetailsAnchorPane.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		stageManager = applicationContext.getBean(StageManager.class);
		stageManager.addEditController(this);
		dssValidation = new DSSValidation();

		assert showStudentDetailsAnchorPane != null
				: "fx:id=\"showStudentDetailsAnchorPane\" was not injected: check your FXML file 'edit-student.fxml'.";

		assert editStudentPane != null
				: "fx:id=\"editStudentPane\" was not injected: check your FXML file 'edit-student.fxml'.";
		assert firstNameFieldText != null
				: "fx:id=\"firstNameFieldText\" was not injected: check your FXML file 'edit-student.fxml'.";
		assert lastNameFieldText != null
				: "fx:id=\"lastNameFieldText\" was not injected: check your FXML file 'edit-student.fxml'.";
		assert passwordTextField != null
				: "fx:id=\"passwordTextField\" was not injected: check your FXML file 'edit-student.fxml'.";
		assert profilePictureImageView != null
				: "fx:id=\"profilePictureImageView\" was not injected: check your FXML file 'edit-student.fxml'.";
		assert emailFieldText != null
				: "fx:id=\"emailFieldText\" was not injected: check your FXML file 'edit-student.fxml'.";
		assert telefonTextField != null
				: "fx:id=\"telefonTextField\" was not injected: check your FXML file 'edit-student.fxml'.";
		assert statusTextField != null
				: "fx:id=\"statusTextField\" was not injected: check your FXML file 'edit-student.fxml'.";
		assert registerdOnTextField != null
				: "fx:id=\"registerdOnTextField\" was not injected: check your FXML file 'edit-student.fxml'.";
		assert lastLoginTextField != null
				: "fx:id=\"lastLoginTextField\" was not injected: check your FXML file 'edit-student.fxml'.";
		assert birthdayDatePicker != null
				: "fx:id=\"birthdayDatePicker\" was not injected: check your FXML file 'edit-student.fxml'.";
		assert maleToggleButton != null
				: "fx:id=\"maleToggleButton\" was not injected: check your FXML file 'edit-student.fxml'.";
		assert femaleToggleButton != null
				: "fx:id=\"femaleToggleButton\" was not injected: check your FXML file 'edit-student.fxml'.";
		assert uploadProfilePictureButton != null
				: "fx:id=\"uploadProfilePictureButton\" was not injected: check your FXML file 'edit-student.fxml'.";
		assert editAddressPane != null
				: "fx:id=\"editAddressPane\" was not injected: check your FXML file 'edit-student.fxml'.";
		assert cityTextField != null
				: "fx:id=\"cityTextField\" was not injected: check your FXML file 'edit-student.fxml'.";
		assert zipcodeTextField != null
				: "fx:id=\"zipcodeTextField\" was not injected: check your FXML file 'edit-student.fxml'.";
		assert streetTextField != null
				: "fx:id=\"streetTextField\" was not injected: check your FXML file 'edit-student.fxml'.";
		assert hauseNrTextField != null
				: "fx:id=\"hauseNrTextField\" was not injected: check your FXML file 'edit-student.fxml'.";
		assert editContactPersonPane != null
				: "fx:id=\"editContactPersonPane\" was not injected: check your FXML file 'edit-student.fxml'.";
		assert contactPersonfirstNameTextField != null
				: "fx:id=\"contactPersonfirstNameTextField\" was not injected: check your FXML file 'edit-student.fxml'.";
		assert contactPersonLastNameTextField != null
				: "fx:id=\"contactPersonLastNameTextField\" was not injected: check your FXML file 'edit-student.fxml'.";
		assert contactPersonTelefonTextField != null
				: "fx:id=\"contactPersonTelefonTextField\" was not injected: check your FXML file 'edit-student.fxml'.";
		assert contactPersonEmailTextField != null
				: "fx:id=\"contactPersonEmailTextField\" was not injected: check your FXML file 'edit-student.fxml'.";
		assert cancelButton != null
				: "fx:id=\"cancelButton\" was not injected: check your FXML file 'edit-student.fxml'.";
		assert saveButton != null : "fx:id=\"saveButton\" was not injected: check your FXML file 'edit-student.fxml'.";
		assert backToMainContent != null
				: "fx:id=\"backToMainContent\" was not injected: check your FXML file 'show-student-details.fxml'.";
		assert contactPersonMaleToggleButton != null
				: "fx:id=\"contactPersonMaleToggleButton\" was not injected: check your FXML file 'show-student-details.fxml'.";
		assert contactPersonFemaleToggleButton != null
				: "fx:id=\"contactPersonFemaleToggleButton\" was not injected: check your FXML file 'show-student-details.fxml'.";

		firstNameFieldText.getValidators()
				.add(dssValidation.getRequiredFieldValidator(firstNameFieldText, firstNameError));
		lastNameFieldText.getValidators()
				.add(dssValidation.getRequiredFieldValidator(lastNameFieldText, lastNameError));
		emailFieldText.getValidators().add(dssValidation.getRequiredFieldValidator(emailFieldText, emailError));

		initStudentDetails();

		genderToggleGroup = new ToggleGroup();

		femaleToggleButton.setToggleGroup(genderToggleGroup);
		maleToggleButton.setToggleGroup(genderToggleGroup);

		contactPersonGenderToggleGroup = new ToggleGroup();

		contactPersonFemaleToggleButton.setToggleGroup(contactPersonGenderToggleGroup);
		contactPersonMaleToggleButton.setToggleGroup(contactPersonGenderToggleGroup);

		uploadProfilePictureButton.setOnAction(event -> {
			uploadImage(event);
		});

		birthdayDatePicker.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				student.setBirthday(birthdayDatePicker.getValue());

			}
		});

	}

}
