package api.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel_Utility {

	FileInputStream fis;
	FileOutputStream fos;
	Workbook book;
	Sheet sh;
	Row row;
	
	String path;
	
	public Excel_Utility(String path)
	{
		this.path=path;
	}
	
	public int getRowCount(String sheetname) throws EncryptedDocumentException, IOException
	{
		fis=new FileInputStream(path);
		book=WorkbookFactory.create(fis);
		sh=book.getSheet(sheetname);
		int rowcount=sh.getLastRowNum();
		return rowcount;
	}
	
	public int getCellCount(String sheetname, int rownum) throws EncryptedDocumentException, IOException
	{
		fis=new FileInputStream(path);
		book=WorkbookFactory.create(fis);
		sh=book.getSheet(sheetname);
		row=sh.getRow(rownum);
		int cellcount=row.getLastCellNum();
		return cellcount;
	}
	
	public String getCellData(String sheetname, int rownum, int cellnum) throws EncryptedDocumentException, IOException
	{
		fis=new FileInputStream(path);
		book=WorkbookFactory.create(fis);
		sh=book.getSheet(sheetname);
		row=sh.getRow(rownum);
		
		DataFormatter format=new DataFormatter();
		String data=format.formatCellValue(row.getCell(cellnum));
		return data;
	}
	
	public String setCellData(String sheetname, int rownum, int cellnum, String data) throws EncryptedDocumentException, IOException
	{
		fis=new FileInputStream(path);
		book=WorkbookFactory.create(fis);
		sh=book.getSheet(sheetname);
		sh.createRow(rownum).createCell(cellnum).setCellValue(data);
		
		fos=new FileOutputStream(path);
		book.write(fos);
		fos.flush();
		return data;
		
	}
}
