package com.example.springboot.service.starter;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.starter.Logs;
import com.example.springboot.mapper.LogsMapper;
import org.springframework.stereotype.Service;

@Service
public class LogsService extends ServiceImpl<LogsMapper, Logs> {

}
