package com.ryan.web.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ryan.model.ParentAccount;
import com.ryan.model.Privilege;
import com.ryan.model.TeacherAccount;
import com.ryan.service.ParentAccountService;
import com.ryan.service.TeacherService;
import com.ryan.util.WebConstant;

public class MyUserDetailServiceImpl implements UserDetailsService {
	
	private TeacherService teacherService;
    private ParentAccountService parentAccountService;

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	public void setParentAccountService(ParentAccountService parentAccountService) {
		this.parentAccountService = parentAccountService;
	}

	public UserDetails loadUserByUsername(String key) throws UsernameNotFoundException {
		System.out.println("username is " + key);
	    String[] keys = key.split(";");
	    String username = keys[0];
	    int loginType = Integer.parseInt(keys[1]);
	    String password = "";
	    String name = "";
	    if(loginType == WebConstant.PARENT_LOGIN){
	    	ParentAccount pa = parentAccountService.getParentAccount(username);
	    	password = pa.getPassword();
	    	name = pa.getParentName();
	    }else{
	    	TeacherAccount ta = teacherService.getTeacherAccountByUsername(username);
	    	password = ta.getPassword();
	    	name = ta.getTeacherName();
	    }
	    
		if (password == null) {
			throw new UsernameNotFoundException(username);
		}
		Set<GrantedAuthority> grantedAuths = null;
		if(loginType == WebConstant.PARENT_LOGIN){
			grantedAuths = obtionParentGrantedAuthorities(username);
		}else{
			grantedAuths = obtionTeacherGrantedAuthorities(username);
		}
		
		grantedAuths.add(SecurityUserDetail.DEFAULT_USER_ROLE);

		boolean enables = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
	    SecurityUserDetail userdetail = new SecurityUserDetail(username,password, name, loginType,enables,
				accountNonExpired, credentialsNonExpired, accountNonLocked,
				grantedAuths);
		return userdetail;
	}

	private Set<GrantedAuthority> obtionTeacherGrantedAuthorities(String telephone) {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		List<Long> roleIDs = teacherService.getTeacherRoleIDs(telephone);
		for(long id : roleIDs){
			authSet.add(new GrantedAuthorityImpl(SecurityUserDetail.ROLE_PREFIX+String.valueOf(id)));
		}
		return authSet;
	}
	
	private Set<GrantedAuthority> obtionParentGrantedAuthorities(String telephone){
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		List<Long> roleIDs = parentAccountService.getParentRoleIDs(telephone);
		for(long id: roleIDs){
			authSet.add(new GrantedAuthorityImpl(SecurityUserDetail.ROLE_PREFIX+String.valueOf(id)));
		}
		return authSet;
	}
}