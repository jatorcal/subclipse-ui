package feature.deployer.actions;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public abstract class WorkbenchWindowAction implements IWorkbenchWindowActionDelegate {

	public void init(IWorkbenchWindow workbenchWindow) {

	}

	/*protected IResource[] getSelectedResources() {
		ArrayList resourceArray = new ArrayList();
		IResource[] resources = (IResource[]) getSelectedResources(IResource.class);
		for (int i = 0; i < resources.length; i++) {
			resourceArray.add(resources[i]);
		}
		ResourceMapping[] resourceMappings = (ResourceMapping[]) getSelectedAdaptables(this.selection,
				ResourceMapping.class);
		for (int i = 0; i < resourceMappings.length; i++) {
			ResourceMapping resourceMapping = resourceMappings[i];
			try {
				ResourceTraversal[] traversals = resourceMapping.getTraversals(null, null);
				for (int j = 0; j < traversals.length; j++) {
					IResource[] traversalResources = traversals[j].getResources();
					for (int k = 0; k < traversalResources.length; k++) {
						if (!resourceArray.contains(traversalResources[k])) {
							resourceArray.add(traversalResources[k]);
						}
					}
				}
			} catch (CoreException e) {
				SVNUIPlugin.log(4, e.getMessage(), e);
			}
		}
		IResource[] selectedResources = new IResource[resourceArray.size()];
		resourceArray.toArray(selectedResources);
		return selectedResources;
	}*/
}
