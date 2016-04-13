/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peepingtom;

import java.util.ArrayList;

/**
 *
 * @author aluno
 */
public class Pacote {
    private ArrayList<Job> jobList;
    private int capacidade;
    
    public Pacote(int _capacidade) {
        capacidade = _capacidade;
        jobList = new ArrayList<>(capacidade);
    }
    
    public Pacote(Pacote _pacote) {
        jobList = new ArrayList<>(_pacote.jobList);
        capacidade = _pacote.capacidade;
    }
    
    /* Adiciona um Job à lista de jobs.
        Retorna true caso tenha adicionado com sucesso.
        Retorna false caso não caiba mais no pacote. */
    public synchronized boolean addJob(Job _job) {
        if (jobList.size() < capacidade) {
            jobList.add(_job);
            return true;
        } else {
            return false;
        }
    }
    //Magica que eu não entendi
    public synchronized ArrayList<Job> getAndClear() {
        ArrayList<Job> oldList = jobList;
        jobList = new ArrayList<>(capacidade);
        
        return oldList;
    }
    
    public synchronized ArrayList<Job> getList() {
        return this.jobList;
    }
    
    public int getSize() {
        return jobList.size();
    }
    
    public Pacote clear(Pacote _pacote){
        _pacote.jobList.clear();
        return _pacote;
        
    }
    
    public synchronized boolean isfull(){
        if(jobList.size() == capacidade)
            return true;
        else
            return false;
        
    }
    public String getJobContent(Job _job) {
        return _job.getContent();
    }
}
