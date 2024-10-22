package com.example.springboot.entity.starter;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Applications {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String phone;
    private Integer adults;
    private Integer children;
    private String applicationDate;

    @TableField(exist = false)
    private String user; // 这个字段在数据库中没有对应的字段
}
