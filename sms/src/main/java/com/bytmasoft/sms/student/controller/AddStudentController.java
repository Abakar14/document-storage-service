/**
 * @author Mahamat
 * Date 19.10.2020 : 14:04:07
 */
package com.bytmasoft.sms.student.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import com.bytmasoft.sms.controller.WebCamController;
import com.bytmasoft.sms.utils.DSSValidation;
import com.bytmasoft.sms.utils.UploadFile;
import com.bytmasoft.sms.view.FxmlView;
import com.bytmasoft.sms.view.StageManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.validation.RequiredFieldValidator;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;

/**
 * @author Mahamat Date 19.10.2020 : 14:04:07
 */
@Log4j2
@Component
public class AddStudentController implements Initializable {

	@Autowired
	private ConfigurableApplicationContext applicationContext;

	@Value("${student.service.address}")
	private String surl;

	@Value("${profile.picture.w}")
	private int profile_picture_w;

	@Value("${profile.picture.h}")
	private int profile_picture_h;

	@Value("${add.student.title}")
	private String title;

	@Autowired
	private StudentClient studentClient;

	@Autowired
	StudentController studentController;

	@Autowired
	WebCamController webCamController;

	@Autowired
	ShowAllStudentsController showAllStudentsController;

	DSSValidation dssValidation;

	private Student student;

	private ToggleGroup genderToggleGroup;
	private ToggleGroup contactGenderToggleGroup;

	private String lastNameError = "Last Name is Required ...";

	private String firstNameError = "First Name is Requird";

	private String addAlertTitle = "Add Student";
	private String addAlertContent = "Wollen Sie den Student speichern....!";

	RequiredFieldValidator requiredFieldValidator;
	private StageManager stageManager;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private AnchorPane addStudentAnchorPane;

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

	@FXML
	private JFXTextField registerdOnTextField;

	@FXML
	private JFXTextField lastLoginTextField;

	@FXML
	private JFXDatePicker birthdayDatePicker;

	@FXML
	private JFXToggleButton maleToggleButton;

	@FXML
	private JFXToggleButton femaleToggleButton;

	@FXML
	private JFXButton uploadProfilePictureButton;

	@FXML
	private JFXButton capturePictureButton;

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
	private JFXButton cancelButton;

	@FXML
	private JFXButton saveButton;

	@FXML
	private JFXButton backToMainContent;

	private String cityError = "Please insert your city";

	private String streetError = "please insert your street";

	private String zipcodeError = "please insert your zipcode";

	private String hausNrError = "please insert your haus number";

	private String telelfonError = "please insert your phone number ";

	private String emailError = "please insert your email";

	@FXML
	void captureImage(ActionEvent event) {
		stageManager.switchAndWait(FxmlView.WEBCAM);
	}

	/**
	 * @return the profilePictureImageView
	 */
	public ImageView getProfilePictureImageView() {
		return profilePictureImageView;
	}

	@FXML
	void Save(ActionEvent event) {

		// TODO Abakar Mahamat 25.11.2020 14:26:59
		// muss probieren JFXDepthManager.setDepth(contaier_id, 1);

		if (this.student == null) {
			this.student = new Student();
		} else {

			if (!this.firstNameFieldText.getValidators().get(0).getHasErrors()
					&& !lastNameFieldText.getValidators().get(0).getHasErrors()) {

				this.student.setFirstName(this.firstNameFieldText.getText());
				this.student.setLastName(this.lastNameFieldText.getText());
				this.student.setEmail(this.emailFieldText.getText());
				this.student.setBirthday(birthdayDatePicker.getValue());
				this.student.setPhoneNr(telefonTextField.getText());

				student.setPassword(passwordTextField.getText());

				if (genderToggleGroup.getSelectedToggle().equals(femaleToggleButton)) {

					student.setGender(GenderType.Mrs);
				} else {
					student.setGender(GenderType.Mr);
				}

				/**
				 * Add Address to Student
				 */

				if (!this.cityTextField.getValidators().get(0).getHasErrors()
						&& !streetTextField.getValidators().get(0).getHasErrors()
						&& !zipcodeTextField.getValidators().get(0).getHasErrors()
						&& !hauseNrTextField.getValidators().get(0).getHasErrors()) {
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

				if (!this.contactPersonfirstNameTextField.getValidators().get(0).getHasErrors()
						&& !contactPersonLastNameTextField.getValidators().get(0).getHasErrors()
						&& !contactPersonTelefonTextField.getValidators().get(0).getHasErrors()
						&& !contactPersonEmailTextField.getValidators().get(0).getHasErrors()) {
					ContactPerson person = new ContactPerson();
					person.setFirstName(contactPersonfirstNameTextField.getText());
					person.setLastName(contactPersonLastNameTextField.getText());
					person.setPhoneNr(contactPersonTelefonTextField.getText());
					person.setEmail(contactPersonEmailTextField.getText());

					if (contactGenderToggleGroup.getSelectedToggle().equals(contactPersonFemaleToggleButton)) {

						person.setGender(GenderType.Mrs);
					} else {
						person.setGender(GenderType.Mr);
					}

					student.getContactPersons().add(person);

				}

				studentClient.addNewStudent(surl, student).subscribe(s -> {
					showAllStudentsController.refreshStudentsTableView();
					clearStudentFields();

				});

			}
		}
	}

	@FXML
	void cancel(ActionEvent event) {
		Stage stage = (Stage) this.addStudentAnchorPane.getScene().getWindow();
		stage.close();
	}

	@FXML
	void goBackToMainContent(ActionEvent event) {

		System.out.println("go back to maincontent");
		stageManager.addFileToMainContent(FxmlView.SHOWALLSTUDENTS.getFxmlFile());
	}

	@FXML
	void uploadImage(ActionEvent event) {

		File imageFile = UploadFile.uploadImage(event);
		FileInputStream is = null;
		if (imageFile != null) {
			try {
				BufferedImage bufferedImage = ImageIO.read(imageFile);

				Image image = SwingFXUtils.toFXImage(bufferedImage, null);
				profilePictureImageView.setFitWidth(100);
				profilePictureImageView.setFitHeight(150);
				profilePictureImageView.setPreserveRatio(true);
				profilePictureImageView.setImage(image);

				is = new FileInputStream(imageFile);
				this.student.setProfile_picture(is.readAllBytes());
				is.close();

			} catch (IOException e) {
				log.error("Erorr occurred during file upload... " + e.getMessage());
			} finally {
				is = null;
			}

		}
	}

	public void initCaptureProfilePicture() {

		FileInputStream is = null;
		File file = null;

		if (profilePictureImageView != null) {

			try {

				if (null == student.getFirstName()) {
					file = new File("temp.png");

				} else {

					file = new File(student.getFirstName().concat(".png"));
				}

				ImageIO.write(SwingFXUtils.fromFXImage(profilePictureImageView.getImage(), null), "PNG", file);

				is = new FileInputStream(file);

				this.student.setProfile_picture(is.readAllBytes());
				is.close();

			} catch (IOException e) {
				log.error(e.getMessage());
			} finally {
				is = null;
				file = null;
			}
		}

	}

	private void clearStudentFields() {

		this.firstNameFieldText.clear();
		this.lastNameFieldText.clear();
		this.emailFieldText.clear();
		this.birthdayDatePicker.setValue(null);
		this.telefonTextField.clear();
		this.cityTextField.clear();
		this.streetTextField.clear();
		this.hauseNrTextField.clear();
		this.contactPersonfirstNameTextField.clear();
		this.contactPersonLastNameTextField.clear();
		this.contactPersonEmailTextField.clear();
		this.contactPersonTelefonTextField.clear();
		this.genderToggleGroup.selectToggle(null);

	}

	public void close() {
		Stage stage = (Stage) this.addStudentAnchorPane.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		stageManager = applicationContext.getBean(StageManager.class);
		stageManager.addAddStudentController(this);
		dssValidation = new DSSValidation();
		studentController.setTitlePage(title);

		assert addStudentAnchorPane != null
				: "fx:id=\"addStudentAnchorPane\" was not injected: check your FXML file 'add-student.fxml'.";
		assert editStudentPane != null
				: "fx:id=\"editStudentPane\" was not injected: check your FXML file 'add-student.fxml'.";
		assert firstNameFieldText != null
				: "fx:id=\"firstNameFieldText\" was not injected: check your FXML file 'add-student.fxml'.";
		assert lastNameFieldText != null
				: "fx:id=\"lastNameFieldText\" was not injected: check your FXML file 'add-student.fxml'.";
		assert passwordTextField != null
				: "fx:id=\"passwordTextField\" was not injected: check your FXML file 'add-student.fxml'.";
		assert profilePictureImageView != null
				: "fx:id=\"profilePictureImageView\" was not injected: check your FXML file 'add-student.fxml'.";
		assert emailFieldText != null
				: "fx:id=\"emailFieldText\" was not injected: check your FXML file 'add-student.fxml'.";
		assert telefonTextField != null
				: "fx:id=\"telefonTextField\" was not injected: check your FXML file 'add-student.fxml'.";
		assert statusTextField != null
				: "fx:id=\"statusTextField\" was not injected: check your FXML file 'add-student.fxml'.";
		assert registerdOnTextField != null
				: "fx:id=\"registerdOnTextField\" was not injected: check your FXML file 'add-student.fxml'.";
		assert lastLoginTextField != null
				: "fx:id=\"lastLoginTextField\" was not injected: check your FXML file 'add-student.fxml'.";
		assert birthdayDatePicker != null
				: "fx:id=\"birthdayDatePicker\" was not injected: check your FXML file 'add-student.fxml'.";
		assert maleToggleButton != null
				: "fx:id=\"maleToggleButton\" was not injected: check your FXML file 'add-student.fxml'.";
		assert femaleToggleButton != null
				: "fx:id=\"femaleToggleButton\" was not injected: check your FXML file 'add-student.fxml'.";
		assert uploadProfilePictureButton != null
				: "fx:id=\"uploadProfilePictureButton\" was not injected: check your FXML file 'add-student.fxml'.";
		assert editAddressPane != null
				: "fx:id=\"editAddressPane\" was not injected: check your FXML file 'add-student.fxml'.";
		assert cityTextField != null
				: "fx:id=\"cityTextField\" was not injected: check your FXML file 'add-student.fxml'.";
		assert zipcodeTextField != null
				: "fx:id=\"zipcodeTextField\" was not injected: check your FXML file 'add-student.fxml'.";
		assert streetTextField != null
				: "fx:id=\"streetTextField\" was not injected: check your FXML file 'add-student.fxml'.";
		assert hauseNrTextField != null
				: "fx:id=\"hauseNrTextField\" was not injected: check your FXML file 'add-student.fxml'.";
		assert editContactPersonPane != null
				: "fx:id=\"editContactPersonPane\" was not injected: check your FXML file 'add-student.fxml'.";
		assert contactPersonfirstNameTextField != null
				: "fx:id=\"contactPersonfirstNameTextField\" was not injected: check your FXML file 'add-student.fxml'.";
		assert contactPersonLastNameTextField != null
				: "fx:id=\"contactPersonLastNameTextField\" was not injected: check your FXML file 'add-student.fxml'.";
		assert contactPersonTelefonTextField != null
				: "fx:id=\"contactPersonTelefonTextField\" was not injected: check your FXML file 'add-student.fxml'.";
		assert contactPersonEmailTextField != null
				: "fx:id=\"contactPersonEmailTextField\" was not injected: check your FXML file 'add-student.fxml'.";
		assert contactPersonMaleToggleButton != null
				: "fx:id=\"contactPersonMaleToggleButton\" was not injected: check your FXML file 'add-student.fxml'.";
		assert contactPersonFemaleToggleButton != null
				: "fx:id=\"contactPersonFemaleToggleButton\" was not injected: check your FXML file 'add-student.fxml'.";
		assert cancelButton != null
				: "fx:id=\"cancelButton\" was not injected: check your FXML file 'add-student.fxml'.";
		assert saveButton != null : "fx:id=\"saveButton\" was not injected: check your FXML file 'add-student.fxml'.";
		assert backToMainContent != null
				: "fx:id=\"backToMainContent\" was not injected: check your FXML file 'add-student.fxml'.";

		requiredFieldValidator = dssValidation.getRequiredFieldValidator(firstNameFieldText, firstNameError);

		firstNameFieldText.getValidators().add(requiredFieldValidator);

		lastNameFieldText.getValidators()
				.add(dssValidation.getRequiredFieldValidator(lastNameFieldText, lastNameError));
		cityTextField.getValidators().add(dssValidation.getRequiredFieldValidator(cityTextField, cityError));
		streetTextField.getValidators().add(dssValidation.getRequiredFieldValidator(streetTextField, streetError));

		zipcodeTextField.getValidators().add(dssValidation.getRequiredFieldValidator(zipcodeTextField, zipcodeError));

		hauseNrTextField.getValidators().add(dssValidation.getRequiredFieldValidator(hauseNrTextField, hausNrError));
		contactPersonfirstNameTextField.getValidators()
				.add(dssValidation.getRequiredFieldValidator(contactPersonfirstNameTextField, firstNameError));

		contactPersonLastNameTextField.getValidators()
				.add(dssValidation.getRequiredFieldValidator(contactPersonLastNameTextField, lastNameError));
		contactPersonTelefonTextField.getValidators()
				.add(dssValidation.getRequiredFieldValidator(contactPersonTelefonTextField, telelfonError));
		contactPersonEmailTextField.getValidators()
				.add(dssValidation.getRequiredFieldValidator(contactPersonEmailTextField, emailError));

		genderToggleGroup = new ToggleGroup();

		femaleToggleButton.setToggleGroup(genderToggleGroup);
		maleToggleButton.setToggleGroup(genderToggleGroup);

		contactGenderToggleGroup = new ToggleGroup();

		contactPersonFemaleToggleButton.setToggleGroup(contactGenderToggleGroup);
		contactPersonMaleToggleButton.setToggleGroup(contactGenderToggleGroup);

		if (this.student == null) {
			this.student = new Student();

		}
	}

}
