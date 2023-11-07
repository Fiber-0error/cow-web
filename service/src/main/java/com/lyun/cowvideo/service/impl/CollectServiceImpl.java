package com.lyun.cowvideo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyun.cowvideo.dao.AccountMapper;
import com.lyun.cowvideo.dao.CollectMapper;
import com.lyun.cowvideo.dao.VideoInfoMapper;
import com.lyun.cowvideo.entity.Result;
import com.lyun.cowvideo.entity.po.AccountPO;
import com.lyun.cowvideo.entity.po.CollectPO;
import com.lyun.cowvideo.entity.po.VideoInfoPO;
import com.lyun.cowvideo.entity.vo.CollectListVo;
import com.lyun.cowvideo.service.CollectService;
import com.lyun.cowvideo.util.AccountInfoContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class CollectServiceImpl extends ServiceImpl<CollectMapper, CollectPO> implements CollectService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private VideoInfoMapper videoInfoMapper;

    @Override
    public Result collect(int videoId) {
        AccountPO accountInfo = AccountInfoContext.getInfo();
        if (accountInfo == null){
            return Result.fail("请先登录");
        }

        int accountId = accountInfo.getId();

        VideoInfoPO videoInfoPO = videoInfoMapper.selectById(videoId);
        if (videoInfoPO == null){
            return Result.fail("未知的视频");
        }

        CollectPO collectPO = this.baseMapper.selectOne(
                new LambdaQueryWrapper<CollectPO>()
                        .eq(CollectPO::getVideoId, videoId)
                        .eq(CollectPO::getAccountId, accountId)
        );
        boolean status = false;
        if (collectPO == null){
            CollectPO newPo = CollectPO.builder()
                    .createdTime(new Timestamp(System.currentTimeMillis()).toString())
                    .videoId(videoId)
                    .accountId(accountId)
                    .build();
            status = true;
            this.baseMapper.insert(newPo);
        }else {
            this.baseMapper.deleteById(collectPO.getId());
        }
        return Result.success(status);
    }

    @Override
    public Result collectList() {
        AccountPO accountInfo = AccountInfoContext.getInfo();
        if (accountInfo == null){
            return Result.fail("请先登录");
        }

        int accountId = accountInfo.getId();

        List<CollectPO> collectPOList = this.baseMapper.selectList(
                new LambdaQueryWrapper<CollectPO>()
                        .eq(CollectPO::getAccountId, accountId)
        );
        List<CollectListVo> resList = new ArrayList<>();
        for (CollectPO collectPO : collectPOList) {
            CollectListVo vo = CollectListVo.builder().id(collectPO.getId())
                    .videoInfoPO(
                            videoInfoMapper.selectById(collectPO.getVideoId())
                    ).build();
            resList.add(vo);
        }
        return Result.success(resList);
    }


}
