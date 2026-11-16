package br.com.otimizacaorede.model;

public class Computador {
    private String identificacao;

    public Computador(String identificacao) {

        if (identificacao == null || identificacao.trim().isEmpty()) {
            throw new IllegalArgumentException("A identificação não pode ficar vazia.");
        }

        this.identificacao = identificacao.trim();
    }

    public String getIdentificacao() {
        return identificacao;
    }

    @Override
    public String toString() {
        return identificacao;
    }
}
