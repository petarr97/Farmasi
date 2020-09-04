/***********************************************************************
 * Module:  EditingTableState.java
 * Author:  Petar
 * Purpose: Defines the Class EditingTableState
 ***********************************************************************/

package toolbarpackage;

import view.ApplicationView;

public class toolbarOn extends toolbarState {

	public ApplicationView appView = null;

	public toolbarOn(ApplicationView appView) {
		this.appView = appView;
		setToolBar();
		setStatusBar();
		setMenuBar();
	}

	@Override
	public void setToolBar() {
		this.appView.getToolbarView().show();
	}

}