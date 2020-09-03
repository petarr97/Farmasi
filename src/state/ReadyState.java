/***********************************************************************
 * Module:  ReadyState.java
 * Author:  Petar
 * Purpose: Defines the Class ReadyState
 ***********************************************************************/

package state;

import view.ApplicationView;

public class ReadyState extends State {

	public ReadyState(ApplicationView appView) {
		this.appView = appView;

		setTableView();
		setStatusBar();
		setToolBar();
		setMenuBar();
	}

	@Override
	public void setToolBar() {
		this.appView.getToolbarView().hide();

	}

	@Override
	public void setTableView() {
		this.appView.centerView.removeAll();
		this.appView.centerView.revalidate();
		this.appView.centerView.repaint();
	}

}