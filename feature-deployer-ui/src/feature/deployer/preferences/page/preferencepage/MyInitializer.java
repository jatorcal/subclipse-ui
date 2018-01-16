package feature.deployer.preferences.page.preferencepage;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import feature.deployer.preferences.page.Activator;

public class MyInitializer extends AbstractPreferenceInitializer {

	public MyInitializer() {
	}

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault("MySTRING1", "Example String");
	}

}
