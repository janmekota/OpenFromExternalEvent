package org.eclipse.jdt.productivity.openfromexternalevent.actions;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class BringEclipseToFrontRunnable implements Runnable
{

	@Override
	public void run()
	{
		// from com.atlassian.connector.eclipse.internal.directclickthrough.servlet.DirectClickThroughServlet.bringEclipseToFront()
		IWorkbenchWindow[] windows = PlatformUI.getWorkbench().getWorkbenchWindows();
		if (windows == null || windows.length == 0)
		{
			return;
		}

		Shell shell = windows[0].getShell();
		if (shell != null)
		{
			Shell dialog = getModalDialogOpen(shell);
			if (shell.getMinimized())
			{
				shell.setMinimized(false);
				if (dialog != null)
				{
					dialog.setFocus();
				}
			}
			// If a model dialog is open on the shell, don't activate it
			if (dialog == null)
			{
				shell.forceActive();
			}
		}
	}

	private Shell getModalDialogOpen(Shell shell)
	{
		Shell[] shells = shell.getShells();
		for (int i = 0; i < shells.length; i++)
		{
			Shell dialog = shells[i];
			if ((dialog.getStyle() & (SWT.APPLICATION_MODAL | SWT.PRIMARY_MODAL | SWT.SYSTEM_MODAL)) > 0)
			{
				return dialog;
			}
		}
		return null;
	}
}
