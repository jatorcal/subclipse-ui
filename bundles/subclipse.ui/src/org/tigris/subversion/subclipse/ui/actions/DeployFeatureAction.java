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
package org.tigris.subversion.subclipse.ui.actions;

import com.feature.deployer.ui.wizards.DeployFeatureWizard;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.wizard.WizardDialog;
import org.tigris.subversion.subclipse.core.ISVNRemoteResource;
import org.tigris.subversion.subclipse.ui.ISVNUIConstants;
import org.tigris.subversion.subclipse.ui.wizards.ClosableWizardDialog;

/**
 * Action to move a remote resource on repository
 */
public class DeployFeatureAction extends SVNAction {

	/* (non-Javadoc)
	 * @see org.tigris.subversion.subclipse.ui.actions.SVNAction#execute(org.eclipse.jface.action.IAction)
	 */
	protected void execute(IAction action) {
          
 
		ISVNRemoteResource svnResource = (ISVNRemoteResource)selection.getFirstElement();
		
		String relativePath = svnResource.getRepositoryRelativePath();
		String repositoryUri = svnResource.getRepository().getUrl().toString();
		String completeFeatureUri = svnResource.getUrl().toString();
        
		
		
		DeployFeatureWizard wizard = new DeployFeatureWizard(completeFeatureUri);
                
		
        WizardDialog dialog = new ClosableWizardDialog(shell, wizard);
        wizard.setParentDialog(dialog);
        dialog.open();

	}

	/* (non-Javadoc)
	 * @see org.eclipse.team.internal.ui.actions.TeamAction#isEnabled()
	 */
	protected boolean isEnabled() {
		return (selection.size() == 1) && (selection.getFirstElement() instanceof ISVNRemoteResource); 
	}

	/*
	 * @see org.tigris.subversion.subclipse.ui.actions.ReplaceableIconAction#getImageId()
	 */
	protected String getImageId() {
		return ISVNUIConstants.IMG_MENU_MOVE;
	}

}
