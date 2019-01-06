package com.capgemini.contactbook.dao;


import java.io.IOException;
import java.lang.Thread.State;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capgemini.contactbook.bean.EnquiryBean;
import com.capgemini.contactbook.exception.ContactBookException;
import com.capgemini.contactbook.util.DBConnection;

public class ContactBookDaoImpl implements ContactBookDao{

	Logger logger=Logger.getRootLogger();
	public ContactBookDaoImpl()
	{
		PropertyConfigurator.configure("resources/log4j.properties");
	}
	
	//------------------------ 1. Enquiry Details Application --------------------------
		/*******************************************************************************************************
		 - Function Name	:	addEnquiry(EnquiryBean enqry)
		 - Input Parameters	:	EnquiryBean enqry
		 - Return Type		:	int
		 - Throws			:  	ConntactBookException
		 - Author			:	CAPGEMINI
		 - Creation Date	:	18/11/2016
		 - Description		:	Adding Details
		 * @throws ContactBookException 
		 ********************************************************************************************************/
	@Override
	public int addEnquiry(EnquiryBean enqry) throws IOException, SQLException, ContactBookException {
			
		
				Connection connection=DBConnection.getConnection();
				PreparedStatement preparedStatement=null;		
				ResultSet resultSet = null;
				
				int enqryId=0;
				
				preparedStatement=connection.prepareStatement(QueryMapper.Insert_Details);

				preparedStatement.setString(1,enqry.getfName());			
				preparedStatement.setString(2,enqry.getlName());
				preparedStatement.setString(3,enqry.getContactNo());
				preparedStatement.setString(4,enqry.getpDomain());
				preparedStatement.setString(5, enqry.getpLocation());
				resultSet=preparedStatement.executeQuery();
				
				Statement statement=connection.createStatement();
				resultSet=statement.executeQuery(QueryMapper.enqryIdS);
				while(resultSet.next())
				{
					enqryId=resultSet.getInt(1);
				}
				
				if(resultSet==null){
					logger.error("insertion failed");
					throw new ContactBookException("Insertion Details failed ");
				}
				else
				{
					logger.error("Inserted Successfully");
					
				}
			
			
			return enqryId;
		}
	

	@Override
	public EnquiryBean getEnquiryDetails(int enqryId) throws SQLException, IOException {
		Connection connection=DBConnection.getConnection();
		PreparedStatement preparedStatement1=null;		
		ResultSet resultSet = null;
		EnquiryBean enqry=new EnquiryBean();
		
		preparedStatement1=connection.prepareStatement(QueryMapper.getDetials);
		preparedStatement1.setInt(1,enqryId);
		resultSet=preparedStatement1.executeQuery();
		
		
		
		while(resultSet.next())
		{
			enqry.setEnqryId(resultSet.getInt(1));
			enqry.setfName(resultSet.getString(2));
			enqry.setlName(resultSet.getString(3));
			enqry.setContactNo(resultSet.getString(4));
			enqry.setpDomain(resultSet.getString(5));
			enqry.setpLocation(resultSet.getString(6));
		}
		
		if(enqry!=null)
		{
			logger.info("Details Found successfully");
			return enqry;
		}
		else{
			logger.info("Details Not Found successfully");
			return null;
		}
		
		
		
	}




	

	
	}


