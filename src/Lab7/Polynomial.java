package Lab7;

import java.util.ArrayList;

/**
 * @author FateDawnLeon
 *
 */
public class Polynomial {
	
	private ArrayList<Monomial> polynomial;
	
	public String toString() {
		int size = polynomial.size();
		if (size>0) {
			Monomial mono;
			StringBuffer poly = new StringBuffer();
			for(int i=0; i<size-1; i++) {
				mono = polynomial.get(i);
				poly.append(mono.toString());
				poly.append("+");
			}
			mono = polynomial.get(size-1);
			poly.append(mono.toString());
			return poly.toString();
		}
		else {
			return "null_polynomial";
		}
	}
	
	public Polynomial(String polynomial) {
		setPolynomial(polynomial);
	}
	
	public ArrayList<Monomial> getPolynomial() {
		return this.polynomial;
	}
	
	public void setPolynomial(String polynomial) {
		String[] monomials = polynomial.split("\\+");
		this.polynomial = new ArrayList<Monomial>();
		for(int i=0; i<monomials.length; i++) {
			if (!monomials[i].equals("")) {
				this.polynomial.add(new Monomial(monomials[i]));
			}
		}
	}
}
