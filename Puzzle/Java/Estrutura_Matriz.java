
package Matrix_nxN;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Matriz{
    public int matriz[][];
    public int dimensao;
    public int linha;
    public int coluna;

    public Matriz(int dimensao){
        this.matriz = new int[dimensao][dimensao];
        this.dimensao = dimensao;
    }
    
    public void Embaralhar(){
        ArrayList<Integer> lista = new ArrayList<Integer>();
        
        for (int i = 0; i < this.dimensao*this.dimensao; i++) {
            lista.add(i);
        }
        Collections.shuffle(lista);
        
        int posicao = 0;
        for (int i = 0; i < this.dimensao; i++) {
            for (int j = 0; j < this.dimensao; j++) {
                this.matriz[i][j] = lista.get(posicao);
                //posicao do 0
                if(this.matriz[i][j] == 0){
                    this.linha = i;
                    this.coluna = j;
                }
                
                posicao ++;
            }
        }
    }
    
    public void exibir(){
        //exibir Matriz
        for (int i = 0; i < this.dimensao; i++) {
            for (int j = 0; j < this.dimensao; j++) {
                System.out.print(this.matriz[i][j]+" ");
            }
            System.out.println("");
        }
        System.out.println("Posicao do 0:  "+this.linha+"," + this.coluna);
    }
    
    public int[][]clonar(){
        int matrizTmp[][] = new int[this.dimensao][this.dimensao];
        
        for (int i = 0; i < this.dimensao; i++) {
            for (int j = 0; j < this.dimensao; j++) {
                matrizTmp[i][j] = this.matriz[i][j];
            }
        }

        return matrizTmp;
    }
    
    public void cima(){
        if(this.linha == 0) return;
        this.matriz[linha][coluna] = this.matriz[linha-1][coluna];
        this.matriz[linha-1][coluna] = 0;
        this.linha--;
    }
    
    public void baixo(){
        if(this.linha == this.dimensao - 1) return;
        this.matriz[linha][coluna] = this.matriz[linha+1][coluna];
        this.matriz[linha+1][coluna] = 0;
        this.linha++;
    }
    
    public void esquerda(){
        if(this.coluna == 0) return;
        this.matriz[linha][coluna] = this.matriz[linha][coluna-1];
        this.matriz[linha][coluna-1]=0;
        this.coluna--;
    }
    
    public void direita(){
        if(this.coluna == this.dimensao - 1) return;
        this.matriz[linha][coluna] = this.matriz[linha][coluna+1];
        this.matriz[linha][coluna+1]=0;
        this.coluna++;
    }
    
}



public class Estrutura {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int dimensao;
        
        
        int matriz[][];
        
        ArrayList<Integer> lista = new ArrayList<Integer>();
        //descobrir dimensao matriz
        System.out.println("Dimensão da Matriz: ");
        dimensao = teclado.nextInt();
        
        Matriz m = new Matriz(dimensao);   
        
        m.Embaralhar();
        m.exibir();
        
//        int matrizOutra[][];
//        matrizOutra = m.clonar();

        m.cima();
        m.exibir();

        m.baixo();
        m.exibir();

        m.direita();
        m.exibir();

        m.esquerda();
        m.exibir();
    }
}
