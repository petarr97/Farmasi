/***********************************************************************
 * Module:  AplicationModel.java
 * Author:  Petar
 * Purpose: Defines the Class AplicationModel
 ***********************************************************************/

package model;

import view.ObserverInterface;
import java.util.*;

import state.State;

public class AplicationModel {
   public TableBrowserModel tableBrowserModel;
   public Korisnik korisnik;
   public State state;
   public java.util.Collection<ObserverInterface> observerInterface;
   public java.util.Collection<FinansijskiSektor> finansijskiSektor;
   
   
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
   /** @pdGenerated default getter */
   public java.util.Collection<FinansijskiSektor> getFinansijskiSektor() {
      if (finansijskiSektor == null)
         finansijskiSektor = new java.util.HashSet<FinansijskiSektor>();
      return finansijskiSektor;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorFinansijskiSektor() {
      if (finansijskiSektor == null)
         finansijskiSektor = new java.util.HashSet<FinansijskiSektor>();
      return finansijskiSektor.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newFinansijskiSektor */
   public void setFinansijskiSektor(java.util.Collection<FinansijskiSektor> newFinansijskiSektor) {
      removeAllFinansijskiSektor();
      for (java.util.Iterator iter = newFinansijskiSektor.iterator(); iter.hasNext();)
         addFinansijskiSektor((FinansijskiSektor)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newFinansijskiSektor */
   public void addFinansijskiSektor(FinansijskiSektor newFinansijskiSektor) {
      if (newFinansijskiSektor == null)
         return;
      if (this.finansijskiSektor == null)
         this.finansijskiSektor = new java.util.HashSet<FinansijskiSektor>();
      if (!this.finansijskiSektor.contains(newFinansijskiSektor))
         this.finansijskiSektor.add(newFinansijskiSektor);
   }
   
   /** @pdGenerated default remove
     * @param oldFinansijskiSektor */
   public void removeFinansijskiSektor(FinansijskiSektor oldFinansijskiSektor) {
      if (oldFinansijskiSektor == null)
         return;
      if (this.finansijskiSektor != null)
         if (this.finansijskiSektor.contains(oldFinansijskiSektor))
            this.finansijskiSektor.remove(oldFinansijskiSektor);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllFinansijskiSektor() {
      if (finansijskiSektor != null)
         finansijskiSektor.clear();
   }

}