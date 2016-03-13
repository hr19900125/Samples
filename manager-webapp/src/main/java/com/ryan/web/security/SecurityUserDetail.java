package com.ryan.web.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;

import com.ryan.util.WebConstant;

public class SecurityUserDetail extends User{

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
	protected static final String ROLE_PREFIX = "ROLE_";
	public static final GrantedAuthority DEFAULT_USER_ROLE = new GrantedAuthorityImpl(ROLE_PREFIX+"USER");
	
	private String name;
	
	private int type;
	
	public SecurityUserDetail(String username, String password,String name,int type,
			boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		this.type = type;
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public boolean isParentAccount(){
		return type == WebConstant.PARENT_LOGIN;
	}
	
	public boolean isTeacherAccount(){
		return type == WebConstant.TEACHER_LOGIN;
	}

}
