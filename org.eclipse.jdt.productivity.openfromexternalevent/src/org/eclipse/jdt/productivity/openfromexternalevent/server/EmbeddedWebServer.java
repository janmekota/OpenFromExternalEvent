package org.eclipse.jdt.productivity.openfromexternalevent.server;

import org.eclipse.jdt.productivity.openfromexternalevent.servlet.ExternalEventHandlingServlet;
import org.eclipse.jdt.productivity.openfromexternalevent.servlet.IconServlet;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.bio.SocketConnector;
import org.eclipse.jetty.servlet.ServletHandler;

public class EmbeddedWebServer
{

	private Server m_server;

	public void startServer(int portNumber) throws Exception
	{
		stop();
		m_server = new Server();
		Connector connector = new SocketConnector();
		connector.setHost("127.0.0.1");
		connector.setPort(portNumber);
		m_server.addConnector(connector);

		ServletHandler handler = new ServletHandler();
		m_server.setHandler(handler);
		handler.addServletWithMapping(IconServlet.class, "/icon/*");
		handler.addServletWithMapping(ExternalEventHandlingServlet.class, "/postevent/*");
		m_server.start();
	}

	public void stop() throws Exception
	{
		if (m_server != null)
		{
			m_server.stop();
			m_server = null;
		}
	}
}
