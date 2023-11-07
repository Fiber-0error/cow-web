package com.lyun.cowvideo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyun.cowvideo.dao.AccountMapper;
import com.lyun.cowvideo.dao.LikeStatusMapper;
import com.lyun.cowvideo.dao.VideoInfoMapper;
import com.lyun.cowvideo.entity.po.AccountPO;
import com.lyun.cowvideo.entity.po.LikeStatusPO;
import com.lyun.cowvideo.entity.po.VideoInfoPO;
import com.lyun.cowvideo.service.LikeStatusService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LikeStatusServiceImpl extends ServiceImpl<LikeStatusMapper, LikeStatusPO> implements LikeStatusService {

    private final VideoInfoMapper videoInfoMapper;

    private final AccountMapper accountMapper;

    public static final int TYPE_TYPE = 1;
    public static final int TYPE_TAG = 2;

    public LikeStatusServiceImpl(VideoInfoMapper videoInfoMapper, AccountMapper accountMapper) {
        this.videoInfoMapper = videoInfoMapper;
        this.accountMapper = accountMapper;
    }

    @Override
    public boolean increase(int accountId, int videoId) {
        return doChange(accountId, videoId, 1);
    }

    @Override
    public boolean decrease(int accountId, int videoId) {
        return doChange(accountId, videoId, -1);
    }

    @Override
    public List<String> topType(int accountId, int len) {
        return doGetTop(accountId, len, TYPE_TYPE);
    }

    @Override
    public List<String> topTag(int accountId, int len) {
        return doGetTop(accountId, len, TYPE_TAG);
    }

    private boolean doChange(int accountId, int videoId, int value){
        AccountPO accountPO = accountMapper.selectById(accountId);
        if (accountPO == null){
            return false;
        }
        VideoInfoPO videoInfoPO = videoInfoMapper.selectById(videoId);
        if (videoInfoPO == null){
            return false;
        }
        String type = videoInfoPO.getType();
        String[] tags = new String[0];
        if (videoInfoPO.getTag() != null){
            tags = videoInfoPO.getTag().split(",");
        }
        // 更新type
        LikeStatusPO likeStatusPOByType = this.baseMapper.selectOne(
                new LambdaQueryWrapper<LikeStatusPO>()
                        .eq(LikeStatusPO::getAccountId, accountId)
                        .eq(LikeStatusPO::getType, TYPE_TYPE)
                        .eq(LikeStatusPO::getName, type)
        );
        if (likeStatusPOByType == null){
            if (value > 0){
                LikeStatusPO newPO = LikeStatusPO.builder()
                        .accountId(accountId).type(1).name(type).count(1)
                        .build();
                this.baseMapper.insert(newPO);
            }
        }else {
            likeStatusPOByType.setCount(Math.max(0, likeStatusPOByType.getCount() + value));
            this.baseMapper.updateById(likeStatusPOByType);
        }
        // 更新标签
        for (String tag : tags) {
            LikeStatusPO likeStatusPOByTag = this.baseMapper.selectOne(
                    new LambdaQueryWrapper<LikeStatusPO>()
                            .eq(LikeStatusPO::getAccountId, accountId)
                            .eq(LikeStatusPO::getType, TYPE_TAG)
                            .eq(LikeStatusPO::getName, tag)
            );
            if (likeStatusPOByTag == null){
                if (value > 0){
                    LikeStatusPO newPO = LikeStatusPO.builder()
                            .accountId(accountId).type(2).name(tag).count(1)
                            .build();
                    this.baseMapper.insert(newPO);
                }
            }else {
                likeStatusPOByTag.setCount(Math.max(0, likeStatusPOByTag.getCount() + value));
                this.baseMapper.updateById(likeStatusPOByTag);
            }
        }
        return true;
    }

    private List<String> doGetTop(int accountId, int len, int type){
        List<LikeStatusPO> likeStatusPOS = this.baseMapper.selectList(
                new LambdaQueryWrapper<LikeStatusPO>()
                        .eq(LikeStatusPO::getAccountId, accountId)
                        .eq(LikeStatusPO::getType, type)
                        .orderBy(true, false, LikeStatusPO::getCount)
                        .last("LIMIT " + len)
        );
        return likeStatusPOS.stream().map(LikeStatusPO::getName).collect(Collectors.toList());
    }
}
