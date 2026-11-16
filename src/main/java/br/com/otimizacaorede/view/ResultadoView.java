package br.com.otimizacaorede.view;

import br.com.otimizacaorede.model.Aresta;
import br.com.otimizacaorede.model.ResultadoKruskal;

public class ResultadoView {
    public void exibir(ResultadoKruskal resultado) {
        System.out.println("\n=== Resultado da conexão dos computadores ===");

        if (resultado.getArestasSelecionadas().isEmpty()) {
            System.out.println("Nenhuma conexão foi selecionada.");
        } else {
            System.out.printf("%-20s %s%n", "Conexão", "Custo");
            for (Aresta aresta : resultado.getArestasSelecionadas()) {
                String conexao = aresta.getOrigem().getIdentificacao() + " - "
                        + aresta.getDestino().getIdentificacao();
                System.out.printf("%-20s %d%n", conexao, aresta.getCusto());
            }
        }

        System.out.println("Custo total: " + resultado.getCustoTotal());
        mostrarSituacaoDaRede(resultado);
    }

    private void mostrarSituacaoDaRede(ResultadoKruskal resultado) {
        if (resultado.ehArvoreGeradora()) {
            System.out.println("Todos os computadores estão conectados.");
        } else {
            System.out.println("Aviso: nem todos os computadores puderam ser conectados.");
            System.out.println("Componentes separados: " + resultado.getComponentesRestantes());
        }
    }
}

