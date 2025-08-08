package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.login.LoginUI;
import controller.member.AddMember;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI frame = new MainUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(124, 86, 419, 237);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton memberLookup = new JButton("會員查詢");
		memberLookup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginUI login =new LoginUI();
				login.setVisible(true);
				dispose();
			}
		});
		memberLookup.setFont(new Font("標楷體", Font.BOLD, 20));
		memberLookup.setBounds(122, 49, 193, 47);
		panel.add(memberLookup);
		
		JButton paymentSystem = new JButton("繳費系統(未完)");
		paymentSystem.setFont(new Font("標楷體", Font.BOLD, 20));
		paymentSystem.setBounds(121, 148, 193, 47);
		panel.add(paymentSystem);

	}

}
