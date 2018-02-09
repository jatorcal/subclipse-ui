/*******************************************************************************
 * Copyright (c) 2003, 2006 Subclipse project and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Subclipse project committers - initial API and implementation
 ******************************************************************************/
package feature.deployer.wizard;


import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.part.DrillDownComposite;
import org.tigris.subversion.subclipse.ui.IHelpContextIds;
import org.tigris.subversion.subclipse.ui.Policy;
import org.tigris.subversion.subclipse.ui.repository.RepositoryFilters;
import org.tigris.subversion.subclipse.ui.repository.model.AllRootsElement;

/**
 * the main wizard page for moving or renaming a remote resource on repository
 */
public class DeployFeatureWizardMainPage extends FeatureDeployerWizardPage {

    private static final int LIST_WIDTH = 250;
    private static final int LIST_HEIGHT = 300;

    private Text urlParentText;
    private Text resourceNameText;

    private TreeViewer viewer;

    private String resourceName = ""; //$NON-NLS-1$

    private ISelectionChangedListener treeSelectionChangedListener = new ISelectionChangedListener() {
        public void selectionChanged(SelectionChangedEvent event) {
            Object selection = ((IStructuredSelection)event.getSelection()).getFirstElement();
        }

    };


	/**
	 * MoveRemoteResourceWizardMainPage constructor.
	 *
	 * @param pageName  the name of the page
	 * @param title  the title of the page
	 * @param titleImage  the image for the page
	 */
	public DeployFeatureWizardMainPage(
        String pageName,
        String title,
        ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
	}

	/**
	 * Creates the UI part of the page.
	 *
	 * @param parent  the parent of the created widgets
	 */
	public void createControl(Composite parent) {
		Composite composite = createComposite(parent, 1);
		// set F1 help
		PlatformUI.getWorkbench().getHelpSystem().setHelp(composite, IHelpContextIds.MOVE_RENAME_REMOTE_RESOURCE_PAGE);

		Listener listener = new Listener() {
			public void handleEvent(Event event) {
                resourceName = resourceNameText.getText();
				validateFields();
			}
		};

        // the text field for the parent folder
		createLabel(composite, Policy.bind("MoveRemoteResourceWizardMainPage.selectParentUrl")); //$NON-NLS-1$

        urlParentText = createTextField(composite);
        urlParentText.addListener(SWT.Selection, listener);
        urlParentText.addListener(SWT.Modify, listener);
        urlParentText.setEditable(false);


        // Create drill down.
        DrillDownComposite drillDown = new DrillDownComposite(composite, SWT.BORDER);
        GridData spec = new GridData(GridData.FILL_BOTH);
        spec.widthHint = LIST_WIDTH;
        spec.heightHint = LIST_HEIGHT;
        drillDown.setLayoutData(spec);

        // Create tree viewer inside drill down.
        viewer = new TreeViewer(drillDown, SWT.H_SCROLL | SWT.V_SCROLL);
        drillDown.setChildTree(viewer);
        viewer.setLabelProvider(new WorkbenchLabelProvider());

        viewer.setInput(new AllRootsElement());
        viewer.addFilter(RepositoryFilters.FOLDERS_ONLY);
        viewer.addSelectionChangedListener(treeSelectionChangedListener);

        // the text field for the resource name
        createLabel(composite, Policy.bind("MoveRemoteResourceWizardMainPage.resourceName")); //$NON-NLS-1$

        resourceNameText = createTextField(composite);
        resourceNameText.addListener(SWT.Selection, listener);
        resourceNameText.addListener(SWT.Modify, listener);
        resourceNameText.setText(resourceName);

		validateFields();
        resourceNameText.setFocus();

		setControl(composite);

	}

	/**
	 * Validates the contents of the editable fields and set page completion
	 * and error messages appropriately. Call each time folder name or parent url
     * is modified
	 */
	private void validateFields() {
		if (resourceNameText.getText().length() == 0) {
			setErrorMessage(null);
			setPageComplete(false);
			return;
		}
		
		setErrorMessage(null);
		setPageComplete(true);
	}


	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
            resourceNameText.setFocus();
		}
	}


    /**
     * get the destination name of the resource
     */
    public String getResourceName() {
        return resourceNameText.getText();
    }


}
