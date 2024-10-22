package com.example.springboot;

import com.example.springboot.entity.starter.Applications;
import com.example.springboot.service.starter.ApplicationsService;
import com.example.springboot.mapper.ApplicationsMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ApplicationsServiceTest {

    @InjectMocks
    private ApplicationsService applicationsService;

    @Mock
    private ApplicationsMapper applicationsMapper;

    private Applications applications;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        applications = Applications.builder()
                .id(1)
                .name("Test Application")
                .phone("123456789")
                .adults(2)
                .children(1)
                .applicationDate("2023-10-08")
                .build();
    }

    /**
     * 测试保存申请
     */
    @Test
    public void testSaveApplication() {
        // 模拟 mapper 的插入操作
        when(applicationsMapper.insert(any(Applications.class))).thenReturn(1);  // 返回1表示插入成功

        // 调用 service 的 save 方法
        boolean result = applicationsService.save(applications);

        // 验证 mapper 的 insert 方法是否被调用
        verify(applicationsMapper, times(1)).insert(any(Applications.class));
    }

    /**
     * 测试根据ID查询申请
     */
    @Test
    public void testGetById() {
        // 模拟 mapper 的查询操作
        when(applicationsMapper.selectById(anyInt())).thenReturn(applications);

        // 调用 service 的 getById 方法
        Applications result = applicationsService.getById(1);

        // 验证 mapper 的 selectById 方法是否被调用
        verify(applicationsMapper, times(1)).selectById(1);

        // 验证返回的申请对象是否正确
        assertEquals(applications, result);
    }

    /**
     * 测试查询所有申请
     */
    @Test
    public void testListApplications() {
        // 模拟 mapper 的查询操作
        List<Applications> applicationsList = new ArrayList<>();
        applicationsList.add(applications);
        when(applicationsMapper.selectList(any())).thenReturn(applicationsList);

        // 调用 service 的 list 方法
        List<Applications> result = applicationsService.list();

        // 验证 mapper 的 selectList 方法是否被调用
        verify(applicationsMapper, times(1)).selectList(any());

        // 验证返回的列表是否正确
        assertEquals(1, result.size());
        assertEquals(applications, result.get(0));
    }
}
