package com.xmg.wms.mapper;

import org.apache.ibatis.annotations.Param;

import com.xmg.wms.domain.Student;

public interface StudentMapper {

	void save(Student s);

	Student get(Long id);

	//维护中间表中的关系
	void updateRelation(@Param("teacherId") Long teacherId,
			@Param("studentId") Long studentId);
}
