package com.ims.controller;

import com.ims.entity.Student;
import com.ims.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 服务提供者-学生信息控制器
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/student")
public class StudentProviderController {

    @Resource
    private StudentService studentService;

    /**
     * 添加或者修改学生信息
     *
     * @param student
     * @return
     */
    @PostMapping(value = "/save")
    public boolean save(@RequestBody Student student) {
        try {
            studentService.save(student);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 查询学生信息
     *
     * @return
     */
    @GetMapping(value = "/list")
    public List<Student> list() {
        return studentService.list();
    }

    /**
     * 根据id查询学生信息
     *
     * @return
     */
    @GetMapping(value = "/get/{id}")
    public Student get(@PathVariable("id") Integer id) {
        return studentService.findById(id);
    }

    /**
     * 根据id删除学生信息
     *
     * @return
     */
    @GetMapping(value = "/delete/{id}")
    public boolean delete(@PathVariable("id") Integer id) {
        try {
            studentService.delete(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
