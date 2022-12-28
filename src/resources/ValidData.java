package resources;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.DataProvider;

import dataExcel.excelText;

public class ValidData extends excelText {

	@DataProvider
	public static Object[][] getData() throws IOException {

//		ArrayList<String> dataForUsername = dataInTheTable("Login2");
//		ArrayList<String> dataForUsername2 = dataInTheTable("Login3");

		Object[][] data = new Object[1][3];
		// oth row
		data[0][0] = "nonrestrictuser@qw.com";
		data[0][1] = "12345";
		data[0][2] = "Restricted user";
//		// 1st row
//		data[1][0] = "restrictduster@qw.com";
//		data[1][1] = "45678";
//		data[1][2] = "Non restrict";
		return data;
	}
}
