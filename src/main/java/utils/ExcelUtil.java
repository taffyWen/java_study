package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	/**
	 * 1). 顺序：读取workbook——>读取工作表——>然后根据先行、后列的顺序，依次解析。 2).
	 * 通过getFirstRowNum()和getFirstCellNum()获取的初始行号和列号是物理值，默认从0开始，与表格中看到的相差1。 3).
	 * “.”是特殊字符，用它进行字符串分割时需要转义
	 * 
	 * @param excelPath 文件路径
	 * @return
	 */
	public static List<Map<String, Object>> getExcelFile(String excelPath) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		try {
			// String encoding = "GBK";
			File excel = new File(excelPath);
			if (excel.isFile() && excel.exists()) { // 判断文件是否存在
				// String[] split = excel.getName().split("\\."); // .是特殊字符，需要转义！！！！！
				String fileName = excel.getName();
				String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
				FileInputStream fis = new FileInputStream(excel); // 文件流对象
				Workbook wb;
				// 根据文件后缀（xls/xlsx）进行判断
				if ("xls".equalsIgnoreCase(suffix)) {
					wb = new HSSFWorkbook(fis);
				} else if ("xlsx".equalsIgnoreCase(suffix)) {
					wb = new XSSFWorkbook(fis);
				} else {
					System.out.println("文件类型错误!");
					return list;
				}
				// 开始解析
				Sheet sheet = wb.getSheetAt(0); // 读取sheet 0
				int firstRowIndex = sheet.getFirstRowNum(); // 第一行是列名
				int lastRowIndex = sheet.getLastRowNum();
				int rowSize = sheet.getRow(firstRowIndex).getLastCellNum(); // 获取第一行的列数
				String[] firstRowParam = new String[rowSize]; // 保存列名
				for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) { // 遍历行
					Map eachRowMap = new HashMap();
					Row row = sheet.getRow(rIndex);
					if (row != null) {
						int firstCellIndex = row.getFirstCellNum();
						int lastCellIndex = row.getLastCellNum();
						for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) { // 遍历列
							Cell cell = row.getCell(cIndex);
							String cellValue = getCellValue(cell);
							if (rIndex == firstRowIndex) { // 如果循环的是第一列，就取出列名
								firstRowParam[cIndex] = cellValue;
							} else {
								eachRowMap.put(firstRowParam[cIndex], cellValue);
							}
						}
						if (!MapUtil.isEmptyMap(eachRowMap)) {
							list.add(eachRowMap);
						}
					}
				}
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public static void main(String[] args) {
		List<Map<String, Object>> list = getExcelFile("D:\\document\\ioFile\\department.xlsx");
		for (Map<String, Object> map : list) {
			for (Map.Entry key : map.entrySet()) {
				System.out.println(key.getKey() + ":" + key.getValue() + "\n");
			}
		}
	}

	/**
	 * 将用户的信息导入到excel文件中去
	 * 
	 * @param userList 用户列表
	 * @param fileName      输出表
	 */
	public static void exportUserExcel(List<Map<String, String>> userList, String fileName) {
		try {
			// 1.创建工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 1.1创建合并单元格对象
			CellRangeAddress callRangeAddress = new CellRangeAddress(0, 0, 0, 4);// 起始行,结束行,起始列,结束列
			// 1.2头标题样式
			HSSFCellStyle headStyle = createCellStyle(workbook, (short) 16);
			// 1.3列标题样式
			HSSFCellStyle colStyle = createCellStyle(workbook, (short) 13);
			// 2.创建工作表
			HSSFSheet sheet = workbook.createSheet("用户列表");
			// 2.1加载合并单元格对象
			sheet.addMergedRegion(callRangeAddress);
			// 设置默认列宽
			sheet.setDefaultColumnWidth(25);
			// 3.创建行
			// 3.1创建头标题行;并且设置头标题
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell(0);

			// 加载单元格样式
			cell.setCellStyle(headStyle);
			cell.setCellValue("用户列表");

			// 3.2创建列标题;并且设置列标题
			HSSFRow row2 = sheet.createRow(1);
			String[] titles = { "用户名", "账号", "所属部门", "性别", "电子邮箱" };
			for (int i = 0; i < titles.length; i++) {
				HSSFCell cell2 = row2.createCell(i);
				// 加载单元格样式
				cell2.setCellStyle(colStyle);
				cell2.setCellValue(titles[i]);
			}

			// 4.操作单元格;将用户列表写入excel
			if (userList != null) {
				for (int j = 0; j < userList.size(); j++) {
					// 创建数据行,前面有两行,头标题行和列标题行
					HSSFRow row3 = sheet.createRow(j + 2);
					HSSFCell cell1 = row3.createCell(0);
					cell1.setCellValue(userList.get(j).get("name"));
					HSSFCell cell2 = row3.createCell(1);
					cell2.setCellValue(userList.get(j).get("cccount"));
					HSSFCell cell3 = row3.createCell(2);
					cell3.setCellValue(userList.get(j).get("dept"));
					HSSFCell cell4 = row3.createCell(3);
					cell4.setCellValue(userList.get(j).get("gender"));
					HSSFCell cell5 = row3.createCell(4);
					cell5.setCellValue(userList.get(j).get("email"));
				}
			}
			// 5.输出
			OutputStream stream = new FileOutputStream(fileName);
			workbook.write(stream);
			workbook.close();
			// out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param workbook
	 * @param fontsize
	 * @return 单元格样式
	 */
	private static HSSFCellStyle createCellStyle(HSSFWorkbook workbook, short fontsize) {
		// TODO Auto-generated method stub
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
		// 创建字体
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints(fontsize);
		// 加载字体
		style.setFont(font);
		return style;
	}

	/**
	 * 获取Excel单元格的值
	 * 
	 * @param cell
	 * @return
	 */
	public static String getCellValue(Cell cell) {
		String cellValue = "";
		if (cell == null) {
			return cellValue;
		}
		// 判断数据的类型
		switch (cell.getCellType()) {
		/**
		 * 根据cell.getCellStyle().getDataFormat() 方法 可以得到excel 格子中的short类型的值 ,从断点中得知
		 * yyyy/MM/dd 格式的值是 14 HH:mm:ss 格式的值是 21 yyyy/MM/dd HH:mm:ss 格式的值是 22
		 */
		case Cell.CELL_TYPE_NUMERIC: // 数字
			// short s = cell.getCellStyle().getDataFormat();
			if (HSSFDateUtil.isCellDateFormatted(cell)) { // 处理日期格式、时间格式
				SimpleDateFormat sdf = null;
				// 验证short值
				if (cell.getCellStyle().getDataFormat() == 14) {
					sdf = new SimpleDateFormat("yyyy/MM/dd");
				} else if (cell.getCellStyle().getDataFormat() == 21) {
					sdf = new SimpleDateFormat("HH:mm:ss");
				} else if (cell.getCellStyle().getDataFormat() == 22) {
					sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				} else {
					throw new RuntimeException("日期格式错误!!!");
				}
				Date date = cell.getDateCellValue();
				cellValue = sdf.format(date);
			} else if (cell.getCellStyle().getDataFormat() == 0) {// 处理数值格式
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cellValue = String.valueOf(cell.getRichStringCellValue().getString());
			}
			break;
		case Cell.CELL_TYPE_STRING: // 字符串
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		case Cell.CELL_TYPE_BOOLEAN: // Boolean
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA: // 公式
			cellValue = String.valueOf(cell.getCellFormula());
			break;
		case Cell.CELL_TYPE_BLANK: // 空值
			cellValue = null;
			break;
		case Cell.CELL_TYPE_ERROR: // 故障
			cellValue = "非法字符";
			break;
		default:
			cellValue = "未知类型";
			break;
		}
		return cellValue;
	}

	/**
	 * 生成excel文件工具
	 */
	private Workbook workbook;
	private OutputStream os;
	private String pattern;// 日期格式

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public ExcelUtil(Workbook workboook) {
		this.workbook = workboook;
	}

	public ExcelUtil(InputStream is, String version) throws FileNotFoundException, IOException {
		if ("2003".equals(version)) {
			workbook = new HSSFWorkbook(is);
		} else {
			workbook = new XSSFWorkbook(is);
		}
	}

	@Override
	public String toString() {

		return "共有 " + getSheetCount() + "个sheet 页！";
	}

	public String toString(int sheetIx) throws IOException {

		return "第 " + (sheetIx + 1) + "个sheet 页，名称： " + getSheetName(sheetIx) + "，共 " + getRowCount(sheetIx) + "行！";
	}

	/**
	 * 
	 * 根据后缀判断是否为 Excel 文件，后缀匹配xls和xlsx
	 * 
	 * @param pathname
	 * @return
	 * 
	 */
	public static boolean isExcel(String pathname) {
		if (pathname == null) {
			return false;
		}
		return pathname.endsWith(".xls") || pathname.endsWith(".xlsx");
	}

	/**
	 * 
	 * 读取 Excel 第一页所有数据
	 * 
	 * @return
	 * @throws Exception
	 * 
	 */
	public List<List<String>> read() throws Exception {
		return read(0, 0, getRowCount(0) - 1);
	}

	/**
	 * 
	 * 读取指定sheet 页所有数据
	 * 
	 * @param sheetIx 指定 sheet 页，从 0 开始
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> read(int sheetIx) throws Exception {
		return read(sheetIx, 0, getRowCount(sheetIx) - 1);
	}

	/**
	 * 
	 * 读取指定sheet 页指定行数据
	 * 
	 * @param sheetIx 指定 sheet 页，从 0 开始
	 * @param start   指定开始行，从 0 开始
	 * @param end     指定结束行，从 0 开始
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> read(int sheetIx, int start, int end) throws Exception {
		Sheet sheet = workbook.getSheetAt(sheetIx);
		List<List<String>> list = new ArrayList<List<String>>();

		if (end > getRowCount(sheetIx)) {
			end = getRowCount(sheetIx);
		}

		int cols = sheet.getRow(0).getLastCellNum(); // 第一行总列数

		for (int i = start; i <= end; i++) {
			List<String> rowList = new ArrayList<String>();
			Row row = sheet.getRow(i);
			for (int j = 0; j < cols; j++) {
				if (row == null) {
					rowList.add(null);
					continue;
				}
				rowList.add(getCellValueToString(row.getCell(j)));
			}
			list.add(rowList);
		}

		return list;
	}

	/**
	 * 
	 * 将数据写入到 Excel 默认第一页中，从第1行开始写入
	 * 
	 * @param rowData 数据
	 * @return
	 * @throws IOException
	 * 
	 */
	public boolean write(List<List<String>> rowData) throws IOException {
		return write(0, rowData, 0);
	}

	/**
	 * 
	 * 将数据写入到 Excel 新创建的 Sheet 页
	 * 
	 * @param rowData   数据
	 * @param sheetName 长度为1-31，不能包含后面任一字符: ：\ / ? * [ ]
	 * @return
	 * @throws IOException
	 */
	public boolean write(List<List<String>> rowData, String sheetName, boolean isNewSheet) throws IOException {
		Sheet sheet = null;
		if (isNewSheet) {
			sheet = workbook.createSheet(sheetName);
		} else {
			sheet = workbook.createSheet();
		}
		int sheetIx = workbook.getSheetIndex(sheet);
		return write(sheetIx, rowData, 0);
	}

	/**
	 * 
	 * 将数据追加到sheet页最后
	 * 
	 * @param rowData  数据
	 * @param sheetIx  指定 Sheet 页，从 0 开始
	 * @param isAppend 是否追加,true 追加，false 重置sheet再添加
	 * @return
	 * @throws IOException
	 */
	public boolean write(int sheetIx, List<List<String>> rowData, boolean isAppend) throws IOException {
		if (isAppend) {
			return write(sheetIx, rowData, getRowCount(sheetIx));
		} else {// 清空再添加
			clearSheet(sheetIx);
			return write(sheetIx, rowData, 0);
		}
	}

	/**
	 * 
	 * 将数据写入到 Excel 指定 Sheet 页指定开始行中,指定行后面数据向后移动
	 * 
	 * @param rowData  数据
	 * @param sheetIx  指定 Sheet 页，从 0 开始
	 * @param startRow 指定开始行，从 0 开始
	 * @return
	 * @throws IOException
	 */
	public boolean write(int sheetIx, List<List<String>> rowData, int startRow) throws IOException {
		Sheet sheet = workbook.getSheetAt(sheetIx);
		int dataSize = rowData.size();
		if (getRowCount(sheetIx) > 0) {// 如果小于等于0，则一行都不存在
			sheet.shiftRows(startRow, getRowCount(sheetIx), dataSize);
		}
		for (int i = 0; i < dataSize; i++) {
			Row row = sheet.createRow(i + startRow);
			for (int j = 0; j < rowData.get(i).size(); j++) {
				Cell cell = row.createCell(j);
				cell.setCellValue(rowData.get(i).get(j) + "");
			}
		}
		return true;
	}

	/**
	 * 
	 * 设置cell 样式
	 * 
	 * @param sheetIx  指定 Sheet 页，从 0 开始
	 * @param colIndex 指定列，从 0 开始
	 * @return
	 * @throws IOException
	 */
	public boolean setStyle(int sheetIx, int rowIndex, int colIndex, CellStyle style) throws IOException {
		Sheet sheet = workbook.getSheetAt(sheetIx);
		// sheet.autoSizeColumn(colIndex, true);// 设置列宽度自适应
		sheet.setColumnWidth(colIndex, 4000);

		Cell cell = sheet.getRow(rowIndex).getCell(colIndex);
		cell.setCellStyle(style);

		return true;
	}

	/**
	 * 
	 * 设置样式
	 * 
	 * @param type 1：标题 2：第一行
	 * @return
	 */
	public CellStyle makeStyle(int type) {
		CellStyle style = workbook.createCellStyle();

		DataFormat format = workbook.createDataFormat();
		style.setDataFormat(format.getFormat("@"));// // 内容样式 设置单元格内容格式是文本
		style.setAlignment(CellStyle.ALIGN_CENTER);// 内容居中

		// style.setBorderTop(CellStyle.BORDER_THIN);// 边框样式
		// style.setBorderRight(CellStyle.BORDER_THIN);
		// style.setBorderBottom(CellStyle.BORDER_THIN);
		// style.setBorderLeft(CellStyle.BORDER_THIN);

		Font font = workbook.createFont();// 文字样式

		if (type == 1) {
			// style.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);//颜色样式
			// 前景颜色
			// style.setFillBackgroundColor(HSSFColor.LIGHT_BLUE.index);//背景色
			// style.setFillPattern(CellStyle.ALIGN_FILL);// 填充方式
			font.setBold(true);
			font.setFontHeight((short) 500);
		}

		if (type == 2) {
			font.setBold(true);
			font.setFontHeight((short) 300);
		}

		style.setFont(font);

		return style;
	}

	/**
	 * 
	 * 合并单元格
	 * 
	 * @param sheetIx  指定 Sheet 页，从 0 开始
	 * @param firstRow 开始行
	 * @param lastRow  结束行
	 * @param firstCol 开始列
	 * @param lastCol  结束列
	 */
	public void region(int sheetIx, int firstRow, int lastRow, int firstCol, int lastCol) {
		Sheet sheet = workbook.getSheetAt(sheetIx);
		sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
	}

	/**
	 * 
	 * 指定行是否为空
	 * 
	 * @param sheetIx  指定 Sheet 页，从 0 开始
	 * @param rowIndex 指定开始行，从 0 开始
	 * @return true 不为空，false 不行为空
	 * @throws IOException
	 */
	public boolean isRowNull(int sheetIx, int rowIndex) throws IOException {
		Sheet sheet = workbook.getSheetAt(sheetIx);
		return sheet.getRow(rowIndex) == null;
	}

	/**
	 * 
	 * 创建行，若行存在，则清空
	 * 
	 * @param sheetIx 指定 sheet 页，从 0 开始
	 * @param rowIndex  指定创建行，从 0 开始
	 * @return
	 * @throws IOException
	 */
	public boolean createRow(int sheetIx, int rowIndex) throws IOException {
		Sheet sheet = workbook.getSheetAt(sheetIx);
		sheet.createRow(rowIndex);
		return true;
	}

	/**
	 * 
	 * 指定单元格是否为空
	 * 
	 * @param sheetIx  指定 Sheet 页，从 0 开始
	 * @param rowIndex 指定开始行，从 0 开始
	 * @param colIndex 指定开始列，从 0 开始
	 * @return true 行不为空，false 行为空
	 * @throws IOException
	 */
	public boolean isCellNull(int sheetIx, int rowIndex, int colIndex) throws IOException {
		Sheet sheet = workbook.getSheetAt(sheetIx);
		if (!isRowNull(sheetIx, rowIndex)) {
			return false;
		}
		Row row = sheet.getRow(rowIndex);
		return row.getCell(colIndex) == null;
	}

	/**
	 * 
	 * 创建单元格
	 * 
	 * @param sheetIx  指定 sheet 页，从 0 开始
	 * @param rowIndex 指定行，从 0 开始
	 * @param colIndex 指定创建列，从 0 开始
	 * @return true 列为空，false 行不为空
	 * @throws IOException
	 */
	public boolean createCell(int sheetIx, int rowIndex, int colIndex) throws IOException {
		Sheet sheet = workbook.getSheetAt(sheetIx);
		Row row = sheet.getRow(rowIndex);
		row.createCell(colIndex);
		return true;
	}

	/**
	 * 返回sheet 中的行数
	 * 
	 * 
	 * @param sheetIx 指定 Sheet 页，从 0 开始
	 * @return
	 */
	public int getRowCount(int sheetIx) {
		Sheet sheet = workbook.getSheetAt(sheetIx);
		if (sheet.getPhysicalNumberOfRows() == 0) {
			return 0;
		}
		return sheet.getLastRowNum() + 1;

	}

	/**
	 * 
	 * 返回所在行的列数
	 * 
	 * @param sheetIx  指定 Sheet 页，从 0 开始
	 * @param rowIndex 指定行，从0开始
	 * @return 返回-1 表示所在行为空
	 */
	public int getColumnCount(int sheetIx, int rowIndex) {
		Sheet sheet = workbook.getSheetAt(sheetIx);
		Row row = sheet.getRow(rowIndex);
		return row == null ? -1 : row.getLastCellNum();

	}

	/**
	 * 
	 * 设置row 和 column 位置的单元格值
	 * 
	 * @param sheetIx  指定 Sheet 页，从 0 开始
	 * @param rowIndex 指定行，从0开始
	 * @param colIndex 指定列，从0开始
	 * @param value    值
	 * @return
	 * @throws IOException
	 */
	public boolean setValueAt(int sheetIx, int rowIndex, int colIndex, String value) throws IOException {
		Sheet sheet = workbook.getSheetAt(sheetIx);
		sheet.getRow(rowIndex).getCell(colIndex).setCellValue(value);
		return true;
	}

	/**
	 * 
	 * 返回 row 和 column 位置的单元格值
	 * 
	 * @param sheetIx  指定 Sheet 页，从 0 开始
	 * @param rowIndex 指定行，从0开始
	 * @param colIndex 指定列，从0开始
	 * @return
	 * 
	 */
	public String getValueAt(int sheetIx, int rowIndex, int colIndex) {
		Sheet sheet = workbook.getSheetAt(sheetIx);
		return getCellValueToString(sheet.getRow(rowIndex).getCell(colIndex));
	}

	/**
	 * 
	 * 重置指定行的值
	 * 
	 * @param rowData  数据
	 * @param sheetIx  指定 Sheet 页，从 0 开始
	 * @param rowIndex 指定行，从0开始
	 * @return
	 * @throws IOException
	 */
	public boolean setRowValue(int sheetIx, List<String> rowData, int rowIndex) throws IOException {
		Sheet sheet = workbook.getSheetAt(sheetIx);
		Row row = sheet.getRow(rowIndex);
		for (int i = 0; i < rowData.size(); i++) {
			row.getCell(i).setCellValue(rowData.get(i));
		}
		return true;
	}

	/**
	 * 
	 * 返回指定行的值的集合
	 * 
	 * @param sheetIx  指定 Sheet 页，从 0 开始
	 * @param rowIndex 指定行，从0开始
	 * @return
	 */
	public List<String> getRowValue(int sheetIx, int rowIndex) {
		Sheet sheet = workbook.getSheetAt(sheetIx);
		Row row = sheet.getRow(rowIndex);
		List<String> list = new ArrayList<String>();
		if (row == null) {
			list.add(null);
		} else {
			for (int i = 0; i < row.getLastCellNum(); i++) {
				list.add(getCellValueToString(row.getCell(i)));
			}
		}
		return list;
	}

	/**
	 * 
	 * 返回列的值的集合
	 * 
	 * @param sheetIx  指定 Sheet 页，从 0 开始
	 * @param rowIndex 指定行，从0开始
	 * @param colIndex 指定列，从0开始
	 * @return
	 */
	public List<String> getColumnValue(int sheetIx, int rowIndex, int colIndex) {
		Sheet sheet = workbook.getSheetAt(sheetIx);
		List<String> list = new ArrayList<String>();
		for (int i = rowIndex; i < getRowCount(sheetIx); i++) {
			Row row = sheet.getRow(i);
			if (row == null) {
				list.add(null);
				continue;
			}
			list.add(getCellValueToString(sheet.getRow(i).getCell(colIndex)));
		}
		return list;
	}

	/**
	 * 
	 * 获取excel 中sheet 总页数
	 * 
	 * @return
	 */
	public int getSheetCount() {
		return workbook.getNumberOfSheets();
	}

	public void createSheet() {
		workbook.createSheet();
	}

	/**
	 * 
	 * 设置sheet名称，长度为1-31，不能包含后面任一字符: ：\ / ? * [ ]
	 * 
	 * @param sheetIx 指定 Sheet 页，从 0 开始，//
	 * @param name
	 * @return
	 * @throws IOException
	 */
	public boolean setSheetName(int sheetIx, String name) throws IOException {
		workbook.setSheetName(sheetIx, name);
		return true;
	}

	/**
	 * 
	 * 获取 sheet名称
	 * 
	 * @param sheetIx 指定 Sheet 页，从 0 开始
	 * @return
	 * @throws IOException
	 */
	public String getSheetName(int sheetIx) throws IOException {
		Sheet sheet = workbook.getSheetAt(sheetIx);
		return sheet.getSheetName();
	}

	/**
	 * 获取sheet的索引，从0开始
	 * 
	 * @param name sheet 名称
	 * @return -1表示该未找到名称对应的sheet
	 */
	public int getSheetIndex(String name) {
		return workbook.getSheetIndex(name);
	}

	/**
	 * 
	 * 删除指定sheet
	 * 
	 * @param sheetIx 指定 Sheet 页，从 0 开始
	 * @return
	 * @throws IOException
	 */
	public boolean removeSheetAt(int sheetIx) throws IOException {
		workbook.removeSheetAt(sheetIx);
		return true;
	}

	/**
	 * 
	 * 删除指定sheet中行，改变该行之后行的索引
	 * 
	 * @param sheetIx  指定 Sheet 页，从 0 开始
	 * @param rowIndex 指定行，从0开始
	 * @return
	 * @throws IOException
	 */
	public boolean removeRow(int sheetIx, int rowIndex) throws IOException {
		Sheet sheet = workbook.getSheetAt(sheetIx);
		sheet.shiftRows(rowIndex + 1, getRowCount(sheetIx), -1);
		Row row = sheet.getRow(getRowCount(sheetIx) - 1);
		sheet.removeRow(row);
		return true;
	}

	/**
	 * 
	 * 设置sheet 页的索引
	 * 
	 * @param sheetname Sheet 名称
	 * @param sheetIx       Sheet 索引，从0开始
	 */
	public void setSheetOrder(String sheetname, int sheetIx) {
		workbook.setSheetOrder(sheetname, sheetIx);
	}

	/**
	 * 
	 * 清空指定sheet页（先删除后添加并指定sheetIx）
	 * 
	 * @param sheetIx 指定 Sheet 页，从 0 开始
	 * @return
	 * @throws IOException
	 */
	public boolean clearSheet(int sheetIx) throws IOException {
		String sheetname = getSheetName(sheetIx);
		removeSheetAt(sheetIx);
		workbook.createSheet(sheetname);
		setSheetOrder(sheetname, sheetIx);
		return true;
	}

	public Workbook getWorkbook() {
		return workbook;
	}

	/**
	 * 
	 * 关闭流
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException {
		if (os != null) {
			os.close();
		}
		workbook.close();
	}

	/**
	 * 
	 * 转换单元格的类型为String 默认的 <br>
	 * 默认的数据类型：CELL_TYPE_BLANK(3), CELL_TYPE_BOOLEAN(4),
	 * CELL_TYPE_ERROR(5),CELL_TYPE_FORMULA(2), CELL_TYPE_NUMERIC(0),
	 * CELL_TYPE_STRING(1)
	 * 
	 * @param cell
	 * @return
	 * 
	 */
	private String getCellValueToString(Cell cell) {
		String strCell = "";
		if (cell == null) {
			return null;
		}
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				Date date = cell.getDateCellValue();
				if (pattern != null) {
					SimpleDateFormat sdf = new SimpleDateFormat(pattern);
					strCell = sdf.format(date);
				} else {
					strCell = date.toString();
				}
				break;
			}
			// 不是日期格式，则防止当数字过长时以科学计数法显示
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			strCell = cell.toString();
			break;
		case Cell.CELL_TYPE_STRING:
			strCell = cell.getStringCellValue();
			break;
		default:
			break;
		}
		return strCell;
	}
}
