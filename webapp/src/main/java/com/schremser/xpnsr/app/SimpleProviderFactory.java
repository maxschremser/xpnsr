package com.schremser.xpnsr.app;

import com.schremser.xpnsr.providers.IExpenseProvider;
import com.schremser.xpnsr.providers.mock.MockExpenseProvider;

public class SimpleProviderFactory implements IProviderFactory {

	public SimpleProviderFactory() {
	}

	@Override
	public IExpenseProvider createExpenseProvider() {
		//IExpenseProvider provider = i_mockMode ? MockExpenseProvider.instance() : UDSDatasetProvider.instance();
		IExpenseProvider provider = MockExpenseProvider.instance();
		return provider;
	}
}
