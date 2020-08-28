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
	public void setMenuBar() {
		this.appView.menuBarView.next.setEnabled(false);
		this.appView.menuBarView.prev.setEnabled(false);
	}

	@Override
	public void setToolBar() {
		this.appView.getToolbarView().hide();

	}

	@Override
	public void setStatusBar() {
		this.appView.infoPanel.rows.setText("");
		this.appView.infoPanel.stanje.setText("Ready State");
	}

	@Override
	public void setTableView() {
		this.appView.centerView.removeAll();
		this.appView.centerView.revalidate();
		this.appView.centerView.repaint();
	}

}