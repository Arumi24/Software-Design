import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AmountFrame {
	
	private JFrame frame;
	private JPanel panel;
	private JButton SubmitButton;
	private JButton CheckingButton;
	private JButton SavingsButton;

    private JTextField IDInput;
    private JTextField AmountInput;
 

	public AmountFrame(MainFrame object,String action) {
		
	
		// frame and panel
		frame = new JFrame();
		panel = new JPanel();
		panel.setLayout(null);
		
		// buttons, labels, and text field
		SubmitButton = new JButton("Submit");
		CheckingButton = new JButton("Checking");
		SavingsButton = new JButton("Savings");
		
		IDInput = new JTextField("",1000);
		AmountInput = new JTextField("",100);

		JLabel IDLabel = new JLabel("ID:");
	    JLabel AmountLabel = new JLabel("Amount:");
	
	    
		// initialize frame
		panel.setBorder(BorderFactory.createTitledBorder(
		        BorderFactory.createEtchedBorder(), "Please Enter & Press Submit"));
		frame.getContentPane().add(panel);
		frame.setSize(275, 180);
		frame.setTitle("Enter Amount");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// put frame in middle
		Toolkit toolkit = frame.getToolkit();
		Dimension size = toolkit.getScreenSize();
		frame.setLocation(size.width / 2 - frame.getWidth() / 2, size.height / 2 - frame.getHeight() / 2);
		
		
		// initialize and place buttons,labels, text fields
		CheckingButton.setBounds(185, 20, 75, 35);
		SavingsButton.setBounds(185, 55, 74, 35);
		SubmitButton.setBounds(5, 100, 100, 50);
		IDLabel.setBounds(10, 15, 160, 50);
		AmountLabel.setBounds(10, 50, 160, 50);
	
		IDInput.setBounds(30, 30, 100, 20);
		AmountInput.setBounds(65, 65, 100, 20);
		
		
		panel.add(AmountLabel);
		panel.add(IDLabel);
		panel.add(IDInput);
		panel.add(AmountInput);
		panel.add(CheckingButton);
		panel.add(SavingsButton);
		panel.add(SubmitButton);
		
		
		// button and input actions
		
		IDInput.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e)
			{
				object.ID=IDInput.getText();
			}
		});
		
		AmountInput.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e)
			{
				object.amount=Double.valueOf(AmountInput.getText());			
			}
		});
		
		CheckingButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				object.type = AccountType.Checking;
			}
		});

		SavingsButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				object.type = AccountType.Savings;
			}
		});
		
		SubmitButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e)
			{
				submit(object,action);
				
			}
		});
		
		
		frame.setVisible(true);
		
	}
	
	public void submit(MainFrame object,String action)
	{
		if(action=="deposit")
		{
			object.deposit();	
		}
		
		if(action=="withdraw")
		{
			object.withdraw();
		}
		
		frame.dispose();
	
	}

}
