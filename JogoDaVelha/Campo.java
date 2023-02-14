/*
 * Created by: Vítor Silva Pastor Gonzalez
 * Last update: 14/02/2023
 */

package Projetos.JogoDaVelha;

public class Campo {

     private char simbolo;

     public Campo() {
        this.simbolo = ' ';
     }

     public char getSimbolo() {
        return this.simbolo;
     }

     public void setSimbolo(char simbolo) {
        if(this.simbolo == ' ') {
            this.simbolo = simbolo;
        } else {
            System.out.print("Campo já utilizado!");
        }
     }
}