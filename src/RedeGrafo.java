import graph.*;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.alg.cycle.HierholzerEulerianCycle;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Pseudograph;
import org.jgrapht.io.EdgeProvider;
import org.jgrapht.io.GmlImporter;
import org.jgrapht.io.ImportException;
import org.jgrapht.io.VertexProvider;

import java.util.HashSet;
import java.util.Set;

public class RedeGrafo {

    public static void main(String[] args){
        VertexProvider<DefaultVertex> vertexProvider = (label,attributes) -> new DefaultVertex (label,attributes);
        EdgeProvider<DefaultVertex, RelationshipEdge> edgeProvider = (from,to,label,attributes) -> new RelationshipEdge(from,to,attributes);
        GmlImporter<DefaultVertex,RelationshipEdge> gmlImporter = new GmlImporter <> (vertexProvider, edgeProvider);
        Pseudograph<DefaultVertex, RelationshipEdge> pseudograph = new Pseudograph<>(RelationshipEdge.class);

        try {
            String gml = "/home/juliafealves/workspace/grafo-rede/rede.gml";
            gmlImporter.importGraph(pseudograph, ImportGraph.readFile(gml));
        } catch (ImportException e) {
            throw new RuntimeException("Não foi possível ler o grafo GML. " + e.getMessage());
        }

        Set<DefaultVertex> vertexes = new HashSet<>(pseudograph.vertexSet());
        Set<DefaultEdge> edges = new HashSet<>(pseudograph.edgeSet());

        System.out.println("Ordem do Grafo G: " + vertexes.size());
        System.out.println("Tamanho do Grafo G: " + edges.size());
        System.out.println("V(G): " + vertexes);
        System.out.println("E(G): " + edges.toString());

        HierholzerEulerianCycle<DefaultVertex, RelationshipEdge> caminhoEuliano = new HierholzerEulerianCycle<>();

        if(caminhoEuliano.isEulerian(pseudograph))
            System.out.println(caminhoEuliano.getEulerianCycle(pseudograph).toString());
    }
}
