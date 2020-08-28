/***********************************************************************
 * Module:  Command.java
 * Author:  Petar
 * Purpose: Defines the Interface Command
 ***********************************************************************/

package command;

import java.util.*;

public interface Command {
   void execute();
   void unexecute();

}