package com.lyun.cowvideo.entity.po;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 视频信息PO类
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@TableName("video_info")
public class VideoInfoPO {

  private int id;
  /**
   * 视频名称
   */
  @TableField("`name`")
  private String name;
  /**
   * 上传用户
   */
  private int uploadUser;
  /**
   * 视频url地址
   */
  private String url;
  /**
   * 封面url地址
   */
  private String imgUrl;
  /**
   * 视频描述
   */
  @TableField("`describe`")
  private String describe;
  /**
   * 视频标签，使用,分割
   */
  private String tag;
  /**
   * 视频创建时间
   */
  private String createdTime;
  /**
   * 视频修改时间
   */
  private String updateTime;
  /**
   * 删除标志
   */
  private long delFlag;
  /**
   * 视频分类
   */
  private String type;
  /**
   * 观看进度
   */
  @TableField(exist = false)
  private String watchProgress;
  /**
   * 视频长度，单位秒
   */
  private long totalTime;
  /**
   * 点赞数
   */
  @TableField(exist = false)
  private Long likeCount;
  /**
   * 是否点赞
   */
  @TableField(exist = false)
  private Boolean isLike;

  @TableField(exist = false)
  private Long collectCount;

  @TableField(exist = false)
  private Boolean isCollect;

}
