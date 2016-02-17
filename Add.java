import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class Add{
	private static int nextMember_ID = 1;
	private static String nextMovie_ID = "M_1";
	private static String nextActor_ID = "A_1";
	private String address = "";
	private String username = "";
	private String password = "";
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	//Default Constructor
	public Add(String address, String username, String password){
		this.address = address;
		this.username = username;
		this.password = password;
	}
	
	//Connection Establishment
	private void connect(){
		try{
			conn = DriverManager.getConnection(address, username, password);
			stmt = conn.createStatement();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	//Inserting into ACCOUNT Table
	public int addAccount(){
		try{
			String first_Name = (String)JOptionPane.showInputDialog("Enter First Name");
			if(first_Name!=null){
				String last_Name = (String)JOptionPane.showInputDialog("Enter Last Name");
				if(last_Name!=null){
					connect(); //Connection Establishment
					//Checking Duplicate member_ID
					rs = stmt.executeQuery("SELECT "+nextMember_ID+" FROM ACCOUNT WHERE member_ID="+nextMember_ID);
					while(rs.next()){
						nextMember_ID++;
						rs = stmt.executeQuery("SELECT "+nextMember_ID+" FROM ACCOUNT WHERE member_ID="+nextMember_ID);
					}
					//Insert into the Database
					stmt.executeQuery("INSERT INTO ACCOUNT Values("+nextMember_ID+", '"+first_Name+"', '"+last_Name+"')");
					JOptionPane.showMessageDialog(null,"Account has been created successfully."+
						"\nYour Member_ID is:    "+nextMember_ID+
						"\nWelcome,   "+first_Name+"  "+last_Name);
					nextMember_ID++; //Increment for next inserts
					conn.close(); //Closing connection
					return 1;
				}
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null,"Insert Cancelled");
		return 0;
	}//end addAccount()
	
	//Inserting into CREDIT_CARD Table
	public int addCredit_Card(){
		try{
			//Get Member_ID (Cannot choose if null)
			int member_ID = getMemberID();
			if(member_ID!=0){
				//Get Credit_card Information
				String cc_Number = (String)JOptionPane.showInputDialog("Enter Credit Card Number");
				if(isNum(cc_Number)		&&
				(cc_Number.length()==16)&&
				(cc_Number!=null)){
					String first_Name = (String)JOptionPane.showInputDialog("Enter First Name on the Credit Card");
					String last_Name = (String)JOptionPane.showInputDialog("Enter Last Name on the Credit Card");
					String exp_Date = (String)JOptionPane.showInputDialog("Enter the Expiration Date in\n  YYYY-MM-DD Form");
					
					connect(); //Connection Establishment
					//Insert into the Database
					stmt.executeQuery("INSERT INTO CREDIT_CARD Values("+
						cc_Number+", '"+first_Name+"', '"+last_Name+
						"', '"+exp_Date+"')");
					//Link Card with Holds
					stmt.executeQuery("INSERT INTO HOLDS Values("+
						member_ID+", '"+cc_Number+"')");
					conn.close(); //Closing connection
					return 1;
				}//end if
			}//end if
		}//end try
		catch(SQLException e){
			e.printStackTrace();
		}//end catch
		JOptionPane.showMessageDialog(null,"Insert Cancelled");
		return 0;
	}//end addCredit_Card()
	
	private boolean isNum(String s){
		try{
			Long.parseLong(s);
		}
		catch(NumberFormatException e){
			return false;
		}
		catch(NullPointerException e){
			return false;
		}
		return true;
	}//end isNum()
	
	private int getMemberID(){
		int member_ID = 0;
		try{
			//Get Member_ID (Cannot choose if null)
			String title = "Member ID";
			String prompt = "Please choose your Member ID";
			connect();
			String[] choiceStr = new String[100];
			ResultSet rs = stmt.executeQuery(
				"SELECT Member_ID FROM ACCOUNT");
			int i = 0;
			while(rs.next()){
				choiceStr[i] = rs.getInt(1)+"";
				i++;
			}
			Data dt = new Data();
			member_ID = Integer.parseInt(
				dt.makeChoice(
				title,
				prompt,
				choiceStr));
			conn.close();
		}//end try
		catch(SQLException e){
			e.printStackTrace();
		}//end catch
		return member_ID;
	}//end getMemberID()

	//Inserting into PROFILE Table
	public int addProfile(){
		try{
			//Get Member_ID (Cannot choose if null)
			int member_ID = getMemberID();
			if(member_ID!=0){
				String p_Name = (String)JOptionPane.showInputDialog("Enter New Profile Name");
				
				connect(); //Connection Establishment
				//Insert into the Database
				stmt.executeQuery("INSERT INTO PROFILE Values("+
					member_ID+", '"+p_Name+"')");
				conn.close(); //Closing connection
				return 1;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}//end catch
		JOptionPane.showMessageDialog(null,"Insert Cancelled");
		return 0;
	}//end addProfile()
	
	//Inserting into MOVIE Table
	public int addMovie(){
		try{
			String movie_Name = (String)JOptionPane.showInputDialog("Enter Movie Name");
			if(movie_Name!=null){
				int year_Prod = 0;
				year_Prod = Integer.parseInt((String)JOptionPane.showInputDialog("Enter Movie Production Year"));
				if((year_Prod!=0) && (1890<year_Prod) && (year_Prod<2016)){
					String producer = (String)JOptionPane.showInputDialog("Enter Producer Name");
					if(producer!=null){
						connect(); //Connection Establishment
						//Checking Duplicate movie_ID
						rs = stmt.executeQuery("SELECT "+nextMovie_ID+" FROM MOVIE WHERE member_ID="+nextMember_ID);
						while(rs.next()){
							nextMovie_ID = increment_ID(nextMovie_ID);
							rs = stmt.executeQuery("SELECT "+nextMovie_ID+" FROM MOVIE WHERE Movie_ID="+nextMovie_ID);
						}
						//Insert into the Database
						stmt.executeQuery("INSERT INTO MOVIE Values("+
							nextMovie_ID+", '"+
							year_Prod+", '"+
							producer+", '"+
							"0.00"+"')");
						JOptionPane.showMessageDialog(null,"MOVIE has been added successfully."+
							"\nThe Movie ID of the Movie"+
							"\n  [ "+movie_Name+" ]"+
							"\nis:    "+nextMovie_ID);
						//Increment for next inserts
						nextMovie_ID = increment_ID(nextMovie_ID);
						conn.close(); //Closing connection
						return 1;
					}//end if producer
				}//end if year_Prod
			}//end if movie_Name
		}//end try
		catch(SQLException e){
			e.printStackTrace();
		}//end catch
		JOptionPane.showMessageDialog(null,"Insert Cancelled");
		return 0;
	}//end addMovie()
	
	private String increment_ID(String id){
		String[] temp_Split = id.split("_");
		int temp_ID = Integer.parseInt(temp_Split[1]);
		temp_ID++;
		return (temp_Split[0]+temp_ID);
	}
	
	public int getNextMember_ID(){
		return nextMember_ID;
	}
	public void setNextMember_ID(int newNextMember_ID){
		this.nextMember_ID = newNextMember_ID;
	}
	
	public String getNextMovie_ID(){
		return nextMovie_ID;
	}
	public void setNextMember_ID(String newNextMovie_ID){
		this.nextMovie_ID = newNextMovie_ID;
	}	
	public String getNextActor_ID(){
		return nextActor_ID;
	}
	public void setNextActor_ID(String newNextActor_ID){
		this.nextActor_ID = newNextActor_ID;
	}
}//end Add class
