package com.ping.wu.dao;

import com.ping.wu.model.TravelRecordData;

public interface TravelRecordDataMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table travelrecord
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table travelrecord
     *
     * @mbggenerated
     */
    int insert(TravelRecordData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table travelrecord
     *
     * @mbggenerated
     */
    int insertSelective(TravelRecordData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table travelrecord
     *
     * @mbggenerated
     */
    TravelRecordData selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table travelrecord
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TravelRecordData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table travelrecord
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TravelRecordData record);
}