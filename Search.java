import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Search {

	public void search(String user, String password){
		Object[] options = {"Actor", "Movie" };
		  	  int result = JOptionPane.showOptionDialog(
		  			  null, "Search by Movie or Actor?","Search",
		  			  JOptionPane.YES_NO_CANCEL_OPTION,
		  			  JOptionPane.QUESTION_MESSAGE,
		  			  null,     //do not use a custom Icon
		  			  options,  //the titles of buttons
		  			  options[0]); //default button title
		switch(result){
			case 0:
				String actor = (String)JOptionPane.showInputDialog("Enter Actor First Name");
				String actor2 = (String)JOptionPane.showInputDialog("Enter Actor Last Name");
				searchActor(actor, actor2, user, password);
				break;
			case 1:
				String movie = (String)JOptionPane.showInputDialog("Enter Movie");
				searchMovie(movie, user, password);
				break;
		}
	}
		public void searchMovie(String movie, String user, String password){
	  // Get a statement from the connection
	  Statement stmt;
	try {
		Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@apollo.vse.gmu.edu:1521:ite10g", user, password ) ;
	      // Demonstrate PreparedStatement
	      String sql = "SELECT Movie_Name, Year_Prod, Avg_Mem_Rating FROM MOVIE WHERE UPPER(Movie_Name) like UPPER(?)";
	          
	      PreparedStatement pStmt = conn.prepareStatement(sql);
	      
	      //pStmt.setInt(1, member);
	      pStmt.setString(1, "%"+movie+"%");

	          
	      ResultSet rs2 = pStmt.executeQuery();

	      System.out.println("MOVIES SEARCH (using PreparedStatement)");
	      Printout hi=new Printout();
		  String forLabel="Movie Name \tYear \tRating\t";
	      while( rs2.next() )
	    	  forLabel=forLabel+"\n"+rs2.getString(1) + "\t" + rs2.getInt(2) + "\t" + rs2.getDouble(3);
	  	  	hi.setLabel(forLabel);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
		
	public void searchActor(String actor, String actor2, String user, String password){
		  // Get a statement from the connection
		  Statement stmt;
		try {
			Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@apollo.vse.gmu.edu:1521:ite10g", user, password ) ;
		      // Demonstrate PreparedStatement
		      String sql = "SELECT MOVIE.Movie_Name, MOVIE.Year_Prod, MOVIE.Avg_Mem_Rating FROM MOVIE, HAS_CAST, ACTOR WHERE HAS_CAST.Movie_ID = MOVIE.Movie_ID AND ACTOR.Actor_ID = HAS_CAST.Actor_ID AND ACTOR.A_First_Name = ? AND ACTOR.A_Last_Name = ?";
		          
		      PreparedStatement pStmt = conn.prepareStatement(sql);
		      
		      pStmt.setString(1, actor);
		      pStmt.setString(2, actor2);
		          
		      ResultSet rs2 = pStmt.executeQuery();

		      System.out.println("MOVIES SEARCH (using PreparedStatement)");
		      Printout hi=new Printout();
			  String forLabel="Movie Name \tYear \tRating\t";
		      while( rs2.next() )
		    	  forLabel=forLabel+"\n"+rs2.getString(1) + "\t" + rs2.getInt(2) + "\t" + rs2.getDouble(3);
		  	  	hi.setLabel(forLabel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
