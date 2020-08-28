/***********************************************************************
 * Module:  State.java
 * Author:  Petar
 * Purpose: Defines the Interface State
 ***********************************************************************/

package state;

import view.ApplicationView;

public abstract class State {
	public ApplicationView appView = null;

	public void setMenuBar() {
		// TODO: implement
	}

	public void setToolBar() {
		// TODO: implement
	}

	public void setStatusBar() {
	}

	public void setTableView() {

	}
}