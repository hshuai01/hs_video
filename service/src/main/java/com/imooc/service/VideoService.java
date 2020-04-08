package com.imooc.service;

import com.imooc.pojo.Bgm;
import com.imooc.pojo.Comments;
import com.imooc.pojo.Videos;
import com.imooc.utils.PagedResult;

import java.util.List;

public interface VideoService {

    /**
     * 保存视频
     * @param videos
     */
    public String saveVideo(Videos videos);


    /**
     * 修改视频封面
     * @param videoId
     * @param coverPath
     * @return
     */
    public void updateVideo(String videoId,String coverPath);


    /**
     * 分页查询视频列表
     * @param page
     * @param pageSize
     * @return
     */
    public PagedResult getAllVideos(Videos videos,Integer isSaveRecord,Integer page,int pageSize);


    /**
     * 获取热搜词
     */
    public List<String> getHotWords();


    /**
     * 用户视频点赞
     * @param userId
     * @param videoId
     * @param videoCreaterId
     */
    public void userLikeVideo(String userId,String videoId, String videoCreaterId);

    /**
     * 取消点赞
     * @param userId
     * @param videoId
     * @param videoCreaterId
     */
    public void userUnLikeVideo(String userId,String videoId, String videoCreaterId);

    /**
     * 查询用户收藏的视频列表
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    PagedResult queryMyLikeVideos(String userId, Integer page, Integer pageSize);

    PagedResult queryMyFollowVideos(String userId, Integer page, int pageSize);

    /**
     * 保存留言
     * @param comment
     */
    void saveComment(Comments comment);

    PagedResult getAllComments(String videoId, Integer page, Integer pageSize);
}
