package com.lyun.cowvideo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyun.cowvideo.entity.po.VideoTypePO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VideoTypeMapper extends BaseMapper<VideoTypePO> {
}
