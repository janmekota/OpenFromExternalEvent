package org.eclipse.jdt.productivity.openfromexternalevent.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jdt.productivity.openfromexternalevent.Activator;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer
{

	@Override
	public void initializeDefaultPreferences()
	{
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();

		store.setDefault(PreferenceConstants.ENABLED, PreferenceConstants.DEFAULT_ENABLED);
		store.setDefault(PreferenceConstants.PORT_NUMBER, PreferenceConstants.DEFAULT_PORT_NUMBER);
	}

}
