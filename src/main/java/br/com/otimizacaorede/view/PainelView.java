package br.com.otimizacaorede.view;

import br.com.otimizacaorede.model.Aresta;
import br.com.otimizacaorede.model.Computador;
import br.com.otimizacaorede.model.ResultadoKruskal;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class PainelView extends JPanel {
    private ResultadoKruskal resultado;
    private List<Computador> computadores;
    private Map<String, Point> posicoes;

    public PainelView(ResultadoKruskal resultado, List<Computador> computadores) {
        this.resultado = resultado;
        this.computadores = computadores;
        this.posicoes = new HashMap<>();
        List<Computador> listaOrdenada = ordenarPorConexao(computadores, resultado.getArestasSelecionadas());
        gerarPosicoesDinamicas(listaOrdenada.size(), listaOrdenada);
    }

    private void gerarPosicoesDinamicas(int n, List<Computador> listaOrdenada) {
        if (n == 0) return;

        int centroX = 220;
        int centroY = 200;
        int raioDoLayout = 140;

        for (int i = 0; i < n; i++) {
            Computador pc = listaOrdenada.get(i);

            double angulo = 2 * Math.PI * i / n;

            int x = (int) (centroX + raioDoLayout * Math.cos(angulo));
            int y = (int) (centroY + raioDoLayout * Math.sin(angulo));

            posicoes.put(pc.getIdentificacao(), new Point(x, y));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int raioNo = 40;

        g2d.setStroke(new BasicStroke(2));
        if (resultado.getArestasSelecionadas() != null) {
            for (Aresta aresta : resultado.getArestasSelecionadas()) {
                Point p1 = posicoes.get(aresta.getOrigem().getIdentificacao());
                Point p2 = posicoes.get(aresta.getDestino().getIdentificacao());

                if (p1 != null && p2 != null) {
                    g2d.setColor(Color.BLACK);
                    g2d.drawLine(p1.x + raioNo / 2, p1.y + raioNo / 2, p2.x + raioNo / 2, p2.y + raioNo / 2);

                    g2d.setColor(Color.BLACK);
                    g2d.setFont(new Font("Arial", Font.BOLD, 14));
                    g2d.drawString("Custo: " + aresta.getCusto(), (p1.x + p2.x) / 2 - 10, (p1.y + p2.y) / 2 - 10);
                }
            }
        }

        for (Computador pc : computadores) {
            Point p = posicoes.get(pc.getIdentificacao());
            if (p == null) continue;

            boolean conectado = resultado.getArestasSelecionadas().stream().anyMatch(a ->
                    a.getOrigem().getIdentificacao().equals(pc.getIdentificacao()) ||
                            a.getDestino().getIdentificacao().equals(pc.getIdentificacao())
            );

            g2d.setColor(conectado ? Color.GREEN : Color.LIGHT_GRAY);
            g2d.fillOval(p.x, p.y, raioNo, raioNo);

            g2d.setColor(Color.BLACK);
            g2d.drawOval(p.x, p.y, raioNo, raioNo);

            g2d.drawString(pc.getIdentificacao(), p.x + raioNo / 2 - g2d.getFontMetrics().stringWidth(pc.getIdentificacao()) / 2, p.y + raioNo / 2 + 5);
        }
    }

    private List<Computador> ordenarPorConexao(List<Computador> nos, List<Aresta> arestas){
        if(nos.isEmpty() || arestas == null) return nos;

        Map<String, List<String>> adj = new HashMap<>();

        for(Computador pc: nos) {
            adj.put(pc.getIdentificacao(), new ArrayList<>());
        }

        for(Aresta a : arestas){
            String origem = a.getOrigem().getIdentificacao();
            String destino = a.getDestino().getIdentificacao();
            if(adj.containsKey(origem) && adj.containsKey(destino)){
                adj.get(origem).add(destino);
                adj.get(destino).add(origem);
            }
        }

        Map<String, Computador> porId = new HashMap<>();
        for (Computador pc : nos){
            porId.put(pc.getIdentificacao(), pc);
        }

        Set<String> visitados = new HashSet<>();
        List<Computador> ordenados = new ArrayList<>();

        for(Computador pc : nos){
            if(!visitados.contains(pc.getIdentificacao())){
                buscarEmProfundidade(pc.getIdentificacao(), adj, visitados, ordenados, porId);
            }
        }
        return ordenados;
    }

    private void buscarEmProfundidade(String atual, Map<String, List<String>> adj, Set<String> visitados, List<Computador> ordenados, Map<String, Computador> porId){
        visitados.add(atual);
        ordenados.add(porId.get(atual));
        for(String vizinho : adj.get(atual)){
            if(!visitados.contains(vizinho)){
                buscarEmProfundidade(vizinho, adj, visitados, ordenados, porId);
            }
        }
    }
}
