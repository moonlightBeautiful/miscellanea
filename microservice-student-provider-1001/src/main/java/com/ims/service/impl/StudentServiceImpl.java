package com.ims.service.impl;

import com.ims.entity.Student;
import com.ims.repository.StudentRepository;
import com.ims.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 学生信息Service实现类
 *
 * @author Administrator
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentRepository studentRepository;

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student findById(Integer id) {
        return studentRepository.findOne(id);
    }

    @Override
    public List<Student> list() {
        return studentRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        studentRepository.delete(id);
    }

}