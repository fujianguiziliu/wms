package com.xmg.pss.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xmg.pss.domain.Student;
import com.xmg.pss.domain.Teacher;
import com.xmg.pss.mapper.StudentMapper;
import com.xmg.pss.mapper.TeacherMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Many2ManyTest {

	@Autowired
	private TeacherMapper teacherMapper;
	@Autowired
	private StudentMapper studentMapper;

	@Test
	public void testSave() throws Exception {
		//保存学生
		Student s1 = new Student();
		s1.setName("朱占湖");
		Student s2 = new Student();
		s2.setName("汪友烽");
		//保存老师
		Teacher t1 = new Teacher();
		t1.setName("Will");
		Teacher t2 = new Teacher();
		t2.setName("Stef");

		teacherMapper.save(t1);
		teacherMapper.save(t2);

		studentMapper.save(s1);
		studentMapper.save(s2);

		//维护老师和学生的关系
		List<Teacher> ts = new ArrayList<>();
		ts.add(t1);
		ts.add(t2);
		s1.setTeachers(ts);
		s2.setTeachers(ts);

		//执行保存操作,将关系保存到中间表中
		for (Teacher teacher : s1.getTeachers()) {
			studentMapper.updateRelation(teacher.getId(), s1.getId());
		}
		for (Teacher teacher : s2.getTeachers()) {
			studentMapper.updateRelation(teacher.getId(), s2.getId());
		}
	}

	@Test
	public void testGet() throws Exception {
		Student student = studentMapper.get(1L);
		System.out.println(student.getTeachers());
	}
}
