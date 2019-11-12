//package controller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.springframework.core.io.InputStreamResource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//
//@Slf4j
//@RestController
//@RequestMapping("/quote")
//public class ExcelController {
//
//	@RequestMapping(value = "/{mediaNo}/download", method = RequestMethod.GET)
//	public ResponseEntity<InputStreamResource> downloadQuizDataAsExcelFile(@PathVariable("mediaNo") Long mediaNo) throws IOException {
//		log.info("[FRANK] mediaNo : {}", mediaNo);
//
//		ByteArrayInputStream in = this.generateExcel();
//
//		HttpHeaders responseHeaders = new HttpHeaders();
//		//	    responseHeaders.setCharacterEncoding("UTF-8");
//
//		//	    responseHeaders.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//		responseHeaders.add("Content-Disposition", "attachment; filename=customers.xlsx");
//
//		return ResponseEntity
//				.ok()
//				.headers(responseHeaders)
//				.body(new InputStreamResource(in));
//	}
//
//	private ByteArrayInputStream generateExcel() throws IOException {
//		Workbook workbook = new XSSFWorkbook();
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//
//		Sheet sheet = workbook.createSheet("Persons");
//		sheet.setColumnWidth(0, 6000);
//		sheet.setColumnWidth(1, 4000);
//
//		Row header = sheet.createRow(0);
//
//		CellStyle headerStyle = workbook.createCellStyle();
//		headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
//		//		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//
//		XSSFFont font = ((XSSFWorkbook) workbook).createFont();
//		font.setFontName("Arial");
//		font.setFontHeightInPoints((short) 16);
//		font.setBold(true);
//		headerStyle.setFont(font);
//
//		Cell headerCell = header.createCell(0);
//		headerCell.setCellValue("Name");
//		headerCell.setCellStyle(headerStyle);
//
//		headerCell = header.createCell(1);
//		headerCell.setCellValue("Age");
//		headerCell.setCellStyle(headerStyle);
//
//		CellStyle style = workbook.createCellStyle();
//		style.setWrapText(true);
//
//		Row row = sheet.createRow(2);
//		Cell cell = row.createCell(0);
//		cell.setCellValue("John Smith");
//		cell.setCellStyle(style);
//
//		cell = row.createCell(1);
//		cell.setCellValue(20);
//		cell.setCellStyle(style);
//
//		workbook.write(out);
//		return new ByteArrayInputStream(out.toByteArray());
//	}
//}
