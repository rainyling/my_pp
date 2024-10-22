package com.example.springboot.controller.starter;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.HoneyLogs;
import com.example.springboot.common.LogType;
import com.example.springboot.common.Result;
import com.example.springboot.entity.starter.Applications;
import com.example.springboot.entity.starter.User;
import com.example.springboot.service.starter.ApplicationsService;
import com.example.springboot.service.starter.UserService;
import com.example.springboot.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationsController {

    @Autowired
    ApplicationsService applicationsService;

    @Autowired
    UserService userService;

    /**
     * 新增信息
     */
    @HoneyLogs(operation = "申请", type = LogType.ADD)
    @PostMapping("/add")
    public Result add(@RequestBody Applications applications) {
        applications.setApplicationDate(DateUtil.today());  //   2023-10-08
        applicationsService.save(applications);
        return Result.success();
    }

    /**
     * 修改信息
     */
    @HoneyLogs(operation = "申请", type = LogType.UPDATE)
    @PutMapping("/update")
    public Result update(@RequestBody Applications applications) {
        applicationsService.updateById(applications);
        return Result.success();
    }

    /**
     * 删除信息
     */
    @HoneyLogs(operation = "申请", type = LogType.DELETE)
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        applicationsService.removeById(id);
        return Result.success();
    }


    /**
     * 批量删除信息
     */
    @HoneyLogs(operation = "申请", type = LogType.BATCH_DELETE)
    @DeleteMapping("/delete/batch")
    public Result batchDelete(@RequestBody List<Integer> ids) {
        applicationsService.removeBatchByIds(ids);
        return Result.success();
    }

    /**
     * 查询全部信息
     */
    @GetMapping("/selectAll")
    public Result selectAll() {
        List<Applications> ordersList = applicationsService.list(new QueryWrapper<Applications>().orderByDesc("id"));
        return Result.success(ordersList);
    }

    /**
     * 根据ID查询信息
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Applications applications = applicationsService.getById(id);
        return Result.success(applications);
    }


    /**
     * 多条件模糊查询信息
     * pageNum 当前的页码
     * pageSize 每页查询的个数
     */
    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String name) {
        QueryWrapper<Applications> queryWrapper = new QueryWrapper<Applications>().orderByDesc("id");  // 默认倒序，让最新的数据在最上面
        queryWrapper.like(StrUtil.isNotBlank(name), "name", name);
        Page<Applications> page = applicationsService.page(new Page<>(pageNum, pageSize), queryWrapper);

        return Result.success(page);
    }


}
