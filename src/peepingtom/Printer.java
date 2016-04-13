/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peepingtom;


/**
 *
 * @author cafrazao
 */
public class Printer{
	private int limiteImpressoes;
	private Pacote pacote;

	public Printer(int _limiteImpressoes){
            limiteImpressoes = _limiteImpressoes;
	}
        
	public boolean print(Pacote _pacote) throws InterruptedException{
            if (isEnded()) {
                System.out.println("Impressora sem limite de impressões, encerrando o dia");
                return true;
            }

            //Dezugb
            printList(_pacote);
            //The printer is printing....
            Thread.sleep(5000);
            limiteImpressoes--;
            return false;
	}
        
        public boolean isEnded(){
            if (limiteImpressoes == 0) {
                System.out.println("Impressora sem limite de impressões, encerrando o dia");
                return true;
            }
            return false;
        }

    private void printList(Pacote _pacote) {
        for(Job j:_pacote.getList())
            System.out.println("Imprimindo Trabalho" + j.getContent());
    }

    
}
