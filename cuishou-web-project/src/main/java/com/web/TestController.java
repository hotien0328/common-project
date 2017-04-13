package com.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestController {
	 /**
     * 刷新缓存
     * 
     * 
     * @author hotien
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("test/refreshCache")
    public ResultVo<String> refreshCache(HttpServletRequest request)
    {
        ResultVo<String> resultVo = new ResultVo<String>();
        resultVo.setCode("0");
        resultVo.setResult("success");
        resultVo.setResultDes("success");
        resultVo.setSuccess(true);
        return resultVo;
    }
}
