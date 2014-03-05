package org.eclipse.jdt.productivity.openfromexternalevent;

import org.eclipse.jdt.productivity.openfromexternalevent.server.EmbeddedServerActivator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin
{

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.jdt.productivity.openfromexternalevent"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	private EmbeddedServerActivator m_embeddedServerActivator;

	public static class EarlyStartup implements IStartup
	{
		@Override
		public void earlyStartup()
		{
			//Early startup is used to activate the plug as soon as possible to start the Embedded webserver
		}
	}

	public Activator()
	{
	}

	@Override
	public void start(BundleContext context) throws Exception
	{
		super.start(context);
		plugin = this;
		m_embeddedServerActivator = new EmbeddedServerActivator(getPreferenceStore());
		m_embeddedServerActivator.start();
	}

	@Override
	public void stop(BundleContext context) throws Exception
	{
		plugin = null;
		m_embeddedServerActivator.stop();
		super.stop(context);
	}

	public static Activator getDefault()
	{
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 * 
	 * @param path
	 *          the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path)
	{
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
