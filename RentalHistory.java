import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class RentalHistory {

	public void rent(String user, String password){
		int member = Integer.parseInt(JOptionPane.showInputDialog("Enter Member ID"));
		String profile = (String)JOptionPane.showInputDialog("Enter Profile Name");
		//System.out.println(option);
	  // Get a statement from the connection
	  Statement stmt;
	try {
		Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@apollo.vse.gmu.edu:1521:ite10g", user, password ) ;
	      // Demonstrate PreparedStatement
	      String sql = "SELECT Member_ID, Profile_Name, Movie_ID, RATING FROM RENTS WHERE Member_ID = ? AND Profile_Name=?";
	          
	      PreparedStatement pStmt = conn.prepareStatement(sql);
	      
	      pStmt.setInt(1, member);
	      pStmt.setString(2, profile);
	          
	      ResultSet rs2 = pStmt.executeQuery();

	      System.out.println("RENTAL HISTORY with Member_ID and Profile  (using PreparedStatement)");
	      Printout hi=new Printout();
		  String forLabel="Member ID \tProfile Name \tMovie ID \tRating\t";
	      while( rs2.next() )
	    	  forLabel=forLabel+"\n"+rs2.getInt(1) + "\t" + rs2.getString(2) + "\t" + rs2.getString(3)+ "\t" + rs2.getDouble(4);
	      		hi.setLabel(forLabel);
	          //System.out.println( rs2.getInt(1) + "  " + rs2.getString(2) + "  " + rs2.getString(3)+ "  " + rs2.getDouble(4)) ;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


	}
}
