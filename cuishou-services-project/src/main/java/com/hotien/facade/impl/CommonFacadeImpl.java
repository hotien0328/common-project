package com.hotien.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.hotien.facade.CommonFacade;
import com.hotien.service.CommonService;

public class CommonFacadeImpl implements CommonFacade {

	@Autowired
	private CommonService commonService;
	@Override
	public String sayHello() {
		// TODO Auto-generated method stub
		return commonService.sayHello();
	}

}
