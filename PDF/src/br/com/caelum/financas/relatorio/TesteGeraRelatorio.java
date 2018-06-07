package br.com.caelum.financas.relatorio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.stream.Stream;

import br.com.caelum.financas.ConnectionFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class TesteGeraRelatorio {

	public static void main(String[] args) throws SQLException, JRException, FileNotFoundException {

		
		/**
		 * 
		 * gerador de jasper 
		 * 
		 */
		//		JasperCompileManager.compileReportToFile("gastos_por_mes_subreport1.jrxml");
		
		/**
		 * 
		 * Gerador de PDF
		 * 
		 */	
		// parametros passados para dentro do pdf
		HashMap<String, Object> params = new HashMap<String, Object>();
		// conexão com banco de dados
		Connection conexao = new ConnectionFactory().getConnection();
		
		// chamada para criação do pdf de acordo com o jasper, parametros e a conexão
		JasperPrint jasperPrint = JasperFillManager.fillReport("financas.jasper", params, conexao);
		
		// utlizado para exportar o pdf
		JRExporter exporter = new JRPdfExporter();
		
		
		// setando os parametros para print e nome do arquivo 
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream("financas.pdf"));
		
		// exporta o report
		exporter.exportReport();
		
		
		conexao.close();
	}
}
