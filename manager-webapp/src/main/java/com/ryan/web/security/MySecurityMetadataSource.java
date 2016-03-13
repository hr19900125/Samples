package com.ryan.web.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.ryan.model.Privilege;
import com.ryan.model.RolePrivilege;
import com.ryan.service.PrivilegeService;
import com.ryan.service.RolePrivilegeService;

//1 加载资源与权限的对应关系
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	
	private PrivilegeService privilegeService;
	private RolePrivilegeService rolePrivilegeService;
	private static Map<String, Collection<ConfigAttribute>> rolesMap = null;

	// 由spring调用
	public MySecurityMetadataSource(PrivilegeService privilegeService,RolePrivilegeService rolePrivilegeService) {
		this.privilegeService = privilegeService;
		this.rolePrivilegeService = rolePrivilegeService;
		loadResourceDefine();
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	// 加载所有资源与角色的关系,加载该资源有哪些角色可以访问
	private void loadResourceDefine() {
		if (rolesMap == null) {
			rolesMap = new HashMap<String, Collection<ConfigAttribute>>();
			List<Privilege> privileges = this.privilegeService.getAllPrivilege();
			
			Collection<ConfigAttribute> configAttributes = null;
			for (Privilege p : privileges) {
				configAttributes = new ArrayList<ConfigAttribute>();
				// 以权限名封装为Spring的security Object
				List<RolePrivilege> rolePlist = rolePrivilegeService.getRolePrivilegesByPid(p.getID());
				if(rolePlist != null){
					ConfigAttribute configAttribute = null;
					for(RolePrivilege rp : rolePlist){
						configAttribute = new SecurityConfig(SecurityUserDetail.ROLE_PREFIX+String.valueOf(rp.getRoleID()));
						configAttributes.add(configAttribute);
					}
				}
				rolesMap.put(p.getPrivilegeAction(), configAttributes);
			}
		}
	}

	// 返回所请求资源所需要的权限的角色
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		if(requestUrl == null || requestUrl.length() == 0){
			return null;
		}
		
		int questIndex = requestUrl.indexOf('?');  
	    String url = null;
		if (questIndex == -1) {  
	       url = requestUrl;
	    }else{
	       url = requestUrl.substring(0, questIndex);
	    }
		System.out.println("requestUrl is " + url);
		if (rolesMap == null) {
			loadResourceDefine();
		}
		return rolesMap.get(url);
	}
	
}