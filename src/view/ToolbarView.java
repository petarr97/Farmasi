/***********************************************************************
 * Module:  ToolbarView.java
 * Author:  Petar
 * Purpose: Defines the Class ToolbarView
 ***********************************************************************/

package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controler.ToolbarControler;

public class ToolbarView extends JPanel {

	JButton add = null;
	JButton edit = null;
	JButton delete = null;
	JButton prev = null;
	JButton next = null;

	public ToolbarView() {
		setLayout(null);
		setLocation(new Point(40, 0));
		setPreferredSize(new Dimension(0, 40));
		setBackground(Color.decode("#98B4D4"));

		add = new JButton();
		add.setOpaque(false);
		add.setContentAreaFilled(false);
		add.setBorderPainted(false);
		add.setIcon(new ImageIcon("./icons/add.png"));
		add.setBorder(BorderFactory.createEmptyBorder());
		// add.addActionListener(new ToolbarControler(this));
		add.setActionCommand("addRow");
		add.setLocation(250, 0);
		add.setSize(new Dimension(35, 35));
		add(add);

		edit = new JButton();
		edit.setOpaque(false);
		edit.setContentAreaFilled(false);
		edit.setBorderPainted(false);
		edit.setIcon(new ImageIcon("./icons/edit.png"));
		edit.setBorder(BorderFactory.createEmptyBorder());
		edit.setLocation(290, 0);
		edit.setActionCommand("editRow");
		// edit.addActionListener(new ToolbarControler(this));
		edit.setSize(new Dimension(35, 35));
		add(edit);

		delete = new JButton();
		delete.setOpaque(false);
		delete.setContentAreaFilled(false);
		delete.setBorderPainted(false);
		delete.setIcon(new ImageIcon("./icons/delete.png"));
		delete.setBorder(BorderFactory.createEmptyBorder());
		delete.setLocation(330, 0);
		// delete.addActionListener(new ToolbarControler(this));
		delete.setActionCommand("deleteRow");
		delete.setSize(new Dimension(35, 35));
		add(delete);

		prev = new JButton();
		prev.setOpaque(false);
		prev.setContentAreaFilled(false);
		prev.setBorderPainted(false);
		prev.setIcon(new ImageIcon("./icons/prev.png"));
		prev.setBorder(BorderFactory.createEmptyBorder());
		// prev.addActionListener(new ToolbarControler(this));
		prev.setActionCommand("prev");
		prev.setLocation(370, 0);
		prev.setSize(new Dimension(35, 35));
		add(prev);

		next = new JButton();
		next.setOpaque(false);
		next.setContentAreaFilled(false);
		next.setBorderPainted(false);
		next.setIcon(new ImageIcon("./icons/next.png"));
		next.setBorder(BorderFactory.createEmptyBorder());
		next.setLocation(410, 0);
		// next.addActionListener(new ToolbarControler(this));
		next.setActionCommand("next");
		next.setSize(new Dimension(35, 35));
		add(next);

	}

	public void dodajListenere() {
		for (Component component : getComponents()) {
			if (component instanceof JButton)
				((JButton) component).addActionListener(new ToolbarControler(this));
		}
	}

}