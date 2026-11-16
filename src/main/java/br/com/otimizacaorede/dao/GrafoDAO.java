package br.com.otimizacaorede.dao;

import br.com.otimizacaorede.model.Aresta;
import br.com.otimizacaorede.model.Computador;

import java.util.ArrayList;

public class GrafoDAO {

    private ArrayList<Computador> computadores = new ArrayList<>();
    private ArrayList<Aresta> arestas = new ArrayList<>();

    public void adicionarComputador(String identificacao) {

        if (identificacao == null || identificacao.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "A identificação não pode ficar vazia.");
        }

        if (buscarComputador(identificacao) != null) {
            throw new IllegalArgumentException(
                    "Essa identificação já foi cadastrada.");
        }

        computadores.add(new Computador(identificacao.trim()));
    }

    public void adicionarAresta(String origem, String destino, int custo) {

        Computador a = buscarComputador(origem);
        Computador b = buscarComputador(destino);

        if (a == null || b == null) {
            throw new IllegalArgumentException(
                    "Informe computadores já cadastrados.");
        }

        if (a == b) {
            throw new IllegalArgumentException(
                    "Um computador não pode ser conectado a ele mesmo.");
        }

        for (Aresta aresta : arestas) {

            boolean mesmaConexao =
                    (aresta.getOrigem() == a && aresta.getDestino() == b)
                            || (aresta.getOrigem() == b && aresta.getDestino() == a);

            if (mesmaConexao) {
                throw new IllegalArgumentException(
                        "Essa conexão já foi cadastrada.");
            }
        }

        arestas.add(new Aresta(a, b, custo));
    }

    public Computador buscarComputador(String identificacao) {

        if (identificacao == null) {
            return null;
        }

        for (int i = 0; i < computadores.size(); i++) {

            if (computadores.get(i).getIdentificacao()
                    .equalsIgnoreCase(identificacao.trim())) {

                return computadores.get(i);
            }
        }

        return null;
    }

    public ArrayList<Computador> getComputadores() {
        return new ArrayList<>(computadores);
    }

    public ArrayList<Aresta> getArestas() {
        return new ArrayList<>(arestas);
    }
}
