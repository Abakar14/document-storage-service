package com.bytmasoft.sms.view;

import java.util.ResourceBundle;

public enum FxmlView {
	
	LOGIN {

		@Override
		String getTitle() {
			return "title";
//			return getStringFromResourceBundle("login.title");
		}

		@Override
		String getFxmlFile() {
			return "/com/bytmasoft/school/sms/fxml/login.fxml";
		}
		
	}, HOME {

		@Override
		String getTitle() {
			return "title";
//			return getStringFromResourceBundle("home.app.title");
		}

		@Override
		String getFxmlFile() {
			return "/com/bytmasoft/school/sms/fxml/home.fxml";
		}		
		
	}, 	DASHBOARD {

		@Override
		String getTitle() {
			return "title";
//			return getStringFromResourceBundle("dashboard.title");
		}

		@Override
		String getFxmlFile() {
			return "/com/bytmasoft/school/sms/fxml/dashboard.fxml";
		}
		
	}, EXAM {

		@Override
		String getTitle() {
			return "title";
//			return getStringFromResourceBundle("dashboard.title");
		}

		@Override
		String getFxmlFile() {
			return "/com/bytmasoft/school/sms/fxml/exam.fxml";
		}
		
	}, STUDENT {

		@Override
		String getTitle() {
			return "title";
//			return getStringFromResourceBundle("student.title");
		}

		@Override
		String getFxmlFile() {
			return "/com/bytmasoft/school/sms/fxml/student.fxml";
		}
		
	}, TEACHER {

		@Override
		String getTitle() {
			return "title";
//			return getStringFromResourceBundle("teacher.title");
		}

		@Override
		String getFxmlFile() {
			return "/com/bytmasoft/school/sms/fxml/teacher.fxml";
		}
		
	}, CLASSES {

		@Override
		String getTitle() {
			return "title";
//			return getStringFromResourceBundle("student.title");
		}

		@Override
		String getFxmlFile() {
			return "/com/bytmasoft/school/sms/fxml/class.fxml";
		}
		
	},TIMETABLE {

		@Override
		String getTitle() {
			return "title";
//			return getStringFromResourceBundle("timetable.title");
		}

		@Override
		String getFxmlFile() {
			return "/com/bytmasoft/school/sms/fxml/timetable.fxml";
		}
		
	},UPDATE {

		@Override
		String getTitle() {
			return "title";
//			return getStringFromResourceBundle("update.title");
		}

		@Override
		String getFxmlFile() {
			return "/com/bytmasoft/school/sms/fxml/update.fxml";
		}
		
	},SETTING {

		@Override
		String getTitle() {
			return "title";
//			return getStringFromResourceBundle("student.title");
		}

		@Override
		String getFxmlFile() {
			return "/com/bytmasoft/school/sms/fxml/setting.fxml";
		}
		
	};
	
	abstract String getTitle();
	abstract String getFxmlFile();
	
	String getStringFromResourceBundle(String key) {
		return ResourceBundle.getBundle("Bundle").getString(key);
	}
	
	

}
