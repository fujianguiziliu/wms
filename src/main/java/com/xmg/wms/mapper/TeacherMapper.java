package com.xmg.wms.mapper;

import com.xmg.wms.domain.Teacher;

public interface TeacherMapper {

	void save(Teacher t);

	Teacher get(Long id);
}
