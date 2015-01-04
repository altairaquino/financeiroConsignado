package com.grupoexata.bancario.utils;

import it.businesslogic.ireport.export.JRTxtExporter;
import it.businesslogic.ireport.export.JRTxtExporterParameter;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class RelatorioTipo {

	public enum TipoSaidaRelatorio {
		PDF, RTF, HTML, TXT, TXTIREPORT
	}

	private ArrayList<Object> itens;
	private String nomeRelatorio;

	/** Creates a new instance of Relatorio */
	public RelatorioTipo() {}

	public byte[] execute(String nomeArquivo,
			TipoSaidaRelatorio tipoSaidaRelatorio) throws Exception {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] output;

		try {

			LinkedList<Object> listNewResultado = new LinkedList<Object>(this.itens);

			InputStream in = this.getClass().getResourceAsStream(this.nomeRelatorio);

			JasperDesign jasperDesign = JRXmlLoader.load(in);

			JasperReport relatorio = JasperCompileManager.compileReport(jasperDesign);
			
			JasperPrint impressao = JasperFillManager.fillReport(relatorio,
					null, new JRBeanCollectionDataSource(listNewResultado));

			impressao.setLocaleCode(Locale.getDefault().getCountry());

			JRAbstractExporter saida = null;

			switch (tipoSaidaRelatorio) {
				case PDF:
					// itext.x.x.x.jar para usar saida como PDF
					saida = new JRPdfExporter();
					saida.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
					saida.setParameter(JRExporterParameter.JASPER_PRINT, impressao);
					saida.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
					saida.exportReport();
					break;
				case RTF:
					saida = new JRRtfExporter();
					saida.setParameter(JRExporterParameter.CHARACTER_ENCODING,"UTF-8");
					saida.setParameter(JRExporterParameter.JASPER_PRINT, impressao);
					saida.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
					saida.exportReport();
					break;
				case HTML:
					saida = new JRHtmlExporter();
					saida.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
					saida.setParameter(JRExporterParameter.JASPER_PRINT, impressao);
					saida.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
					saida.exportReport();
					break;
				case TXT:
					saida = new JRTextExporter();
					saida.setParameter(JRTextExporterParameter.CHARACTER_ENCODING,"UTF-8");
					saida.setParameter(JRTextExporterParameter.JASPER_PRINT, impressao);
					saida.setParameter(JRTextExporterParameter.OUTPUT_STREAM, baos);
					saida.exportReport();
					break;
				case TXTIREPORT:
					saida = new JRTxtExporter();
					saida.setParameter(JRTxtExporterParameter.CHARACTER_ENCODING,"UTF-8");
					saida.setParameter(JRTxtExporterParameter.PAGE_ROWS, "76");
					saida.setParameter(JRTxtExporterParameter.PAGE_COLUMNS, "80");
					saida.setParameter(JRExporterParameter.JASPER_PRINT, impressao);
					saida.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
					saida.exportReport();
					break;
				}

			output = baos.toByteArray();

			return output;

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	
	public void setNomeRelatorio(String nomeRelatorio) {
		this.nomeRelatorio = nomeRelatorio;
	}
	
	public static void main(String[] args) {
		try {
			RelatorioTipo r = new RelatorioTipo();
			byte [] x = r.execute("E:/workspace/exata/ireport/jasper/movimento_caixa.jasper", RelatorioTipo.TipoSaidaRelatorio.RTF);
			System.out.println(x);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
