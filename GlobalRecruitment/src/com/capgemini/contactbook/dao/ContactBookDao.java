package com.capgemini.contactbook.dao;

import java.io.IOException;
import java.sql.SQLException;

import com.capgemini.contactbook.bean.EnquiryBean;
import com.capgemini.contactbook.exception.ContactBookException;

public interface ContactBookDao {

	public int addEnquiry(EnquiryBean enqry) throws IOException, SQLException, ContactBookException;
	public EnquiryBean getEnquiryDetails(int enqryId) throws SQLException, IOException;
	//EnquiryBean getEnquiryDetails(int enqryId) throws SQLException, IOException;


}
