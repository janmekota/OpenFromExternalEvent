package org.eclipse.jdt.productivity.openfromexternalevent.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.productivity.openfromexternalevent.actions.BringEclipseToFrontRunnable;
import org.eclipse.jdt.productivity.openfromexternalevent.actions.OpenFromExternalEventRunnable;
import org.eclipse.ui.PlatformUI;

@SuppressWarnings("serial")
public class ExternalEventHandlingServlet extends HttpServlet
{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		addAllowCrossOriginRequests(response);
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter()
						.println("<h3>To trigger an event you have to send a POST request rather then a GET request, see <a href=\"https://github.com/cbos/OpenFromExternalEvent\">https://github.com/cbos/OpenFromExternalEvent</a></h3>");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		addAllowCrossOriginRequests(response);
		IconServlet.writeIcon(response);
		if (request.getContentLength() > 0)
		{
			try (InputStream in = request.getInputStream())
			{
				final String input = streamToString(in);
				asyncExecute(new OpenFromExternalEventRunnable(input));
			}
			bringEclipseToFront();
		}
	}

	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException,
																																										IOException
	{
		addAllowCrossOriginRequests(response);
		super.doOptions(request, response);
	}

	private void addAllowCrossOriginRequests(HttpServletResponse response)
	{
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
		response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
	}

	private void bringEclipseToFront()
	{
		asyncExecute(new BringEclipseToFrontRunnable());
	}

	private void asyncExecute(Runnable runnable)
	{
		PlatformUI.getWorkbench().getDisplay().asyncExec(runnable);
	}

	private String streamToString(InputStream inputStream) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length = 0;
		while ((length = inputStream.read(buffer)) != -1)
		{
			baos.write(buffer, 0, length);
		}
		return baos.toString();
	}
}
