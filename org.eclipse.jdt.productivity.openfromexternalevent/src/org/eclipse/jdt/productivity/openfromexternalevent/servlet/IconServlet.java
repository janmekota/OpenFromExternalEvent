package org.eclipse.jdt.productivity.openfromexternalevent.servlet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.productivity.openfromexternalevent.Activator;

@SuppressWarnings("serial")
public class IconServlet extends HttpServlet
{
	public static final URL BASE_URL = Activator.getDefault().getBundle().getEntry("/icons/");

	public static final String PATH_ECLIPSE_IMAGE = "eclipse.gif"; //$NON-NLS-1$  

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		writeIcon(response);
	}

	public static void writeIcon(final HttpServletResponse response) throws IOException
	{
		try (InputStream icon = new BufferedInputStream(new URL(BASE_URL, PATH_ECLIPSE_IMAGE).openStream()))
		{
			response.setContentType("image/gif");
			response.setStatus(HttpServletResponse.SC_OK);

			OutputStream output = response.getOutputStream();
			for (int b = icon.read(); b != -1; b = icon.read())
			{
				output.write(b);
			}
		}
	}
}
