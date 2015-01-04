package com.grupoexata.bancario.utils;

import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.grupoexata.bancario.dao.Banco;

public class GeraRelatorio {

	public static void geracao(HttpServletRequest request,
			HttpServletResponse response, Map<Object, Object> map,
			boolean exporta) {

		String path = request.getSession().getServletContext().getRealPath("/");

		map.put("IMAGE_PATH", path + "imagens/");
		map.put("REPORT_CONNECTION", Banco.getConnection());
		map.put("SUBREPORT_DIR", path + "/ireport/jasper/");

		String jasperName = map.get("REPORT_NAME").toString();

		String dia = new SimpleDateFormat("ddMMyyyyhhmm",
				new Locale("pt", "BR")).format(new Date());

		JasperReport jasperReport;

		try {

			jasperReport = (JasperReport) JRLoader.loadObject(path
					+ "ireport/jasper/" + jasperName + ".jasper");
			byte[] pdfRelatorio = JasperRunManager.runReportToPdf(jasperReport,
					map);

			response.setContentType("application/pdf");
			response.setHeader("Cache-Control", "no-store");
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ (jasperName + dia) + ".pdf");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 1);

			ServletOutputStream servletOutputStream = response
					.getOutputStream();
			servletOutputStream.write(pdfRelatorio);
			servletOutputStream.flush();
			servletOutputStream.close();

			if (exporta) {
				JasperPrint impressao = JasperFillManager.fillReport(path
						+ "jasper/" + jasperName + ".jasper", map);
				JasperExportManager.exportReportToPdfFile(impressao, path
						+ "relatorios/" + (jasperName + dia) + ".pdf");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void gerarDocumentoExcel(String arquivo, ResultSet rs) {

		try {
			FileOutputStream out = new FileOutputStream(arquivo);
			
			HSSFWorkbook hssfworkbook = new HSSFWorkbook();
			
			HSSFSheet sheet = hssfworkbook.createSheet("sheet");
			
			
			ResultSetMetaData meta = rs.getMetaData();
			
			
			HSSFCellStyle cs = hssfworkbook.createCellStyle();
			
//			HSSFDataFormat df = hssfworkbook.createDataFormat();
//			
//			cs.setDataFormat(df.getFormat("#,##0.0"));
			
			List<String> coluns = new ArrayList<String>(); 
			
			for (int i = 0; i < meta.getColumnCount(); i++) {
				coluns.add(meta.getColumnLabel(i+1));				
			}
			
			HSSFRow row = sheet.createRow(0);
			
			Iterator<String> it = coluns.iterator();
			for (int i = 0; i < meta.getColumnCount(); i++){
				if (it.hasNext()){
					HSSFCell cell = row.createCell((short) i);		
					cell.setCellValue(it.next());
					cell.setCellStyle(cs);
				}
			}	
			int linha = 1;
			while (rs.next()){
				for (int i = 0; i < meta.getColumnCount(); i++){
					HSSFRow row2 = sheet.createRow(linha);
					HSSFCell cell = row2.createCell((short) i);				
					cell.setCellValue(rs.getString(i+1));
					cell.setCellStyle(cs);		
				}	
				linha++;
				Runtime.getRuntime().gc();
			}
			
			hssfworkbook.write(out);
			
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			String sql = "select * from VW_angariador";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			
			gerarDocumentoExcel("c:/planilha.xls", rs);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
