package br.com.otimizacaorede.service;

public class GruposDeComputadoresService {

    private int[] pai;

    public GruposDeComputadoresService(int quantidade) {

        if(quantidade < 0) {
            throw new IllegalArgumentException("A quantidade de computadores não pode ser negativa.");
        }

        pai = new int[quantidade];

        for(int i = 0; i < quantidade; i++){
            pai[i] = i;
        }
    }

    public int encontrar(int computador) {
        if(computador < 0 || computador >= pai.length) {
            throw new IllegalArgumentException("Índice do computador inválido.");
        }

        if(pai[computador] != computador){
            pai[computador] = encontrar(pai[computador]);
        }
        return pai[computador];
    }

    public boolean unir(int computadorUm, int computadorDois){
        int grupoA = encontrar(computadorUm);
        int grupoB = encontrar(computadorDois);

        if(grupoA == grupoB){
            return false;
        }

        pai[grupoB] = grupoA;

        return true;
    }
}
