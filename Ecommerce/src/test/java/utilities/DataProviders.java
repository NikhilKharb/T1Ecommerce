package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException {

		String path = "C:\\Users\\nikhil.kharb\\eclipse-workspace\\Ecommerce\\testData\\TestData.xlsx"; // taking xl
																										// file from
																										// testData

		ExcelUtility xlutil = new ExcelUtility(path);

		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcol = xlutil.getCellCount("Sheet1", 1);

		String logindata[][] = new String[totalrows][totalcol]; // created for two dimensional array which can store

		for (int i = 1; i <= totalrows; i++) {
			for (int j = 0; j < totalcol; j++) {
				logindata[i - 1][j] = xlutil.getCellData("Sheet1", i, j);

			}
		}
		return logindata;
	}
}
