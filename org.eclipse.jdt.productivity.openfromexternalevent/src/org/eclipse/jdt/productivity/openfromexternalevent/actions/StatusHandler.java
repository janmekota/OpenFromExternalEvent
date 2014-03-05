package org.eclipse.jdt.productivity.openfromexternalevent.actions;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.productivity.openfromexternalevent.Activator;

public class StatusHandler
{
	public static void log(IStatus status)
	{
		Activator.getDefault().getLog().log(status);
	}
}
