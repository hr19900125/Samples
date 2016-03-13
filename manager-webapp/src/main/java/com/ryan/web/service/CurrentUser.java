package com.ryan.web.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.ryan.service.UserService;
import com.ryan.web.security.SecurityUserDetail;

public class CurrentUser {
	
	/**
	 * Gets the user name of the user from the Authentication object
	 * 
	 * @return the user name as string
	 */
	public static String getPrincipal() {
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
		if (obj instanceof SecurityUserDetail) {
			return ((SecurityUserDetail) obj).getName();
		} else {
			return "";
		}
	}
	
	public static boolean isParentAccount(){
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (obj instanceof SecurityUserDetail) {
			return ((SecurityUserDetail) obj).isParentAccount();
		} else {
			return false;
		}
	}
	
	public static boolean isTeacherAccount(){
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (obj instanceof SecurityUserDetail) {
			return ((SecurityUserDetail) obj).isTeacherAccount();
		} else {
			return false;
		}
	}
	
	public static String getUsername(){
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (obj instanceof SecurityUserDetail) {
			return ((SecurityUserDetail) obj).getUsername();
		} else {
			return "";
		}
	}

	/**
	 * Is the user granted all of the grantedAuthorities passed in
	 * 
	 * @param roles
	 *            a string array of grantedAuth
	 * @return true if user has all of the listed authorities/roles, otherwise
	 *         false
	 */
	public static boolean allGranted(String[] checkForAuths) {
		Set<String> userAuths = getUserAuthorities();
		for (String auth : checkForAuths) {
			if (userAuths.contains(auth))
				continue;
			return false;
		}
		return true;
	}

	/**
	 * Is the user granted any of the grantedAuthorities passed into
	 * 
	 * @param roles
	 *            a string array of grantedAuth
	 * @return true if user has any of the listed authorities/roles, otherwise
	 *         false
	 */
	public static boolean anyGranted(String[] checkForAuths) {
		Set<String> userAuths = getUserAuthorities();
		for (String auth : checkForAuths) {
			if (userAuths.contains(auth))
				return true;
		}
		return false;
	}

	/**
	 * is the user granted none of the supplied roles
	 * 
	 * @param roles
	 *            a string array of roles
	 * @return true only if none of listed roles are granted
	 */
	public static boolean noneGranted(String[] checkForAuths) {
		Set<String> userAuths = getUserAuthorities();
		for (String auth : checkForAuths) {
			if (userAuths.contains(auth))
				return false;
		}
		return true;
	}

	private static Set<String> getUserAuthorities() {
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Set<String> roles = new HashSet<String>();
		if (obj instanceof UserDetails) {
			Collection<GrantedAuthority> gas = (Collection<GrantedAuthority>) ((UserDetails) obj).getAuthorities();
			for (GrantedAuthority ga : gas) {
				roles.add(ga.getAuthority());
			}
		}
		return roles;
	}
}
