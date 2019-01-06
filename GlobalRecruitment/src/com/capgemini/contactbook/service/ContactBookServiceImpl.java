package com.capgemini.contactbook.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.contactbook.bean.EnquiryBean;
import com.capgemini.contactbook.dao.ContactBookDao;
import com.capgemini.contactbook.dao.ContactBookDaoImpl;
import com.capgemini.contactbook.exception.ContactBookException;

public class ContactBookServiceImpl implements ContactBookService {

	ContactBookDao contactBookImpl=new ContactBookDaoImpl();
	EnquiryBean enqry=new EnquiryBean();
	
	@Override
	public int addEnquiry(EnquiryBean enqry) throws IOException, SQLException, ContactBookException {
		int enqrySeq;
		enqrySeq=contactBookImpl.addEnquiry(enqry);
		return enqrySeq;
		
	}

	
	
	
	public boolean validateCustomer(EnquiryBean enqry) {
		
		boolean boo=true;
		List<String> validationErrors=new ArrayList<String>();
		if(!isValidFName(enqry.getfName()))
		{
			validationErrors.add("Please Enter more than 3 Characters\n");
			
		}
		if(!isValidLName(enqry.getlName()))
		{
			validationErrors.add("\nPlease enter more than 3 characters\n");
			
		}
		if(!isValidateContactNo(enqry.getContactNo()))
		{
			validationErrors.add("\nPlease enter 10 digit\n");
			
		}
		if(!isValidatePDomain(enqry.getpDomain()))
		{
			validationErrors.add("\nPlease Enter Correct Domain\n");
		}
		
		if(!isValidatePLocation(enqry.getpLocation()))
		{
			validationErrors.add("\nPlease enter correct location\n");
			
		}
		
		if(!validationErrors.isEmpty())
		{
			System.out.println("Errors"+validationErrors);
			boo=false;
		}
		return boo;
	}

	private boolean isValidatePLocation(String pLocation) {
		Pattern pLocationPattern=Pattern.compile("^[A-Za-z]{3,}$");
		Matcher pLocationmatcher=pLocationPattern.matcher(pLocation);
		return pLocationmatcher.matches();
	}

	private boolean isValidatePDomain(String pDomain) {
		Pattern pDomainPattern=Pattern.compile("^[A-Za-z]{3,}$");
		Matcher pDomainmatcher=pDomainPattern.matcher(pDomain);
		return pDomainmatcher.matches();
	}

	private boolean isValidateContactNo(String contactNo) {
		Pattern phonePattern=Pattern.compile("^[6-9]{1}[0-9]{9}$");
		Matcher phonematcher=phonePattern.matcher(contactNo);
		return phonematcher.matches();
	}

	private boolean isValidLName(String lName) {
		Pattern lnamePattern=Pattern.compile("^[A-Za-z]{3,}$");
		Matcher lnamematcher=lnamePattern.matcher(lName);
		return lnamematcher.matches();
	}

	private boolean isValidFName(String fName) {
		Pattern fnamePattern=Pattern.compile("^[A-Za-z]{3,}$");
		Matcher fnamematcher=fnamePattern.matcher(fName);
		return fnamematcher.matches();
	}


	@Override
	public EnquiryBean getEnquiryDetails(int enqryId) throws SQLException, IOException {
		contactBookImpl=new ContactBookDaoImpl();
		EnquiryBean enqry=null;
		enqry=contactBookImpl.getEnquiryDetails(enqryId);
		return enqry;
	}

	@Override
	public boolean isValidEnquiry(EnquiryBean enqry) {
	return true;
	}

}
