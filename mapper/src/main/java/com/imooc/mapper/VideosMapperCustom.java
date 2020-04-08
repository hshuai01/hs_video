package com.imooc.mapper;

import com.imooc.pojo.Videos;
import com.imooc.pojo.vo.VideosVO;
import com.imooc.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideosMapperCustom extends MyMapper<Videos> {

    /**
     * 条件查询视频
     * @param videoDesc
     * @return
     */
    public List<VideosVO> queryAllVideos(@Param("videoDesc") String videoDesc,@Param("userId") String userId);

    /**
     * 对视频喜欢的数量进行累加
     * @param videoId
     */
    public void addVideoLikeCount(String videoId);

    /**
     * 对视频喜欢的数量进行累减
     * @param videoId
     */
    public void reduceVideoLikeCount(String videoId);

    /**
     * 获取用户喜欢的视频
     * @param userId
     * @return
     */
    List<VideosVO> queryMyLikeVideos(String userId);

    List<VideosVO> queryMyFollowVideos(String userId);
}