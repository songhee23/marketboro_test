package com.marketboro.demo.service;

import com.marketboro.demo.repository.UserPointRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserPointService {

	@Autowired
	UserPointRepository userPointRepository;
}
