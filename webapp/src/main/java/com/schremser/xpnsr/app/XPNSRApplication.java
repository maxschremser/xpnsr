package com.schremser.xpnsr.app;

import com.schremser.xpnsr.providers.CORSFilter;
import com.schremser.xpnsr.services.ExpenseResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/*
 * Notes:
 * 
 * - injection of ServletContext did not work. Using ServletContextListener instead
 * 
 * - 'mock' mode
 * If ALGO_HOME or ALGO_TOP are not defined launching in 'mock' mode
 * This means basically that mock implementations of LoginService and others may be launched
 * In 'mock' mode needs to assure that no calls to configuration and logging are made as this will 
 * cause attempts by those modules to contact the naming service
 * 
 * 
 */


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
		//
		ProviderRegistry.instance( new SimpleProviderFactory() );

		Set<Object> singletons = new HashSet<>( );

		singletons.add(new ExpenseResource());
		singletons.add(new CORSFilter());

		// singletons.add( new TestRequestFilter( ) );
		log.info( "Number of singletons: " + singletons.size( ) );
		
		return singletons;
	}
	
}

