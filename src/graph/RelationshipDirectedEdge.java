package graph;

import java.util.Map;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.io.Attribute;

public class RelationshipDirectedEdge extends DefaultEdge {
    // Arcos com labels e demais atributos - Grafos Direcionados
	
	static final long serialVersionUID = 0;
    private Object v1;
    private Object v2;
    //private String label;
    private Map <String,Attribute> att;

    public RelationshipDirectedEdge(Object v1, Object v2, Map <String,Attribute> att) {
        this.v1 = v1;
        this.v2 = v2;
        this.att = att;
    }

    public Object getLabel () {
       	String l = "label";
       	Object o = att.get(l);
        if (o==null) {
           	return ("("+v1+":"+v2+")"); 
        } else return o;
    }
    
    public Object getSource () {
    	return v1;
    }
    
    public Object getTarget () {
    	return v2;
    }
    
    @Override
    public String toString() {
      	String l = "label";
       	Object o = att.get(l);
       	if (o == null) {
       		return ("("+v1+":"+v2+")");
       	} else return (att.get(l)).toString()+"->("+v1+":"+v2+")";
    }
        
    public Object getNeighbour (Object v) {
        if (v.equals(v1)) {
          	return v2;
        } else return v1;
    }
        
    public boolean equals (RelationshipDirectedEdge e) {
      	return (this.getLabel()).equals(e.getLabel());
    }
}
