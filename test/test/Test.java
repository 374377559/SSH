package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;

public class Test {

	@Before
	public void setUp() throws Exception {
		
	}

	@org.junit.Test
	public void test() throws Exception {
		//创建工作簿
		 HSSFWorkbook workbook =new HSSFWorkbook();
		 //创建工作表
		 HSSFSheet sheet = workbook.createSheet("Hello World");
		 //创建行(创建第三行)
		 HSSFRow row = sheet.createRow(3);
		 //创建单元格
		 HSSFCell cell = row.createCell(3);
		 cell.setCellValue("Hello World");
		 
		 //输出到硬盘
		 FileOutputStream out = new FileOutputStream("F:\\test\\测试.xls");
		 workbook.write(out);
		 workbook.close();
		 out.close();
	}
	@org.junit.Test
	public void test2() throws Exception {
		//读取单元格
		FileInputStream input=new FileInputStream("F:\\test\\测试.xls");
		//创建工作簿
		 HSSFWorkbook workbook =new HSSFWorkbook(input);
		 //创建工作表
		 HSSFSheet sheet = workbook.getSheetAt(0);
		 //创建行(创建第三行)
		 HSSFRow row = sheet.getRow(3);
		 //创建单元格 
		 HSSFCell cell = row.getCell(3);
		 System.out.println(cell.getStringCellValue());
		 //输出到硬盘
		 workbook.close();
		 input.close();
	}
	
	@org.junit.Test
	public void test3() throws Exception {
		//创建工作簿
		 XSSFWorkbook workbook =new XSSFWorkbook();
		 //创建工作表
		 XSSFSheet sheet = workbook.createSheet("Hello World");
		 //创建行(创建第三行)
		 XSSFRow row = sheet.createRow(3);
		 //创建单元格
		 XSSFCell cell = row.createCell(3);
		 cell.setCellValue("Hello World");
		 
		 //输出到硬盘
		 FileOutputStream out = new FileOutputStream("F:\\test\\测试.xlsx");
		 workbook.write(out);
		 workbook.close();
		 out.close();
	}
	
	@org.junit.Test
	public void test4() throws Exception {
		//读取单元格
		FileInputStream input=new FileInputStream("F:\\test\\测试.xlsx");
		//创建工作簿
		 XSSFWorkbook workbook =new XSSFWorkbook(input);
		 //创建工作表
		 XSSFSheet sheet = workbook.getSheetAt(0);
		 //创建行(创建第三行)
		 XSSFRow row = sheet.getRow(3);
		 //创建单元格 
		 XSSFCell cell = row.getCell(3);
		 System.out.println(cell.getStringCellValue());
		 //输出到硬盘
		 workbook.close();
		 input.close();
	}
	//同时读2种格式文件
	@org.junit.Test
	public void test5() throws Exception {
		String filename="F:\\test\\测试.xlsx";
		if(filename.matches("^.+\\.(?i)((xsl)|(xslx))$")){
			
			boolean is03Excel = filename.matches("^.+\\.(?i)(xls)$");
			//读取单元格
			FileInputStream input=new FileInputStream(filename);
			//创建工作簿
			 Workbook workbook =is03Excel ? new HSSFWorkbook(input) : new XSSFWorkbook(input);
			 //创建工作表
			 Sheet sheet = workbook.getSheetAt(0);
			 //创建行(创建第三行)
			 Row row = sheet.getRow(3);
			 //创建单元格 
			 Cell cell = row.getCell(3);
			 System.out.println(cell.getStringCellValue());
			 //输出到硬盘
			 workbook.close();
			 input.close();
		}
		
	}
	
	@org.junit.Test
	public void test6() throws Exception {
		//创建工作簿
		 HSSFWorkbook workbook =new HSSFWorkbook();
		//合并
		 CellRangeAddress cellRangeAddress = new CellRangeAddress(3,3,3,4);
		 //创建单元格样式
		  HSSFCellStyle  style = workbook.createCellStyle();
		  style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
		  style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		 //创建工作表
		 HSSFSheet sheet = workbook.createSheet("Hello World");
		 HSSFFont font = workbook.createFont();
		 font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗字体	
		 font.setFontHeightInPoints((short)16);
		 style.setFont(font);
		 //加载单元格对象
		 sheet.addMergedRegion(cellRangeAddress);
		 //创建行(创建第三行)
		 HSSFRow row = sheet.createRow(3);
		 //创建单元格
		 HSSFCell cell = row.createCell(3);
		 //加载样式
		 cell.setCellStyle(style);
		 cell.setCellValue("Hello World");
		 
		 //输出到硬盘
		 FileOutputStream out = new FileOutputStream("F:\\test\\测试.xls");
		 workbook.write(out);
		 workbook.close();
		 out.close();
	}

}
