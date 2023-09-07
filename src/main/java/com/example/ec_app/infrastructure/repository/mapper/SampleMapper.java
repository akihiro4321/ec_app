package com.example.ec_app.infrastructure.repository.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SampleMapper {
    List<String> selectString();
}


