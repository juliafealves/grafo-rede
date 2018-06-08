import graph.*;
import org.jgrapht.alg.cycle.HierholzerEulerianCycle;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.GraphWalk;
import org.jgrapht.graph.Pseudograph;
import org.jgrapht.io.EdgeProvider;
import org.jgrapht.io.GmlImporter;
import org.jgrapht.io.ImportException;
import org.jgrapht.io.VertexProvider;

import java.util.*;

public class RedeGrafo {

    public static void main(String[] args){
        VertexProvider<DefaultVertex> fornecedorVertices = (label, attributes) -> new DefaultVertex (label,attributes);
        EdgeProvider<DefaultVertex, RelationshipEdge> fornecedorArestas = (from,to,label,attributes) -> new RelationshipEdge(from,to,attributes);
        GmlImporter<DefaultVertex,RelationshipEdge> importadorGML = new GmlImporter <> (fornecedorVertices, fornecedorArestas);
        Pseudograph<DefaultVertex, RelationshipEdge> pseudografo = new Pseudograph<>(RelationshipEdge.class);

        try {
            String caminhoArquivo = "/Volumes/Arquivos/workspace/grafo-rede/rede.gml";
            importadorGML.importGraph(pseudografo, ImportGraph.readFile(caminhoArquivo));
        } catch (Exception excecao) {
            throw new RuntimeException("Não foi possível ler o grafo GML. " + excecao.getMessage());
        }

        Set<DefaultVertex> vertices = new HashSet<>(pseudografo.vertexSet());
        Set<RelationshipEdge> arestas = new HashSet<>(pseudografo.edgeSet());

        /**
         * Estrutura do Grafo importado.
         */
        System.out.println("Estrutura do Grafo GML Importado:");
        System.out.println("Ordem do Grafo G: " + vertices.size());
        System.out.println("Tamanho do Grafo G: " + arestas.size());
        System.out.println("V(G): " + vertices);
        System.out.println("E(G): " + arestas.toString());

        HierholzerEulerianCycle<DefaultVertex, RelationshipEdge> caminhoEuliano = new HierholzerEulerianCycle<>();
        List<RelationshipEdge> listaArestas = new ArrayList<>();
        listaArestas.addAll(arestas);
        DefaultVertex vertice = DefaultVertexSet.getVertexfromLabel(vertices, "C");
        GraphWalk<DefaultVertex, RelationshipEdge> caminho = new GraphWalk<>(pseudografo, vertice, vertice, listaArestas, 0.0D);

        if(caminhoEuliano.isEulerian(pseudografo)) {
            System.out.println(caminhoEuliano.getEulerianCycle(pseudografo));
            System.out.println(caminho);
        }
    }
}
