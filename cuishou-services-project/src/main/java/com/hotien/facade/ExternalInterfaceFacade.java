package com.hotien.facade;

/**
 * 外部接口
  * @ClassName: ExternalInterfaceFacade
  * @Description: TODO
  * Copyright: Copyright (c) 2017 
  * @author hotien
  * @date 2017年4月14日 上午11:40:06
  *
 */
public interface ExternalInterfaceFacade
{
    /**
     * 统一数据入口
     * 
     * 
     * @author hotien
     * @param source
     * @param jsonData
     */
    public void dataSync(String source, String jsonData);
}
