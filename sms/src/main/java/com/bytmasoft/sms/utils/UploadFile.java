/**
 * 
 */
package com.bytmasoft.sms.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * @author Mahamat Abakar
 * created on 18.11.2020 at 13:52:12
 */
public class UploadFile {
	
	public static File uploadImage(ActionEvent event) {

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
		
		return imageFile;

	}

}
