package com.lyun.cowvideo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyun.cowvideo.entity.Result;
import com.lyun.cowvideo.entity.param.VideoUpdateInfoParam;
import com.lyun.cowvideo.entity.param.VideoUploadParam;
import com.lyun.cowvideo.entity.po.VideoInfoPO;
import org.springframework.stereotype.Service;

@Service
public interface VideoInfoService extends IService<VideoInfoPO> {

    Result uploadVideo(VideoUploadParam param);

    Result swapperNext();

    Result doWatch(int videoId);

    Result flashWatchProgress(int videoId, long watchProgress);

    long getVideoLikeCount(int videoId);

    long getVideoCollectCount(int videoId);

    Result recommendVideoByType(String type, int size);

    Result searchVideo(String keyWord, Page<VideoInfoPO> page, String type);

    Result userRemoveVideo(int videoId);

    Result userUpdateVideoInfo(VideoUpdateInfoParam param);

    Result hotKeyWord(int len);
}
