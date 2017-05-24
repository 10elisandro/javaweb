package controller.portal;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public abstract class PortalServlet extends HttpServlet {

	protected abstract void doGetPortal(HttpServletRequest request, HttpServletResponse response) throws Exception;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doGetPortal(request, response);
			trataRetornoSucesso(response);
		} catch (Exception e) {
			trataRetornoErroCritico(response, e);
		}
	}
	
	public static void trataRetornoSucesso(HttpServletResponse response) throws IOException {
		trataRetorno(response, null);
	}
	
	public static void trataRetornoErroEsperado(HttpServletResponse response, String msgErro) throws IOException {
		trataRetorno(response, "Atenção: " + msgErro);
	}

	public static void trataRetornoErroCritico(HttpServletResponse response, Exception e) throws IOException {
		e.printStackTrace();
		trataRetorno(response, "Erro crítico no sistema: " + e.getMessage());
	}

	private static void trataRetorno(HttpServletResponse response, String msgErro) throws IOException {
		response.setContentType("text/html; charset=iso-8859-1;");

		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Projeto Java Web</title></head><body>");
		if (msgErro == null) {
			out.println("<h4>Operação efetuada com sucesso!</h4>");
		} else {
			out.println("<h4>" + msgErro + "</h4><a href='javascript:window.history.go(-1)'><input type='button' value='Voltar'></a>");
		}
		out.println("<a href='index.jsp'><input type='button' value='Tela Inicial'></a></body></html>");
		out.close();	
	}
}