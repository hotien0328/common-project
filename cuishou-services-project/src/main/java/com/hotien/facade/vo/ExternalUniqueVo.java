package com.hotien.facade.vo;

import com.vo.BaseVo;

/**
 * 
  * @ClassName: DataSyncUniqueVo
  * @Description: TODO
  * Copyright: Copyright (c) 2017 
  * @author hotien
  * @date 2017年4月14日 下午1:19:18
  *
 */
public class ExternalUniqueVo extends BaseVo
{

    /**
      * @Fields serialVersionUID : TODO
      */
    private static final long serialVersionUID = 718742042157120794L;
    
    private String userId;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    
    
    
}
