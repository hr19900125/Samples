package com.ryan.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ryan.http.HttpResult;
import com.ryan.model.Privilege;
import com.ryan.model.Role;
import com.ryan.service.PrivilegeService;
import com.ryan.service.RolePrivilegeService;
import com.ryan.service.RoleService;
import com.ryan.util.Pagination;
import com.ryan.util.ResultCode;
import com.ryan.util.TextUtils;

@Controller
public class RolesController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private PrivilegeService privilegeService;
	@Autowired
	private RolePrivilegeService rolePrivilegeService;
	
	GsonBuilder builder = new GsonBuilder();   

	@RequestMapping("roles.htm")
	public ModelAndView showRolesPage(
			@RequestParam(value = "p", defaultValue = "1") int page,
			@RequestParam(value = "s", defaultValue = "") String search,
			HttpServletRequest request, HttpServletResponse response) {
		Pagination p = new Pagination();
		p.setPage(page);
		if(TextUtils.isEmpty(search)){
			search = null;
		}
		List<Role> roles = roleService.getRoles(search, p);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("roles");
        mv.addObject("roleList", roles);
        mv.addObject("p", p);
		return mv;
	}
	
	@RequestMapping("role.htm")
    public ModelAndView showRole(@RequestParam(value="rid",defaultValue = "0")long rid,HttpServletRequest request, HttpServletResponse response){
    	ModelAndView mv = new ModelAndView();
    	if(rid == 0){
    		//FIXME rid = 0的情况
    	}
    	
    	Role r = roleService.getRole(rid);
    	List<Privilege> privilegeList = rolePrivilegeService.getPrivilegeByRoleID(rid);
    	mv.addObject("privilegeList", privilegeList);
    	mv.addObject("roleID", rid);
    	if(r == null){
    		//FIXME r == null 的情况
    	}else{
    		mv.addObject("role",r);
    	}
    	mv.setViewName("role");
    	return mv;
    }
	
	@RequestMapping("roleEdit.htm")
	public ModelAndView showRoleEditPage(@RequestParam(value = "rid",defaultValue = "0")long rid,HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("roleEdit");
		List<Privilege> privilegeList = privilegeService.getAllPrivilege();
		if(rid == 0){
		   mv.addObject("isEdit",false);
		   mv.addObject("roleID",rid);
		   mv.addObject("privilegeList", privilegeList);
		}else{
		   Role role = roleService.getRole(rid);
		   mv.addObject("role", role);
		   mv.addObject("roleID",rid);
		   mv.addObject("isEdit", true);
		   List<Privilege> rolePrivilegeList = rolePrivilegeService.getPrivilegeByRoleID(rid);
		   if(privilegeList!=null){
			   Privilege p1 = null;
			   Privilege p2 = null;
			   for(int i=0,size=privilegeList.size();i<size;i++){
				   p1 = privilegeList.get(i);
				   for(int j=0,length=rolePrivilegeList.size();j<length;j++){
					   p2 = rolePrivilegeList.get(j);
					   if(p1.getID() == p2.getID()){
						   p1.setSelected(true);
						   break;
					   }
				   }
			   }
		   }
		   mv.addObject("privilegeList", privilegeList);
		}
		return mv;
	}
	
	@RequestMapping(value="deleteRole.do",method = RequestMethod.POST)
	public @ResponseBody String deleteRole(@RequestParam(value="rid",defaultValue="0")long rid){
		Gson gson = builder.create();
		String body = "";
		HttpResult result = new HttpResult();
		if(rid>0){
			roleService.deleteRole(rid);
			rolePrivilegeService.deleteRolePrivilegeByRid(rid);
			result.setResultCode(ResultCode.RESULT_OK);
			result.setResultValue(String.valueOf(rid));
		}else{
			result.setResultCode(ResultCode.RESULT_ERROR);
			result.setResultValue(null);
		}
		body = gson.toJson(result);
		return body;
	}
	
	@RequestMapping(value="saveRole.do",method = RequestMethod.POST)
	public @ResponseBody String saveRole(@RequestParam(value="rid",defaultValue="0")long rid,@RequestParam(value = "n")String type,@RequestParam(value = "d")String descr,@RequestParam("ids")String ids){
//		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();
		if(rid == 0){
			//创建新的角色
			HttpResult result = new HttpResult();
			Role role = new Role();
			role.setRoleName(type);
			role.setRoleDescr(descr);
			Date nowTime = new Date();
			role.setCreateTime(nowTime);
			role.setModifyTime(nowTime);
			role.setCreateUserName("admin");
			roleService.insertRole(role);

			if (ids != null && role.getRoleID() > 0) {
				String[] idList = ids.split(",");
				if (idList.length > 0) {
					for (String id : idList) {
						if (!TextUtils.isEmpty(id) && TextUtils.isNumeric(id)) {
							long pid = Long.valueOf(id);
							rolePrivilegeService.addRolePrivilege(
									role.getRoleID(), pid);
						}
					}
				}
			}
			String body = "";
			try {
				if (role.getRoleID() > 0) {
					result.setResultCode(ResultCode.RESULT_OK);
					result.setResultValue(String.valueOf(role.getRoleID()));
				} else {
					result.setResultCode(ResultCode.RESULT_ERROR);
					result.setResultValue(null);
				}
			} catch (Exception e) {
				e.printStackTrace();
				result.setResultCode(ResultCode.RESULT_ERROR);
				result.setResultValue(null);
			} finally {
				body = gson.toJson(result);
			}
			return body;
		}else{
			//编辑后保存
			HttpResult result = new HttpResult();
			Role role = roleService.getRole(rid);
			role.setRoleName(type);
			role.setRoleDescr(descr);
			Date nowTime = new Date();
			role.setModifyTime(nowTime);
			roleService.updateRole(role);
			rolePrivilegeService.deleteRolePrivilegeByRid(rid);
			if (ids != null && role.getRoleID() > 0) {
				String[] idList = ids.split(",");
				if (idList.length > 0) {
					for (String id : idList) {
						if (!TextUtils.isEmpty(id) && TextUtils.isNumeric(id)) {
							long pid = Long.valueOf(id);
							rolePrivilegeService.addRolePrivilege(
									role.getRoleID(), pid);
						}
					}
				}
			}
			String body = "";
			try {
				if (role.getRoleID() > 0) {
					result.setResultCode(ResultCode.RESULT_OK);
					result.setResultValue(String.valueOf(role.getRoleID()));
				} else {
					result.setResultCode(ResultCode.RESULT_ERROR);
					result.setResultValue(null);
				}
			} catch (Exception e) {
				e.printStackTrace();
				result.setResultCode(ResultCode.RESULT_ERROR);
				result.setResultValue(null);
			} finally {
				body = gson.toJson(result);
			}
			System.out.println("body="+body);
			return body;
		}
	}
	
	@RequestMapping(value="getAllRoles.do")
	public @ResponseBody String getAllRoles(){
		String body = "";
		Gson gson = builder.create();
		HttpResult result = new HttpResult();
        List<Role> list = roleService.getAllRoles();
        result.setResultCode(ResultCode.RESULT_OK);
        result.setResultValue(list);
        body = gson.toJson(result);
        System.out.println("body="+body);
		return body;
	}
}
