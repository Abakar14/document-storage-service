/**
 * 
 */
package com.bytmasoft.sms.controller;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bytmasoft.sms.module.WebCamInfo;
import com.bytmasoft.sms.student.controller.AddStudentController;
import com.bytmasoft.sms.student.controller.ShowStudentDetailsController;
import com.github.sarxos.webcam.Webcam;
import com.jfoenix.controls.JFXButton;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import lombok.extern.log4j.Log4j2;

/**
 * @author Mahamat Abakar created on 02.12.2020 at 23:54:06
 */
@Log4j2
@Component
public class WebCamController implements Initializable {

	private BufferedImage grabbedImage;
	private Webcam selWebCam = null;
	private boolean stopCamera = false;
	private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<Image>();

	private String cameraListPromptText = "Choose Camera";

	@Autowired
	private AddStudentController addStudentController;

	@Autowired
	ShowStudentDetailsController ShowStudentDetailsController;

//	@FXML
//	private FlowPane fpBottomPane;
	@FXML
	private VBox vBoxBottom;

	@FXML
	private JFXButton btnStartCamera;

	@FXML
	private JFXButton btnStopCamera;

	@FXML
	private JFXButton btnDisposeCamera;

	@FXML
	private BorderPane bpWebCamPaneHolder;

	@FXML
	private ImageView imgWebCamCapturedImage;

	@FXML
	private ComboBox<WebCamInfo> cbCameraOptions;
	private ImageView profilePictureImageViewAddStudent;
	private ImageView profilePictureImageViewDetailStudent;

	protected void setImageViewSize() {

		double height = bpWebCamPaneHolder.getHeight();
		double width = bpWebCamPaneHolder.getWidth();
		imgWebCamCapturedImage.setFitHeight(height);
		imgWebCamCapturedImage.setFitWidth(width);
		imgWebCamCapturedImage.prefHeight(height);
		imgWebCamCapturedImage.prefWidth(width);
		imgWebCamCapturedImage.setPreserveRatio(true);

	}

	protected void initializeWebCam(final int webCamIndex) {

		Task<Void> webCamIntilizer = new Task<Void>() {

			@Override
			protected Void call() throws Exception {

				if (selWebCam == null) {
					selWebCam = Webcam.getWebcams().get(webCamIndex);
					selWebCam.open();
				} else {
					closeCamera();
					selWebCam = Webcam.getWebcams().get(webCamIndex);
					selWebCam.open();
				}
				startWebCamStream();
				return null;
			}

		};

		new Thread(webCamIntilizer).start();
		vBoxBottom.setDisable(false);
//		fpBottomPane.setDisable(false);
		btnStartCamera.setDisable(true);
	}

	protected void startWebCamStream() {

		stopCamera = false;
		Task<Void> task = new Task<Void>() {

			@Override
			protected Void call() throws Exception {

				while (!stopCamera) {
					try {
						if ((grabbedImage = selWebCam.getImage()) != null) {

							Platform.runLater(new Runnable() {

								@Override
								public void run() {
									final Image mainiamge = SwingFXUtils.toFXImage(grabbedImage, null);
									imageProperty.set(mainiamge);
								}
							});

							grabbedImage.flush();

						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				return null;
			}

		};
		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();

		imgWebCamCapturedImage.imageProperty().bind(imageProperty);
		if (profilePictureImageViewAddStudent != null) {

			profilePictureImageViewAddStudent.imageProperty().bind(imageProperty);
		}
		if (profilePictureImageViewDetailStudent != null) {
			profilePictureImageViewDetailStudent.imageProperty().bind(imageProperty);
		}

	}

	private void closeCamera() {
		if (selWebCam != null) {
			selWebCam.close();
		}
	}

	@FXML
	public void stopCamera(ActionEvent event) {
		stopCamera = true;
		btnStartCamera.setDisable(false);
		btnStopCamera.setDisable(true);
	}

	@FXML
	public void startCamera(ActionEvent event) {
		stopCamera = false;
		startWebCamStream();
		btnStartCamera.setDisable(true);
		btnStopCamera.setDisable(false);
	}

	@FXML
	public void disposeCamera(ActionEvent event) {
		stopCamera = true;
		closeCamera();
		btnStopCamera.setDisable(true);
		btnStartCamera.setDisable(true);
		addStudentController.initCaptureProfilePicture();
		ShowStudentDetailsController.initCaptureProfilePicture();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

//		if (fpBottomPane == null) {
//			System.out.println("fpBottomPane == null");
//			fpBottomPane = new FlowPane();
//
//		}

		this.profilePictureImageViewAddStudent = addStudentController.getProfilePictureImageView();
		this.profilePictureImageViewDetailStudent = ShowStudentDetailsController.getProfilePictureImageView();

		assert vBoxBottom != null
				: "fx:id=\"vBoxBottom\" was not injected: check your FXML file 'web-cam-dialog.fxml'.";

//		assert fpBottomPane != null
//				: "fx:id=\"fpBottomPane\" was not injected: check your FXML file 'web-cam-dialog.fxml'.";
		assert btnStartCamera != null
				: "fx:id=\"btnStartCamera\" was not injected: check your FXML file 'web-cam-dialog.fxml'.";
		assert btnStopCamera != null
				: "fx:id=\"btnStopCamera\" was not injected: check your FXML file 'web-cam-dialog.fxml'.";
		assert btnDisposeCamera != null
				: "fx:id=\"btnDisposeCamera\" was not injected: check your FXML file 'web-cam-dialog.fxml'.";
		assert bpWebCamPaneHolder != null
				: "fx:id=\"bpWebCamPaneHolder\" was not injected: check your FXML file 'web-cam-dialog.fxml'.";
		assert imgWebCamCapturedImage != null
				: "fx:id=\"imgWebCamCapturedImage\" was not injected: check your FXML file 'web-cam-dialog.fxml'.";

		assert cbCameraOptions != null
				: "fx:id=\"cbCameraOptions\" was not injected: check your FXML file 'web-cam-dialog.fxml'.";

//		fpBottomPane.setDisable(true);
		vBoxBottom.setDisable(true);
		ObservableList<WebCamInfo> options = FXCollections.observableArrayList();
		int webCamCounter = 0;
		for (Webcam webcam : Webcam.getWebcams()) {
			WebCamInfo webCamInfo = new WebCamInfo();
			webCamInfo.setWebCamIndex(webCamCounter);
			webCamInfo.setWebCamName(webcam.getName());
			options.add(webCamInfo);
			webCamCounter++;
		}
		cbCameraOptions.setItems(options);
		cbCameraOptions.setPromptText(cameraListPromptText);
		cbCameraOptions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<WebCamInfo>() {

			@Override
			public void changed(ObservableValue<? extends WebCamInfo> arg0, WebCamInfo arg1, WebCamInfo arg2) {
				if (arg2 != null) {
					System.out.println(
							"WebCam Index: " + arg2.getWebCamIndex() + ": WebCam Name:" + arg2.getWebCamName());
					initializeWebCam(arg2.getWebCamIndex());
				}
			}
		});
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				setImageViewSize();
			}
		});

	}

}
