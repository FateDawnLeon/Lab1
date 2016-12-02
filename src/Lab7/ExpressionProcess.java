package Lab7;

import java.util.Scanner;

/**
 * @author FateDawnLeon & hyptk
 *
 */
public class ExpressionProcess {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		UserInterface expProcess = new UserInterface();
		boolean flag = false;
		
		while(true) {
			String str = input.nextLine();
			if(str.startsWith("!")) {
				if (str.equals("!exit")) {
					break;
				}
				else {
					if (flag == true) {
						if (expProcess.setCmd(str)) {
							expProcess.excuteCmd();
							System.out.println(expProcess.getStdOutputExp());
						}
					}
					else {
						System.out.println("No Expression for operatioin!");
					}
				}
			}
			else {
				if (expProcess.setStdInputExp(str)) {
					if (expProcess.setCmd("!simplify ")) {
						expProcess.excuteCmd();
						System.out.println(expProcess.getStdOutputExp());
						flag = true;
					}
				}
				else {
					System.out.println("Illegal Expression!");
				}
			}
		}
		input.close();
	}

}
