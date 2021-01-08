package fragmentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SpinnerListModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSpinner;

public class partition extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	String[] columns= {"c1","c2","c3","c4"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					partition frame = new partition();
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
	public partition() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1354, 733);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton show = new JButton("SHOW TABLE");
		show.setBounds(151, 67, 170, 25);
		contentPane.add(show);
		
		table = new JTable();
		table.setBounds(26, 119, 473, 379);
		contentPane.add(table);
		
		JLabel lblHashPartitioning = new JLabel("HASH PARTITIONING");
		lblHashPartitioning.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblHashPartitioning.setBounds(779, 101, 359, 82);
		contentPane.add(lblHashPartitioning);
		
		table_1 = new JTable();
		table_1.setBounds(725, 294, 359, 151);
		contentPane.add(table_1);
		
		JLabel lblSelectFrom = new JLabel("SELECT * FROM DETAILS PARTITION");
		lblSelectFrom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSelectFrom.setBounds(725, 226, 339, 31);
		contentPane.add(lblSelectFrom);
		
		JSpinner spinner = new JSpinner(new SpinnerListModel(columns));
		spinner.setFont(new Font("Tahoma", Font.BOLD, 17));
		spinner.setBounds(1038, 230, 45, 22);
		contentPane.add(spinner);
		
		JButton btnCommit = new JButton("COMMIT");
		btnCommit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCommit.setBounds(849, 256, 97, 25);
		contentPane.add(btnCommit);
		show.addActionListener(new ActionListener() {
			String a;
			public void actionPerformed(ActionEvent arg0) {
	            //System.out.println("SELECT * FROM employees where "+spinner_1.getValue()+"="+textField_1.getText());
	            
				//Fragdb f=new Fragdb();
				//spinner.addChangeListener(f);
				//System.out.print("hi"+spinner.getValue());

				Connection myConn = null;
				Statement myStmt = null;
				ResultSet myRs = null;
				
				try {
					// 1. Get a connection to database
					myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/partition", "root","");
				
					// 2. Create a statement
					myStmt = myConn.createStatement();
					
					// 3. Execute SQL query
					myRs = myStmt.executeQuery("select * from details");
					table.setModel(DbUtils.resultSetToTableModel(myRs));
					ArrayList a=new ArrayList();
				}
				catch (Exception exc) {
					exc.printStackTrace();
				}
				finally {
					if (myRs != null) {
						try {
							myRs.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					if (myStmt != null) {
						try {
							myStmt.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					if (myConn != null) {
						try {
							myConn.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
			}
		});
		btnCommit.addActionListener(new ActionListener() {
			String a;
			public void actionPerformed(ActionEvent arg0) {
	            //System.out.println("SELECT * FROM employees where "+spinner_1.getValue()+"="+textField_1.getText());
	            
				//Fragdb f=new Fragdb();
				//spinner.addChangeListener(f);
				//System.out.print("hi"+spinner.getValue());

				Connection myConn = null;
				Statement myStmt = null;
				ResultSet myRs = null;
				
				try {
					// 1. Get a connection to database
					myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/partition", "root","");
				
					// 2. Create a statement
					myStmt = myConn.createStatement();
					
					// 3. Execute SQL query
					myRs = myStmt.executeQuery("select * from details partition ("+spinner.getValue()+")");
					table_1.setModel(DbUtils.resultSetToTableModel(myRs));
					ArrayList a=new ArrayList();
				}
				catch (Exception exc) {
					exc.printStackTrace();
				}
				finally {
					if (myRs != null) {
						try {
							myRs.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					if (myStmt != null) {
						try {
							myStmt.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					if (myConn != null) {
						try {
							myConn.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
			}
		});
	}
}
