package Lab7;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author FateDawnLeon
 *
 */
public class UserInterface {
	private String std_input_exp;
	private String std_output_exp;
	private String sim_cmd;
	private String der_cmd;
	private CmdType type;
	
	public String getStdOutputExp() {
		return this.std_output_exp;
	}
	
	public boolean setStdInputExp(String str) {
		str = str.replaceAll(" ", "");
		str = transform_addcheng(str);
		str = transform_subtract(str);
		str = transform_exponential(str);
		boolean f1 = check(str);
		if (f1) {
			std_input_exp = str;
			std_output_exp = str;
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean setCmd(String str) {
		if (str.startsWith("!simplify ")) {
			//check whether the simplify command is legal
			str = str.replaceFirst("!simplify ","");
//			System.out.println(str);
			String rule11 = "[\\s0-9A-Za-z\\=\\-]{0,}";
			String rule41 = "[\\=]{2,}";
			String rule51 = "[A-Za-z][0-9]{1,}";
			String rule61 = "[0-9][A-Za-z]{1,}";
			String rule71 = "[A-Za-z][\\=][0-9]{1,}";
			String rule81 = "[A-Za-z][\\=][\\-][0-9]{1,}";
			Pattern p11 = Pattern.compile(rule11);
			Pattern p41 = Pattern.compile(rule41);
			Pattern p51 = Pattern.compile(rule51);
			Pattern p61 = Pattern.compile(rule61);
			Pattern p71 = Pattern.compile(rule71);
			Pattern p81 = Pattern.compile(rule81);
			Matcher m11 = p11.matcher(str);
			boolean flag11 = m11.matches();
			Matcher m41 = p41.matcher(str);
			boolean flag41 = m41.find();
			Matcher m51 = p51.matcher(str);
			boolean flag51 = m51.find();
			Matcher m61 = p61.matcher(str);
			boolean flag61 = m61.find();
			Matcher m71 = p71.matcher(str);
			boolean flag71 = m71.find();
			Matcher m81 = p81.matcher(str);
			boolean flag81 = m81.find();
			boolean flag100 = flag11 & !flag41 & !flag51 &!flag61 & (flag71 || flag81 || str.equals(""));
//			System.out.println(flag11);
//			System.out.println(flag41);
//			System.out.println(flag51);
//			System.out.println(flag61);
//			System.out.println(flag71);
//			System.out.println(flag81);
			if (flag100) {
				sim_cmd = str;
				type = CmdType.simplification;
				return true;
			} 
			else {
				System.out.println("Illegal Simplify Command");
				return false;
			}		
		} 
		else if (str.startsWith("!d/d ")) {
			str = str.replaceFirst("!d/d ","");
			String rule1 = "[A-Za-z]{0,}";
			Pattern p1 = Pattern.compile(rule1);
			Matcher m1 = p1.matcher(str);
			boolean flag1 = m1.matches();
			if (flag1) {
				der_cmd = str;
				type = CmdType.derivation;
				return true;
			}
			else {
				System.out.println("Illegal Derivative Command");
				return false;
			}
		} 
		else {
			System.out.println("Command Error!");
			return false;
		}
	}
	
	public boolean excuteCmd() {
		if (type == CmdType.simplification) {
			Simplification simCase = new Simplification(std_input_exp);
			simCase.simplify(sim_cmd);
			std_output_exp = simCase.getPolynomial().toString();
		}
		else {
			Derivation derCase = new Derivation(std_input_exp);
			derCase.derivative(der_cmd);
			Simplification simCase = new Simplification("");
			simCase.setPolynomial(derCase.getPolynomial());
			simCase.simplify("");
			std_output_exp = simCase.getPolynomial().toString();
		}
		return false;
	}
	
	private String transform_subtract(String str){
        StringBuilder nstr = new StringBuilder(str);
        int cur = 0;
        while (cur<nstr.length()) {
            char x = nstr.charAt(cur);
            if (x=='-') {
                nstr.replace(cur,cur+1,"+-1*");
                cur += 3;
            }
            cur++;
        }
        return nstr.toString();
    }

    private String transform_exponential(String str){
    	String rule6 = "[0-9][a-zA-Z]";
        Pattern p6 = Pattern.compile(rule6);
        Matcher m6 = p6.matcher(str);
		boolean flag7 = m6.find();
		String rule8 = "[\\^][a-zA-Z]";
		Pattern p8 = Pattern.compile(rule8);
		Matcher m8 = p8.matcher(str);
		boolean flag8 = m8.find();
		boolean flag9 = flag8 || flag7;
		if (flag9) {
			System.out.println("Illegal Expression : Misuse Of Operator ^");
			return null;
		}
		
        String rule = "[\\^][1-9]";
        Pattern p = Pattern.compile(rule);
        Matcher m = p.matcher(str);
        boolean flag = m.find();
        while (flag) {
            int loca = m.start();
            String var;
            int exp;
            int i=loca+1, j=loca-1;
            while (i<str.length() && Character.isDigit(str.charAt(i))) {
                i++;
            }
            exp = Integer.parseInt(str.substring(loca+1,i));
            while (j>=0 && Character.isLetter(str.charAt(j))) {
                j--;
            }
            var = str.substring(j+1,loca);
            StringBuilder strf = new StringBuilder(str); 
            StringBuilder strh = new StringBuilder(); 
            for (int h=0; h<exp-1; h++) {
                strh.append("*");
                strh.append(var);
            }
            var = strh.toString();
            strf.delete(loca, i);
            strf.insert(loca, var);
            str = strf.toString();
            m = p.matcher(str);
            flag = m.find();
        }
        return str;     
    }
	
	private String transform_addcheng(String str){
        String rule6 = "[0-9][a-zA-Z]";
		Pattern p6 = Pattern.compile(rule6);
		Matcher m6 = p6.matcher(str);
		boolean flag6 = m6.find();
		while (flag6) {
			int loca = m6.start();
			loca++;
			StringBuilder strh = new StringBuilder(str);
			strh.insert(loca, "*");  
			str = strh.toString();
			m6 = p6.matcher(str);
			flag6 = m6.find();
		}
        return str;     
    }
			
	private boolean check(String str) {
        boolean flag2 = str.startsWith("+") || str.startsWith("*");
        boolean flag3 = str.endsWith("+") || str.endsWith("*");
        String rule1 = "[0-9A-Za-z\\*\\+\\-]{0,}";
        String rule4 = "[\\+\\*]{2,}";
        String rule5 = "[A-Za-z][0-9]{1,}";
        Pattern p1 = Pattern.compile(rule1);
        Pattern p4 = Pattern.compile(rule4);
        Pattern p5 = Pattern.compile(rule5);
        Matcher m1 = p1.matcher(str);
        boolean flag1 = m1.matches();
        Matcher m4 = p4.matcher(str);
        boolean flag4 = m4.find();
        Matcher m5 = p5.matcher(str);
        boolean flag5 = m5.find();
        boolean flag = flag1 & !flag2 & !flag3 & !flag4 & !flag5;
        
        
        if (!flag1) {
            System.out.println("Illegal Expression : Containing Illegal Character");
        } 
        if(flag2) {
            System.out.println("Illegal Expression : Expression Starts With An Operator");
        } 
        if(flag3) {
            System.out.println("Illegal Expression : Expression Ends With An Operator");
        } 
        if(flag4) {
            System.out.println("Illegal Expression : An Operator Followed By Another Operator");
        } 
        if(flag5) {
            System.out.println("Illegal Expression : An Varible Followed By An Number");
        }
        return flag;
    }
}

enum CmdType {simplification, derivation};
