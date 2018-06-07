package graph;

import java.util.Iterator;
import java.util.Set;

public class DefaultVertexSet {
	/**
	 * Para um conjunto de Vértices retorna o objeto vértice identificado pelo label
	 * @param V
	 * @param label
	 * @return
	 */
	static public DefaultVertex getVertexfromLabel (Set <DefaultVertex> V, String label) {
		Iterator<DefaultVertex> it = V.iterator();
		while (it.hasNext()) {
			DefaultVertex v = it.next();
			String l = v.getLabel();
			if (l.equals(label)) {
				return v;
			}
		}
		return null;
	}
	
	
	
}
