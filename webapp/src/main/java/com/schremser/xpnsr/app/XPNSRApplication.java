package com.schremser.xpnsr.app;

import com.schremser.xpnsr.providers.CORSFilter;
import com.schremser.xpnsr.services.ExpenseResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath( "/api" )
public class XPNSRApplication extends Application {
	private final static Logger log = LoggerFactory.getLogger(XPNSRApplication.class);
	public XPNSRApplication( ) {
		log.info("XPNSRApplication created");
	}

	@Override
	public Set<Class<?>> getClasses( ) {
		Set<Class<?>> classes = new HashSet<Class<?>>( );
		return classes;
	}
	
	@Override
	public Set<Object> getSingletons( ) {
		log.debug( "XPNSRApplication: getSingletons" );

		// Initialize ProviderRegistry
		ProviderRegistry.instance( new SimpleProviderFactory() );

		Set<Object> singletons = new HashSet<>( );

		singletons.add(new ExpenseResource());
		singletons.add(new CORSFilter());

		log.info( "Number of singletons: " + singletons.size( ) );
		
		return singletons;
	}
	
}

