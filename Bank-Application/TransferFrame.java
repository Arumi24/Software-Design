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

public class TransferFrame {

	private JFrame frame;
	private JPanel panel;
	private JButton SubmitButton;
	private JButton CheckingButton;
	private JButton SavingsButton;

	private JTextField IDInput;
	private JTextField AmountInput;

	public TransferFrame(MainFrame object) {

		// frame and panel
		frame = new JFrame();
		panel = new JPanel();
		panel.setLayout(null);

		// buttons, labels, and text field
		SubmitButton = new JButton("Submit");
		CheckingButton = new JButton("Checking->Savings");
		SavingsButton = new JButton("Savings->Checking");

		IDInput = new JTextField("", 1000);
		AmountInput = new JTextField("", 100);

		JLabel IDLabel = new JLabel("ID:");
		JLabel AmountLabel = new JLabel("Amount:");

		// initialize frame
		panel.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Please Enter & Press Submit"));
		frame.getContentPane().add(panel);
		frame.setSize(375, 180);
		frame.setTitle("Make Transfer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// put frame in middle
		Toolkit toolkit = frame.getToolkit();
		Dimension size = toolkit.getScreenSize();
		frame.setLocation(size.width / 2 - frame.getWidth() / 2, size.height / 2 - frame.getHeight() / 2);

		// initialize and place buttons,labels, text fields
		CheckingButton.setBounds(185, 20, 175, 35);
		SavingsButton.setBounds(185, 55, 175, 35);
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

			public void actionPerformed(ActionEvent e) {
				object.ID = IDInput.getText();
			}
		});

		AmountInput.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				object.amount = Double.valueOf(AmountInput.getText());
			}
		});

		CheckingButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				object.from = AccountType.Checking;
				object.to = AccountType.Savings;
			}
		});

		SavingsButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				object.from = AccountType.Savings;
				object.to = AccountType.Checking;
			}
		});

		SubmitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				submit(object);

			}
		});

		frame.setVisible(true);

	}

	public void submit(MainFrame object) {
		object.transfer();
		frame.dispose();

	}

}
