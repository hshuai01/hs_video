package com.imooc.service;

import com.imooc.pojo.Bgm;

import java.util.List;

public interface BgmService {

    /**
     * 查询背景音乐列表
     * @return
     */
    public List<Bgm> queryBgmList();

    /**
     * 根据BgmId查询bgm
     * @param bgmId
     * @return
     */
    public Bgm queryBgmById(String bgmId);
}
