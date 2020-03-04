import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class CustomerFrame {

	private JFrame frame;
	private JPanel panel;
	private JButton SubmitButton;

	private JTextField NameInput;
	private JTextField DiscountFactorInput;

	public CustomerFrame(MainFrame object) {

		// frame and panel
		frame = new JFrame();
		panel = new JPanel();
		panel.setLayout(null);

		// buttons, labels, and text field
		SubmitButton = new JButton("Submit");

		NameInput = new JTextField("", 1000);
		DiscountFactorInput = new JTextField("", 100);

		JLabel NameLabel = new JLabel("Name:");
		JLabel DiscountFactorLabel = new JLabel("Discount Factor:");

		// initialize frame
		panel.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Please Enter & Press Submit"));
		frame.getContentPane().add(panel);
		frame.setSize(400, 125);
		frame.setTitle("Create Customer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// put frame in middle
		Toolkit toolkit = frame.getToolkit();
		Dimension size = toolkit.getScreenSize();
		frame.setLocation(size.width / 2 - frame.getWidth() / 2, size.height / 2 - frame.getHeight() / 2);

		// initialize and place buttons,labels, text fields
		SubmitButton.setBounds(265, 30, 100, 50);
		NameLabel.setBounds(10, 15, 160, 50);
		DiscountFactorLabel.setBounds(10, 50, 160, 50);

		NameInput.setBounds(55, 30, 100, 20);
		DiscountFactorInput.setBounds(120, 65, 100, 20);

		panel.add(DiscountFactorLabel);
		panel.add(NameLabel);
		panel.add(NameInput);
		panel.add(DiscountFactorInput);
		panel.add(SubmitButton);

		// button and input actions

		NameInput.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				object.name = NameInput.getText();
			}
		});

		DiscountFactorInput.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				object.discountFactor = Double.valueOf(DiscountFactorInput.getText());
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
		object.createCustomer();
		frame.dispose();

	}

}
