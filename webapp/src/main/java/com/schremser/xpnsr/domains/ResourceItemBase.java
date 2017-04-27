package com.schremser.xpnsr.domains;

import java.util.Date;


public abstract class ResourceItemBase {
	private String i_id;
	private String i_name;
	private String i_owner;
	private Date i_created;

	public ResourceItemBase() { }
	
	public ResourceItemBase(ResourceItemBase source ) {
		i_id = source.i_id;
		i_name = source.i_name;
		i_owner = source.i_owner;
		i_created = source.i_created;
	}
	
	@Override
	public boolean equals( Object o ) {
		if( this == o ) return true;
		if( !( o instanceof ResourceItemBase ) ) return false;
		ResourceItemBase other = (ResourceItemBase) o;
		if( i_id != null ? !i_id.equals( other.i_id ) : other.i_id != null ) return false;
		if( i_name != null ? !i_name.equals( other.i_name ) : other.i_name != null ) return false;
    return i_owner != null ? i_owner.equals(other.i_owner) : other.i_owner == null;
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
	
}
