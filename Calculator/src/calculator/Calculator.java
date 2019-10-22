package calculator;

import java.util.Scanner;

public class Calculator implements ICalc {
	
	private void startText() {
		System.out.println("Wpisz działanie (ze spacjami, np. 2 + 2)");
		System.out.println("Wpisz \"exit\", żeby wyjść z programu");
	}

	@Override
	public void calculateResult(double firstNumber, String operand, double secondNumber) {
		switch (operand) {
		case "+":
			System.out.println("Wynik = " + (firstNumber + secondNumber));
			break;
		case "-":
			System.out.println("Wynik = " + (firstNumber - secondNumber));
			break;
		case "*":
			System.out.println("Wynik = " + (firstNumber * secondNumber));
			break;
		case "/":
			try {
				if (secondNumber == 0) {
					throw new ArithmeticException();
				}
				System.out.println("Wynik = " + (firstNumber / secondNumber));
				break;
			} catch (Exception e) {
				System.out.println("Błąd: Dzielenie przez 0");
				break;
			}
		default:
			System.out.println("Nieobsługiwane działanie");
			break;
		}
	}

	@Override
	public void start() {
		String operation;
		double firstNumber, secondNumber;
		String operand;

		Scanner input = new Scanner(System.in);

		while (true) {
			startText();

			operation = input.nextLine();

			if (operation.equals("exit")) {
				break;
			}

			String[] arrOperation = operation.split(" ");

			firstNumber = Double.parseDouble(arrOperation[0]);
			operand = arrOperation[1];
			secondNumber = Double.parseDouble(arrOperation[2]);

			calculateResult(firstNumber, operand, secondNumber);
		}
		input.close();
	}
}