package org.eclipse.jdt.productivity.openfromexternalevent.actions;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

public abstract class JobWithStatus extends Job
{

	private IStatus status = Status.OK_STATUS;

	public JobWithStatus(String name)
	{
		super(name);
	}

	protected void setStatus(IStatus status)
	{
		this.status = status;
	}

	/**
	 * @return if run did not set status it will return {@link Status#OK_STATUS} just to make using this method easier
	 */
	public IStatus getStatus()
	{
		return status;
	}

	@Override
	public IStatus run(IProgressMonitor monitor)
	{
		if (monitor != null && monitor.isCanceled())
		{
			setStatus(Status.CANCEL_STATUS);
			return Status.CANCEL_STATUS;
		}

		try
		{
			runImpl(monitor);
			return Status.OK_STATUS;
		}
		catch (CoreException e)
		{
			setStatus(e.getStatus());
			return Status.OK_STATUS;
		}
	}

	protected abstract void runImpl(IProgressMonitor monitor) throws CoreException;
}