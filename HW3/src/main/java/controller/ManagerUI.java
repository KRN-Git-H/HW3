package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.admin.AdminModifyProfitUI;
import controller.login.LoginUI;
import controller.member.MemberHistoryDataUI;
import controller.member.MemberModifyProfitUI;
import model.Member;
import util.Tool;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class ManagerUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerUI frame = new ManagerUI();
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
	public ManagerUI() {
		setTitle("PorderManager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("會員資料修改");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MemberModifyProfitUI membermodifyprofit =new MemberModifyProfitUI();
				membermodifyprofit.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton_2.setBounds(147, 69, 148, 34);
		contentPane.add(btnNewButton_2);
		
		JLabel logout = new JLabel("");
		logout.setBounds(46, 8, 135, 31);
		contentPane.add(logout);
		Member member=(Member)Tool.readFile("member.txt");
		String show=member.getName()+",歡迎你";
		logout.setText(show);

		JButton monthRent = new JButton("月租繳費查詢");
		
		if(Tool.isAdmin(member))
		{
			monthRent.setVisible(true);
		}
		else
		{
			monthRent.setVisible(false);
		}	

		monthRent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminModifyProfitUI adminmodifyprofitui = new AdminModifyProfitUI();
				adminmodifyprofitui.setVisible(true);
				dispose();
			}
		});
		monthRent.setBounds(147, 123, 148, 31);
		contentPane.add(monthRent);
		
		JButton btnNewButton = new JButton("登出");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginUI loginui = new LoginUI();
				loginui.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(191, 224, 64, 23);
		contentPane.add(btnNewButton);
		

	}
}
