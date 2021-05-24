/**
 * 
 */
package com.bytmasoft.sms;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bytmasoft.clientDomain.enums.GenderType;
import com.bytmasoft.clientDomain.models.BaseUser;
import com.bytmasoft.clientDomain.models.Student;
import com.bytmasoft.sms.utils.DSSValidation;

/**
 * @author Mahamat Abakar created on 18.11.2020 at 17:46:20
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String password = "Aba14a";
//		Pattern pattern = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\s+$).{6,15}");
		Pattern pattern = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,15}");
		Matcher matcher = pattern.matcher(password);

		if (matcher.matches()) {

			System.out.println("Passt");
		} else {
			System.out.println("Passt nicht");

		}

		DSSValidation validation = new DSSValidation();

		List<BaseUser> list1 = new ArrayList<BaseUser>();
		List<BaseUser> list2 = new ArrayList<BaseUser>();

		Student s1 = new Student();
		s1.setId(1L);
		s1.setFirstName("Mahamat");
		s1.setLastName("Abakar");
		s1.setEmail("Aba@web.de");
		s1.setGender(GenderType.Mr);

		Student s2 = new Student();
		s2.setId(1L);
		s2.setFirstName("Roushed");
		s2.setLastName("Abakar");
		s2.setEmail("roushed@web.de");
		s2.setGender(GenderType.Mr);

		list1.add(s1);
		list1.add(s2);

//		list2.add(s1);
//		list2.add(s2);

		System.out.println("Cal compare Methode " + validation.isListsEqual(list1, list2));

		Student s3 = new Student();
		s3.setId(1L);
		s3.setFirstName("Tasnim");
		s3.setLastName("Abakar");
		s3.setEmail("tasn@web.de");
		s3.setGender(GenderType.Mrs);

		Student s4 = new Student();
		s4.setId(1L);
		s4.setFirstName("Amal");
		s4.setLastName("Frikh");
		s4.setEmail("amal@web.de");
		s4.setGender(GenderType.Mrs);

		list2.add(s3);
		list2.add(s4);
		System.out.println("Cal compare Methode " + validation.isListsEqual(list1, list2));

	}

}
