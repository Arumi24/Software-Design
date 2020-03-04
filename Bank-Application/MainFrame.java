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

public class MainFrame {

	private JFrame frame;
	private JPanel panel;

	public String ID;
	public double amount = 0;
	public double balance = 0;
	public AccountType type = null;
	public String name = null;
	public double discountFactor = 0.0;
	public AccountType from = null;
	public AccountType to = null;

	public BankApplication app;

	public MainFrame(BankApplication app) {

		this.app = app;
		// buttons
		JButton createCustomer = new JButton("Create Customer");
		JButton createAccount = new JButton("Create Bank Account");
		JButton showBalance = new JButton("Get Balance");
		JButton deposit = new JButton("Deposit");
		JButton withdrawal = new JButton("Withdrawal");
		JButton transfer = new JButton("Make a Transfer");
		JButton quit = new JButton("Quit");

		// frame and panel
		frame = new JFrame();
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Select Options"));

		// initialize frame
		frame.getContentPane().add(panel);
		frame.setSize(300, 500);
		frame.setTitle("Bank Application");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// put frame in middle
		Toolkit toolkit = frame.getToolkit();
		Dimension size = toolkit.getScreenSize();
		frame.setLocation(size.width / 2 - frame.getWidth() / 2, size.height / 2 - frame.getHeight() / 2);

		// create customer
		panel.add(createCustomer);
		createCustomer.setBounds(65, 35, 160, 50);
		createCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				openFrame2();
				frame.setVisible(true);

			}
		});

		// create accounts
		panel.add(createAccount);
		createAccount.setBounds(65, 95, 160, 50);
		createAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				openFrame3();
				frame.setVisible(true);

			}
		});

		// show balance
		panel.add(showBalance);
		showBalance.setBounds(65, 155, 160, 50);
		showBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				openFrame4();
				frame.setVisible(true);

			}
		});

		// deposit
		panel.add(deposit);
		deposit.setBounds(65, 215, 160, 50);
		deposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				openFrame5("deposit");
				frame.setVisible(true);

			}
		});

		// withdrawal
		panel.add(withdrawal);
		withdrawal.setBounds(65, 275, 160, 50);
		withdrawal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				openFrame5("withdraw");
				frame.setVisible(true);

			}
		});

		// transfer
		panel.add(transfer);
		transfer.setBounds(65, 335, 160, 50);
		transfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				openFrame6();
				frame.setVisible(true);

			}
		});

		// quit
		panel.add(quit);
		quit.setBounds(65, 395, 160, 50);
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		frame.setVisible(true);
	}

	public void createCustomer() {
		this.app.createCustomer(this.name, this.discountFactor);
	}

	public void createAccount() {
		this.app.addAccount(new BankAccount(this.ID, this.balance, this.type));
	}

	public void getBalance() {
		this.app.getBalance(this.ID, this.type);
	}

	public void deposit() {
		this.app.deposit(this.ID, this.amount, this.type);
		;
	}

	public void withdraw() {
		this.app.withdraw(this.ID, this.amount, this.type);
		;
	}

	public void transfer() {
		this.app.transfer(this.ID, this.amount, this.from, this.to);

	}

	public void openFrame2() {
		CustomerFrame frame = new CustomerFrame(this);
	}

	public void openFrame3() {
		AccountFrame frame = new AccountFrame(this);
	}

	public void openFrame4() {
		BalanceFrame frame = new BalanceFrame(this);
	}

	public void openFrame5(String action) {
		AmountFrame frame = new AmountFrame(this, action);
	}

	public void openFrame6() {
		TransferFrame frame = new TransferFrame(this);
	}

}
