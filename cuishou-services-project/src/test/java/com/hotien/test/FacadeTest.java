package com.hotien.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.hotien.facade.ExternalInterfaceFacade;
import com.hotien.facade.vo.ExternalUniqueVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-modules.xml" })
public class FacadeTest
{
    @Autowired
    private  ExternalInterfaceFacade externalInterfaceFacade;
    
    @Test
    public void dataSync()
    {
        List<ExternalUniqueVo> list = new ArrayList<ExternalUniqueVo>();
        ExternalUniqueVo externalUniqueVo = new ExternalUniqueVo();
        externalUniqueVo.setUserId("111");
        list.add(externalUniqueVo);
        Gson gson = new Gson();
        String jsonData = gson.toJson(list);
        externalInterfaceFacade.dataSync("sb", jsonData);
    }
    
}
