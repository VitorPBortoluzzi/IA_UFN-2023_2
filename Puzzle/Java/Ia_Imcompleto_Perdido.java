
package IA;

import busca.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Puzzle implements Estado{
    
    @Override
    public String getDescricao() {
        return "Problema do Puzzle NxN";
    }
    
    final int matriz[][];
    public int dimensao;
    public int linha;
    public int coluna;
    public String Op;
    
    public int[][]clonar(){
        int matrizTmp[][] = new int[this.dimensao][this.dimensao];
        
        for (int i = 0; i < this.dimensao; i++) {
            for (int j = 0; j < this.dimensao; j++) {
                matrizTmp[i][j] = this.matriz[i][j];
            }
        }
        return matrizTmp;
    }
    
    public Puzzle(int m[][],int linha, int coluna, String Op){
        this.matriz = m;
        this.linha = linha;
        this.coluna = coluna;
        this.Op = Op;
        this.dimensao = m.length;
    }
    
    @Override
    public boolean ehMeta() {
        int posicao =0;
        for (int i = 0; i < this.dimensao; i++) {
            for (int j = 0; j < this.dimensao; j++) {
                if(this.matriz[i][j] != posicao){
                    return false;
                }
                posicao ++;
            }
            
        }
        return true;
    }
    
    

    @Override
    public int custo() {
        return 1;
    }

    @Override
    public List<Estado> sucessores() {        
        List<Estado> visitados = new LinkedList<Estado>();
        
        cima(visitados);
        baixo(visitados);
        esquerda(visitados);
        direita(visitados);
        
        return visitados;
    }
    
    
    
    public void cima(List<Estado> visitados){
        if(this.linha == 0) return;
        
        int matrizTmp[][];
        matrizTmp = this.clonar();
        
        matrizTmp[this.linha][this.coluna] = matrizTmp[this.linha-1][this.coluna];
        matrizTmp[this.linha-1][this.coluna] = 0;
        
        Puzzle novo = new Puzzle(matrizTmp,this.linha-1,this.coluna,"Indo para cima");
        
        if(!visitados.contains(novo)){
            visitados.add(novo);
        }else{
            System.gc();
        }
    }
    
    public void baixo(List<Estado> visitados){
        if(this.linha == this.dimensao-1) return;
        
        int matrizTmp[][];
        matrizTmp = this.clonar();
        
        matrizTmp[this.linha][this.coluna] = matrizTmp[this.linha+1][this.coluna];
        matrizTmp[this.linha+1][this.coluna] = 0;
        
        Puzzle novo = new Puzzle(matrizTmp,this.linha+1,this.coluna,"Indo para baixo");
        
        if(!visitados.contains(novo)){
            visitados.add(novo);
        }else{
            System.gc();
        }
    }
    
    public void esquerda(List<Estado> visitados){
        if(this.coluna == 0) return;
        
        int matrizTmp[][];
        matrizTmp = this.clonar();
        
        matrizTmp[this.linha][this.coluna] = matrizTmp[this.linha][this.coluna-1];
        matrizTmp[this.linha][this.coluna-1] = 0;
        
        Puzzle novo = new Puzzle(matrizTmp,this.linha,this.coluna-1,"Indo para Esquerda");
        
        if(!visitados.contains(novo)){
            visitados.add(novo);
        }else{
            System.gc();
        }
    }
    
    public void direita(List<Estado> visitados){
        if(this.coluna == this.dimensao-1) return;
        
        int matrizTmp[][];
        matrizTmp = this.clonar();
        
        matrizTmp[this.linha][this.coluna] = matrizTmp[this.linha][this.coluna+1];
        matrizTmp[this.linha][this.coluna+1] = 0;
        
        Puzzle novo = new Puzzle(matrizTmp,this.linha,this.coluna+1,"Indo para direita");
        
        if(!visitados.contains(novo)){
            visitados.add(novo);
        }else{
            System.gc();
        }
    }
    
     /**
     * verifica se um estado e igual a outro
     * (usado para poda)
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Puzzle) {
            Puzzle e = (Puzzle)o;
            for (int i = 0; i < this.dimensao; i++) {
                for (int j = 0; j < this.dimensao; j++) {
                    if(e.matriz[i][j] != this.matriz[i][j]){
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuffer resultado = new StringBuffer();
        for (int i = 0; i < this.dimensao; i++) {
            for (int j = 0; j < this.dimensao; j++) {
                resultado.append(this.matriz[i][j] + "\t");
            }
            resultado.append("\n");
        }
        return "\n"+ Op + "\n" + resultado + "\n\n";
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
    
}
