package com.example.ec_app.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ec_app.infrastructure.repository.mapper.SampleMapper;

@Service
public class SampleService {

    @Autowired
    private SampleMapper sampleMapper;

    public List<String> selectString() {
        return this.sampleMapper.selectString();
    }
}
