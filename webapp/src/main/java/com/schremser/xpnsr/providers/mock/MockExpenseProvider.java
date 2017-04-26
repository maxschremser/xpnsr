package com.schremser.xpnsr.providers.mock;

import com.schremser.xpnsr.domains.ExpenseCreationInfo;
import com.schremser.xpnsr.domains.ExpenseInfo;
import com.schremser.xpnsr.domains.ExpenseType;
import com.schremser.xpnsr.domains.ResourceItemBase;
import com.schremser.xpnsr.providers.IExpenseProvider;
import com.schremser.xpnsr.providers.ResourceNotFoundException;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public class MockExpenseProvider implements IExpenseProvider {
	private AtomicInteger i_nextExpenseIdnb = new AtomicInteger( 1 );
	private Map<String,ExpenseInfo> i_expenseByIdnb;
	private Map<ExpenseType,Map<String,ExpenseInfo>> i_expenseByTypeAndName;

	private static MockExpenseProvider s_instance;
	
	public static synchronized MockExpenseProvider instance( ) {
		if( s_instance == null )
			s_instance = new MockExpenseProvider( );
		return s_instance;
	}
	
	private MockExpenseProvider() {
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
		setLastUpdated( loginSessionId, expenseInfo );
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

  protected void setLastUpdated( String loginSessionId, ResourceItemBase item ) {
    // call with loginSessionId == null only made during initialization of the mock data
    //
    // String user = loginSessionId == null ? "algo" : LoginService.instance().getLoginSessionInfo( loginSessionId ).getUser();
    String user = loginSessionId == null ? "algo" : "maxi";
    item.setLastUpdatedBy( user );
    if( item.getOwner() == null )
      item.setOwner( user );
    Date now = Calendar.getInstance().getTime();
    if( item.getCreated() == null )
      item.setCreated( now );
    item.setLastUpdated( now );
  }

  private boolean expenseHasName( ExpenseInfo expenseInfo ) {
    return expenseInfo.getName() != null && expenseInfo.getName( ).trim( ).length( ) > 0;
  }

  String[] expenseNames = {
		"Master 2016/05/01",
		"Behavioural Assumptions 2016/04/01",
		"Position Data 2016/05/01",
		"Position Data 2016/05/02",
		"Market Data 2016/05/01",
		"Market Data 2016/05/02",
		"Planning 2016/04/01"
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
		20160423,
		20160424,
		20160424,
		20160425,
		20160426
	};
	
	private void createSampleExpenses( ) {
		
		for(int i = 0; i< expenseTypes.length; i++ ) {
			ExpenseInfo ds = new ExpenseInfo( ); 
			ds.setType( expenseTypes[i] );
			ds.setName( expenseNames[i] );
			ds.setExpenseDate( expenseDates[i] );
			ds.setOwner( "algo" );
			addExpense( null, ds );

			ExpenseInfo ds2 = new ExpenseInfo( );
			ds2.setType( expenseTypes[i] );
			ds2.setName( "another " + expenseNames[i]);
			ds.setExpenseDate( expenseDates[i] );
			ds2.setOwner( "algo" );
			addExpense( null, ds2 );
		}

		ExpenseInfo templateDS = new ExpenseInfo( );
		templateDS.setType( ExpenseType.Electronic);
		templateDS.setName( "PrePaid SIM" );
		templateDS.setExpenseDate( 20170420 );
		templateDS.setOwner( "algo" );
		addExpense( null, templateDS );
	}
}
