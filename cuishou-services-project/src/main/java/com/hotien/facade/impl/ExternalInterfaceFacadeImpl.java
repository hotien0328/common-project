package com.hotien.facade.impl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hotien.facade.ExternalInterfaceFacade;
import com.hotien.facade.vo.ExternalUniqueVo;
import com.hotien.service.thread.DataSyncThread;

public class ExternalInterfaceFacadeImpl implements ExternalInterfaceFacade
{
    private Gson gson = new Gson();
    
    private ExecutorService threadPool = Executors.newFixedThreadPool(5);
    
    @Override
    public void dataSync(String source, String json)
    {
        List<ExternalUniqueVo> dataList = gson.fromJson(json, new TypeToken<List<ExternalUniqueVo>>()
        {
        }.getType());
        Thread thread = new DataSyncThread(source, dataList);
        threadPool.submit(thread);
    }
    
}
