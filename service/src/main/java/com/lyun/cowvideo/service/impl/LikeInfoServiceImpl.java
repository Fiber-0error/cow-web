package com.lyun.cowvideo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyun.cowvideo.dao.LikeInfoMapper;
import com.lyun.cowvideo.dao.VideoInfoMapper;
import com.lyun.cowvideo.entity.Result;
import com.lyun.cowvideo.entity.po.AccountPO;
import com.lyun.cowvideo.entity.po.CollectPO;
import com.lyun.cowvideo.entity.po.LikeInfoPO;
import com.lyun.cowvideo.entity.po.VideoInfoPO;
import com.lyun.cowvideo.entity.vo.CollectListVo;
import com.lyun.cowvideo.entity.vo.LikeInfoListVo;
import com.lyun.cowvideo.service.LikeInfoService;
import com.lyun.cowvideo.service.LikeStatusService;
import com.lyun.cowvideo.util.AccountInfoContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class LikeInfoServiceImpl extends ServiceImpl<LikeInfoMapper, LikeInfoPO> implements LikeInfoService {

    @Autowired
    private VideoInfoMapper videoInfoMapper;

    @Autowired
    @Lazy
    private LikeStatusService likeStatusService;

    @Override
    public Result like(int videoId, int accountId) {
        VideoInfoPO videoInfoPO = videoInfoMapper.selectById(videoId);
        if (videoInfoPO == null) {
            return Result.fail("未知的视频");
        }
        LikeInfoPO likeInfoPO = this.baseMapper.selectOne(
                new LambdaQueryWrapper<LikeInfoPO>()
                        .eq(LikeInfoPO::getVideoId, videoId)
                        .eq(LikeInfoPO::getAccountId, accountId)
        );
        boolean status = false;
        if (likeInfoPO == null) {
            LikeInfoPO newPo = LikeInfoPO.builder()
                    .videoId(videoId).accountId(accountId)
                    .updateTime(new Timestamp(System.currentTimeMillis()).toString())
                    .build();
            save(newPo);
            status = true;
            // 用户喜好
            likeStatusService.increase(accountId, videoId);
        }else {
            removeById(likeInfoPO.getId());
            // 用户喜好
            likeStatusService.decrease(accountId, videoId);
        }
        return Result.success(status);
    }

    @Override
    public Result getLikeList() {
        AccountPO accountInfo = AccountInfoContext.getInfo();
        if (accountInfo == null){
            return Result.fail("请先登录");
        }

        int accountId = accountInfo.getId();

        List<LikeInfoPO> likeInfoPOList = this.baseMapper.selectList(
                new LambdaQueryWrapper<LikeInfoPO>()
                        .eq(LikeInfoPO::getAccountId, accountId)
        );
        List<LikeInfoListVo> resList = new ArrayList<>();
        for (LikeInfoPO likeInfoPO : likeInfoPOList) {
            LikeInfoListVo vo = LikeInfoListVo.builder().id(likeInfoPO.getId())
                    .videoInfoPO(
                            videoInfoMapper.selectById(likeInfoPO.getVideoId())
                    ).build();
            resList.add(vo);
        }
        return Result.success(resList);
    }


}
