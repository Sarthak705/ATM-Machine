import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ATMSystem extends JFrame implements ActionListener {

    CardLayout card = new CardLayout();
    JPanel mainPanel = new JPanel(card);

    JTextField cardField;
    JPasswordField pinField;

    JTextField withdrawAmount, depositAmount;

    double balance = 5000;  // sample balance

    public ATMSystem() {

        // -------- WINDOW SETTINGS ----------
        setTitle("Modern ATM System");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // FULL SCREEN
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // -------- LOGIN PANEL ----------
        JPanel loginPanel = new GradientPanel();
        loginPanel.setLayout(null);

        JLabel title = new JLabel("ATM MACHINE");
        title.setBounds(500, 80, 500, 80);
        title.setFont(new Font("Arial", Font.BOLD, 50));
        title.setForeground(Color.WHITE);
        loginPanel.add(title);

        JLabel cardLabel = new JLabel("Enter Card Number:");
        cardLabel.setBounds(450, 250, 300, 40);
        cardLabel.setFont(new Font("Arial", Font.BOLD, 22));
        cardLabel.setForeground(Color.WHITE);
        loginPanel.add(cardLabel);

        cardField = new JTextField();
        cardField.setBounds(700, 250, 300, 40);
        cardField.setFont(new Font("Arial", Font.PLAIN, 20));
        loginPanel.add(cardField);

        JLabel pinLabel = new JLabel("Enter PIN:");
        pinLabel.setBounds(450, 320, 300, 40);
        pinLabel.setFont(new Font("Arial", Font.BOLD, 22));
        pinLabel.setForeground(Color.WHITE);
        loginPanel.add(pinLabel);

        pinField = new JPasswordField();
        pinField.setBounds(700, 320, 300, 40);
        pinField.setFont(new Font("Arial", Font.PLAIN, 20));
        loginPanel.add(pinField);

        JButton loginBtn = createButton("LOGIN");
        loginBtn.setBounds(650, 420, 200, 50);
        loginBtn.addActionListener(this);
        loginPanel.add(loginBtn);

        // -------- MENU PANEL ----------
        JPanel menuPanel = new GradientPanel();
        menuPanel.setLayout(null);

        JLabel menuTitle = new JLabel("Welcome to ATM");
        menuTitle.setBounds(520, 50, 500, 70);
        menuTitle.setFont(new Font("Arial", Font.BOLD, 45));
        menuTitle.setForeground(Color.WHITE);
        menuPanel.add(menuTitle);

        JButton btnWithdraw = createButton("Withdraw");
        btnWithdraw.setBounds(520, 200, 300, 55);
        btnWithdraw.addActionListener(this);
        menuPanel.add(btnWithdraw);

        JButton btnDeposit = createButton("Deposit");
        btnDeposit.setBounds(520, 280, 300, 55);
        btnDeposit.addActionListener(this);
        menuPanel.add(btnDeposit);

        JButton btnBalance = createButton("Check Balance");
        btnBalance.setBounds(520, 360, 300, 55);
        btnBalance.addActionListener(this);
        menuPanel.add(btnBalance);

        JButton btnExit = createButton("Exit");
        btnExit.setBounds(520, 440, 300, 55);
        btnExit.addActionListener(this);
        menuPanel.add(btnExit);

        // -------- WITHDRAW PANEL ----------
        JPanel withdrawPanel = new GradientPanel();
        withdrawPanel.setLayout(null);

        JLabel wTitle = new JLabel("Withdraw Amount");
        wTitle.setBounds(520, 100, 500, 60);
        wTitle.setFont(new Font("Arial", Font.BOLD, 40));
        wTitle.setForeground(Color.WHITE);
        withdrawPanel.add(wTitle);

        withdrawAmount = new JTextField();
        withdrawAmount.setBounds(520, 220, 300, 50);
        withdrawAmount.setFont(new Font("Arial", Font.PLAIN, 22));
        withdrawPanel.add(withdrawAmount);

        JButton wConfirm = createButton("Confirm Withdraw");
        wConfirm.setBounds(520, 300, 300, 50);
        wConfirm.addActionListener(this);
        withdrawPanel.add(wConfirm);

        JButton wBack = createButton("Back");
        wBack.setBounds(520, 380, 300, 50);
        wBack.addActionListener(this);
        withdrawPanel.add(wBack);

        // -------- DEPOSIT PANEL ----------
        JPanel depositPanel = new GradientPanel();
        depositPanel.setLayout(null);

        JLabel dTitle = new JLabel("Deposit Amount");
        dTitle.setBounds(520, 100, 500, 60);
        dTitle.setFont(new Font("Arial", Font.BOLD, 40));
        dTitle.setForeground(Color.WHITE);
        depositPanel.add(dTitle);

        depositAmount = new JTextField();
        depositAmount.setBounds(520, 220, 300, 50);
        depositAmount.setFont(new Font("Arial", Font.PLAIN, 22));
        depositPanel.add(depositAmount);

        JButton dConfirm = createButton("Confirm Deposit");
        dConfirm.setBounds(520, 300, 300, 50);
        dConfirm.addActionListener(this);
        depositPanel.add(dConfirm);

        JButton dBack = createButton("Back");
        dBack.setBounds(520, 380, 300, 50);
        dBack.addActionListener(this);
        depositPanel.add(dBack);

        // -------- BALANCE PANEL ----------
        JPanel balancePanel = new GradientPanel();
        balancePanel.setLayout(null);

        JLabel bTitle = new JLabel("Your Balance");
        bTitle.setBounds(520, 100, 500, 60);
        bTitle.setFont(new Font("Arial", Font.BOLD, 40));
        bTitle.setForeground(Color.WHITE);
        balancePanel.add(bTitle);

        JLabel bValue = new JLabel();
        bValue.setBounds(520, 220, 500, 60);
        bValue.setFont(new Font("Arial", Font.BOLD, 35));
        bValue.setForeground(Color.WHITE);
        balancePanel.add(bValue);

        JButton bBack = createButton("Back");
        bBack.setBounds(520, 360, 300, 50);
        bBack.addActionListener(e -> card.show(mainPanel, "menu"));
        balancePanel.add(bBack);

        // Add all screens
        mainPanel.add(loginPanel, "login");
        mainPanel.add(menuPanel, "menu");
        mainPanel.add(withdrawPanel, "withdraw");
        mainPanel.add(depositPanel, "deposit");
        mainPanel.add(balancePanel, "balance");

        add(mainPanel);
        setVisible(true);
    }

    // ---------- Beautified Button ----------
    JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 22));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(60, 90, 200));
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        return btn;
    }

    // ---------- Button Actions ----------
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd) {
            case "LOGIN":
                String cardNum = cardField.getText();
                String pinNum = new String(pinField.getPassword());

                if (cardNum.equals("123456") && pinNum.equals("1234")) {
                    card.show(mainPanel, "menu");
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Card or PIN");
                }
                break;

            case "Withdraw":
                card.show(mainPanel, "withdraw");
                break;

            case "Deposit":
                card.show(mainPanel, "deposit");
                break;

            case "Check Balance":
                JOptionPane.showMessageDialog(this, "Current Balance: ₹" + balance);
                break;

            case "Exit":
                System.exit(0);
                break;

            case "Confirm Withdraw":
                double w = Double.parseDouble(withdrawAmount.getText());
                if (w <= balance) {
                    balance -= w;
                    JOptionPane.showMessageDialog(this, "Withdraw Successful!");
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient Balance!");
                }
                card.show(mainPanel, "menu");
                break;

            case "Confirm Deposit":
                double d = Double.parseDouble(depositAmount.getText());
                balance += d;
                JOptionPane.showMessageDialog(this, "Deposit Successful!");
                card.show(mainPanel, "menu");
                break;

            case "Back":
                card.show(mainPanel, "menu");
                break;
        }
    }

    // ---------- Gradient Background Panel ----------
    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            GradientPaint gp = new GradientPaint(0, 0, new Color(45, 60, 150), getWidth(), getHeight(), new Color(80, 150, 240));
            g2.setPaint(gp);
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    public static void main(String[] args) {
        new ATMSystem();
    }
}
