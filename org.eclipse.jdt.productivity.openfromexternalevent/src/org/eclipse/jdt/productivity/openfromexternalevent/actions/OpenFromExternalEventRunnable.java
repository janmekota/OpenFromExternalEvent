package org.eclipse.jdt.productivity.openfromexternalevent.actions;

public class OpenFromExternalEventRunnable implements Runnable
{
	private final String m_input;

	public OpenFromExternalEventRunnable(String input)
	{
		m_input = input;
	}

	@Override
	public void run()
	{
		OpenFromExternalEventAction openFromExternalEventAction = new OpenFromExternalEventAction();
		openFromExternalEventAction.setInputText(m_input);
		openFromExternalEventAction.run(null);
	}

}
