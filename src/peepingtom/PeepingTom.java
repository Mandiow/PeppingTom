/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peepingtom;


/**
 *
 * @author aluno
 */
public class PeepingTom {

    //I can just make a new with the args, but let's do this 1st    
    
    /**
     * @param args the command line arguments
     * args[0] = Clients
     * args[1] = Package Size
     * args[2] = Max Prints
     */
    public static void main(String[] args) {
        
        //Instanciate Manager
        PrintManager printmanager;
        
        //Initialize all the things
        printmanager = new PrintManager(Integer.parseInt(args[0]),
                    Integer.parseInt(args[1]),Integer.parseInt(args[2]));
        
        //GO FOR IT MAN!
        printmanager.start();
        
        
        
        
    }
    
}
