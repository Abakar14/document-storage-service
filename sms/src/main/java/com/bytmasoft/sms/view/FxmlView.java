package com.bytmasoft.sms.view;

import java.util.ResourceBundle;

public enum FxmlView {
	
	LOGIN {

		@Override
		public String getTitle() {
			return "Login";
//			return getStringFromResourceBundle("login.title");
		}

		@Override
		public
		String getFxmlFile() {
			return "/com/bytmasoft/school/sms/fxml/login.fxml";
		}
		
	}, HOME {

		@Override
		public String getTitle() {
			return "Home";
//			return getStringFromResourceBundle("home.app.title");
		}

		@Override
		public
		String getFxmlFile() {
			return "/com/bytmasoft/school/sms/fxml/home.fxml";
		}		
		
	}, 	DASHBOARD {

		@Override
		public String getTitle() {
			return "Dashboard";
//			return getStringFromResourceBundle("dashboard.title");
		}

		@Override
		public
		String getFxmlFile() {
			return "/com/bytmasoft/school/sms/fxml/dashboard.fxml";
		}
		
	}, EXAM {

		@Override
		public String getTitle() {
	
			return "Exam";
//			return getStringFromResourceBundle("Exam");
		}

		@Override
		public
		String getFxmlFile() {
			return "/com/bytmasoft/school/sms/fxml/exam.fxml";
		}
		
	}, STUDENT {

		@Override
		public String getTitle() {
			return "Students";
//			return getStringFromResourceBundle("Students");
		}

		@Override
		public
		String getFxmlFile() {
			return "/com/bytmasoft/school/sms/fxml/student.fxml";
		}
		
	}, ADDSTUDENT {

		@Override
		public String getTitle() {
			
			return "Add Student";
//			return getStringFromResourceBundle("Add Student");
		}

		@Override
		public
		String getFxmlFile() {
			return "/com/bytmasoft/school/sms/fxml/add-student.fxml";
		}
		
	}, SHOWALLSTUDENTS {

		@Override
		public String getTitle() {
			
			return "View Students";
//			return getStringFromResourceBundle("Add Student");
		}

		@Override
		public
		String getFxmlFile() {
			return "/com/bytmasoft/school/sms/fxml/show-all-students.fxml";
		}
		
	},SHOWSTUDENTDETAILS {

		@Override
		public String getTitle() {
				return "Show Student Details";
			//			return getStringFromResourceBundle("Edit Student");
		}

		@Override
		public
		String getFxmlFile() {
			return "/com/bytmasoft/school/sms/fxml/show-student-details.fxml";
			
		}
		
	}
	, TEACHER {

		@Override
		public String getTitle() {
			return "Teacher";
//			return getStringFromResourceBundle("teacher.title");
		}

		@Override
		public
		String getFxmlFile() {
			return "/com/bytmasoft/school/sms/fxml/teacher.fxml";
		}
		
	}, CLASSES {

		@Override
		public String getTitle() {
			return "Classes";
//			return getStringFromResourceBundle("student.title");
		}

		@Override
		public
		String getFxmlFile() {
			return "/com/bytmasoft/school/sms/fxml/class.fxml";
		}
		
	},TIMETABLE {

		@Override
		public String getTitle() {
			return "Timetable";
//			return getStringFromResourceBundle("timetable.title");
		}

		@Override
		public
		String getFxmlFile() {
			return "/com/bytmasoft/school/sms/fxml/timetable.fxml";
		}
		
	},UPDATE {

		@Override
		public String getTitle() {
			return "Update";
//			return getStringFromResourceBundle("update.title");
		}

		@Override
		public
		String getFxmlFile() {
			return "/com/bytmasoft/school/sms/fxml/update.fxml";
		}
		
	},SETTING {

		@Override
		public String getTitle() {
			return "Setting";
//			return getStringFromResourceBundle("student.title");
		}

		@Override
		public String getFxmlFile() {
			return "/com/bytmasoft/school/sms/fxml/setting.fxml";
		}
		
	};
	
	public abstract String getTitle();
	public abstract String getFxmlFile();
	
	String getStringFromResourceBundle(String key) {
		return ResourceBundle.getBundle("Bundle").getString(key);
	}
	
	

}
