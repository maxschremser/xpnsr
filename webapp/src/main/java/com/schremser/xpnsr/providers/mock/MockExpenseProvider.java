package com.schremser.xpnsr.providers.mock;

import com.schremser.xpnsr.domains.ExpenseCreationInfo;
import com.schremser.xpnsr.domains.ExpenseInfo;
import com.schremser.xpnsr.domains.ExpenseType;
import com.schremser.xpnsr.providers.IExpenseProvider;
import com.schremser.xpnsr.providers.ResourceNotFoundException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public class MockExpenseProvider implements IExpenseProvider {
	private AtomicInteger i_nextExpenseIdnb = new AtomicInteger( 1 );
	private Map<String,ExpenseInfo> i_expenseByIdnb;
	private Map<ExpenseType,Map<String,ExpenseInfo>> i_expenseByTypeAndName;

	private static MockExpenseProvider s_instance;
	
	public static synchronized MockExpenseProvider instance( ) throws ParseException{
		if( s_instance == null )
			s_instance = new MockExpenseProvider( );
		return s_instance;
	}
	
	private MockExpenseProvider() throws ParseException {
		i_expenseByIdnb = new LinkedHashMap<String,ExpenseInfo>( );
		i_expenseByTypeAndName = new HashMap<ExpenseType,Map<String,ExpenseInfo>>( );
		createSampleExpenses( );
	}

	@Override
	public ExpenseInfo createExpense(String loginSessionId,	ExpenseCreationInfo expenseInfo) {
		expenseInfo.setId( null );
		addExpense( loginSessionId, expenseInfo );
		return expenseInfo;
	}
	
	@Override
	public ExpenseInfo updateExpense(String loginSessionId, String id, ExpenseInfo expenseInfo) {
		if( !i_expenseByIdnb.containsKey(id) )
			throw new ResourceNotFoundException( "Expense", id );
		expenseInfo.setId( id );
		i_expenseByIdnb.put(id, expenseInfo);
		return expenseInfo;
	}

	@Override
	public void deleteExpense(String loginSessionId, String id) {
		if( !i_expenseByIdnb.containsKey(id) )
			throw new ResourceNotFoundException( "Expense", id );
		removeExpense( id );
	}

	@Override
	public ExpenseInfo getExpense(String loginSessionId, String id) {
		if( !i_expenseByIdnb.containsKey( id ) )
			throw new ResourceNotFoundException( "Expense", id );
		return i_expenseByIdnb.get( id );
	}

	@Override
	public Collection<ExpenseInfo> getExpenses(String loginSessionId) {
		return i_expenseByIdnb.values( );
	}
	
	@Override
	public Collection<ExpenseInfo> getExpensesByType( String loginSessionId, ExpenseType type ) {
		Map<String,ExpenseInfo> typeDatasets = i_expenseByTypeAndName.get( type );
		if( typeDatasets != null )
			return typeDatasets.values();
		else
			return Collections.emptyList();
	}

	public ExpenseInfo getExpenseByName( ExpenseType type, String name ) {
		Map<String,ExpenseInfo> typeExpenses = i_expenseByTypeAndName.get( type );
		if( typeExpenses != null )
			return typeExpenses.get( name );
		else
			return null;
	}
	
	@Override
	public String getExpenseIdByName( String loginSessionId, ExpenseType type, String datasetName ) {
		Map<String,ExpenseInfo> typeDatasets = i_expenseByTypeAndName.get( type );
		if( typeDatasets != null ) {
			ExpenseInfo di = typeDatasets.get( datasetName );
			return di != null ? di.getId() : null;
		} else
			return null;
		
	}
	
	private synchronized void addExpense( String loginSessionId, ExpenseInfo expenseInfo ) {
		Integer dsIdnb = i_nextExpenseIdnb.getAndIncrement();
    expenseInfo.setId( dsIdnb.toString( ) );
		i_expenseByIdnb.put( dsIdnb.toString( ), expenseInfo );
		if( expenseHasName( expenseInfo ) ) {
			Map<String,ExpenseInfo> typeExpenses = i_expenseByTypeAndName.get( expenseInfo.getType( ) );
			if( typeExpenses == null ) {
        typeExpenses = new HashMap<String,ExpenseInfo>( );
				i_expenseByTypeAndName.put( expenseInfo.getType(), typeExpenses );
			}
      typeExpenses.put( expenseInfo.getName(), expenseInfo );
		}
	}
	
	private synchronized void removeExpense( String id ) {
		ExpenseInfo dsInfo = i_expenseByIdnb.get( id );
		if( dsInfo != null ) {
			Map<String,ExpenseInfo> typeDatasets = i_expenseByTypeAndName.get( dsInfo.getType( ) );
			if( typeDatasets != null ) {
				typeDatasets.remove( dsInfo.getName( ) );
			}
			i_expenseByIdnb.remove( id );
		}
	}

  private boolean expenseHasName( ExpenseInfo expenseInfo ) {
    return expenseInfo.getName() != null && expenseInfo.getName( ).trim( ).length( ) > 0;
  }

  String[] expenseNames = {
		"Service 2017",
		"Shopping Lui&Max",
		"Sebastiano",
		"Latino (Suceava)",
		"Fitnessland",
		"Waschmaschine",
		"Internet"
	};
	ExpenseType[] expenseTypes = {
		ExpenseType.Car,
		ExpenseType.Fun,
		ExpenseType.Food,
		ExpenseType.Food,
		ExpenseType.Sport,
		ExpenseType.Home,
		ExpenseType.Home
	};
	int[] expenseDates = {
		20170421,
		20170421,
		20170423,
		20170424,
		20170424,
		20170425,
		20170426
	};
	double[] expenseAmounts = {
	    710.59,
      623.42,
      110,
      36.70,
      89,
      30,
      20
  };
	
	private void createSampleExpenses( ) throws ParseException {
		
		for(int i = 0; i< expenseTypes.length; i++ ) {
			ExpenseInfo ds = new ExpenseInfo( ); 
			ds.setType( expenseTypes[i] );
			ds.setName( expenseNames[i] );
			ds.setDate( new SimpleDateFormat("yyyyMMdd").parse(expenseDates[i] + "") );
			ds.setOwner( "algo" );
			ds.setAmount(expenseAmounts[i]);
			addExpense( null, ds );

			ExpenseInfo ds2 = new ExpenseInfo( );
			ds2.setType( expenseTypes[i] );
			ds2.setName( "another " + expenseNames[i]);
      ds2.setDate( new SimpleDateFormat("yyyyMMdd").parse(expenseDates[i] + "") );
			ds2.setOwner( "algo" );
			ds2.setAmount(expenseAmounts[i]);
			addExpense( null, ds2 );
		}

		ExpenseInfo templateDS = new ExpenseInfo( );
		templateDS.setType( ExpenseType.Electronic);
		templateDS.setName( "PrePaid SIM" );
		Calendar calendar = Calendar.getInstance();
		calendar.set(2017,04,20);
		templateDS.setDate( calendar.getTime() );
		templateDS.setOwner( "algo" );
		templateDS.setAmount(5);
		addExpense( null, templateDS );
	}
}
