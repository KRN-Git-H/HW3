package controller.member;


import java.awt.EventQueue;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.ManagerUI;
import dao.impl.ParkingRecordDaoImpl;
import model.Member;
import model.ParkingRecord;
import util.Tool;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MemberHistoryDataUI extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberHistoryDataUI frame = new MemberHistoryDataUI();
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
	public MemberHistoryDataUI() {
        // 2. 定義表格的欄位名稱 (Column Names)
        String[] columnNames = {"車號", "進場時間", "出場時間", "停車時間","繳費金額"};
        
        getContentPane().setLayout(null);

     // 4.1. 建立 DefaultTableModel
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0); // 0 代表一開始沒有資料

        // 4.2. 建立 JTable 並將 tableModel 傳入
        JTable table = new JTable(tableModel);
        
		//read
		Member member=(Member)Tool.readFile("member.txt");
		
		List<ParkingRecord> pr=new ParkingRecordDaoImpl().readLicense(member.getLicense());
//		List<ParkingRecord> pr=new ParkingRecordDaoImpl().readAll();
		
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
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        //第五欄位右對齊
		table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer); 
		// 手動設定欄寬
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(4).setPreferredWidth(0);

        // 4.3. 動態新增資料列
//        tableModel.addRow(new Object[]{"101", "陳小明", "三年級", "男"});
//        tableModel.addRow(new Object[]{"102", "林小華", "二年級", "女"});

        
        // 4.4. 可以在任何時候新增、刪除或更新資料
        // tableModel.removeRow(0); // 刪除第一列
        // tableModel.setValueAt("王大明", 0, 1); // 修改第一列第二欄的資料

        // 5. 啟用 JTable 的滾動功能
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 611, 298);

        // 6. 將滾動面板加入視窗中
        getContentPane().add(scrollPane);
        
        JButton btnNewButton = new JButton("返回功能表");
        btnNewButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		ManagerUI managerui = new ManagerUI();
        		managerui.setVisible(true);
        		dispose();
        	}
        });
        btnNewButton.setBounds(201, 323, 166, 30);
        getContentPane().add(btnNewButton);

        // 7. 設定視窗大小並顯示
        setSize(647, 400);
        setLocationRelativeTo(null); // 視窗置中
        setVisible(true);
		
	}
}
