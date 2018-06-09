import graph.*;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.io.EdgeProvider;
import org.jgrapht.io.GmlImporter;
import org.jgrapht.io.VertexProvider;

import java.util.*;

/**
 * Classe responsável por execução da atividade de prática 2 da disciplina Teoria dos Grafos.
 *
 * @author Álex Micaela de Oliveira Fidelis
 * @author Júlia Fernandes Alves
 * @author Tiago Silva Araújo
 * @author Wendson Magalhães da Silva
 */
public class RedeGrafo {

    /**
     * Método principal para execução do Grafo (formato GML) representando uma rede de computadores.
     * E atenda os requisitos descritos abaixo:
     *
     * Um gerente de uma rede local de computadores necessita medir o tempo necessário para uma mensagem ser transmitida
     * de uma máquina para outra da rede através de uma conexão direta se existir. Para tal, o gerente enviará um
     * programa (agente móvel) a partir da máquina gerente. Este agente é um programa ativo que deverá percorrer todas
     * as conexões diretas entre duas máquina e registrar o tempo gasto para percorrer cada conexão. A fim de minimizar
     * o tráfego na rede, cada conexão deve ser percorrida uma única vez e o agente só retorna à máquina gerente após
     * percorrer todas as conexões.
     *
     * O que atende é um Circuito Euleriano.
     *
     * @param args Não existem parâmetros disponíveis.
     */
    public static void main(String[] args){
        VertexProvider<DefaultVertex> fornecedorVertices = (label, attributes) -> new DefaultVertex (label,attributes);
        EdgeProvider<DefaultVertex, DefaultEdge> fornecedorArestas = (from,to,label,attributes) -> new DefaultEdge();
        GmlImporter<DefaultVertex,DefaultEdge> importadorGML = new GmlImporter <> (fornecedorVertices, fornecedorArestas);
        SimpleGraph<DefaultVertex, DefaultEdge> grafo = new SimpleGraph<>(DefaultEdge.class);

        /**
         * Importa o grafo no formato arquivo GML.
         */
        try {
            String caminhoArquivo = "/Volumes/Arquivos/workspace/grafo-rede/rede.gml";
            importadorGML.importGraph(grafo, ImportGraph.readFile(caminhoArquivo));
        } catch (Exception excecao) {
            throw new RuntimeException("Não foi possível ler o grafo GML. " + excecao.getMessage());
        }

        Set<DefaultVertex> vertices = new HashSet<>(grafo.vertexSet());
        Set<DefaultEdge> arestas = new HashSet<>(grafo.edgeSet());

        /**
         * Estrutura do Grafo importado.
         */
        System.out.println("::: Estrutura do Grafo GML Importado :::");
        System.out.println("Ordem do Grafo G: " + vertices.size());
        System.out.println("Tamanho do Grafo G: " + arestas.size());
        System.out.println("------");
        System.out.println("V(G): " + vertices);
        System.out.println("E(G): " + arestas.toString());
        System.out.println("------");

        if(verificaCircuitoEuleriano(grafo)){
            DefaultVertex verticeGerencia = DefaultVertexSet.getVertexfromLabel(vertices, "C");
            System.out.println("Rota da Máquina Gerente(C) - Circuito Euleriano: ");
            System.out.println(obtemCircuitoEuler(grafo, verticeGerencia));
        }else {
            System.out.println("Não é um Ciclo Euleriano.");
        }
    }

    /**
     * Verifica se atende os pré-requisitos de ser um Circuito Euleriano.
     * Se for par e se é conectado.
     *
     * @param grafo Grafo simple.
     * @return Retorna true caso o grafo seja par e conectado.
     */
    private static boolean verificaCircuitoEuleriano(Graph grafo){
        return verificarConectado(grafo) && verificarPar(grafo);
    }

    /**
     * Verifica se um grafo é conectado.
     *
     * @param grafo Grafo
     * @return Retorna true caso o grafo seja conectado.
     */
    private static boolean verificarConectado(Graph grafo){
        ConnectivityInspector conectividade = new ConnectivityInspector<>(grafo);
        return conectividade.isConnected();
    }

    /**
     * Verifica se o grafo é par.
     *
     * @param grafo Grafo a ser verificado.
     * @return Retorna true caso o grafo seja par.
     */
    private static boolean verificarPar(Graph grafo){
        Set<DefaultVertex> vertices = grafo.vertexSet();

        for(DefaultVertex vertice: vertices)
            if(grafo.degreeOf(vertice) % 2 != 0) return false;

        return true;
    }

    /**
     * Retorna um Circuito Euleriano através do Algoritmo de Fleury.
     *
     * @param grafo Grafo a ser analisado.
     * @param vertice Vértice a ser iniciado e finalizado.
     * @return Retorna o circuito Euleriano.
     */
    private static List obtemCircuitoEuler(Graph grafo, DefaultVertex vertice){
        Graph<DefaultVertex, DefaultEdge> subgrafo = new SimpleGraph<>(DefaultEdge.class);
        Graphs.addGraph(subgrafo, grafo);

        List<DefaultEdge> circuito = new ArrayList<>();
        Set<DefaultEdge> corteArestas = obtemArestasCorte(subgrafo, vertice);
        DefaultEdge aresta;

        while (!corteArestas.isEmpty()){
            aresta = obtemAresta(subgrafo, corteArestas);
            circuito.add(aresta);

            if(subgrafo.getEdgeSource(aresta).toString().equals(vertice.getLabel()))
               vertice = subgrafo.getEdgeTarget(aresta);
            else
               vertice = subgrafo.getEdgeSource(aresta);

            subgrafo.removeEdge(aresta);
            corteArestas = obtemArestasCorte(subgrafo, vertice);
        }

        return circuito;
    }

    /**
     * Retorna o conjunto de cortes de arestas de um vértice.
     *
     * @param grafo Grafo
     * @param vertice Vértice
     * @return Retorna o conjunto de arestas de corte.
     */
    private static Set obtemArestasCorte(Graph grafo, DefaultVertex vertice){
        Set<DefaultEdge> arestasCorte = new HashSet<>();
        Set<DefaultEdge> arestas = grafo.edgeSet();

        for(DefaultEdge aresta: arestas){
            if(aresta.toString().contains(vertice.toString())){
                arestasCorte.add(aresta);
            }
        }

        return arestasCorte;
    }

    /**
     * Verifica se uma aresta é de corte.
     *
     * @param grafo Grafo utilizado no teste de aresta de corte.
     * @param aresta Aresta a ser verificada.
     * @return Retorna true caso a aresta seja de corte.
     */
    private static boolean verificaArestaCorte(Graph grafo, DefaultEdge aresta){
        Graph<DefaultVertex, DefaultEdge> subgrafo = new SimpleGraph<>(DefaultEdge.class);
        Graphs.addGraph(subgrafo, grafo);
        subgrafo.removeEdge(aresta);
        ConnectivityInspector<DefaultVertex, DefaultEdge> conectividade = new ConnectivityInspector<>(subgrafo);

        return !conectividade.isConnected();
    }

    /**
     * Retorna a aresta adequada, ou seja, a aresta que não é de corte. Caso não exista nenhuma aresta que não seja
     * de corte, será retornada qualquer uma.
     *
     * @param grafo Grafo
     * @param arestas Conjunto de arestas.
     * @return Retorna a aresta adequada, prioritaramente sem ser a de corte, caso não exista alternativa retorna
     * qualquer outra.
     */
    private static DefaultEdge obtemAresta(Graph grafo, Set<DefaultEdge> arestas){
        for(DefaultEdge aresta: arestas){
            if(!verificaArestaCorte(grafo, aresta)){
                return aresta;
            }
        }

        return arestas.iterator().next();
    }
}
