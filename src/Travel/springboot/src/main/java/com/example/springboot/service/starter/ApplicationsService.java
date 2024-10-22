package com.example.springboot.service.starter;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.starter.Applications;
import com.example.springboot.mapper.ApplicationsMapper;
import org.springframework.stereotype.Service;

@Service
public class ApplicationsService extends ServiceImpl<ApplicationsMapper, Applications> {

}
