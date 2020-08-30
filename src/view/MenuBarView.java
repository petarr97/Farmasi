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
		JMenu[] menuBarStavke = new JMenu[1];

		menuBarStavke[0] = new JMenu("Navigacija");
		menuBarStavke[0].setMnemonic(KeyEvent.VK_E);
		postaviView(menuBarStavke[0]);

		return menuBarStavke;
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