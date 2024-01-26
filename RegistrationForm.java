import java.awt.*;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout.Alignment;

//import javafx.stage.Popup;

class RegistrationForm  extends Frame implements ActionListener
{
	Label label, lblname, lblrollno, lblbranch, lblgen, lblage, lbl, lblpop ;
	
	TextField txtname, txtrollno, txtbranch, txtgen, txtage ;
	
	JButton cancel, submit;

	JRadioButton gen1, gen2, gen3;
	
	Font f ;
	
	RegistrationForm(String title)
	{
        //For Frame
		super(title);
		setSize(600, 600);
		setVisible(true);
		setBackground(Color.orange);

		//Remove Layout Manager
		setLayout(null);


		//Add Label label
		label = new Label("Student Registration Form");
		label.setLocation(100, 40);
		label.setSize(500, 100);
		f = new Font("Comic Sans MS", Font.BOLD, 30);
		label.setFont(f);
		add(label);
		
		//Add Label lblname
		lblname = new Label("Name: ");
		lblname.setLocation(50, 112);
		lblname.setSize(100, 100);
		lblname.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		add(lblname);
		
		//Add Label lblrollno
		lblrollno = new Label("Rollno. : ");
		lblrollno.setLocation(50, 172);
		lblrollno.setSize(100, 100);
		lblrollno.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		add(lblrollno);

		//Add Label lblage
		lblage = new Label("Age: ");
		lblage.setLocation(50, 232);
		lblage.setSize(100, 100);
		lblage.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		add(lblage);

		//Add Label lblgen
		lblgen = new Label("Gender: ");
		lblgen.setLocation(50, 292);
		lblgen.setSize(100, 100);
		lblgen.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		add(lblgen);

		//Add Label lblbranch
		lblbranch = new Label("Branch: ");
		lblbranch.setLocation(50, 410);
		lblbranch.setSize(80, 80);
		lblbranch.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		add(lblbranch);

        //Add Label lbl
        lbl = new Label("Status", Label.CENTER);
        lbl.setLocation(150, 550);
        lbl.setSize(320, 50);
        lbl.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		//lbl.setBackground(Color.blue);
        add(lbl);
		
		//Add Textfield txtname
		txtname = new TextField(20);
		txtname.setLocation(150, 150); 
		txtname.setSize(200, 30);
		add(txtname);
		
		//Add Textfield txtrollno
		txtrollno = new TextField(20);
		txtrollno.setLocation(150, 210);
		txtrollno.setSize(200, 30);
		add(txtrollno);

		//Add Textfield txtage
		txtage = new TextField(20);
		txtage.setLocation(150, 270);
		txtage.setSize(200, 30);
		add(txtage);

        gen1 = new JRadioButton("Male");
		gen2 = new JRadioButton("Female");
		gen3 = new JRadioButton("Other");
		gen1.setBounds(150,320,100,50); 
		gen1.setFont(new Font("Comic Sans MS", Font.BOLD, 13));   
		gen2.setBounds(150,350,100,50);    
		gen2.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		gen3.setBounds(150,380,100,50);
		gen3.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		ButtonGroup bg = new ButtonGroup();    
		bg.add(gen1);
		bg.add(gen2);
		bg.add(gen3);    
		add(gen1);
		add(gen2);
		add(gen3);
		
		//Add Textfield txtbranch
		txtbranch = new TextField(20);
		txtbranch.setLocation(150, 440);
		txtbranch.setSize(200, 30);
		add(txtbranch);
		
		//Add Button cancel
		cancel = new JButton("Cancel");
		cancel.setLocation(250, 500);
		cancel.setSize(90, 30);
		cancel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		cancel.setBackground(Color.blue);
		cancel.setForeground(Color.white);
		add(cancel);
		
		//Add Button Cancel to ActionListener List
		cancel.addActionListener(this);
			
		//Add Button submit
		submit = new JButton("Submit");
		submit.setLocation(150, 500);
		submit.setSize(90, 30);
		submit.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		submit.setBackground(Color.blue);
		submit.setForeground(Color.white);
		add(submit);
		
		//Add Button submit to ActionListener List
		submit.addActionListener(this);

        //Frame Closing
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
	}
	
    public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Submit")) 			
		{
			if(txtname.getText().isEmpty() || txtrollno.getText().isEmpty() || txtage.getText().isEmpty() || txtbranch.getText().isEmpty())
			{			
				lbl.setText("Please Enter valid Information");
				lbl.setForeground(Color.red);
			}
			else
			{
				lbl.setText("Information Added Successfully..");
				lbl.setForeground(Color.green);
			}
			
			try 
		    {
		        //loading the jdbc odbc driver
	            Class.forName("com.mysql.jdbc.Driver");
	            //System.out.println("\n Drivers are loaded Successfully");

	            //Creating Connection to the database
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","Ashu@24*#");

	            String query = "insert into registration values(?,?,?,?,?)";
	            PreparedStatement ps = con.prepareStatement(query);
	            //System.out.println("jdbc:odbc:stud_info");

	            ps.setString(1, txtname.getText());
	            ps.setInt(2, Integer.parseInt(txtrollno.getText()));
	            ps.setInt(3, Integer.parseInt(txtage.getText()));
	            if(gen1.isSelected())
	            {
	            	ps.setString(4, gen1.getText());
	            }
	            else if(gen2.isSelected())
	            {
	            	ps.setString(4, gen2.getText());
	            }
	            else
	            {
	            	ps.setString(4, gen3.getText());
	            }
	            
	            ps.setString(5, txtbranch.getText());
	            
	            int i = ps.executeUpdate();
	            System.out.println("\n Connection Established");

	            System.out.println("\n Record has been added Succesfully..");
	            //System.out.println("\n Connection Established");

	            //Closing Connection
	            ps.close();
	            con.close();
	            System.out.println("\n Connection Closed");
	        } 
	        catch (SQLException e1) 
	        {
	            // TODO: handle exception
	            System.err.println("\n SQL Exception");
	            System.out.println(e1);
	        }
	        catch (Exception ex) 
	        {
	            System.err.println("\n Exception : ");
	            System.err.println(ex.getMessage());
	        }
		}
			
		if(e.getActionCommand().equals("Cancel")) 
		{
			System.out.println(" Txtname : " + txtname + " : txtrollno " + txtrollno + " : txtage " + txtage);
			
			txtname.setText(null);
			txtrollno.setText(null);
            txtage.setText(null);
            txtbranch.setText(null);
			lbl.setText("Please Enter valid Information");
			lbl.setForeground(Color.red);
		}
    }

	public static void main(String args[])
	{
		RegistrationForm S = new RegistrationForm("Registration Form");
	}
}