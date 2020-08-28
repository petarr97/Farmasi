/***********************************************************************
 * Module:  ObserverInterface.java
 * Author:  Petar
 * Purpose: Defines the Interface ObserverInterface
 ***********************************************************************/

package view;

import java.awt.Component;

public interface ObserverInterface {
	void updateAll();

	void update();

	void subscribe(Component component);

}