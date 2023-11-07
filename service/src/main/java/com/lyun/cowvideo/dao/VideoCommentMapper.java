package com.lyun.cowvideo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyun.cowvideo.entity.po.VideoCommentPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VideoCommentMapper extends BaseMapper<VideoCommentPO> {
}
