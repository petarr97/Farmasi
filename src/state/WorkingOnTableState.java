/***********************************************************************
 * Module:  EditingTableState.java
 * Author:  Petar
 * Purpose: Defines the Class EditingTableState
 ***********************************************************************/

package state;

import view.ApplicationView;

public class WorkingOnTableState extends State {

	public ApplicationView appView = null;

	public WorkingOnTableState(ApplicationView appView) {
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