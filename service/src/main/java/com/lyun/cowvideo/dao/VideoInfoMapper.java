package com.lyun.cowvideo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyun.cowvideo.entity.po.VideoInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VideoInfoMapper extends BaseMapper<VideoInfoPO> {

    List<VideoInfoPO> nextMostViewAndUserNotWatch(List<Integer> watchedId, int len);

    List<VideoInfoPO> queryRandomInTypeAndTagWithoutIds(List<String> types, List<String> tags, List<Integer> ids, int len);

    List<String> hotKeyWord(int len);

}
