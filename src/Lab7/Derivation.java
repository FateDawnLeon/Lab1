package Lab7;

import java.util.ArrayList;

/**
 * @author hyptk
 *
 */
public class Derivation {
	private Polynomial polynomial;
	
	public Derivation (String expression) {
		polynomial = new Polynomial(expression);
	}
	
	public Polynomial getPolynomial () {
		return this.polynomial;
	}
	
	public void derivative (String der) {
		ArrayList<Monomial> poly = polynomial.getPolynomial();
		for(int i=0; i<poly.size(); i++) {
			Monomial monomial = poly.get(i);
			ArrayList<VarNode> mono = monomial.getMonomial();
			int num = 0;
			for(int j=0; j<mono.size(); j++) {
				VarNode item = mono.get(j);
				if (item.getType()==Type.variable && item.getVarname().equals(der)) {
					num++;
				}
			}
			if (num == 0) {
				poly.remove(i);
				i--;
			}
			else {
				int index = 0;
//				System.out.println(num);
				while(mono.get(index).getType()==Type.constant || !mono.get(index).getVarname().equals(der)) {
//					System.out.println(index);
					index++;
				}
				mono.get(index).setValue(num);
				mono.get(index).setType(Type.constant);
			}
		}
		if (poly.size()==0) {
			poly.add(new Monomial("0"));
		}
	}
}
