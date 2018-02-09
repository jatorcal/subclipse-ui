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


import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.Wizard;
import org.tigris.subversion.subclipse.core.ISVNRemoteResource;
import org.tigris.subversion.subclipse.ui.ISVNUIConstants;
import org.tigris.subversion.subclipse.ui.Policy;
import org.tigris.subversion.subclipse.ui.SVNUIPlugin;
import org.tigris.subversion.subclipse.ui.wizards.ClosableWizardDialog;

import feature.deployer.preferences.page.Activator;

/**
 * Wizard to move a remote resource
 */
public class DeployFeatureWizard extends Wizard {
	private DeployFeatureWizardMainPage mainPage;
    //private CommentCommitWizardPage commitCommentPage; 
    private ISVNRemoteResource selection;
    private Dialog parentDialog;
	
   
	public DeployFeatureWizard(ISVNRemoteResource selection) {
		setWindowTitle(Policy.bind("moveRemoteFolderWizard.title")); //$NON-NLS-1$
        this.selection = selection;
	}

	/**
	 * Creates the wizard pages
	 */
	public void addPages() {
		// add the main page
        mainPage = new DeployFeatureWizardMainPage(
            "newRemoteFolderPage1",  //$NON-NLS-1$ 
            Policy.bind("DeployFeatureWizardMainPage.deployFeatureHeading"), //$NON-NLS-1$
            Activator.getPlugin().getImageDescriptor(ISVNUIConstants.IMG_WIZBAN_NEW_FOLDER));
        //mainPage.setRemoteResource(selection);
		addPage(mainPage);
        
        // add commit comment page
        String pageTitle = Policy.bind("DeployFeatureWizardMainPage.deployFeature"); //$NON-NLS-1$
        String pageDescription = Policy.bind("DeployFeatureWizardMainPage.deployFeaturePageDescription"); //$NON-NLS-1$
        ImageDescriptor image = Activator.getPlugin().getImageDescriptor("/icons/features.png");
        //commitCommentPage = new CommentCommitWizardPage(parentDialog, pageTitle, pageTitle, image, pageDescription);
        //addPage(commitCommentPage); 
                
	}
    
	/*
	 * @see IWizard#performFinish
	 */
	public boolean performFinish() {
        try {
            SVNUIPlugin.runWithProgress(getContainer().getShell(), false /*cancelable*/, new IRunnableWithProgress() {
                public void run(IProgressMonitor monitor) throws InvocationTargetException {
                	
                }
            });
        } catch (InterruptedException e) {
            // operation canceled
        } catch (InvocationTargetException e) {
            SVNUIPlugin.openError(getContainer().getShell(), Policy.bind("exception"), null, e.getCause(), SVNUIPlugin.PERFORM_SYNC_EXEC); //$NON-NLS-1$
            return false;
        }
	   return true;
	}
    
    /**
     * Method setParentDialog.
     * @param dialog
     */
    public void setParentDialog(Dialog dialog) {
        this.parentDialog = dialog;
    }
    
    public void finishAndClose() {
    	if (parentDialog != null && parentDialog instanceof ClosableWizardDialog && canFinish()) {
    		((ClosableWizardDialog)parentDialog).finishPressed();
    	}
    }    
    
}
