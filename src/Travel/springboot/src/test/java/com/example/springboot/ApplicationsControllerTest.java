package com.example.springboot;

import com.example.springboot.common.Result;
import com.example.springboot.controller.starter.ApplicationsController;
import com.example.springboot.entity.starter.Applications;
import com.example.springboot.service.starter.ApplicationsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import static org.mockito.ArgumentMatchers.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ApplicationsControllerTest {

    @InjectMocks
    private ApplicationsController applicationsController;

    @Mock
    private ApplicationsService applicationsService;

    private Applications applications;

    @BeforeEach
    public void setUp() {
        // 初始化 Mockito 的注解
        MockitoAnnotations.openMocks(this);

        // 初始化一个测试用的 Applications 实例
        applications = new Applications();
        applications.setId(1);
        applications.setName("Test Application");
        applications.setPhone("1234567890");
        applications.setAdults(2);
        applications.setChildren(1);
        applications.setApplicationDate("2023-10-08");
    }

    /**
     * 测试新增申请
     */
    @Test
    public void testAddApplication() {
        // 模拟 service 的保存操作 (save 是 void 方法)
        when(applicationsService.save(any(Applications.class))).thenReturn(true);

        // 调用 controller 的 add 方法
        Result result = applicationsController.add(applications);

        // 验证 save 方法是否被调用
        verify(applicationsService, times(1)).save(any(Applications.class));
    }


    /**
     * 测试修改申请
     */
    @Test
    public void testUpdateApplication() {
        // 模拟 service 的更新操作，updateById 返回 true 表示更新成功
        when(applicationsService.updateById(any(Applications.class))).thenReturn(true);

        // 调用 controller 的 update 方法
        Result result = applicationsController.update(applications);

        // 验证 updateById 方法是否被调用
        verify(applicationsService, times(1)).updateById(any(Applications.class));
    }


    /**
     * 测试删除申请
     */
    @Test
    public void testDeleteApplication() {
        // 模拟 service 的删除操作 (removeById 是 void 方法)
        when(applicationsService.removeById(anyInt())).thenReturn(true);

        // 调用 controller 的 delete 方法
        Result result = applicationsController.delete(1);

        // 验证 removeById 方法是否被调用
        verify(applicationsService, times(1)).removeById(1);
    }


    /**
     * 测试批量删除申请
     */
    @Test
    public void testBatchDeleteApplication() {
        // 模拟 service 的批量删除操作 (removeBatchByIds 是 void 方法)
        when(applicationsService.removeBatchByIds(anyList())).thenReturn(true);

        // 调用 controller 的 batchDelete 方法
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        Result result = applicationsController.batchDelete(ids);

        // 验证 removeBatchByIds 方法是否被调用
        verify(applicationsService, times(1)).removeBatchByIds(anyList());
    }


    /**
     * 测试查询所有申请
     */
    @Test
    public void testSelectAllApplications() {
        // 模拟返回的申请列表
        List<Applications> applicationsList = new ArrayList<>();
        applicationsList.add(applications);

        // 使用 any(QueryWrapper.class) 来匹配 QueryWrapper 类型的参数
        when(applicationsService.list(any(QueryWrapper.class))).thenReturn(applicationsList);

        // 调用 controller 的 selectAll 方法
        Result result = applicationsController.selectAll();

        // 验证 list 方法是否被调用，并使用 any(QueryWrapper.class) 来匹配
        verify(applicationsService, times(1)).list(any(QueryWrapper.class));
    }




    /**
     * 测试根据ID查询申请
     */
    @Test
    public void testSelectApplicationById() {
        // 模拟返回的申请
        when(applicationsService.getById(anyInt())).thenReturn(applications);

        // 调用 controller 的 selectById 方法
        Result result = applicationsController.selectById(1);

        // 验证 getById 方法是否被调用
        verify(applicationsService, times(1)).getById(1);
    }
}
