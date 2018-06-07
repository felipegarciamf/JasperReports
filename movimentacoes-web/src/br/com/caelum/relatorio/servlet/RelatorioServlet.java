package br.com.caelum.relatorio.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.relatorio.ConnectionFactory;
import br.com.caelum.relatorio.gerador.GeradorRelatorio;

@WebServlet("/movimentacoes")
public class RelatorioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			Connection connection = new ConnectionFactory().getConnection();

			//Aqui gera o PDF.
			//O arquivo .jasper se encontra na pasta /WEB-INF/jasper/movimentacoes.jasper
			//Você pode utilizar a classe GeradorRelatorio que já existe no projeto.
			
			
			connection.close();
		} catch (SQLException e) {
			throw new ServletException(e);
		} 
	}
}
