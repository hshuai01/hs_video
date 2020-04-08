package com.imooc.controller;

import com.imooc.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

	@Autowired
	public RedisOperator redis;

	public static final String USER_REDIS_SESSION = "user-redis-session";

	public static final String FFMPEG_EXE = "C:\\ffmpeg\\bin\\ffmpeg.exe";

	public static final String FILE_SPACE = "C:\\imooc_videos_dev";

	//每页记录数
	public static final Integer PAGE_SIZE = 5;
}
