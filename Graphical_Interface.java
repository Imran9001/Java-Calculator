import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents the graphical interface of the calculator.It implements ActionListener which enables the buttons
 * to have interactivity. Also it inherits from the class Operations, so it can use its methods as if it was its
 * own methods. 
 */
public class Graphical_Interface extends Operations implements ActionListener {
	
	private JFrame frame; // Number pad of the calculator
	private JPanel panel; //  The window of the calculator program
	
	private JTextField textField; // Represents the text field of the calculator
	private JButton addButton, subButton, mulButton, divButton; // Operation buttons of the calculator
	private JButton decButton, equButton, delButton, clrButton;
	private JButton[] functionButtons = new JButton[9]; // Array of operation buttons 
	private JButton[] numberButtons = new JButton[10];   // Array of number buttons
	
	private String answer = ""; // Variable that will store the answer the calculator works out.
	private Float num1 = 0f;  
	private Float num2 = 0f;
	private boolean equalsButtonPressedLast = false; // Checks if equals button has been pressed before
	private String operator = " ";   // Used in switch statement to select correct method in Operations class
	private String consecutiveEqualCheck = "no"; // States if the equals button has been used two times in a row for
	                                             // calculations without any other operator button being pressed
	/**
	 * This constructor initialises the graphical interface of the calculator 
	 */
	
	public Graphical_Interface()
	{
	    frame = new JFrame();   // Initialises object called frame from class JFrame
	    frame.setSize(420,500); // Sets size of window of program 
		frame.setLayout(null);
		
		
		textField = new JTextField();
		textField.setEditable(false); // Text field of calculator cannot be edited, user can only input values via buttons. 
		textField.setBounds(50,25,300,50); // Sets size and position of text field.
		textField.setFont(new Font("Arial", Font.BOLD, 30)); // Sets font type and size of text in the text field
		
		
		for (int i = 0 ; i< numberButtons.length; i++) {  // for loop which iterates through numberButtons array
			numberButtons[i] = new JButton (String.valueOf(i)); // Sets value of each button 
			numberButtons[i].setFont(new Font("Arial", Font.BOLD, 30)); // Sets font of each button
			numberButtons[i].addActionListener(this); // adds ActionListener to each button 
		}
		
		//
		addButton = new JButton("+");  // Sets value of button to the value of the string "+" passed in 
		subButton = new JButton("-");
		mulButton = new JButton("x");
		divButton = new JButton("÷");
		decButton = new JButton(".");
		equButton = new JButton("=");
		delButton = new JButton("D");
		clrButton = new JButton("C");
		
		// 
		functionButtons[0] = addButton;  // Set the operation buttons as part of the functionButtons array 
		functionButtons[1] = subButton;
		functionButtons[2] = mulButton;
		functionButtons[3] = divButton;
		functionButtons[4] = decButton;
		functionButtons[5] = equButton;
		functionButtons[6] = delButton;
		functionButtons[7] = clrButton;
		
	        for (int i = 0; i < 8; i++) {
	            functionButtons[i].setFont(new Font("Arial", Font.PLAIN, 30));
	            functionButtons[i].setFocusable(false);
	            functionButtons[i].addActionListener(this);
	            
	        }
		
		panel = new JPanel();
		panel.setBounds(50, 100, 300, 300);
	    panel.setLayout(new GridLayout(5, 4, 10, 10)); // Sets button pad layout to a grid with 5 rows and 4 columns
	    panel.setBackground(Color.GRAY);
		
	    panel.add(numberButtons[1]); // Adds buttons to panel in a specific order 
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);

        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);

        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);

        panel.add(decButton);

        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);
        
        panel.add(clrButton);
        panel.add(delButton);
        
		
        frame.add(panel);   // adds panel to frame
		frame.add(textField); // adds textField to frame
		frame.setVisible(true);  // Sets the frame to visible so that it can be seen. 
		
		
		
	}

	@Override
	/**
	 * This method is from the ActionListner interface. It provides interactivity to the buttons, it enables them to remember and to respond to events.
	 */
	public void actionPerformed(ActionEvent e)
	{
		for (int i = 0; i < 10; i++) // Iterates through numberButtons
		{
			if (e.getSource() == numberButtons[i]) // Triggers when a numberButton is pressed 
			{
				textField.setText(textField.getText().concat(String.valueOf(i))); // Sets a value of numberButton in the text field 
				                                                                  // and puts any subsequent values next to the previous one
			}
		}
		
		if(e.getSource() == addButton) // If addButton is pressed 
		{
			if(textField.getText().isEmpty() || textField.getText().contentEquals("-")) // If the text field is empty and there is no minus in the text field
			{
				    // does nothing, this is to prevent errors when user press add when there isn't a valid value to use for addition 
			}
			else
			{
				num1 = Float.parseFloat(textField.getText()); // sets value of first number to the value of the number in the text field
			    textField.setText(""); // clears the text field 
			    operator = "+"; // value of String operator is set to the symbol of the operator that has just been used. In this case
			                    // "+" as the add button has been pressed. 
			}
		    
		    
		}
		
		else if(e.getSource() == subButton)
		{
			if(textField.getText().isEmpty())
			{
				textField.setText("-"); // For negative numbers 
			
			}
			else if(!(textField.getText().contentEquals("-"))) // If the text field only contains a minus.
			{
				num1 = Float.parseFloat(textField.getText());
				textField.setText("");
				operator = "-";
			}
				
			 
		}
		
		else if(e.getSource() == decButton)
		{
			if(!(textField.getText().contains(".")) && (!(textField.getText().isEmpty())&& (!(textField.getText().contentEquals("-")))))
				// Can't use decimal point if text field is empty, already has a decimal point or if there is only a minus sign. 
			{
				textField.setText(textField.getText().concat("."));
			}
			
		}
		
		
		
		else if(e.getSource() == equButton)
		{
			if(textField.getText().isEmpty() || textField.getText().contentEquals("-"))
			{
				num2 = 0f;
			
			}
			else if (consecutiveEqualCheck == "no") 
			{
				num2 = Float.parseFloat(textField.getText());
			}
			else
			{
				num1 = Float.parseFloat(textField.getText()); // This triggers when user presses the equals button straight after a calculation
				                                              // Value of num1 changes to value of number in textField while value of num2 stays
				                                              // the same. 
			}
			switch(operator) // Switch statement compares value of String operator with cases 
			{
			case "+": answer = String.valueOf(this.addition(num1,num2)); // Due to inheritance, the this keyword is used as if it is using a method from its own class
			break;
			
			case "-" :answer = String.valueOf(this.subtraction(num1,num2)); // Uses method from operation class to work out the answer 
			break;
			
			case "*" : answer = String.valueOf(this.multiplication(num1,num2));
			break;
			
			case "/" : answer = String.valueOf(this.division(num1,num2));
			break;
			}
			
			textField.setText(answer); 
			equalsButtonPressedLast = true; // In the next calculation the equals button will be counted as being pressed before 
			
		}
		
	
		else if(e.getSource() == mulButton)
		{
			if(textField.getText().isEmpty() || textField.getText().contentEquals("-"))
			{
				
			}
			else
			{
				num1 = Float.parseFloat(textField.getText());
			    textField.setText("");
			    operator = "*";
			}
		}
		
		else if(e.getSource() == divButton)
		{
			if(textField.getText().isEmpty() || textField.getText().contentEquals("-"))
			{
				
			}
			else
			{
				num1 = Float.parseFloat(textField.getText());
			    textField.setText("");
			    operator = "/";
			}
		}
		
		else if (e.getSource() == clrButton)
		{
			textField.setText("");
		}
		
		else if (e.getSource() == delButton)
		{
			String content = textField.getText();
			textField.setText("");
			for(int i=0;i<content.length()-1;i++)
			{
				textField.setText(textField.getText()+content.charAt(i));
			}
				
			
		}

		if (equalsButtonPressedLast && e.getSource() == equButton) // Triggers when the equals button has been used twice in a row.
			                                                       // This is for when the user has just done a calculation and wants to repeat it
			                                                       // but with the current number in the text field. 
		 {
			 consecutiveEqualCheck = "yes";
		 }
		
		else
		{
			consecutiveEqualCheck = "no";
		}
		 
		 equalsButtonPressedLast = false; // Resets equals button check, this triggers when another button
		                                  // has been pressed after the equals button. 

		
	}
	
	

	

}
