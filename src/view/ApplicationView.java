/***********************************************************************
 * Module:  ApplicationView.java
 * Author:  Petar
 * Purpose: Defines the Class ApplicationView
 ***********************************************************************/

package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

import model.Korisnik;
import toolbarpackage.toolbarOff;

public class ApplicationView extends JFrame {

	public ToolbarView toolbarView;
	public TableBrowserView tableBrowserView;
	public LogInView logInView;
	public TableView centerView;
	public StatusBar infoPanel;
	public toolbarpackage.toolbarState state;

	public void setState(toolbarpackage.toolbarState state) {
		this.state = state;
	}

	public ApplicationView() {

		setLayout(new BorderLayout());
		setTitle("Farmasi aplikacija");
		setBackground(Color.decode("#E9FDFF"));

		toolbarView = new ToolbarView();
		centerView = new TableView();
		tableBrowserView = new TableBrowserView();
		infoPanel = new StatusBar();
		infoPanel.postaviPodatke(Korisnik.getInstance().username);

		add(toolbarView, BorderLayout.NORTH);
		add(infoPanel, BorderLayout.SOUTH);
		add(tableBrowserView, BorderLayout.WEST);
		add(centerView, BorderLayout.CENTER);

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setState(new toolbarOff(this));
		toolbarView.dodajListenere();

	}

	public StatusBar getInfoPanel() {
		return infoPanel;
	}

	public void setInfoPanel(StatusBar infoPanel) {
		this.infoPanel = infoPanel;
	}

	public ToolbarView getToolbarView() {
		return toolbarView;
	}

	public void setToolbarView(ToolbarView toolbarView) {
		this.toolbarView = toolbarView;
	}

	public TableBrowserView getTableBrowserView() {
		return tableBrowserView;
	}

	public void setTableBrowserView(TableBrowserView tableBrowserView) {
		this.tableBrowserView = tableBrowserView;
	}

	public LogInView getLogInView() {
		return logInView;
	}

	public void setLogInView(LogInView logInView) {
		this.logInView = logInView;
	}

	public TableView getCenterView() {
		return centerView;
	}

	public void setCenterView(TableView centerView) {
		this.centerView = centerView;
	}

}