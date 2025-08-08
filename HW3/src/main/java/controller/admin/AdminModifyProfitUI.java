package controller.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.ManagerUI;
import dao.impl.MonthRentDaoImpl;
import model.MonthRent;
import service.impl.MemberServiceImpl;
import service.impl.MonthRentServiceImpl;
import util.Tool;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.SystemColor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class AdminModifyProfitUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField expirytime;
	private JTextField paymenttime;
	private JTextField license;
	private JTextField paymentamount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminModifyProfitUI frame = new AdminModifyProfitUI();
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
	public AdminModifyProfitUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 661, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBounds(10, 26, 625, 132);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblLcd = new JLabel("繳費日期");
		lblLcd.setBounds(142, 10, 95, 15);
		panel.add(lblLcd);
		
		JLabel lblRam = new JLabel("車牌");
		lblRam.setBounds(9, 10, 30, 15);
		panel.add(lblRam);

		license = new JTextField();
		license.setColumns(10);
		license.setBounds(9, 35, 96, 21);
		panel.add(license);
		
		JLabel lblNewLabel = new JLabel("到期日期");
		lblNewLabel.setBounds(148, 67, 92, 15);
		panel.add(lblNewLabel);
		
		expirytime = new JTextField();
		expirytime.setEditable(false);
		expirytime.setBounds(143, 91, 204, 21);
		panel.add(expirytime);
		expirytime.setColumns(10);

		
		JLabel lblNewLabel_1 = new JLabel("繳費金額");
		lblNewLabel_1.setBounds(381, 18, 107, 19);
		panel.add(lblNewLabel_1);
		
		paymentamount = new JTextField();
		paymentamount.setEditable(false);
		paymentamount.setBounds(379, 40, 96, 21);
		panel.add(paymentamount);
		paymentamount.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("繳費方式");
		lblNewLabel_1_1.setBounds(376, 69, 107, 19);
		panel.add(lblNewLabel_1_1);
		
		//================================================= Table =================================================
		
        // 2. 定義表格的欄位名稱 (Column Names)
        String[] columnNames = {"車號", "繳費時間", "截止時間", "繳費金額","繳費方式"};
        
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
		
		
		
		

        // 4.3. 動態新增資料列
//      tableModel.addRow(new Object[]{"101", "陳小明", "三年級", "男"});
//      tableModel.addRow(new Object[]{"102", "林小華", "二年級", "女"});

      
      // 4.4. 可以在任何時候新增、刪除或更新資料
      // tableModel.removeRow(0); // 刪除第一列
      // tableModel.setValueAt("王大明", 0, 1); // 修改第一列第二欄的資料

      // 5. 啟用 JTable 的滾動功能
//      JScrollPane scrollPane = new JScrollPane(table);
//      scrollPane.setBounds(10, 168, 625, 211);

      // 6. 將滾動面板加入視窗中
  //    getContentPane().add(scrollPane);
        
        
        
        
//        // 新增滑鼠監聽器，當點擊表格時取得選取資料
//        table.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                // 取得選取列的索引
//                int selectedRowIndex = table.getSelectedRow();
//
//                // 檢查是否有選取列
//                if (selectedRowIndex != -1) {
//                    // 取得模型中的真實索引，以處理排序問題
//                    int modelRowIndex = table.convertRowIndexToModel(selectedRowIndex);
//                    
//                    // 從模型中取得該列的資料
//                    Object license = tableModel.getValueAt(modelRowIndex, 0);
//                    Object paymenttime = tableModel.getValueAt(modelRowIndex, 1);
//                    Object expirytime = tableModel.getValueAt(modelRowIndex, 2);
//                    Object paymentamount = tableModel.getValueAt(modelRowIndex, 3);
//                    Object paymentmethod = tableModel.getValueAt(modelRowIndex, 4);
//                    
//                    
//                    System.out.println(license + "\t" + 
//                                       paymenttime + "\t" + 
//                    		           expirytime + "\t" +
//                                       paymentamount + "\t" +
//                    		           paymentmethod
//                    		           );
//                }
//            }
//
//        });

        
        
        
        
        
        
        
        
        
		//======================================= setup =======================================
		
		MemberServiceImpl MemberSI = new MemberServiceImpl();	
		MonthRentServiceImpl MonthRentSI = new MonthRentServiceImpl();	
		
//		Member member=(Member)Tool.readFile("member.txt");
//		paymenttime.setText(member.getPhone());	
//		license.setText(member.getLicense());
//		category.setSelectedItem(member.getCategory());
		


		//button
		
		JButton btnNewButton = new JButton("返回功能表");
		btnNewButton.setBounds(240, 389, 116, 23);
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
		
		String[] methodOpt = {"請選擇","月繳","季繳"};
		JComboBox paymentmethod = new JComboBox(methodOpt);
		paymentmethod.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (license.getText().isEmpty())
				{
					Tool.alarmMsg(contentPane,"請先輸入車號。");
				}
			}
		});		
		
		paymentmethod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (license.getText().isEmpty())
				{
					Tool.alarmMsg(contentPane,"請先輸入車號。");
				}
				else
				{
					//取得付款方式
	                String choice = paymentmethod.getSelectedItem().toString();
	                int addMonth = choice =="季繳" ? 3:1;
					
	                //取得截止日期
	                LocalDateTime localDateTime = Tool.StrToLocalDateTime(paymenttime.getText(),addMonth);
			        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	                expirytime.setText(localDateTime.format(format).toString());				
	
	                int amount = new MemberServiceImpl().readAmount(license.getText()) * addMonth;
					paymentamount.setText(Integer.toString(amount));
				}
			}
		});

		paymentmethod.setBackground(SystemColor.text);
		paymentmethod.setBounds(371, 98, 121, 21);
		panel.add(paymentmethod);
		
		paymenttime = new JTextField();
		paymenttime.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (license.getText().isEmpty())
				{
					Tool.alarmMsg(contentPane,"請先輸入車號。");
				}
				else	
				{
					//選擇日期
					String selectdata = Tool.SelectData(AdminModifyProfitUI.this);
		            paymenttime.setText(selectdata);
				}
			}
		});
		paymenttime.setColumns(10);
		paymenttime.setBounds(142, 35, 205, 21);
		panel.add(paymenttime);
		
		String[] opt = {"新增","查詢","修改","刪除","列印","匯出"}; 
		JComboBox function = new JComboBox(opt);
		function.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String choice = function.getSelectedItem().toString();
				
				if(choice=="新增")
				{
					paymenttime.setEnabled(true);
					paymenttime.setEditable(true);
					paymentmethod.setEnabled(true);
					paymentmethod.setEditable(true);
				}
				else if(choice=="查詢")
				{
					paymenttime.setEnabled(false);
					paymenttime.setEditable(false);
					paymentmethod.setEnabled(false);
					paymentmethod.setEditable(false);
				}
				else if(choice =="修改")
				{
					paymenttime.setEnabled(true);
					paymenttime.setEditable(true);
					paymentmethod.setEnabled(true);
					paymentmethod.setEditable(true);
				}
				else if(choice=="刪除")
				{
					paymenttime.setEnabled(false);
					paymenttime.setEditable(false);
					paymentmethod.setEnabled(false);
					paymentmethod.setEditable(false);
				}
				
			}
		});
		function.setBackground(SystemColor.text);
		function.setBounds(517, 31, 96, 23);
		panel.add(function);

		JButton btnNewButton_1 = new JButton("確認");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String choice = function.getSelectedItem().toString();
				
				if(choice=="新增")
				{
					try 
					{
						String License = license.getText();
						LocalDateTime PaymentTime = Tool.StrToLocalDateTime(paymenttime.getText(),0);			
						LocalDateTime ExpiryTime = Tool.StrToLocalDateTime(expirytime.getText(),0);			
						int PaymentAmount = Integer.parseInt(paymentamount.getText());			
						String PaymentMethod = paymentmethod.getSelectedItem().toString();
	
						MonthRent monthrent = new MonthRent(License,PaymentTime,ExpiryTime,PaymentAmount,PaymentMethod);
						
			            if (PaymentAmount==0) {
			                throw new IllegalArgumentException("月租資料新增失敗。");
			            }
			            else
			            {
							if(MonthRentSI.create(monthrent))
							{
								Tool.alarmMsg(contentPane,"月租資料新增成功。");
							}
							else
							{
								Tool.alarmMsg(contentPane,"月租資料重覆。");
							}
			            }
					}
					catch (IllegalArgumentException E)
					{
						Tool.alarmMsg(contentPane,E.getMessage());
					}
					
					catch (Exception E)
					{
						Tool.alarmMsg(contentPane,"資料內容有誤。");
					}
				}
				else if(choice=="查詢")
					{
				        tableModel.getDataVector().removeAllElements();
				        tableModel.fireTableDataChanged();
					
						if(license.getText().isEmpty())
								
						{
							List<MonthRent> MonthRentSI=new MonthRentServiceImpl().readAll();
							
							for(MonthRent monthrent:MonthRentSI)
							{
								
								tableModel.addRow(new Object[]{monthrent.getLicense(), 
															   monthrent.getPaymentTime().format(formatter),
															   monthrent.getExpiryTime().format(formatter),
															   monthrent.getPaymentAmount(),
															   monthrent.getPaymentMethod(),
															  });
									
								System.out.println(monthrent.getLicense() + "\t" +
										monthrent.getPaymentTime().format(formatter) + "\t" +
										monthrent.getExpiryTime().format(formatter) + "\t" +
										monthrent.getPaymentAmount() + "\t" +
										monthrent.getPaymentMethod() 
								);
							}
						}
						else
						{
							try
							{
							MonthRent monthrent=new MonthRentServiceImpl().readLicense(license.getText());
							tableModel.addRow(new Object[]{monthrent.getLicense(),
														   monthrent.getPaymentTime().format(formatter),
														   monthrent.getExpiryTime().format(formatter),
														   monthrent.getPaymentAmount(),
														   monthrent.getPaymentMethod()
														   });
							}
							catch(Exception E)
							{
								Tool.alarmMsg(contentPane,"無此車號，請註冊會員登錄車號。");
							}
						}
					}
				else if(choice =="修改")
					{
						String License = license.getText();
						LocalDateTime PaymentTime = Tool.StrToLocalDateTime(paymenttime.getText(),0);			
						LocalDateTime ExpiryTime = Tool.StrToLocalDateTime(expirytime.getText(),0);			
						int PaymentAmount = Integer.parseInt(paymentamount.getText());			
						String PaymentMethod = paymentmethod.getSelectedItem().toString();
	
						MonthRent monthrent = new MonthRent(License,PaymentTime,ExpiryTime,PaymentAmount,PaymentMethod);
						
						if(MonthRentSI.update(monthrent))
						{
							Tool.alarmMsg(contentPane,"月租資料修改成功。");
						}
						else
						{
							Tool.alarmMsg(contentPane,"無此月租資料。");
						}
					}
				else if(choice=="刪除")
				{
					MonthRent monthrent = new MonthRent();
					monthrent.setLicense(license.getText());
					
					if(MonthRentSI.delete(monthrent))
					{
						Tool.alarmMsg(contentPane,"月租資料刪除成功。");
					}
					else
					{
						Tool.alarmMsg(contentPane,"無此月租資料。");
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
		btnNewButton_1.setBounds(517, 63, 85, 23);
		panel.add(btnNewButton_1);
		

		JButton btnNewButton_2 = new JButton("取消");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				license.setText("");
				paymenttime.setText("");
				expirytime.setText("");
				paymentamount.setText("");
				paymentmethod.setSelectedItem("請選擇");

			}
		});
		btnNewButton_2.setBounds(515, 91, 87, 23);
		panel.add(btnNewButton_2);

		
		
		
		

		
		
		


		

	}
}
