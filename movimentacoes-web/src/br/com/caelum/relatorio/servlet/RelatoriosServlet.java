package br.com.caelum.relatorio.servlet;

import java.io.IOException;
import java.sql.Connection;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import br.com.caelum.relatorio.ConnectionFactory;
import br.com.caelum.relatorio.gerador.GeradorRelatorio;


/**
 * Servlet implementation class RelatoriosServlet
 */
@WebServlet("/gastos")
public class RelatoriosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		try {
		String nome = request.getServletContext().getRealPath("/WEB-INF/jasper/gastos_por_mes.jasper");
		
		Connection connection = new ConnectionFactory().getConnection();
		
		String dataIni = request.getParameter("data_ini");
		String dataFim = request.getParameter("data_fim");
		String tipo = request.getParameter("tipo");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		
		Date dataInicial = sdf.parse(dataIni);
		Date dataFinal = sdf.parse(dataFim);
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("DATA_INI", dataInicial);
		parametros.put("DATA_FIM", dataFinal);
		parametros.put("TIPO", tipo);
		
		
		GeradorRelatorio gerador = new GeradorRelatorio(nome, parametros, connection);
		gerador.geraPDFParaOutputStream(response.getOutputStream());
		
		connection.close();
		} catch (SQLException e) {
			throw new ServletException(e);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
