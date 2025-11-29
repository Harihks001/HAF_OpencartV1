package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	// DataProvider for Registration Data
	@DataProvider(name = "DP_RegisterData")
	public Object[][] getregisterData() throws IOException {

		String path = ".\\testData\\UserAccounts.xlsx"; // taking Excel file from testData folder
		ExcelUtils xlutil = new ExcelUtils(path); // creating an object for XLUtility

		int totalrows = xlutil.getRowCount("TestUsers");
		int totalcols = xlutil.getCellCount("TestUsers", 1);

		Object registerData[][] = new Object[totalrows][totalcols]; // created for two dimension array

		// read the data from Excel storing in two dimension array
		for (int i = 1; i <= totalrows; i++) {
			for (int j = 0; j < totalcols; j++) {
				registerData[i - 1][j] = xlutil.getCellData("TestUsers", i, j);
			}
			registerData[i - 1][5] = i;
		}

		return registerData; // returning two dimension array }
	}
	

	// DataProvider for Login Data
	@DataProvider(name = "DP_LoginData")
	public Object[][] getloginData() throws IOException {
		String path = ".\\testData\\UserAccounts.xlsx"; // taking Excel file from testData folder
		ExcelUtils xlutil = new ExcelUtils(path); // creating an object for XLUtility

		int totalrows = xlutil.getRowCount("LoginData");

		Object loginData[][] = new Object[totalrows][5]; // created for two dimension array with columns including the
															// row index

		// read the data from Excel storing in two dimension array
		for (int i = 1; i <= totalrows; i++) {
			loginData[i - 1][0] = xlutil.getCellData("LoginData", i, 0); // Email
			loginData[i - 1][1] = xlutil.getCellData("LoginData", i, 1); // Password
			loginData[i - 1][2] = xlutil.getCellData("LoginData", i, 2); // Validity
			loginData[i - 1][3] = xlutil.getCellData("LoginData", i, 5);
			loginData[i - 1][4] = i;// Status (6th column, index 5)
		}

		return loginData; // returning two dimension array
	}

}
