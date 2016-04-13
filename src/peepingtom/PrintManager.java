/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peepingtom;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aluno
 */
public class PrintManager extends Thread {
   private Pacote pacote;
   private Printer printer;
   private ArrayList<Job> jobs;
   private int clients;

    public PrintManager(int _clients,int TAMANHO_PACOTE, int _maxPrints) {
        pacote = new Pacote(TAMANHO_PACOTE);
        jobs = new ArrayList<>();
        clients = _clients;
        printer = new Printer(_maxPrints);
    }
    
   @Override
    public void run(){
        //Populating the ArrayList
        for(int i =0; i<clients; i++){
            jobs.add(new Job("CLIENTE" + i,this));
            System.out.println("Trabalho CRIADO " + jobs.get(i).getContent());
            
        }
        //Start all Clients
        /* A implementação, da forma que está, não gera race condition, ou qualquer outro problema de concorrência
         * primeiro que, java não possui parallel for nativo, segundo que, as threads são criadas e iniciadas de forma
         * sequencial. 
         * 
        */
        for(Thread t: jobs)
            t.start();
        
        while(!printer.isEnded()){
            //If it's full, send to the printer
            //When returns, unlock and unpack
            if(pacote.isfull())
            {
                try {
                    printer.print(pacote);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PrintManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                //Notifica as Threads que não conseguiram seu lugar na fila.             
                //Safe thing to do.
                pacote.getList().removeAll(jobs);
                //Notify all Clients.
                System.out.println("NOTIFY");
                unlock();
            } 
        }
    }
    
    public void addToPackage(Job _job) throws InterruptedException {
        // Adiciona o job ao pacote existente
        synchronized(pacote) {
            if (pacote.addJob(_job)) {
                System.out.println("Trabalho inserido " + _job.getContent());
                pacote.wait();
            }else{
                //Caso não consiga mais inserir, o pacote deve estar cheio, mandar Imprimir
                System.out.println("WAIT JOB" + _job.getContent());
                pacote.wait();
            }
        }
    }
    
    public void unlock(){
        synchronized(pacote){
            pacote.notifyAll();
        }
        
    }
    
    public Printer getPrinter() {
        return printer;
    }

}
