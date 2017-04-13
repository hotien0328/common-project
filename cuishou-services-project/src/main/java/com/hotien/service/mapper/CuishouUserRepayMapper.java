package com.hotien.service.mapper;

import com.hotien.service.po.CuishouUserRepay;
import com.hotien.service.po.CuishouUserRepayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CuishouUserRepayMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cuishou_user_repay
     *
     * @mbggenerated Fri Apr 07 14:35:43 CST 2017
     */
    int countByExample(CuishouUserRepayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cuishou_user_repay
     *
     * @mbggenerated Fri Apr 07 14:35:43 CST 2017
     */
    int deleteByExample(CuishouUserRepayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cuishou_user_repay
     *
     * @mbggenerated Fri Apr 07 14:35:43 CST 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cuishou_user_repay
     *
     * @mbggenerated Fri Apr 07 14:35:43 CST 2017
     */
    int insert(CuishouUserRepay record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cuishou_user_repay
     *
     * @mbggenerated Fri Apr 07 14:35:43 CST 2017
     */
    int insertSelective(CuishouUserRepay record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cuishou_user_repay
     *
     * @mbggenerated Fri Apr 07 14:35:43 CST 2017
     */
    List<CuishouUserRepay> selectByExample(CuishouUserRepayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cuishou_user_repay
     *
     * @mbggenerated Fri Apr 07 14:35:43 CST 2017
     */
    CuishouUserRepay selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cuishou_user_repay
     *
     * @mbggenerated Fri Apr 07 14:35:43 CST 2017
     */
    int updateByExampleSelective(@Param("record") CuishouUserRepay record, @Param("example") CuishouUserRepayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cuishou_user_repay
     *
     * @mbggenerated Fri Apr 07 14:35:43 CST 2017
     */
    int updateByExample(@Param("record") CuishouUserRepay record, @Param("example") CuishouUserRepayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cuishou_user_repay
     *
     * @mbggenerated Fri Apr 07 14:35:43 CST 2017
     */
    int updateByPrimaryKeySelective(CuishouUserRepay record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cuishou_user_repay
     *
     * @mbggenerated Fri Apr 07 14:35:43 CST 2017
     */
    int updateByPrimaryKey(CuishouUserRepay record);
}