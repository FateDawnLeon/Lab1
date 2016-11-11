package lab6;

public class Node{
	public boolean isNum;
	private int num;
	private String variable;
	Node(String variable){
		this.isNum=false;
		this.variable=variable;
	}
	Node(int num) {
		this.isNum=true;
		this.num=num;
	}
	public int getNum() {
		return num;
	}

	public String getC() {
		return variable;
	}
	
	public String toString(){
		if (isNum) {
			return String.valueOf(num);
		}
		else {
			return String.valueOf(variable);
		}
	}
	
	public boolean equals(Node a){
		if (this.isNum==a.isNum && this.num==a.num && this.variable==a.variable){
			return true;
		}
		else{
			return false;
		}
	}
}
