package org.eclipse.jdt.productivity.openfromexternalevent.preferences;

import org.eclipse.jdt.productivity.openfromexternalevent.Activator;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * This class represents a preference page that
 * is contributed to the Preferences dialog. By
 * subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows
 * us to create a page that is small and knows how to
 * save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the preference store that belongs to the main
 * plug-in class. That way, preferences can be accessed directly via the preference store.
 */

public class PreferencesPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage
{

	private static final int PORT_RANGE_MAX = 0xFFFF - 1;

	private static final int PORT_RANGE_MIN = 1024;

	public PreferencesPage()
	{
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setTitle("Open from External Event preferences");
		setDescription("A demonstration of a preference page implementation");
		setDescription("Open from External Event preferences allows you to easily open items in Eclipse "
									 + "with one click and open selected item automatically. This feature opens a locally "
									 + "available TCP/IP port, only localhost can access it.");
	}

	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	@Override
	public void createFieldEditors()
	{
		addField(new BooleanFieldEditor(PreferenceConstants.ENABLED,
																		"Enabled Open from External Event",
																		getFieldEditorParent()));

		final IntegerFieldEditor port = new IntegerFieldEditor(PreferenceConstants.PORT_NUMBER,
																													 "Let Eclipse listen on following TCP/IP port",
																													 getFieldEditorParent(),
																													 5);

		port.setValidRange(PORT_RANGE_MIN, PORT_RANGE_MAX);
		addField(port);
	}

	@Override
	public void init(IWorkbench workbench)
	{
	}
}