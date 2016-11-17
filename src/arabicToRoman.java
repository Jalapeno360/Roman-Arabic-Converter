



/*
 * UIN:4469
 * Date: 10/17/2016
 * Assignment: Homework 2
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class arabicToRoman extends JFrame implements KeyListener {
	static boolean check; // used to make sure a false or true is being passed
	public static JTextField arabicText = new JTextField();
	public static JTextField romanText = new JTextField();
	public JLabel romanLabel = new JLabel("Roman Numeral");
	public JLabel arabicLabel = new JLabel("Arabic Numeral");
	static JPanel window = new JPanel();
	private static int temp;

	// Constructor
	public arabicToRoman(String title) {

		super(title);

		windowConstruction();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public JPanel windowConstruction() {

		setSize(300, 100);

		window.setLayout(new GridLayout(2, 1));

		window.add(arabicLabel, BorderLayout.WEST);

		window.add(arabicText, BorderLayout.CENTER);

		arabicText.addKeyListener(this);

		romanText.addKeyListener(this);

		window.add(romanLabel, BorderLayout.WEST);

		window.add(romanText, BorderLayout.CENTER);

		add(window);

		setVisible(true);

		setResizable(false);

		return window;

	}

	public static boolean isValidArabic(int x) {
		String num = String.valueOf(x);

		// Checks each character if it is a digit.
		for (int k = 0; k < num.length(); k++) {

			if (Character.isDigit(num.charAt(k)) == false) {

				if (arabicText.getText().length() == 1) {
					arabicText.setText("");
					return false;
				} else if (arabicText.getText().length() > 1) {

					arabicText.setText(arabicText.getText().substring(0,
							arabicText.getText().length() - 1));

					return false;
				}
				return false;
			}
		}

		if (x > 3999 || x < 1) {
			return false;
		}
		return true;

		// Checking if the number is bigger than 3999 or smaller than 1

	}

	public static boolean isValidRoman(String y) {

		String letter = y.toUpperCase();
		for (int i = 0; i < y.length(); i++) {

			if (letter.charAt(i) != 'I' && letter.charAt(i) != 'V'
					&& letter.charAt(i) != 'X' && letter.charAt(i) != 'L'
					&& letter.charAt(i) != 'C' && letter.charAt(i) != 'D'
					&& letter.charAt(i) != 'M'
					&& romanText.getText().length() == 1) {

				romanText.setText("");
				return check = false;

			} else if (letter.charAt(i) != 'I' && letter.charAt(i) != 'V'
					&& letter.charAt(i) != 'X' && letter.charAt(i) != 'L'
					&& letter.charAt(i) != 'C' && letter.charAt(i) != 'D'
					&& letter.charAt(i) != 'M'
					&& romanText.getText().length() > 1) {

				romanText.setText(romanText.getText().substring(0,
						romanText.getText().length() - 1));

				return check = false;
			} else {
				return check = true;
			}
		}
		return check;
	}

	public String toCovertRoman(int num) {

		if (isValidArabic(num)) { // Checking if the number is a valid Arabic
									// number

			String Roman = ""; // This will be our result string.

			// Declare and Initiate our Arrays
			String onesArray[] = { "I", "II", "III", "IV", "V", "VI", "VII",
					"VIII", "IX" };
			String tensArray[] = { "X", "XX", "XXX", "XL", "L", "LX", "LXX",
					"LXXX", "XC" };
			String hundredsArray[] = { "C", "CC", "CCC", "CD", "D", "DC", "DCC",
					"DCCC", "CM" };

			// Get the ones in the number
			int ones = num % 10;

			// Get the tens
			num = (num - ones) / 10;
			int tens = num % 10;

			// Get the hundreds
			num = (num - tens) / 10;
			int hundreds = num % 10;

			// Get and write the thousands in the number to our string
			num = (num - hundreds) / 10;
			for (int i = 0; i < num; i++) {
				Roman += "M";
			}

			// Write the hundreds
			if (hundreds >= 1) {
				Roman += hundredsArray[hundreds - 1];
			}

			// Write the tens
			if (tens >= 1) {
				Roman += tensArray[tens - 1];
			}

			// And finally, write the ones
			if (ones >= 1) {
				Roman += onesArray[ones - 1];
			}

			// Return our string.
			return String.valueOf(Roman);
		} else {
			return null; // If the number isn't a valid Arabic number, return
							// null.
		}
	}

	public static String toConvertArabic(String change) {

		if (isValidRoman(change)) { // Check if the number is a valid Roman
									// Number

			int arabic = 0;
			int last_digit = 0;
			int current_digit = 0;

			for (int i = 0; i < change.length(); i++) {

				if (change.charAt(i) == 'I' || change.charAt(i) == 'i') {
					current_digit = 1;
				}
				if (change.charAt(i) == 'V' || change.charAt(i) == 'v') {
					current_digit = 5;
				}
				if (change.charAt(i) == 'X' || change.charAt(i) == 'x') {
					current_digit = 10;
				}
				if (change.charAt(i) == 'L' || change.charAt(i) == 'l') {
					current_digit = 50;
				}
				if (change.charAt(i) == 'C' || change.charAt(i) == 'c') {
					current_digit = 100;
				}
				if (change.charAt(i) == 'D' || change.charAt(i) == 'd') {
					current_digit = 500;
				}
				if (change.charAt(i) == 'M' || change.charAt(i) == 'm') {
					current_digit = 1000;
				}

				// If the last number is smaller than the current number,
				// subtract the last number from the current number
				// Otherwise, just add the current number. We must also skip the
				// first number from this rule simply because
				// e.g. someone enters 1799 in which case it would subtract 1
				// from 7

				if (last_digit < current_digit && last_digit != 0) {
					current_digit -= last_digit;
					arabic -= last_digit;
					arabic += current_digit;
					last_digit = current_digit;
					current_digit = 0;
				} else {
					last_digit = current_digit;
					arabic += current_digit;
					current_digit = 0;
				}
			}

			return String.valueOf(arabic);

		} else {
			return null;
		}
	}

	public void keyReleased(KeyEvent e) {

		if (e.getSource() == arabicText) { // if typed in arabicField
			String inputStr = null;
			inputStr = arabicText.getText();
			try {
				temp = Integer.parseInt(inputStr);
				// checks user does not input zero
				if (inputStr.length() == 1 && inputStr.charAt(0) == '0') {
					arabicText.setText("");
				}
				// checks temp >3999 then delete last number and convert.
				if (temp > 3999) {
					arabicText.setText(toConvertArabic(romanText.getText()));
				} else {
					romanText.setText(toCovertRoman(temp));
				}
			} catch (NumberFormatException r) {
				arabicText.setText(toConvertArabic(romanText.getText()));
			}
			// if the textfield is given an empty string set both to null
			if (inputStr.isEmpty()) {
				arabicText.setText(null);
				romanText.setText(null);
			}

		} else { // typed in romanField
			String strInput = null;
			strInput = romanText.getText();
			try {
				temp = Integer.parseInt(toConvertArabic(strInput));
			} catch (NumberFormatException r) {
				// System.out.println("hi"); for testing
				romanText.setText(strInput);
				arabicText.setText("");

			}
			// checks if roman is greater than 3999
			if (temp > 3999) {
				romanText.setText(
						toCovertRoman(Integer.parseInt(arabicText.getText())));
			}

			if (isValidRoman(strInput)) {
				arabicText.setText(toConvertArabic(strInput));

			}

			if (strInput.isEmpty()) {
				arabicText.setText(null);
			}
		}

	}

	/*
	 * not used
	 */
	public void keyPressed(KeyEvent e) {

	}

	/*
	 * not used
	 */
	public void keyTyped(KeyEvent e) {

	}

	public static void main(String args[]) {
		new arabicToRoman("Roman <--> Arabic");

	}

}