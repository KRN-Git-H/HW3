package controller.member;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.ManagerUI;
import controller.login.LoginUI;
import dao.impl.ParkingRecordDaoImpl;
import model.Member;
import model.MonthRent;
import model.ParkingRecord;
import model.Porder;
import service.impl.MemberServiceImpl;
import service.impl.MonthRentServiceImpl;
import service.impl.PorderServiceImpl;
import util.Tool;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class MemberModifyProfitUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField phone;
	private JTextField license;
	private JTextField account;
	private JPasswordField password;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberModifyProfitUI frame = new MemberModifyProfitUI();
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
	public MemberModifyProfitUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 661, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnNewButton = new JButton("回管理頁");
		btnNewButton.setBounds(255, 392, 87, 23);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ManagerUI pm=new ManagerUI();
				pm.setVisible(true);
				dispose();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(11, 20, 625, 134);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("姓名");
		lblNewLabel.setBounds(10, 10, 30, 15);
		panel.add(lblNewLabel);
		
		JLabel lblLcd = new JLabel("電話");
		lblLcd.setBounds(142, 10, 30, 15);
		panel.add(lblLcd);
		
		JLabel lblRam = new JLabel("車牌");
		lblRam.setBounds(277, 10, 30, 15);
		panel.add(lblRam);
		
		JLabel lblMouse = new JLabel("身份別");
		lblMouse.setBounds(409, 10, 53, 15);
		panel.add(lblMouse);
		
		name = new JTextField();
		name.setBounds(10, 35, 96, 21);
		panel.add(name);
		name.setColumns(10);
		
		phone = new JTextField();
		phone.setBounds(142, 35, 96, 21);
		phone.setColumns(10);
		panel.add(phone);
		
		license = new JTextField();
		license.setBounds(277, 35, 96, 21);
		license.setColumns(10);
		panel.add(license);

		JLabel lblNewLabel_3 = new JLabel("Account");
		lblNewLabel_3.setBounds(10, 77, 96, 15);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Password");
		lblNewLabel_3_1.setBounds(142, 77, 96, 15);
		panel.add(lblNewLabel_3_1);
		
		account = new JTextField();
		account.setBounds(10, 101, 96, 21);
		account.setEditable(false);

		account.setColumns(10);
		panel.add(account);

		password = new JPasswordField();
		password.setBounds(142, 102, 96, 23);

		password.setEchoChar('*');
		panel.add(password);
		
		
		//================================================= Table =================================================
		
        // 2. 定義表格的欄位名稱 (Column Names)
        String[] columnNames = {"車號", "進場時間", "出場時間", "停車時間","繳費金額"};
        
        getContentPane().setLayout(null);

     // 4.1. 建立 DefaultTableModel
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0); // 0 代表一開始沒有資料

        // 4.2. 建立 JTable 並將 tableModel 傳入
        JTable table = new JTable(tableModel);

        tableModel.getDataVector().removeAllElements();
        tableModel.fireTableDataChanged();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        //第五欄位右對齊
		table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer); 
		// 手動設定欄寬
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(4).setPreferredWidth(0);
		
        // 5. 啟用 JTable 的滾動功能
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 168, 625, 211);

        // 6. 將滾動面板加入視窗中
        getContentPane().add(scrollPane);	
		
		
        //================================================= button =============================================
		
		JToggleButton showPassword = new JToggleButton("顯示密碼");
		showPassword.setBounds(277, 102, 99, 24);
		showPassword.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
		        if (showPassword.isSelected()) {
		            // 顯示密碼
		            password.setEchoChar((char) 0);
		        } else {
		            // 隱藏密碼
		            password.setEchoChar('*');
		        }
			}
		});
		panel.add(showPassword);
		
		Member member=(Member)Tool.readFile("member.txt");		
		
		JComboBox category = new JComboBox();
		category.setBounds(409, 34, 96, 23);
		if (Tool.isAdmin(member))
		{
			category.setModel(new DefaultComboBoxModel(new String[] {"admin"}));
		}
		else
		{
			category.setModel(new DefaultComboBoxModel(new String[] {"一般","身障","里民"}));
		}
		category.setBackground(SystemColor.text);
		panel.add(category);
		

		//======================================= setup =======================================
		
		MemberServiceImpl MemberSI = new MemberServiceImpl();	
		
		account.setText(member.getAccount());
		password.setText(member.getPassword());
		name.setText(member.getName());
		phone.setText(member.getPhone());	
		license.setText(member.getLicense());
		category.setSelectedItem(member.getCategory());
		
		
		String[] opt = {"新增(未完)","查詢","修改","刪除","列印","匯出"}; 
		JComboBox function = new JComboBox(opt);
		function.setBackground(SystemColor.text);
		function.setBounds(411, 66, 97, 28);
		function.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(function);
		
		JButton btnNewButton_3 = new JButton("確認");
		btnNewButton_3.setBounds(411, 103, 98, 23);
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
	
				String choice = function.getSelectedItem().toString();
				
				if(choice=="新增")
				{
		
				}
				else if(choice=="查詢")
				{
					List<ParkingRecord> pr=new ParkingRecordDaoImpl().readLicense(member.getLicense());
//					List<ParkingRecord> pr=new ParkingRecordDaoImpl().readAll();
					
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					
					for(ParkingRecord parkingrecord:pr)
					{
						
						tableModel.addRow(new Object[]{parkingrecord.getLicense(), 
													   parkingrecord.getEntryTime().format(formatter),
													   parkingrecord.getExitTime().format(formatter),
													   parkingrecord.getParkingTime(),
													   parkingrecord.getPaymentAmount(),
													  });
							
						System.out.println(parkingrecord.getLicense() + "\t" +
										   parkingrecord.getEntryTime().format(formatter) + "\t" +
										   parkingrecord.getExitTime().format(formatter) + "\t" +
										   parkingrecord.getParkingTime() + "\t" +
										   parkingrecord.getPaymentAmount() 
						);
					}
				}
				else if(choice =="修改")
				{
					member.setPassword(String.valueOf(password.getPassword()));
					member.setName(name.getText());
					member.setPhone(phone.getText());
					member.setLicense(license.getText());
					member.setCategory(category.getSelectedItem().toString());

					MemberSI.update(member);
					
					Tool.saveFile(member, "member.txt");
					
					JOptionPane.showMessageDialog(contentPane,"會員資料修改成功。","訊息",JOptionPane.INFORMATION_MESSAGE);
					System.out.println("會員資料修改成功。");
				}
				else if(choice=="刪除")
				{
			        // 顯示確認視窗
			        int result = JOptionPane.showConfirmDialog(
			        	contentPane,
			            "您確定要刪除帳號嗎？",
			            "刪除確認",
			            JOptionPane.YES_NO_OPTION,
			            JOptionPane.WARNING_MESSAGE
			        );
			        
			        if (result == JOptionPane.YES_OPTION) {
			        	MemberSI.delete(member);
			        	
			            JOptionPane.showMessageDialog(contentPane,"帳號刪除成功。","訊息",JOptionPane.INFORMATION_MESSAGE);
			            System.out.println("確認刪除帳號。");
			            
						LoginUI login =new LoginUI();
						login.setVisible(true);
						dispose();

			        } else if (result == JOptionPane.NO_OPTION) {
			            System.out.println("取消刪除帳號。");

			        } else {
			            System.out.println("視窗已關閉。");
			        }
				}
				else if(choice=="列印")
				{
		            MessageFormat header = new MessageFormat("使用者資料");
		            MessageFormat footer = new MessageFormat("第 {0} 頁");
					 try {
						boolean complete = table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
					} catch (PrinterException e1) {
						e1.printStackTrace();
					}
				}	
				
				else if(choice=="匯出")
				{
					Tool.TabletoExcel(table, "Table.xlsx");
					Tool.alarmMsg(contentPane,"資料匯出成功。");
				}
			}
		});
		panel.add(btnNewButton_3);
		

		

	}
}
