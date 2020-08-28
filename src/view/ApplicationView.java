/***********************************************************************
 * Module:  ApplicationView.java
 * Author:  Petar
 * Purpose: Defines the Class ApplicationView
 ***********************************************************************/

package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

import model.AplicationModel;
import model.Korisnik;
import state.ReadyState;

public class ApplicationView extends JFrame {

	public MenuBarView menuBarView;
	public ToolbarView toolbarView;
	public TableBrowserView tableBrowserView;
	public LogInView logInView;
	public TableView centerView;
	public AplicationModel aplicationModel;
	public StatusBar infoPanel;
	public state.State state;

	public void setState(state.State state) {
		this.state = state;
	}

	public ApplicationView() {

		setLayout(new BorderLayout());
		setTitle("Aplikacija za upravljanje smještajnim jedinicama");
		setBackground(Color.decode("#E9FDFF"));

		toolbarView = new ToolbarView();
		centerView = new TableView();
		tableBrowserView = new TableBrowserView();
		infoPanel = new StatusBar();
		infoPanel.postaviPodatke(Korisnik.getInstance().username);
		menuBarView = new MenuBarView();

		add(toolbarView, BorderLayout.NORTH);
		add(infoPanel, BorderLayout.SOUTH);
		add(tableBrowserView, BorderLayout.WEST);
		add(centerView, BorderLayout.CENTER);

		setJMenuBar(menuBarView);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setState(new ReadyState(this));
		menuBarView.PostaviActionListenere();
		toolbarView.dodajListenere();

	}

	public StatusBar getInfoPanel() {
		return infoPanel;
	}

	public void setInfoPanel(StatusBar infoPanel) {
		this.infoPanel = infoPanel;
	}

	public MenuBarView getMenuBarView() {
		return menuBarView;
	}

	public void setMenuBarView(MenuBarView menuBarView) {
		this.menuBarView = menuBarView;
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

	public AplicationModel getAplicationModel() {
		return aplicationModel;
	}

	public void setAplicationModel(AplicationModel aplicationModel) {
		this.aplicationModel = aplicationModel;
	}

}