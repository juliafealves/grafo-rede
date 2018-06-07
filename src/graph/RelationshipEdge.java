package graph;

import java.util.Map;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.io.Attribute;


public class RelationshipEdge extends DefaultEdge {
    // Arestas com labels e demais atributos - Grafos N�o-direcionados
	
	static final long serialVersionUID = 0;
	private Object v1;
    private Object v2;
    //private String label;
    private Map <String,Attribute> att;

    public RelationshipEdge(Object v1, Object v2, Map <String,Attribute> att) {
        this.v1 = v1;
        this.v2 = v2;
        this.att = att;
    }

    public Object getLabel () {
     	String l = "label";
      	Object o = att.get(l);
        if (o==null) {
          	return ("{"+v1+","+v2+"}"); 
        } else return o;
    }
    
    public Object getV1 () {
    	return v1;
    }
    
    public Object getV2 () {
    	return v2;
    }
    @Override
    public String toString() {
      	String l = "label";
      	Object o = att.get(l);
       	if (o == null) {
       		return ("{"+v1+","+v2+"}");
       	} else return (att.get(l)).toString()+"->{"+v1+";"+v2+"}";
    }
        
    public Object getNeighbour (Object v) {
        if (v.equals(v1)) {
          	return v2;
        } else return v1;
    }
        
    public boolean equals (RelationshipEdge e) {
      	return (this.getLabel()).equals(e.getLabel());
    }
}

