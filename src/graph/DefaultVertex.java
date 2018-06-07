package graph;

import java.io.Serializable;
import java.util.Map;

import org.jgrapht.io.Attribute;

public class DefaultVertex implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// Vértices com id e label
	private Object id;
	private Map <String,Attribute> att;
		
	public DefaultVertex (Object id, Map <String,Attribute> att) {
		this.id = id;
		this.att = att;
	}
		
	public Object getId () {
		return id;
	}
	
	public String getLabel () {
      	String l = "label";
        return (att.get(l)).toString();
	}
		
	public String toString () {
       	String l = "label";
        return (att.get(l)).toString();
	}
		
	public boolean equals (DefaultVertex v) {
		return this.id.equals(v.id);
	}
}

