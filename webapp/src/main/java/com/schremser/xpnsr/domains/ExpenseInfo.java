package com.schremser.xpnsr.domains;


import java.util.Date;

/*
 * ExpenseInfo
 *
 * uses expense.ts
 *
 export class Expense {
  id: number;
  date: Date; // YYYYmmdd
  name: string;
  amount: number;
  type: string;
  owner: string;
 }

 */
public class ExpenseInfo extends ResourceItemBase {
	private ExpenseType i_type;
	private Double d_amount;
	private Date i_date; // YYYYMMDD format
	private String i_description;

	public ExpenseInfo() {
	}
	
	public ExpenseInfo(ExpenseInfo source ) {
		i_type = source.i_type;
		i_date = source.i_date;
		i_description = source.i_description;
	}
	
	public void setType( ExpenseType type ) {
		i_type = type;
	}
	
	public ExpenseType getType( ) {
		return i_type;
	}
	
	public Date getDate( ) {
		return i_date;
	}
	
	public void setDate( Date expenseDate ) {
		i_date = expenseDate;
	}
	
	public void setDescription( String description ) {
		i_description = description;
	}
	
	public String getDescription( ) {
		return i_description;
	}

	public Double getAmount() {
		return d_amount;
	}

	public void setAmount(Double amount) {
		this.d_amount = amount;
	}

	public void setAmount(Integer amount) {
		this.d_amount = Double.valueOf(amount);
	}
}
