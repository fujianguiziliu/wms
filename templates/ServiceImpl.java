package ${packageName}.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${packageName}.domain.${className};
import ${packageName}.mapper.${className}Mapper;
import ${packageName}.page.PageResult;
import ${packageName}.query.${className}QueryObject;
import ${packageName}.service.I${className}Service;
import lombok.Setter;
public class ${className}ServiceImpl implements I${className}Service {
	@Setter
	private ${className}Mapper ${objectName}Mapper;
	
	public void  delete(Long id) {
		  ${objectName}Mapper.delete(id);
	}

	public void save(${className} entity) {
		  ${objectName}Mapper.save(entity);
	}

	public ${className} get(Long id) {
		return ${objectName}Mapper.get(id);
	}

	public List<${className}> list() {
		return ${objectName}Mapper.list();
	}

	public void update(${className} entity) {
		  ${objectName}Mapper.update(entity);
	}

	@Override
	public PageResult pageQuery(${className}QueryObject qo) {
		Long count = ${objectName}Mapper.getTotalCount(qo);
		if(count<=0){
			return new PageResult(Collections.EMPTY_LIST,0, 1,qo.getPageSize());
		}
		List<${className}> result = ${objectName}Mapper.pageQuery(qo);
		PageResult pageResult = new PageResult(result,count.intValue(),qo.getCurrentPage(),qo.getPageSize());
		return pageResult;
	}
}
