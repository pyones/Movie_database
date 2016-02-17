import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class Delete {
	private String address = "";
	private String username = "";
	private String password = "";
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	//Default Constructor
	public Delete(String address, String username, String password){
		this.address = address;
		this.username = username;
		this.password = password;
	}
	
	//Connection Establishment
	private void connect(){
		try{
			conn = DriverManager.getConnection(address, username, password);
			stmt = conn.createStatement();
		}//end try
		catch(SQLException e){
			e.printStackTrace();
		}//end catch
	}
	
	//Delete from ACCOUNT Table
	public int deleteAccount(){
		try{
			int member_ID = Integer.parseInt((String)JOptionPane.showInputDialog("Enter Member_ID to Remove"));
			connect();
			stmt.executeQuery("DELETE FROM HOLDS "+
				"WHERE Member_ID="+member_ID);
			stmt.executeQuery("DELETE FROM ACCOUNT "+
				"WHERE Member_ID="+member_ID );
			conn.close();
			return member_ID;//return erased member_ID
		}//end try
		catch(SQLException e){
			e.printStackTrace();
		}//end catch
		return 0;//if fail, return 0
	}//end deleteAccount()
	
	//Delete from CREDIT_CARD Table
	public void deleteCredit_Card(){
		try{
			int member_ID = Integer.parseInt((String)JOptionPane.showInputDialog("Enter Your Member_ID"));
			String cc_Number = (String)JOptionPane.showInputDialog("Enter Credit Card Number to Remove");
			connect();
			/*stmt.executeQuery("DELETE FROM HOLDS "+
				"WHERE Member_ID="+member_ID+" AND "+
				"CC_Number="+cc_Number );*/ //CHILD DELETE NECESSARY
			stmt.executeQuery("DELETE FROM CREDIT_CARD "+
				"WHERE CC_Number="+cc_Number );
			conn.close();
		}//end try
		catch(SQLException e){
			e.printStackTrace();
		}//end catch
	}//end deleteCredit_Card()
	
	//Delete from Profile Table
	public void deleteProfile(){
		try{
			int member_ID = Integer.parseInt((String)JOptionPane.showInputDialog("Enter your Member_ID"));
			String p_Name = (String)JOptionPane.showInputDialog("Enter Profile Name to Remove");
			connect();
			stmt.executeQuery("DELETE FROM PROFILE WHERE "+
				"Member_ID="+member_ID+" AND "+
				"Profile_Name="+p_Name);
			conn.close();
		}//end try
		catch(SQLException e){
			e.printStackTrace();
		}//end catch
	}//end deleteProfile()
	
	//Delete from Movie Table
	public void deleteMovie(){
		try{
			String movie_ID = (String)JOptionPane.showInputDialog("Enter the Movie ID to remove");
			connect();
			stmt.executeQuery("DELETE FROM MOVIE WHERE "+
				"movie_ID="+movie_ID);
			conn.close();
		}//end try
		catch(SQLException e){
			e.printStackTrace();
		}//end catch
	}//end deleteMovie()
	
	//Delete from Genre Table
	public void deleteGenre(){
		try{
			String m_Genre = (String)JOptionPane.showInputDialog("Enter the Movie Genre to remove");
			connect();
			stmt.executeQuery("DELETE FROM GENRE WHERE "+
				"Movie_Genre="+m_Genre);
			conn.close();
		}//end try
		catch(SQLException e){
			e.printStackTrace();
		}//end catch
	}//end deleteGenre()
	
	//Delete from Actor Table
	public void deleteActor(){
		try{
			String a_ID = (String)JOptionPane.showInputDialog("Enter the Actor ID\n of the Actor to remove");
			connect();
			stmt.executeQuery("DELETE FROM ACTOR WHERE "+
				"Actor_ID="+a_ID);
			conn.close();
		}//end try
		catch(SQLException e){
			e.printStackTrace();
		}//end catch
	}//end deleteActor()
	/*
	//Delete from Holds Table
	public void deleteHolds(){
		try{
			int member_ID = Integer.parseInt((String)JOptionPane.showInputDialog("Enter your Member_ID"));
			String cc_Number = (String)JOptionPane.showInputDialog("Enter Credit Card Number to Unlink");
			connect();
			stmt.executeQuery( "DELETE FROM HOLDS WHERE "+
				"member_ID="+movie_ID);
			conn.close();
		}//end try
		catch(SQLException e){
			e.printStackTrace();
		}//end catch
	}//end deleteHolds()
	*/
}//end Delete class
