package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mysql.cj.result.Row;

import controller.admin.AdminModifyProfitUI;
import model.Member;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;


public class Tool {

	public static void main(String[] args) {
//		Tool.saveFile(new Member("abc","abc","789","taipei","000"), "c:/ABC/member.txt");
//		
//		System.out.println(Tool.readFile("c:/ABC/member.txt"));
		
//		Member m=(Member)Tool.readFile("c:/ABC/member.txt");
//				
//		System.out.println(m.getName()+"\t"+m.getAccount()+"\t"+m.getPassword());	

	}
	
	//存物件檔
	public static void saveFile(Object object,String fileName)
	{
		try {
			FileOutputStream fos=new FileOutputStream(fileName);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(object);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//讀取物件檔
	public static Object readFile(String fileName)
	{
		Object object=null;
		
		try {
			FileInputStream fis=new FileInputStream(fileName);
			ObjectInputStream ois=new ObjectInputStream(fis);
			object=ois.readObject();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return object;
	}
	
	//查詢管理者
	public static boolean isAdmin(Member member)
	{
		return member.getCategory().equals("admin") ? true:false;	
	}
	
	
    public static LocalDateTime StrToLocalDateTime(String strDateTime,int addMonth)
    {
	    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    LocalDateTime localDateTime = LocalDateTime.parse(strDateTime, format);
	    
	    LocalDateTime newDateTime = localDateTime.plusMonths(addMonth);

	    return newDateTime;
    }
    
    
    public static String SelectData(AdminModifyProfitUI test)
    {
    	final String[] result = {null};
    	
	    // 1. 建立 JDatePicker 元件
	    UtilDateModel model = new UtilDateModel();
	    JDatePanelImpl datePanel = new JDatePanelImpl(model);
	    JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
	
	    // 2. 建立 JDialog，並將 datePicker 放入其中
	    // JDialog 的父視窗設為 AdminModifyProfitUI.this
	    JDialog dateDialog = new JDialog(test, "選擇日期", true);
	    dateDialog.getContentPane().add(datePicker);
	    dateDialog.pack(); // 自動調整大小
	    dateDialog.setLocationRelativeTo(test); // 視窗置中
	
	    // 3. 為 JDatePicker 添加監聽器，當選擇日期後觸發
	    datePicker.addActionListener(actionEvent -> {
	        // 從 JDatePicker 的 Model 中取得選定的日期 (java.util.Date)
	        Date selectedDate = (Date) datePicker.getModel().getValue();
	        
	        if (selectedDate != null) {
	            // 將選定的日期與當前時間合併
	            Calendar calendar = Calendar.getInstance();
	            calendar.setTime(selectedDate);
	            
	            Calendar now = Calendar.getInstance();
	            calendar.set(Calendar.HOUR_OF_DAY, now.get(Calendar.HOUR_OF_DAY));
	            calendar.set(Calendar.MINUTE, now.get(Calendar.MINUTE));
	            calendar.set(Calendar.SECOND, now.get(Calendar.SECOND));
	
	            // 創建 SimpleDateFormat 以格式化日期與時間
	            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            
	            // 將合併後的日期與時間格式化為字串
	            String formattedDateTime = formatter.format(calendar.getTime());
	            
	            result[0] = formattedDateTime;

	            System.out.println(formattedDateTime);
	        }
	
	        // 選擇完畢後，關閉彈出的對話框
	        dateDialog.dispose();
	    });
	
	    // 4. 顯示對話框
	    dateDialog.setVisible(true);
	    
	    return result[0];
    }

    public static void alarmMsg(JPanel panel,String Msg)
	{
		JOptionPane.showMessageDialog(panel,Msg,"Message",JOptionPane.INFORMATION_MESSAGE);
		System.out.println(Msg);
	}    
    
    public static void TabletoExcel(JTable table, String filePath)
    {
        try (Workbook workbook = new XSSFWorkbook();
                FileOutputStream fileOut = new FileOutputStream(filePath)) {

               Sheet sheet = workbook.createSheet("JTable Data"); // 這行現在會正常運作
               TableModel model = table.getModel();

               // 寫入表頭
               org.apache.poi.ss.usermodel.Row headerRow = sheet.createRow(0);
               for (int i = 0; i < model.getColumnCount(); i++) {
                   Cell cell = headerRow.createCell(i);
                   cell.setCellValue(model.getColumnName(i));
               }

               // 寫入表格資料
               for (int rows = 0; rows < model.getRowCount(); rows++) {
                   org.apache.poi.ss.usermodel.Row row = sheet.createRow(rows + 1);
                   for (int cols = 0; cols < model.getColumnCount(); cols++) {
                       Cell cell = row.createCell(cols);
                       Object value = model.getValueAt(rows, cols);
                       if (value != null) {
                           cell.setCellValue(value.toString());
                       }
                   }
               }

               workbook.write(fileOut);
               System.out.println("Excel 檔案已成功建立在: " + filePath);

           } catch (IOException e) {
               e.printStackTrace();
               System.err.println("匯出 Excel 檔案時發生錯誤。");
           }
    }
    

    
    
}