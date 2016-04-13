/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peepingtom;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aluno
 */
public class Job extends Thread {
    private String content;
    static PrintManager printManager; // All the jobs have the same print manager
    private boolean isIn;
    
    public Job(String _content, PrintManager _printManager) {
        content = _content;
        printManager = _printManager;
        isIn = false;
    }
    
    public String getContent() {
        return content;
    }
    
    public void run() { 
        try {
            //Ask to add
            while(!printManager.getPrinter().isEnded())
            {
                printManager.addToPackage(this);
            }
            System.out.println("Trabalho" + content + " MORREU");
        } catch (InterruptedException ex) {
            Logger.getLogger(Job.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
