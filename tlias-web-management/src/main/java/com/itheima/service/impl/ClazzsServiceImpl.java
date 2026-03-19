package com.itheima.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ClazzsMapper;
import com.itheima.pojo.Clazz;
import com.itheima.exception.BusinessException;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClazzsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ClazzsServiceImpl implements ClazzsService {

    @Autowired
    private ClazzsMapper clazzsMapper;

    /**
     * 查询所有员工信息
     *
     * @return
     */
    @Transactional( rollbackFor = Exception.class )
    @Override
    public PageResult<Clazz> findAllClazzs(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        List<Clazz> clazzsList = clazzsMapper.findAllClazzs(empQueryParam);


        Page<Clazz> clazzsPage = (Page<Clazz>) clazzsList;
        return new PageResult<>(clazzsPage.getTotal(), clazzsPage.getResult());
    }

    /**
     *  删除班级
     */
    @Override
    public void deleteClazzById(Integer id) {
        //clazzsMapper.deleteClazzById(id);
        Integer count = clazzsMapper.countStudentByClazzId(id);
        log.info("班级下有学生:{}", count);
        if (count > 0) {
            throw new BusinessException("该班级下有学生，不能删除");
        }
        clazzsMapper.deleteClazzById(id);
    }

    /**
    * 添加班级
    */
    @Override
    public void addClazz(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzsMapper.addClazz(clazz);
    }

    @Override
    public Clazz getClazzById(Integer id) {
        return clazzsMapper.getClazzById(id);
    }

    @Override
    public void updateClazzById(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzsMapper.updateClazzById(clazz);
    }

    @Override
    public List<Clazz> clazzsList() {
        return clazzsMapper.clazzsList();
    }
}
