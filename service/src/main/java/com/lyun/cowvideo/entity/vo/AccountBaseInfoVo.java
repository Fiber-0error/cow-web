package com.lyun.cowvideo.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountBaseInfoVo {

    private int id;

    private String username;

    private String name;
}
