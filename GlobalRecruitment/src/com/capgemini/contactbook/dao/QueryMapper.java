package com.capgemini.contactbook.dao;

public interface QueryMapper {
	public static final String Insert_Details="Insert into enquiry values(enqryId_Seq.nextval,?,?,?,?,?)";
	public static final String enqryIdS="Select enqryId_Seq.currval from enquiry";
	public static final String getDetials="Select * from enquiry where enqryId=?";

}
