package com.xmg.pss.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.pss.domain.Employee;
import com.xmg.pss.domain.SystemMenu;
import com.xmg.pss.mapper.SystemMenuMapper;
import com.xmg.pss.page.PageResult;
import com.xmg.pss.query.SystemMenuQueryObject;
import com.xmg.pss.service.ISystemMenuService;
import com.xmg.pss.util.UserContext;
import com.xmg.pss.vo.SystemMenuVO;

import lombok.Setter;
public class SystemMenuServiceImpl implements ISystemMenuService {
	@Setter
	private SystemMenuMapper systemMenuMapper;
	
	public void  delete(Long id) {
		  systemMenuMapper.delete(id);
	}

	public void save(SystemMenu entity) {
		  systemMenuMapper.save(entity);
	}

	public SystemMenu get(Long id) {
		return systemMenuMapper.get(id);
	}

	public List<SystemMenu> list() {
		return systemMenuMapper.list();
	}

	public void update(SystemMenu entity) {
		  systemMenuMapper.update(entity);
	}

	@Override
	public PageResult pageQuery(SystemMenuQueryObject qo) {
		Long count = systemMenuMapper.getTotalCount(qo);
		if(count<=0){
			return new PageResult(Collections.EMPTY_LIST,0, 1,qo.getPageSize());
		}
		List<SystemMenu> result = systemMenuMapper.pageQuery(qo);
		PageResult pageResult = new PageResult(result,count.intValue(),qo.getCurrentPage(),qo.getPageSize());
		return pageResult;
	}

	@Override
	public List<SystemMenuVO> queryParentListById(Long parentId) {
		List<SystemMenuVO> parentList=new ArrayList<>();
		//菜单对象
		SystemMenu current = systemMenuMapper.get(parentId);
		while (current!=null) {
		SystemMenuVO vo=new SystemMenuVO();
		vo.setId(current.getId());
		vo.setName(current.getName());
		parentList.add(vo);
		 current = current.getParent();
		}
		Collections.reverse(parentList);
		return parentList;
	}

	@Override
	public List<SystemMenu> queryMenusByParentSn(String parentSn) {
		//需要很具用户当前的权限进行菜单的过滤
		//1.获取到当前的登录的用户信息
		Employee employee = UserContext.getCurrentUser();
		if (employee.getAdmin()) {
			return systemMenuMapper.queryMenusByParentSn(parentSn);
		}else {
			return systemMenuMapper.queryMenusByParentSnAndEmpId(parentSn,employee.getId());
		}
				
	}
}
