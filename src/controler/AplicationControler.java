/***********************************************************************
 * Module:  AplicationControler.java
 * Author:  Petar
 * Purpose: Defines the Class AplicationControler
 ***********************************************************************/

package controler;

import model.AplicationModel;
import view.ObserverInterface;
import java.util.*;

public class AplicationControler {
   public AplicationModel aplicationModel;
   public java.util.Collection<ObserverInterface> observerInterface;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<ObserverInterface> getObserverInterface() {
      if (observerInterface == null)
         observerInterface = new java.util.HashSet<ObserverInterface>();
      return observerInterface;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorObserverInterface() {
      if (observerInterface == null)
         observerInterface = new java.util.HashSet<ObserverInterface>();
      return observerInterface.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newObserverInterface */
   public void setObserverInterface(java.util.Collection<ObserverInterface> newObserverInterface) {
      removeAllObserverInterface();
      for (java.util.Iterator iter = newObserverInterface.iterator(); iter.hasNext();)
         addObserverInterface((ObserverInterface)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newObserverInterface */
   public void addObserverInterface(ObserverInterface newObserverInterface) {
      if (newObserverInterface == null)
         return;
      if (this.observerInterface == null)
         this.observerInterface = new java.util.HashSet<ObserverInterface>();
      if (!this.observerInterface.contains(newObserverInterface))
         this.observerInterface.add(newObserverInterface);
   }
   
   /** @pdGenerated default remove
     * @param oldObserverInterface */
   public void removeObserverInterface(ObserverInterface oldObserverInterface) {
      if (oldObserverInterface == null)
         return;
      if (this.observerInterface != null)
         if (this.observerInterface.contains(oldObserverInterface))
            this.observerInterface.remove(oldObserverInterface);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllObserverInterface() {
      if (observerInterface != null)
         observerInterface.clear();
   }

}