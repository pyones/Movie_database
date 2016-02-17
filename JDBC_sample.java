import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.* ;
import java.util.Scanner;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class JDBC_sample extends JFrame
{
	private static Connection conn;
	private static Statement stmt;
	private static ResultSet rs;
	private static String address = "";
	private static String username = "";
	private static String password = "";
	private static JLabel info = new JLabel();
	static JPanel panel = new JPanel(); 
	static JFrame frame = new JFrame();
	static JButton viewB = new JButton("View Table");
	static JButton addB = new JButton("Add Records");
	static JButton deleteB = new JButton("Delete Records");
	static JButton updateB = new JButton("Update Records");
	static JButton search1 = new JButton("Search Database");
	static JButton show = new JButton("Show History");
	static JButton cancelB = new JButton("Cancel");
	
	private static void connect(){
		try{
			conn = DriverManager.getConnection(address, username, password);
			stmt = conn.createStatement();
		}//end try
		catch(SQLException e){
			e.printStackTrace();
		}//end catch
	}
	public static void main( String args[] )
	{
		address = "jdbc:oracle:thin:@apollo.vse.gmu.edu:1521:ite10g";
		//username = (String)JOptionPane.showInputDialog("Enter usernamename");
		//password = (String)JOptionPane.showInputDialog("Enter Password");
		username = "";//temp
		password = "";//temp
		try{
			//Load the database driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Get a connection to the database
			connect();
			//Print all warnings
			for( SQLWarning warn = conn.getWarnings(); warn != null; warn = warn.getNextWarning() )
			{
				System.out.println( "SQL Warning:" );
				System.out.println( "State  : " + warn.getSQLState()  );
				System.out.println( "Message: " + warn.getMessage()   );
				System.out.println( "Error  : " + warn.getErrorCode() );
			}
			displayMenu();
			conn.close() ;
		}
		catch( SQLException se )
		{
			System.out.println( "SQL Exception:" ) ;
			//Loop through the SQL Exceptions
			while( se != null )
			{
				System.out.println( "State  : " + se.getSQLState()  ) ;
				System.out.println( "Message: " + se.getMessage()   ) ;
				System.out.println( "Error  : " + se.getErrorCode() ) ;
				
				se = se.getNextException() ;
			}
		}
		catch( Exception e )
		{
			System.out.println( e ) ;
		}
	}//end main
    
    public static void displayMenu(){
		//boolean go=true;
		//while (go){
		//System.out.println("Welcome to Netflix Database");
		
		final Data view=new Data();
		final RentalHistory rent=new RentalHistory();
		final Search search=new Search();
		final Add add = new Add(address, username, password);
		final Delete del = new Delete(address, username, password);
		
		
		frame = new JFrame("Netflix");
		frame.setSize(350, 200);
		//panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		info=new JLabel("What would you like to do?");

		panel.add(info, BorderLayout.CENTER);
		panel.add(viewB);
		panel.add(addB);
		panel.add(deleteB);
		panel.add(deleteB);
		panel.add(search1);
		panel.add(show);
		panel.add(cancelB);
		frame.add(panel, BorderLayout.CENTER);
		
		//frame.pack();
		frame.setVisible(true);
		viewB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Object[] possibilities = {"account", "credit card",
					"profile", "movie", "genre", "actor", "holds",
					"prefers", "rents", "belongs to", "has cast"};
				try{
					String viewOp = (String)JOptionPane.showInputDialog(
						null,
						"Which table would you like to view?",
						"Customized Dialog",
						JOptionPane.PLAIN_MESSAGE,
						null,
						possibilities,
						"account");
					if(!viewOp.equals(null))
						view.view(viewOp, username, password);
				}
				catch(Exception normE){}
			}
		});
		addB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				addToDatabase(add, view);
			}
		});
		deleteB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				deleteFromDatabase(add, del, view);
			}
		});
		deleteB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				System.out.println("Hello");
			}
		});
		search1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				search.search(username, password);
			}
		});
		show.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				rent.rent(username, password);
			}
		});
		cancelB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
	}//end displayMenu()
	
	private static void addToDatabase(Add add, Data view){
		Object[] adds = {"account", "credit card", "profile",
			"movie", "genre", "actor", "prefers", "rents"};
		try{
			String addTo = (String)JOptionPane.showInputDialog(
				null,
				"Where would you like to add to?",
				"Customized Dialog",
				JOptionPane.PLAIN_MESSAGE,
				null,
				adds,
				"account");
		
			//add.addAccount();
			//add.addCredit_Card();
			//add.addProfile();
			

			switch (addTo){
				case "account":
					add.addAccount();
				break;
				case "credit card":
					add.addCredit_Card();
					//Also adding Holds in method
				break;
				case "profile":
					add.addProfile();
				break;
				case "movie":
					add.addMovie();
				break;/*
				case "genre":
					add.addGenre();
				break;
				case "actor":
					add.addActor();
				break;*//*
				case "holds":
					add.addHolds();
				break;*//*
				case "prefers":
					add.addPrefers();
				break;
				case "rents":
					add.addRents();
				break;
				case "belongs to":
					add.addBelongs_To();
				break;
				case "has cast":
					add.addHas_Cast();
				break;*/
				default: //Add when necessary
					System.out.print("");
			}//end switch
			view.view(addTo, username, password);
		}//end try
		catch(Exception normE){}
	}//end addToDatabase()
	
	private static void deleteFromDatabase(Add add, Delete del, Data view){
		Object[] dels = {"account", "credit card", "profile",
			"movie", "genre", "actor", "prefers", "rents"};
		String delFrom = (String)JOptionPane.showInputDialog(
			null,
			"Where would you like to add to?",
			"Customized Dialog",
			JOptionPane.PLAIN_MESSAGE,
			null,
			dels,
			"account");
		/*
		int tempMember_ID = del.deleteAccount();
		if((add.getNextMember_ID() > tempMember_ID) &&
			(tempMember_ID!=0)){
			add.setNextMember_ID(tempMember_ID);
		}
		del.deleteCredit_Card();
		del.deleteProfile();
		*/
		
		
		switch (delFrom){
			case "account":
				int tempMember_ID = del.deleteAccount();
				if((add.getNextMember_ID() > tempMember_ID) &&
				(tempMember_ID!=0)){
					add.setNextMember_ID(tempMember_ID);
				}
			break;
			case "credit card":
				del.deleteCredit_Card();
			break;
			case "profile":
				del.deleteProfile();
			break;
			case "movie":
				del.deleteMovie();
			break;
			case "genre":
				del.deleteGenre();
			break;
			case "actor":
				del.deleteActor();
			break;/*
			case "holds":
				del.deleteHolds();
			break;*//*
			case "prefers":
				del.deletePrefers();
			break;
			case "rents":
				del.deleteRents();
			break;
			case "belongs to":
				del.deleteBelongs_To();
			break;
			case "has cast":
				del.deleteHas_Cast();
			break;*/
			default: //Add when necessary
				System.out.print("");
		}
		view.view(delFrom, username, password);
	}
}
