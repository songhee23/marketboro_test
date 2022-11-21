package com.marketboro.demo.service;

import com.marketboro.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserService {

	@Autowired
	UserRepository userRepository;


}
