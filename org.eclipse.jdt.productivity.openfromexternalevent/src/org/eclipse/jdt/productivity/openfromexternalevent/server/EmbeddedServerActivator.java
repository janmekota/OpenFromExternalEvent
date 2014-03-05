package org.eclipse.jdt.productivity.openfromexternalevent.server;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jdt.productivity.openfromexternalevent.Activator;
import org.eclipse.jdt.productivity.openfromexternalevent.actions.JobWithStatus;
import org.eclipse.jdt.productivity.openfromexternalevent.actions.StatusHandler;
import org.eclipse.jdt.productivity.openfromexternalevent.preferences.PreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

public class EmbeddedServerActivator
{
	private final IPreferenceStore m_preferenceStore;

	private org.eclipse.jdt.productivity.openfromexternalevent.server.EmbeddedWebServer m_embeddedWebServer;

	public EmbeddedServerActivator(IPreferenceStore iPreferenceStore)
	{
		m_preferenceStore = iPreferenceStore;
		addPropertyChangeListener();
	}

	public void start()
	{
		if (isEnabled())
		{
			startEmbeddedServer();
		}
	}

	private void addPropertyChangeListener()
	{
		m_preferenceStore.addPropertyChangeListener(new IPropertyChangeListener()
		{
			@Override
			public void propertyChange(PropertyChangeEvent event)
			{
				stopEmbeddedServer();

				if (isEnabled())
				{
					startEmbeddedServer();
				}
			}
		});
	}

	private boolean isEnabled()
	{
		return m_preferenceStore.getBoolean(PreferenceConstants.ENABLED);
	}

	private int getPortNumber()
	{
		return m_preferenceStore.getInt(PreferenceConstants.PORT_NUMBER);
	}

	public void stop()
	{
		stopEmbeddedServer();
	}

	private void startEmbeddedServer()
	{
		if (m_embeddedWebServer != null)
		{
			stopEmbeddedServer();
		}

		final JobWithStatus serverJob = new JobWithStatus("Start Embedded Web Server")
		{
			@Override
			protected void runImpl(IProgressMonitor monitor)
			{
				try
				{
					m_embeddedWebServer = new EmbeddedWebServer();
					m_embeddedWebServer.startServer(getPortNumber());
				}
				catch (Exception e)
				{
					setStatus(new Status(IStatus.INFO,
															 Activator.PLUGIN_ID,
															 "Unable to run embedded web server, Open from External Event will not be available",
															 e));
				}
			}
		};
		serverJob.addJobChangeListener(new JobChangeAdapter()
		{
			@Override
			public void done(IJobChangeEvent event)
			{
				if (!serverJob.getStatus().isOK())
				{
					StatusHandler.log(serverJob.getStatus());
				}
			}
		});
		serverJob.schedule();
	}

	private void stopEmbeddedServer()
	{
		try
		{
			if (m_embeddedWebServer != null)
			{
				m_embeddedWebServer.stop();
				m_embeddedWebServer = null;
			}
		}
		catch (Exception e)
		{
			StatusHandler.log(new Status(IStatus.WARNING,
																	 Activator.PLUGIN_ID,
																	 "Unabled to stop embedded Open from External Event server",
																	 e));
		}
	}
}
