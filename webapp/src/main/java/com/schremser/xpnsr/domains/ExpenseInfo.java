package com.schremser.xpnsr.domains;


/*
 * ExpenseInfo
 * 
 */
public class ExpenseInfo extends ResourceItemBase {
	private ExpenseType i_type;
	private Integer i_expenseDate; // YYYYMMDD format
	private String i_description;

	public ExpenseInfo() {
	}
	
	public ExpenseInfo(ExpenseInfo source ) {
		i_type = source.i_type;
		i_expenseDate = source.i_expenseDate;
		i_description = source.i_description;
	}
	
	public void setType( ExpenseType type ) {
		i_type = type;
	}
	
	public ExpenseType getType( ) {
		return i_type;
	}
	
	public Integer getExpenseDate( ) {
		return i_expenseDate;
	}
	
	public void setExpenseDate( Integer expenseDate ) {
		i_expenseDate = expenseDate;
	}
	
	public void setDescription( String description ) {
		i_description = description;
	}
	
	public String getDescription( ) {
		return i_description;
	}
	
}
