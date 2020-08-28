/***********************************************************************
 * Module:  MenuBarView.java
 * Author:  Petar
 * Purpose: Defines the Class MenuBarView
 ***********************************************************************/

package view;

import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controler.ReportsControler;
import controler.ToolbarControler;

public class MenuBarView extends JMenuBar {

	JMenu[] menuBarStavke;

	public JMenuItem next;
	public JMenuItem prev;
	public JMenuItem close = new JMenuItem();
	public JMenuItem exit = new JMenuItem();

	public MenuBarView() {
		setBackground(Color.decode("#98B4D4"));
		menuBarStavke = postavi_MenuBar();

		for (int i = 0; i < menuBarStavke.length; i++) {
			add(menuBarStavke[i]);
		}
	}

	public JMenu[] postavi_MenuBar() {
		JMenu[] menuBarStavke = new JMenu[2];

		menuBarStavke[0] = new JMenu("Navigacija");
		menuBarStavke[0].setMnemonic(KeyEvent.VK_E);
		postaviView(menuBarStavke[0]);

		menuBarStavke[1] = new JMenu("Izvjestaji");
		menuBarStavke[1].setMnemonic(KeyEvent.VK_V);
		postaviReports(menuBarStavke[1]);

		return menuBarStavke;
	}

	public void postaviReports(JMenu Reports) {
		JMenuItem finansije = new JMenuItem();
		finansije.setText("Finansije");
		finansije.setActionCommand("finansije");
		finansije.addActionListener(new ReportsControler());

		JMenuItem rezervacije = new JMenuItem();
		rezervacije.setText("Rezervacije");
		rezervacije.setActionCommand("rezervacije");
		rezervacije.addActionListener(new ReportsControler());

		JMenuItem sistemi = new JMenuItem();
		sistemi.setText("Poslovni sistemi");
		sistemi.setActionCommand("poslovni_sistemi");
		sistemi.addActionListener(new ReportsControler());

		Reports.add(finansije);
		Reports.add(rezervacije);
		Reports.add(sistemi);

	}

	public void postaviView(JMenu Navigacija) {
		next = new JMenuItem();
		next.setText("Sledeci");
		next.setIcon(new ImageIcon("./icons/next.png"));
		next.setActionCommand("next");

		prev = new JMenuItem();
		prev.setText("Prethodni");
		prev.setIcon(new ImageIcon("./icons/prev.png"));
		prev.setActionCommand("prev");

		Navigacija.add(prev);
		Navigacija.add(next);

	}

	public void PostaviActionListenere() {
		next.addActionListener(new ToolbarControler(this));
		prev.addActionListener(new ToolbarControler(this));
	}

}