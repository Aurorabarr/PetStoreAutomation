package api.utilities;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataProviders {
	//public Logger logger; //create logger class
	
	//@BeforeClass
	//public void initLogger() {
		//logs
		//logger = LogManager.getLogger(this.getClass()); //initial logger
	//}
	
	@DataProvider(name = "Data")
	public String [][] getAllData() throws IOException {
		System.out.print("DataProvider, getAllData() \n");
		String path = System.getProperty("user.dir") + "\\testData\\Userdata.xlsx";
		System.out.print("DataProvider: " + path+"\n");
		XLUtility xl = new XLUtility(path);
		
		int rowNum = xl.getRowCount("Sheet1");
		int colCount = xl.getCellCount("Sheet1", 1);
		
		System.out.print("DataProvider, row number: " + rowNum+"\n");
		System.out.print("DataProvider, coloum number: " + colCount+"\n");
		
		String apidata [][] = new String [rowNum][colCount];
		for (int i=1; i<= rowNum; i++ ) {
			for (int j=0; j<colCount; j++) {
				System.out.print("DataProvider, rowNum i, colCount j: " + i +","+j+"\n");
				System.out.print("DataProvider, cell data: " + xl.getCellData("Sheet1", i, j)+"\n");
				apidata[i-1][j] = xl.getCellData("Sheet1", i, j);
			}
		}
		return apidata;
	}

	@DataProvider(name = "UserName")
	public String [] getUserNames() throws IOException {
		String path = System.getProperty("user.dir") + "\\testData\\Userdata.xlsx";
		System.out.print("DataProvider-getUserNames(): " + path+"\n");
		XLUtility xl = new XLUtility(path);
		
		int rowNum = xl.getRowCount("Sheet1");
		System.out.print("DataProvider-getUserNames(), rowNum: " + rowNum+"\n");
		String apidata [] = new String [rowNum +1];
		for (int i=1; i<=rowNum; i++) {
				apidata[i] = xl.getCellData("Sheet1", i, 1);
				System.out.print("DataProvider-getUserNames(), apidata: " + apidata[i]+"\n");
		}
		return apidata;
	}
	
}
