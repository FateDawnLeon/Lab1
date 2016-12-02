package Lab7;

import java.util.ArrayList;

/**
 * @author FateDawnLeon
 *
 */
public class Monomial {
	
	private ArrayList<VarNode> monomial;
	
	public String toString() {
		int size = monomial.size();
		if (size>0) {
			VarNode item;
			StringBuffer mono = new StringBuffer();
			for(int i=0; i<size-1; i++) {
				item = monomial.get(i);
				mono.append(item.toString());
				mono.append("*");
			}
			item = monomial.get(size-1);
			mono.append(item.toString());
			return mono.toString();
		}
		else {
			return "null_monomial";
		}
	}
	
	public Monomial(String monomial) {
		setMonomial(monomial);
	}
	
	public ArrayList<VarNode> getMonomial() {
		return this.monomial;
	}
	
	public void setMonomial(String monomial) {
		String[] items = monomial.split("\\*");
		this.monomial = new ArrayList<VarNode>();
		for(int i=0; i<items.length; i++) {
			if (!items[i].equals("")) {
				this.monomial.add(new VarNode(items[i]));
			}
		}
	}
}
