package com.lyun.cowvideo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyun.cowvideo.entity.Result;
import com.lyun.cowvideo.entity.param.VideoCommentParam;
import com.lyun.cowvideo.entity.po.VideoCommentPO;
import org.springframework.stereotype.Service;

@Service
public interface VideoCommentService extends IService<VideoCommentPO> {

    Result comment(VideoCommentParam param);

    Result commentList(int videoId);
}
