package com.schremser.xpnsr.domains;

import java.util.Date;


public abstract class ResourceItemBase {
	private String i_id;
	private String i_name;
	private String i_owner;
	private Date i_created;
	private String i_lastUpdatedBy;
	private Date i_lastUpdated;

	public ResourceItemBase() { }
	
	public ResourceItemBase(ResourceItemBase source ) {
		i_id = source.i_id;
		i_name = source.i_name;
		i_owner = source.i_owner;
		i_created = source.i_created;
		i_lastUpdatedBy = source.i_lastUpdatedBy;
		i_lastUpdated = source.i_lastUpdated;
	}
	
	@Override
	public boolean equals( Object o ) {
		if( this == o ) return true;
		if( !( o instanceof ResourceItemBase ) ) return false;
		ResourceItemBase other = (ResourceItemBase) o;
		if( i_id != null ? !i_id.equals( other.i_id ) : other.i_id != null ) return false;
		if( i_name != null ? !i_name.equals( other.i_name ) : other.i_name != null ) return false;
		if( i_owner != null ? !i_owner.equals( other.i_owner ) : other.i_owner != null ) return false;
		if( i_lastUpdatedBy != null ? !i_lastUpdatedBy.equals( other.i_lastUpdatedBy ) : other.i_lastUpdatedBy != null ) return false;
		// skip i_lastUpdated and i_created
		
		return true;
	}
	
	public void setId( String id ) {
		i_id = id;
	}
	
	public String getId( ) {
		return i_id;
	}
	
	public void setName( String name ) {
		i_name = name;
	}
	
	public String getName( ) {
		return i_name;
	}
	
	public void setOwner( String owner ) {
		i_owner = owner;
	}
	
	public String getOwner( ) {
		return i_owner;
	}
	
	public void setCreated( Date created ) {
		i_created = created;
	}
	
	public Date getCreated( ) {
		return i_created;
	}
	
	public void setLastUpdatedBy( String lastUpdatedBy ) {
		i_lastUpdatedBy = lastUpdatedBy;
	}
	
	public String getLastUpdatedBy( ) {
		return i_lastUpdatedBy;
	}
	
	public void setLastUpdated( Date lastUpdated ) {
		i_lastUpdated = lastUpdated;
	}

	public Date getLastUpdated( ) {
		return i_lastUpdated;
	}

}
