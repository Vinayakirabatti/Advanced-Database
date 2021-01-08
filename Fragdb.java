package fragmentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.sql.*;
import java.util.ArrayList;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.SpinnerListModel;
import javax.swing.JSpinner;

public class Fragdb extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JButton horizontal;
	String[] columns= {"first_name","last_name","salary","department","email"};
	private JLabel lblFrom;
	private JSpinner spinner_1;
	private JLabel label;
	private JTextField textField_1;
	JSpinner horispinner1;
	JSpinner horispinner2 ;
	private JLabel lblFragmentation;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)throws SQLException {
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fragdb frame = new Fragdb();
					Container c = frame.getContentPane();

					c.setBackground(Color.yellow);
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
	public Fragdb() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println();
		setBounds(100, 100, 1393, 763);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn = new JButton("SHOW DATABASE");
		btn.addActionListener(new ActionListener() {
			String a;
			public void actionPerformed(ActionEvent arg0) {
	            System.out.println("SELECT * FROM employees where "+spinner_1.getValue()+"="+textField_1.getText());
	            
				//Fragdb f=new Fragdb();
				//spinner.addChangeListener(f);
				//System.out.print("hi"+spinner.getValue());

				Connection myConn = null;
				Statement myStmt = null;
				ResultSet myRs = null;
				
				try {
					// 1. Get a connection to database
					myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fragment", "root","");
				
					// 2. Create a statement
					myStmt = myConn.createStatement();
					
					// 3. Execute SQL query
					myRs = myStmt.executeQuery("select * from employees");
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
		btn.setBounds(206, 142, 143, 25);
		contentPane.add(btn);
		
		table = new JTable();
		table.setBounds(42, 180, 519, 381);
		contentPane.add(table);
		
		textField = new JTextField();
		textField.setBounds(287, 223, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton vertical = new JButton("VERTICAL");
		vertical.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection c = null;
			      Statement stmt = null;
			      try {
			         Class.forName("org.postgresql.Driver");
			         c = DriverManager
			            .getConnection("jdbc:postgresql://localhost:5432/demo",
			            "postgres", "root");
			         System.out.println("Opened database successfully");

			         stmt = c.createStatement();
			         String sql1 = "CREATE TABLE TABLE1 " +
			            "(COLUMN1 TEXT      ," +
			            " COLUMN2           TEXT    )";
			         
//			         String sql = "CREATE TABLE TABLE2 " +
//					            "( NAME           TEXT    NOT NULL, " +
//					            " LASTNAME            TEXT     NOT NULL)";
			        // stmt.executeUpdate(sql);
			         stmt.executeUpdate(sql1);

			         stmt.close();
			         c.close();
			      } catch ( Exception e ) {
			         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			         System.exit(0);
			      }
			      System.out.println("Table created successfully");
				try {
		            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fragment", "root","");
		            Connection con1 = DriverManager
		                    .getConnection("jdbc:postgresql://localhost:5432/demo",
		                            "postgres", "root");
		                         System.out.println("Opened database successfully");

		            String sql1 = "INSERT INTO TABLE1("+ "COLUMN1,"+ "COLUMN2)"+ "VALUES(?,?)";
		            //String sql = "INSERT INTO TABLE2("+ "NAME,"+ "LASTNAME)"+ "VALUES(?,?)";

		            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

		           // PreparedStatement pstmt = con1.prepareStatement(sql);
		            PreparedStatement pstmt1 = con1.prepareStatement(sql1);


		            ResultSet rs = statement.executeQuery("SELECT * FROM employees");
		            while (rs.next()) {
		               // String nm = rs.getString("id");
		                String log = rs.getString("last_name");
		                String pass = rs.getString("first_name");
		                String f=(String) horispinner1.getValue();
		                String z=(String) horispinner2.getValue();
		                
		                String id = rs.getString(f);
		                String email = rs.getString(z);
		                //String salary = rs.getString("salary");


		                //pstmt.setString(1, nm);
//		                pstmt.setString(1, log);
//		                pstmt.setString(2, pass);
		                
		                pstmt1.setString(1, id);
		                pstmt1.setString(2, email);
		               // pstmt1.setString(3, salary);

		                

		               // pstmt.executeUpdate();
		                pstmt1.executeUpdate();

		            }
		        } catch (SQLException e) {
		        	e.printStackTrace();
		            System.out.println("could not get JDBC connection: " +e);
		        } finally {
		        
		            
		        }
		    
			}
		});
		vertical.setBounds(920, 244, 143, 35);
		contentPane.add(vertical);
		
		horizontal = new JButton("HORIZONTAL");
		horizontal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//String query=horizontalquery.getText().toString();
				//System.out.println(query);
				Connection c = null;
			      Statement stmt = null;
			      try {
			         Class.forName("org.postgresql.Driver");
			         c = DriverManager
			            .getConnection("jdbc:postgresql://localhost:5432/demo",
			            "postgres", "root");
			         System.out.println("Opened database successfully");

			         stmt = c.createStatement();
//			         String sql = "CREATE TABLE HORIZONTAL " +
//					            "( NAME           TEXT    NOT NULL)";
			         String sql = "CREATE TABLE HORIZONTAL " +
					            "( id           TEXT    NOT NULL, " +
					            "first_name           TEXT    NOT NULL, " +
					            "last_name           TEXT    NOT NULL, " +
					            "email           TEXT    NOT NULL, " +
					            " salary            TEXT     NOT NULL)";
			         stmt.executeUpdate(sql);

			         stmt.close();
			         c.close();
			      } catch ( Exception e ) {
			         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			         System.exit(0);
			      }
			      System.out.println("Table created successfully");
				try {
		            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fragment", "root","");
		            Connection con1 = DriverManager
		                    .getConnection("jdbc:postgresql://localhost:5432/demo",
		                            "postgres", "root");
		                         System.out.println("Opened database successfully");

		            String sql = "INSERT INTO HORIZONTAL("+ "id,"+"first_name,"+"last_name,"+"email,"+"salary)"+ "VALUES(?,?,?,?,?)";
		            //String sql1 = "INSERT INTO TABLE1("+ "COLUMN1,"+ "COLUMN2)"+ "VALUES(?,?)";


		            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

		            PreparedStatement pstmt = con1.prepareStatement(sql);


		            ResultSet rs = statement.executeQuery("SELECT * FROM employees where "+spinner_1.getValue()+"="+textField_1.getText());
		           // System.out.println("SELECT "+spinner.getValue()+" FROM employees where"+spinner_1.getValue()+"="+textField_1.getText());
		            while (rs.next()) {
		               String nm = rs.getString("id");
		                String id = rs.getString("first_name");
		                String last = rs.getString("last_name");
		                String email = rs.getString("email");
		                String salary=rs.getString("salary");

//		                
//		                
//
//
		                pstmt.setString(1, nm);
		                pstmt.setString(2, id);
		                pstmt.setString(3, last);
		                pstmt.setString(4, email);
		                pstmt.setString(5, salary);

		                
//		                
//		   

		                

		                pstmt.executeUpdate();

		            }
		        } catch (SQLException e) {
		        	e.printStackTrace();
		            System.out.println("could not get JDBC connection: " +e);
		        } finally {
		        
		            
		        }
				
			}
		});
		horizontal.setBounds(930, 419, 133, 35);
		contentPane.add(horizontal);
		
		JLabel select = new JLabel("SELECT  *");
		select.setBounds(744, 467, 131, 35);
		contentPane.add(select);
		
		lblFrom = new JLabel("FROM  EMPLOYEES WHERE");
		lblFrom.setBounds(830, 467, 193, 30);
		contentPane.add(lblFrom);
		
		 spinner_1=new JSpinner(new SpinnerListModel(columns));
		spinner_1.setBounds(999, 473, 97, 22);
		contentPane.add(spinner_1);
		
		label = new JLabel("=");
		label.setBounds(1122, 476, 56, 16);
		contentPane.add(label);

		textField_1 = new JTextField();
		textField_1.setBounds(1145, 473, 116, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblSelect = new JLabel("SELECT ");
		lblSelect.setBounds(744, 326, 65, 25);
		contentPane.add(lblSelect);
		
		 horispinner1 = new JSpinner(new SpinnerListModel(columns));
		horispinner1.setBounds(808, 327, 84, 22);
		contentPane.add(horispinner1);
		
		JLabel lblFromEmployees = new JLabel("FROM EMPLOYEES");
		lblFromEmployees.setBounds(1035, 328, 143, 21);
		contentPane.add(lblFromEmployees);
		
		 horispinner2 = new JSpinner(new SpinnerListModel(columns));
		horispinner2.setBounds(920, 327, 84, 22);
		contentPane.add(horispinner2);
		
		lblFragmentation = new JLabel("FRAGMENTATION");
		lblFragmentation.setBounds(632, 13, 342, 35);
		contentPane.add(lblFragmentation);
	}
}
