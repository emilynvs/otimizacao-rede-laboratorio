package br.com.otimizacaorede.service;

import br.com.otimizacaorede.dao.GrafoDAO;
import br.com.otimizacaorede.model.Aresta;
import br.com.otimizacaorede.model.ResultadoKruskal;
import java.util.ArrayList;

public class KruskalService {

    public ResultadoKruskal encontrarArvoreMinima(GrafoDAO grafo) {

        if (grafo == null) {
            throw new IllegalArgumentException("O grafo não pode ser nulo.");
        }

        if (grafo.getComputadores().isEmpty()) {
            throw new IllegalArgumentException("Cadastre pelo menos um computador.");
        }

        ArrayList<Aresta> ordenadas = new ArrayList<Aresta>(grafo.getArestas());

        for (int i = 0; i < ordenadas.size(); i++) {
            for (int j = 0; j < ordenadas.size() - 1; j++) {

                if (ordenadas.get(j).getCusto() > ordenadas.get(j + 1).getCusto()) {

                    Aresta temp = ordenadas.get(j);
                    ordenadas.set(j, ordenadas.get(j + 1));
                    ordenadas.set(j + 1, temp);
                }
            }
        }

        ArrayList<Aresta> selecionadas = new ArrayList<Aresta>();

        GruposDeComputadoresService grupos = new GruposDeComputadoresService(grafo.getComputadores().size());

        int custoTotal = 0;

        for (int i = 0; i < ordenadas.size(); i++) {

            Aresta aresta = ordenadas.get(i);

            int origem = grafo.getComputadores().indexOf(aresta.getOrigem());
            int destino = grafo.getComputadores().indexOf(aresta.getDestino());

            if (origem == -1 || destino == -1) {
                throw new IllegalArgumentException("A conexão possui computador inválido.");
            }

            if (aresta.getCusto() < 0) {
                throw new IllegalArgumentException("O custo da conexão não pode ser negativo.");
            }

            if (grupos.unir(origem, destino)) {
                selecionadas.add(aresta);
                custoTotal += aresta.getCusto();
            }
        }

        int componentes = grafo.getComputadores().size() - selecionadas.size();

        return new ResultadoKruskal(selecionadas, custoTotal, componentes);
    }
}
