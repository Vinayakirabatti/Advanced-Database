package fragmentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTable;
class T1 extends Thread
{
	JTable table;
	public T1(JTable table) {
		this.table=table;
		// TODO Auto-generated constructor stub
	}

	public synchronized void run()
	{

		Connection myConn = null;
		Statement myStmt1= null;
		ResultSet myRs1=null;
		

		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/partition", "root","");
		
			// 2. Create a statement
			myStmt1 = myConn.createStatement();

			
			// 3. Execute SQL query
			myRs1= myStmt1.executeQuery("SELECT count(id) FROM details");
			table.setModel(DbUtils.resultSetToTableModel(myRs1));

			ArrayList a=new ArrayList();
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}

	}
}
class T2 extends Thread
{
	JTable table_1;

	public T2(JTable table_1) {
		this.table_1=table_1;
		// TODO Auto-generated constructor stub
	}

	public synchronized void run()
	{

		
		ResultSet myRs = null;
		Statement myStmt = null;
		Connection myConn = null;

		try {
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/partition", "root","");

			myStmt = myConn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			myRs = myStmt.executeQuery("select id from details where id between 2 and 5");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table_1.setModel(DbUtils.resultSetToTableModel(myRs));






		System.out.println("hello");

	}
}
public class intraquery extends JFrame {

	public JPanel contentPane;
	private JTable table_2;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					intraquery frame = new intraquery();
				 
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
	public intraquery() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1436, 762);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		 JTable table;
		table = new JTable();
		table.setBounds(345, 339, 214, 161);
		contentPane.add(table);
		JLabel lblIntraQueryParallelism = new JLabel("INTRA QUERY PARALLELISM");
		lblIntraQueryParallelism.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblIntraQueryParallelism.setBounds(554, 35, 362, 53);
		contentPane.add(lblIntraQueryParallelism);
		
		JLabel lblNewLabel = new JLabel("select count(id) from details where id between 2 and 5;");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(466, 134, 450, 28);
		contentPane.add(lblNewLabel);
		
		JButton btnExecute = new JButton("EXECUTE");
		btnExecute.setBounds(643, 192, 97, 25);
		contentPane.add(btnExecute);
		
		JTable table_1;
		table_1 = new JTable();
		table_1.setBounds(1056, 325, 171, 150);
		contentPane.add(table_1);
		
		
		JLabel lblThisQueryIs = new JLabel("THIS QUERY IS PARTITIONED INTO 2 PARTS");
		lblThisQueryIs.setBounds(575, 241, 263, 16);
		contentPane.add(lblThisQueryIs);
		
		JLabel lblselectIdFrom = new JLabel("2.select id from details where id between 2 and 5");
		lblselectIdFrom.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblselectIdFrom.setBounds(967, 294, 413, 16);
		contentPane.add(lblselectIdFrom);
		
		JLabel lblselectCountidFrom = new JLabel("1.select count(id) from details");
		lblselectCountidFrom.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblselectCountidFrom.setBounds(314, 310, 312, 16);
		contentPane.add(lblselectCountidFrom);
		
		JLabel lblCombiningBothResults = new JLabel("COMBINING BOTH RESULTS");
		lblCombiningBothResults.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
		lblCombiningBothResults.setBounds(648, 538, 291, 16);
		contentPane.add(lblCombiningBothResults);
		
		table_2 = new JTable();
		table_2.setBounds(739, 601, 99, 76);
		contentPane.add(table_2);
		btnExecute.addActionListener(new ActionListener() {
			String a;
			public void actionPerformed(ActionEvent arg0) {
	            //System.out.println("SELECT * FROM employees where "+spinner_1.getValue()+"="+textField_1.getText());
	            
				//Fragdb f=new Fragdb();
				//spinner.addChangeListener(f);
				//System.out.print("hi"+spinner.getValue());

				T1 t1=new T1(table);
				T2 t2=new T2(table_1);
				t1.start();
				t2.start();
				
				
				ResultSet myRs = null;
				Statement myStmt = null;
				Connection myConn = null;

				try {
					myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/partition", "root","");

					myStmt = myConn.createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					myRs = myStmt.executeQuery("select count(id) from details where id between 2 and 5");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				table_2.setModel(DbUtils.resultSetToTableModel(myRs));

				
				
				
			}
		});
	}
}
