package br.com.otimizacaorede.model;
import java.util.ArrayList;


public class ResultadoKruskal {
    private ArrayList<Aresta> arestasSelecionadas;
    private int custoTotal;
    private int componentesRestantes;

    public ResultadoKruskal(ArrayList<Aresta> arestasSelecionadas, int custoTotal, int componentesRestantes) {

        if (arestasSelecionadas == null) {
            throw new IllegalArgumentException("A lista de conexões não pode ser nula.");
        }

        if (componentesRestantes < 0) {
            throw new IllegalArgumentException("A quantidade de componentes não pode ser negativa.");
        }

        this.arestasSelecionadas = new ArrayList<>(arestasSelecionadas);
        this.custoTotal = custoTotal;
        this.componentesRestantes = componentesRestantes;
    }

    public ArrayList<Aresta> getArestasSelecionadas() {
        return new ArrayList<>(arestasSelecionadas);
    }

    public int getCustoTotal() {
        return custoTotal;
    }

    public int getComponentesRestantes() {
        return componentesRestantes;
    }

    public boolean ehArvoreGeradora() {
        return componentesRestantes <= 1;
    }
}
