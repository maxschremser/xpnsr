package com.schremser.xpnsr.app;

import com.schremser.xpnsr.providers.IExpenseProvider;

public interface IProviderFactory {
	IExpenseProvider createExpenseProvider();
}
