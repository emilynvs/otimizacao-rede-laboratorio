package br.com.otimizacaorede.model;

public class Aresta {

        private Computador origem;
        private Computador destino;
        private int custo;

        public Aresta(Computador origem, Computador destino, int custo) {

            if (origem == null || destino == null) {
                throw new IllegalArgumentException("Os computadores não podem ser nulos.");
            }

            if (custo < 0) {
                throw new IllegalArgumentException("O custo não pode ser negativo.");
            }

            this.origem = origem;
            this.destino = destino;
            this.custo = custo;
        }

        public Computador getOrigem() {
            return origem;
        }

        public Computador getDestino() {
            return destino;
        }

        public int getCusto() {
            return custo;
        }
    }


