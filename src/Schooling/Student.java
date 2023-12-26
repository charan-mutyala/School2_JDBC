package Schooling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.*;


public class Student {

	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		String url="jdbc:mysql://localhost:3306/School2";
		String user="root";
		String psw="mysql123";
		int regdno = 0;
		String name;
		int Class;
		String DOB;
		int choice=0;
	    int Subject1,Subject2,Subject3,totalMarks;
		try {
			Connection con=DriverManager.getConnection(url,user,psw);
			Statement s=con.createStatement();
			System.out.println("Welcome to School2");
//			System.out.println("Please enter the Student Details");
            String createTableSQL = "CREATE TABLE IF NOT EXISTS StudentDetails (Regdno INT PRIMARY KEY, Name VARCHAR(255), Class INT, DOB VARCHAR(10))";
            try (PreparedStatement ps= con.prepareStatement(createTableSQL)) {
                ps.executeUpdate();
                System.out.println("StudentDetails table created successfully");
            }
            
            String createStdMarks="CREATE TABLE IF NOT EXISTS StudentMarks (Regdno INT PRIMARY KEY, Subject1 INT, Subject2 INT, Subject3 INT, Total INT)";
            try(PreparedStatement ps=con.prepareStatement(createStdMarks)){
            	ps.executeUpdate();
            	System.out.println("Students Marks Details Table is Created Succesfully");
            }
            
            
            //For Reading the user Inputs
            
            
//            while (true) {
//            	
//                System.out.println("Please enter the Student Details");
//
//                System.out.println("Enter Registration Number (Regdno):");
//                regdno = sc.nextInt();
//
//                if (isRegdNoExists(regdno, con)) {
//                    System.out.println("Registration number already exists. Please enter a different registration number.");
//                    continue;
//                }
//
//                System.out.println("Enter the Student Name:");
//                name = sc.next();
//
//                System.out.println("Enter Student Class:");
//                Class = sc.nextInt();
//
//                sc.nextLine(); 
//
//                System.out.println("Enter the Student Date of Birth (DOB)");
//                DOB = sc.nextLine();
//
//                break;
//            }
//            
//            
            
            //Inserting the data Into Database
            
//            String insertData = "INSERT INTO StudentDetails (Regdno, Name, Class, DOB) VALUES (?, ?, ?, ?)";
//              try(PreparedStatement ps= con.prepareStatement(insertData))
//              {
//            	  ps.setInt(1, regdno);
//                  ps.setString(2, name);
//                  ps.setInt(3, Class);
//                  ps.setString(4, DOB);
//
//                  int rowsAffected = ps.executeUpdate();
//
//                  if (rowsAffected > 0)
//                  {
//                      System.out.println("Data inserted successfully");
//                  }
//                  else
//                  {
//                      System.out.println("Failed to insert data");
//                  }
                           
               //System.out.println("StudentDetails are inserted into Table successfully");  
//              }
              while(true) {
//            	  
            	  System.out.println("choose 1 for Inserting the Marks");
			    	System.out.println("Choose 2 for Display the Student ");
			    	System.out.println("Choose 3 for Delete the Student List");
			    	System.out.println("Choose 4 for Update the Student");
//			    	System.out.println("Choose 5 for Exit or Quit");
			    	System.out.println(" ");
			    	System.out.println("Enter your choice");
			    	choice=sc.nextInt();
			    	switch(choice) {
			    	case 1:
			    		
			    		while (true) {
			            	
			                System.out.println("Please enter the Student Details");

			                System.out.println("Enter Registration Number (Regdno):");
			                regdno = sc.nextInt();

			                if (isRegdNoExists(regdno, con)) {
			                    System.out.println("Registration number already exists. Please enter a different registration number.");
			                    continue;
			                }

			                System.out.println("Enter the Student Name:");
			                name = sc.next();

			                System.out.println("Enter Student Class:");
			                Class = sc.nextInt();

			                sc.nextLine(); 

			                System.out.println("Enter the Student Date of Birth (DOB)");
			                DOB = sc.nextLine();

			                break;
			            }
			            
			    		String insertData = "INSERT INTO StudentDetails (Regdno, Name, Class, DOB) VALUES (?, ?, ?, ?)";
			              try(PreparedStatement ps= con.prepareStatement(insertData))
			              {
			            	  ps.setInt(1, regdno);
			                  ps.setString(2, name);
			                  ps.setInt(3, Class);
			                  ps.setString(4, DOB);

			                  int rowsAffected = ps.executeUpdate();

			                  if (rowsAffected > 0)
			                  {
			                      System.out.println("Data inserted successfully");
			                  }
			                  else
			                  {
			                      System.out.println("Failed to insert data");
			                  }
			              }
            	// For entering marks in StudentMarks Table
                  
                  
                  
	              System.out.println("Enter marks for Subject1:");
	              Subject1 = sc.nextInt();

	              System.out.println("Enter marks for Subject2:");
	              Subject2 = sc.nextInt();

	              System.out.println("Enter marks for Subject3:");
	              Subject3 = sc.nextInt();

	              totalMarks = Subject1 + Subject2 + Subject3;
	              
			
			
			       String insertStudentMarks= "INSERT INTO StudentMarks (Regdno, Subject1, Subject2, Subject3, Total) VALUES (?, ?, ?, ?, ?)";
	               try (PreparedStatement ps = con.prepareStatement(insertStudentMarks)) {
	               ps.setInt(1, regdno);
	               ps.setInt(2, Subject1);
	               ps.setInt(3, Subject2);
	               ps.setInt(4, Subject3);
	               ps.setInt(5, totalMarks);

	               int rowsAffected = ps.executeUpdate();

	                if (rowsAffected > 0) 
	                {
	                	System.out.println(" ");
	                       System.out.println("StudentMarks data inserted successfully");
	                       System.out.println(" ");
	                       System.out.println("Thank you for Inserting the marks");
	                       System.out.println(" ");
	                       System.out.println(" Your Total Marks are:"+totalMarks);
	                       
	                } 
	                
	                else 
	                {
	                     System.out.println("Failed to insert StudentMarks data");
	                }
	                break;
	                
	               } 
	                
	                case 2:
	                	System.out.println("Enter the Regd No. to View the Students Marks");
	                	int regno=sc.nextInt();
	                	
	                	String selectStudentMarks = "SELECT * FROM StudentMarks WHERE Regdno = ?";
	                    try (PreparedStatement ps = con.prepareStatement(selectStudentMarks)) {
	                        ps.setInt(1, regno);
	                        ResultSet rs = ps.executeQuery();

	                        if (rs.next()) {
	                            // Displaying retrieved data
	                             regdno = rs.getInt("Regdno");
	                             Subject1 = rs.getInt("Subject1");
	                            Subject2 = rs.getInt("Subject2");
	                            Subject3 = rs.getInt("Subject3");
	                            totalMarks = rs.getInt("Total");

	                            System.out.println("StudentMarks data for Regdno " + regdno + ":");
	                            System.out.println("Subject1: " + Subject1);
	                            System.out.println("Subject2: " + Subject2);
	                            System.out.println("Subject3: " + Subject3);
	                            System.out.println("Total Marks: " +totalMarks);
	                            
	                        }
	                        else 
	                        {
	                            System.out.println("No data found for Regdno " + regdno);
	                        }
	                        break;
	                	
			    		}
	                case 3:
	                	
	                	//For Deleting the record
	                	
	                	
	                	System.out.println("Enter the Regdno. to delete the record");
	                	regno=sc.nextInt();
	                	
	                	String DeleteStudents="DELETE FROM StudentDetails WHERE Regdno = ?";
	                	try(PreparedStatement ps=con.prepareStatement(DeleteStudents)){
	                		ps.setInt(1, regno);
	                		int rowsAffected= ps.executeUpdate();

	                        if (rowsAffected > 0) {
	                            System.out.println("Student details deleted successfully");
	                        } else {
	                            System.out.println("No data found for Regdno " + DeleteStudents + " in StudentDetails");
	                        }
	                    }
	                	String DeleteMarks="DELETE FROM StudentMarks WHERE Regdno = ?";
	                	try(PreparedStatement ps=con.prepareStatement(DeleteMarks)){
	                		ps.setInt(1, regno);
	                		int rowsAffected= ps.executeUpdate();

	                        if (rowsAffected > 0) {
	                            System.out.println("Student details deleted successfully");
	                        } else {
//	                            System.out.println("No data found for Regdno " + DeleteMarks + " in StudentDetails");
	                        }
	                        break;
	                    }
	                	
	                	
	                case 4:
	                	
	                	  	       //for Updating the Student details
	                	

	                    System.out.println("Enter Registration Number (Regdno):");
	                    regdno = sc.nextInt();
	                    
	                    System.out.println("Enter the Student Name:");
	                    name = sc.next();

	                    System.out.println("Enter Student Class:");
	                    Class = sc.nextInt();

	                    sc.nextLine(); 

	                    System.out.println("Enter the Student Date of Birth (DOB)");
	                    DOB = sc.nextLine();
	                    
	                    String updateStudentDetails = "UPDATE StudentDetails SET Name = ?, Class = ?, DOB = ? WHERE Regdno = ?";
	                    try (PreparedStatement ps = con.prepareStatement(updateStudentDetails)) {
	                        ps.setString(1, name);
	                        ps.setInt(2, Class);
	                        ps.setString(3, DOB);
	                        ps.setInt(4, regdno);

	                        int rowsAffectedDetails = ps.executeUpdate();

	                        if (rowsAffectedDetails > 0) {
	                            System.out.println("Student details updated successfully");
	                        } else {
	                            System.out.println("No data found for Regdno " + regdno + " in StudentDetails");
	                        }
	                        
	                        System.out.println("Enter updated marks for Subject1:");
	                        Subject1 = sc.nextInt();

	                        System.out.println("Enter updated marks for Subject2:");
	                        Subject2 = sc.nextInt();

	                        System.out.println("Enter updated marks for Subject3:");
	                        Subject3 = sc.nextInt();

	                        totalMarks = Subject1 + Subject2 + Subject3;

	                        String updateStudentMarks = "UPDATE StudentMarks SET Subject1 = ?, Subject2 = ?, Subject3 = ?, Total = ? WHERE Regdno = ?";
	                        try (PreparedStatement pss = con.prepareStatement(updateStudentMarks)) {
	                            pss.setInt(1, Subject1);
	                            pss.setInt(2, Subject2);
	                            pss.setInt(3, Subject3);
	                            pss.setInt(4, totalMarks);
	                            pss.setInt(5, regdno);

	                            int rowsAffectedMarks = ps.executeUpdate();

	                            if (rowsAffectedMarks > 0) {
	                                System.out.println("Student marks updated successfully");
	                                System.out.println("Updated Total Marks are: " + totalMarks);
	                            } else {
	                                System.out.println("No data found for Regdno " + regdno + " in StudentMarks");
	                            }
	                        
	                	
	                	
	                	}
	                	

              
              
              
                         
			         
			    	}
              
              }
		
              }
              }
			              
			               
			               
			               
			        
		     
			               
		catch(Exception e) {
			System.out.println(e);
		}

		
	}

	private static boolean isRegdNoExists(int regdno, Connection con) {
		return false;
	}	
	}

