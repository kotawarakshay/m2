package com.capgemini.contactbook.ui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capgemini.contactbook.bean.EnquiryBean;
import com.capgemini.contactbook.exception.ContactBookException;
import com.capgemini.contactbook.service.ContactBookService;
import com.capgemini.contactbook.service.ContactBookServiceImpl;

public class Client {

	static ContactBookService contactBook=null;
	static ContactBookServiceImpl contactBookServiceImpl=null;
	static Scanner scanner=new Scanner(System.in);
	static  EnquiryBean enqry=null;
	static Logger logger=Logger.getRootLogger();
	public static void main(String[] args) throws ContactBookException, SQLException, IOException
	{
		PropertyConfigurator.configure("resources/log4j.properties");
		int enqryId=0;
		
		while(true)
		{
			System.out.println("**********Global Recruitment*******************");
			System.out.println("Choose an option");
			System.out.println("1.Enter Enquiry Details");
			System.out.println("2.View Enquiry Details on Id");
			System.out.println("0.Exit");
			System.out.println("**************************************************");
			
			
			System.out.println("Please enter a Choice ");
			int option=scanner.nextInt();
			switch(option){
			
			case 1:
				while(enqry==null)
				{
					enqry=populateEnquiryBean();
				}
			try{
			
				contactBookServiceImpl=new ContactBookServiceImpl();
				enqryId=contactBookServiceImpl.addEnquiry(enqry);
				
				System.out.println("Customer Details are successfully added");
				System.out.println("Customer Id is:"+ enqryId);
				
			}catch(Exception messege)
			{
				logger.error("Exception occur", messege);
				System.out.println(messege);
			}
		break;
		
			case 2:
				
				 enqryId=0;
				 enqry=new EnquiryBean();
				 try
				 {
				contactBookServiceImpl=new ContactBookServiceImpl();
				System.out.println("Enter the Enquiry Id");
				 enqryId=scanner.nextInt();
				 enqry=contactBookServiceImpl.getEnquiryDetails(enqryId);
				 System.out.println("Enquiry Id is: "+enqry.getEnqryId());
				 System.out.println(enqry.getfName());
				 System.out.println(enqry.getlName());
				 System.out.println(enqry.getContactNo());
				 System.out.println(enqry.getpDomain());
				 System.out.println(enqry.getpLocation());
				 if(enqry==null)
				 {
					 throw new ContactBookException("Error in insertion"); 
				 }
				 }
				 catch(ContactBookException messege)
				 {
					 
					
					 logger.error("error in insertion",messege);
				 }
				 break;
				 
			case 0:
				System.exit(0);
				break;
		default:
			break;
			}
		}
			
		
		
	}


	private static EnquiryBean populateEnquiryBean() {
		enqry=new EnquiryBean();
		System.out.println("Enter First Name");
		enqry.setfName(scanner.next());
		System.out.println("Enter the Last Name");
		enqry.setlName(scanner.next());
		System.out.println("Enter Contact No");
		enqry.setContactNo(scanner.next());
		System.out.println("Enter Prefered Location");
		enqry.setpLocation(scanner.next());
		System.out.println("Enter preferred Domain");
		enqry.setpDomain(scanner.next());
		try
		{
		contactBookServiceImpl=new ContactBookServiceImpl();
		boolean boo=contactBookServiceImpl.validateCustomer(enqry);
		if(boo==true)
		{
			return enqry;
		}
		else{
			System.out.println("Enter valid details");
			enqry=populateEnquiryBean();
		}
		
		}catch(Exception e)
		{
			System.out.println(e);
		}
		return enqry;
	}
	
}
