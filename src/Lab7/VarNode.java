package Lab7;

/**
 * @author FateDawnLeon
 *
 */
public class VarNode {
	private int value;
	private String varname;
	private Type type;
	
	public String toString() {
		if (type == Type.constant) {
			return String.valueOf(value);
		}
		else if (type == Type.variable) {
			return varname;
		}
		else {
			return "null_varnode";
		}
	}

	public VarNode(String item) {
		if (Character.isDigit(item.charAt(0)) || item.charAt(0)=='-') {
			setValue(Integer.parseInt(item));
			setType(Type.constant);
		}
		else {
			setVarname(item);
			setType(Type.variable);
		}
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}

	public String getVarname() {
		return this.varname;
	}

	public void setVarname(String varname) {
		this.varname = varname;
	}

	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}

enum Type {constant, variable};
