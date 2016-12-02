package Lab7;

import java.util.ArrayList;

/**
 * @author FateDawnLeon
 *
 */
public class Simplification {
	
	private Polynomial polynomial;
	
	public Simplification(String expression) {
		polynomial = new Polynomial(expression);
	}
	
	public void simplify(String assignment) {
		//表达式变量按照赋值命令用值替换
		String[] equations = assignment.split(" ");
		for(int i=0; i<equations.length; i++) {
			if (equations[i].equals("")) {
				continue;
			}
			else {
				String[] pair = equations[i].split("=");
				String varname = pair[0];
				String value = pair[1];
				substitute(varname, value);
			}
		}
		
		
		//遍历化简每一个单项式并合并常数项
		int sum = 0;
		ArrayList<Monomial> poly = polynomial.getPolynomial();
		for(int i=0; i<poly.size(); i++) {
			int product = 1;
			Monomial monomial = poly.get(i);
			ArrayList<VarNode> mono = monomial.getMonomial();
			for(int j=0; j<mono.size(); j++) {
				VarNode item = mono.get(j);
				if (item.getType()==Type.constant) {
					product = product * item.getValue();
					mono.remove(j);
					j--;
				}
			}
			if (mono.size()==0 || product!=1) {
				mono.add(0,new VarNode(String.valueOf(product)));
			}
			if (mono.size()==1 && mono.get(0).getType()==Type.constant) {
				sum = sum + mono.get(0).getValue();
				poly.remove(i);
				i--;
			}
		}
		if (poly.size()==0 || sum!=0) {
			poly.add(new Monomial(String.valueOf(sum)));
		}
	}
	
	private void substitute(String varname, String value) {
		ArrayList<Monomial> poly = polynomial.getPolynomial();
		for(int i=0; i<poly.size(); i++) {
			Monomial monomial = poly.get(i);
			ArrayList<VarNode> mono = monomial.getMonomial();
			for(int j=0; j<mono.size(); j++) {
				if (mono.get(j).getType()==Type.variable) {
					if (mono.get(j).getVarname().equals(varname)) {
						mono.get(j).setType(Type.constant);
						mono.get(j).setValue(Integer.parseInt(value));
					}
				}
			}
		}
	}
	
	public Polynomial getPolynomial() {
		return this.polynomial;
	}
	
	public void setPolynomial(Polynomial polynomial) {
		this.polynomial = polynomial;
	}
}
