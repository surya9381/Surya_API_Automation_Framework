package api.utilities;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;

public class DataProviders {

	Excel_Utility elib;
	
	@DataProvider(name="Data")
	public String[][] getAllData() throws EncryptedDocumentException, IOException
	{
		String path=System.getProperty("user.dir")+"./userdata.xlsx";
		elib=new Excel_Utility(path);
		
		int rowscount=elib.getRowCount("Sheet1");
		int colcount=elib.getCellCount("Sheet1",1);
		
		String[][] apidata=new String[rowscount][colcount];
		
		for(int i=1;i<=rowscount;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				apidata[i-1][j]=elib.getCellData("Sheet1", i, j);
			}
		}
		return apidata;
	}
	
	@DataProvider(name="UserNames")
	public String[] getUserNames() throws EncryptedDocumentException, IOException
	{
		String path=System.getProperty("user.dir")+"./userdata.xlsx";
	    elib=new Excel_Utility(path);
	    int rowscount=elib.getRowCount("Sheet1");
	    
	    String[] apidata=new String[rowscount];
	    for(int i=1;i<=rowscount;i++)
	    {
	    	apidata[i-1]= elib.getCellData("Sheet1",i,1);
	    }
	    return apidata;
	}
	
}
