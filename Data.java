import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.sql.* ;


public class Data {

	public void view(String option, String user, String password){
		
		//System.out.println(option);
	  // Get a statement from the connection
	  Statement stmt;
	try {
		Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@apollo.vse.gmu.edu:1521:ite10g", user, password ) ;
		switch (option){
		case "account":
		stmt = conn.createStatement();
			Printout hi=new Printout();
		  // Execute the query
		  ResultSet rs = stmt.executeQuery( "SELECT Member_ID, M_First_Name, M_Last_Name FROM ACCOUNT" ) ;
		  
		  //System.out.println("All Accounts (using Statement object):");
		  String forLabel="Member ID \tFirst Name \t\tLast Name\t\n";
		  // Loop through the result set
		  while( rs.next() )
			  forLabel=forLabel+"\n"+rs.getInt(1) + "\t" + rs.getString(2) + "\t\t" + rs.getString(3);
		  	  hi.setLabel(forLabel);
		  	
		  	//System.out.println(forLabel);
		  break;
		
		case "credit card":
			stmt = conn.createStatement();
			  // Execute the query
			  ResultSet rs1 = stmt.executeQuery( "SELECT CC_Number, M_First_Name, M_Last_Name, Exp_Date FROM CREDIT_CARD" ) ;
			  
			  //System.out.println("All Credit Cards (using Statement object):");
			  Printout hi1=new Printout();
			  String forLabel1="Credit Card \t\tFirst Name \tLast Name \tDate\t\n";
			  // Loop through the result set
			  while( rs1.next() )
				  forLabel1=forLabel1+"\n"+rs1.getString(1) + "\t" + rs1.getString(2) + "\t" + rs1.getString(3)+ "\t" + rs1.getDate(4);
		      	hi1.setLabel(forLabel1);
			      break;
		case "profile":
			stmt = conn.createStatement();
			  // Execute the query
			  ResultSet rs2 = stmt.executeQuery( "SELECT Member_ID, Profile_Name FROM PROFILE" ) ;
			  
			  //System.out.println("All Profiles (using Statement object):");
			  Printout hi2=new Printout();
			  String forLabel2="Member ID \tProfile Name\t\n";
			  // Loop through the result set
			  while( rs2.next() )
				  forLabel2=forLabel2+"\n"+rs2.getInt(1) + "\t" + rs2.getString(2);
		  	  		hi2.setLabel(forLabel2);
			  break;
		case "movie":
			stmt = conn.createStatement();
			  // Execute the query
			  ResultSet rs3 = stmt.executeQuery( "SELECT Movie_ID, Movie_Name, Year_Prod, Producer, Avg_Mem_Rating FROM MOVIE" ) ;
			  
			  //System.out.println("All sailors (using Statement object):");
			  Printout hi3=new Printout();
			  String forLabel3="Movie ID \tMovie Name Name\t\t Year \t Producer \t Average Rating \t\n";
			  // Loop through the result set
			  while( rs3.next() )
				  forLabel3=forLabel3+"\n"+rs3.getString(1) + "\t" + rs3.getString(2) + "\t\t" + rs3.getInt(3)+ "\t" + rs3.getString(4)+ "\t" + rs3.getDouble(5);
		  	  		hi3.setLabel(forLabel3);
		  	  		break;
		case "genre":
			stmt = conn.createStatement();
			  // Execute the query
			  ResultSet rs4 = stmt.executeQuery( "SELECT Movie_Genre FROM GENRE" ) ;
			  
			  //System.out.println("All Genres (using Statement object):");
			  Printout hi4=new Printout();
			  String forLabel4="Movie Genre\t\n";
			  // Loop through the result set
			  while( rs4.next() )
				  forLabel4=forLabel4+"\n"+rs4.getString(1);
	  	  		hi4.setLabel(forLabel4);
			  break;
		case "actor":
			stmt = conn.createStatement();
			  // Execute the query
			  ResultSet rs5 = stmt.executeQuery( "SELECT Actor_ID, A_First_Name, A_Last_Name FROM ACTOR" ) ;
			  
			  //System.out.println("All Actors (using Statement object):");
			  Printout hi5=new Printout();
			  String forLabel5="Actor ID\t First Name \tLast Name\t\n";
			  // Loop through the result set
			  while( rs5.next() )
				  forLabel5=forLabel5+"\n"+rs5.getString(1) + "\t" + rs5.getString(2) + "\t" + rs5.getString(3);
	  	  		hi5.setLabel(forLabel5);
			  break;
		case "holds":
			stmt = conn.createStatement();
			  // Execute the query
			  ResultSet rs6 = stmt.executeQuery( "SELECT Member_ID, CC_Number FROM HOLDS" ) ;
			  
			  //System.out.println("All Holds (using Statement object):");
			  Printout hi6=new Printout();
			  String forLabel6="Member ID \tCredit Card\t\n";
			  // Loop through the result set
			  while( rs6.next() )
				  forLabel6=forLabel6+"\n"+rs6.getInt(1) + "\t" + rs6.getString(2) ;
	  	  		hi6.setLabel(forLabel6);
			  break;
		case "prefers":
			stmt = conn.createStatement();
			  // Execute the query
			  ResultSet rs7 = stmt.executeQuery( "SELECT Member_ID, Profile_Name, Movie_Genre FROM PREFERS" ) ;
			  
			  //System.out.println("All Prefers (using Statement object):");
			  Printout hi7=new Printout();
			  String forLabel7="Member ID \tProfile Name\t Movie Genre\t\n";
			  // Loop through the result set
			  while( rs7.next() )
				  forLabel7=forLabel7+"\n"+rs7.getInt(1) + "\t" + rs7.getString(2) + "\t" + rs7.getString(3);
	  	  		hi7.setLabel(forLabel7);
			  break;
		case "rents":
			stmt = conn.createStatement();
			  // Execute the query
			  ResultSet rs8 = stmt.executeQuery( "SELECT Member_ID, Profile_Name, Movie_ID, RATING FROM RENTS" ) ;
			  
			  //System.out.println("All Rents (using Statement object):");
			  Printout hi8=new Printout();
			  String forLabel8="Member ID \tProfile Name\t Movie ID\t\n";
			  // Loop through the result set
			  while( rs8.next() )
				  forLabel8=forLabel8+"\n"+rs8.getInt(1) + "\t" + rs8.getString(2) + "\t" + rs8.getString(3)+ "\t" + rs8.getDouble(4);
	  	  		hi8.setLabel(forLabel8);
			  break;
		case "belongs to":
			stmt = conn.createStatement();
			  // Execute the query
			  ResultSet rs9 = stmt.executeQuery( "SELECT Movie_ID, Movie_Genre FROM BELONGS_TO" ) ;
			  
			  //System.out.println("All Belongs To (using Statement object):");
			  Printout hi9=new Printout();
			  String forLabel9="Movie ID \tMovie Genre\t\n";
			  // Loop through the result set
			  while( rs9.next() )
				  forLabel9=forLabel9+"\n"+rs9.getString(1) + "\t" + rs9.getString(2);
	  	  		hi9.setLabel(forLabel9);
			  break;
		case "has cast":
			stmt = conn.createStatement();
			  // Execute the query
			  ResultSet rs10 = stmt.executeQuery( "SELECT Movie_ID, Actor_ID FROM HAS_CAST" ) ;
			  
			  //System.out.println("All Has Cast (using Statement object):");
			  Printout hi10=new Printout();
			  String forLabel10="Movie ID \tActor ID\t\n";
			  // Loop through the result set
			  while( rs10.next() )
				  forLabel10=forLabel10+"\n"+rs10.getString(1) + "\t" + rs10.getString(2);
	  	  		hi10.setLabel(forLabel10);
			  break;
		default:
			System.out.println("NOT VALID");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
	}

	public String makeChoice(String title, String prompt, String[] choiceStr){
		JFrame frame = new JFrame();
		return ((String)JOptionPane.showInputDialog(
			null,
			prompt, title,
			JOptionPane.QUESTION_MESSAGE,
			null, choiceStr, choiceStr[0]));
	}
	
}
