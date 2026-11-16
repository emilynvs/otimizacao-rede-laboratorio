package br.com.otimizacaorede.view;

import br.com.otimizacaorede.dao.GrafoDAO;
import br.com.otimizacaorede.model.ResultadoKruskal;
import br.com.otimizacaorede.service.KruskalService;

import java.util.Scanner;

public class EntradaView {

    private Scanner scanner;

    public EntradaView() {
        scanner = new Scanner(System.in);
    }

    public void iniciar() {

        ResultadoView resultadoView = new ResultadoView();

        mostrarTitulo();

        GrafoDAO grafoDAO = new GrafoDAO();

        int quantidadeComputadores = lerQuantidade(
                "Quantidade de computadores: ", 1);

        for (int i = 1; i <= quantidadeComputadores; i++) {

            while (true) {
                try {
                    grafoDAO.adicionarComputador(
                            lerTexto("Computador " + i + ": "));
                    break;

                } catch (IllegalArgumentException erro) {
                    mostrarMensagem(erro.getMessage());
                }
            }
        }

        int quantidadeConexoes = lerQuantidade(
                "Quantidade de conexões possíveis: ", 0);

        for (int i = 1; i <= quantidadeConexoes; i++) {

            while (true) {

                String origem = lerTexto(
                        "Conexão " + i + " - computador de origem: ");

                String destino = lerTexto(
                        "Conexão " + i + " - computador de destino: ");

                int custo = lerQuantidade(
                        "Custo da conexão: ", 0);

                try {
                    grafoDAO.adicionarAresta(origem, destino, custo);
                    break;

                } catch (IllegalArgumentException erro) {
                    mostrarMensagem(erro.getMessage());
                }
            }
        }

        KruskalService servico = new KruskalService();

        ResultadoKruskal resultado =
                servico.encontrarArvoreMinima(grafoDAO);

        resultadoView.exibir(resultado);
    }

    public void mostrarTitulo() {
        System.out.println("=== Otimização da rede do laboratório ===");
    }

    public String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    public int lerQuantidade(String mensagem, int minimo) {

        while (true) {
            try {
                int valor = Integer.parseInt(lerTexto(mensagem));

                if (valor >= minimo) {
                    return valor;
                }

                mostrarMensagem(
                        "Informe um valor maior ou igual a " + minimo + ".");

            } catch (NumberFormatException erro) {
                mostrarMensagem("Digite um número inteiro válido.");
            }
        }
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}
