package com.schremser.xpnsr.providers;

import com.schremser.xpnsr.domains.ExpenseCreationInfo;
import com.schremser.xpnsr.domains.ExpenseInfo;
import com.schremser.xpnsr.domains.ExpenseType;

import java.util.Collection;

/**
 * Created by AT003053 on 25/04/2017.
 */
public interface IExpenseProvider {
  ExpenseInfo createExpense( String loginSessionId, ExpenseCreationInfo datasetInfo );
  ExpenseInfo updateExpense(String loginSessionId, String id, ExpenseInfo datasetInfo );
  void deleteExpense(String loginSessionId, String id );
  ExpenseInfo getExpense( String loginSessionId, String id );
  Collection<ExpenseInfo> getExpenses(String loginSessionId );
  Collection<ExpenseInfo> getExpensesByType( String loginSessionId, ExpenseType type );
  String getExpenseIdByName( String loginSessionId, ExpenseType dsType, String expenseName );

}
