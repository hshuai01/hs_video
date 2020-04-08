package com.imooc.controller;

import com.imooc.service.BgmService;
import com.imooc.utils.IMoocJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "背景音乐业务接口", tags = "背景音乐业务Controller")
@RestController
@RequestMapping("/bgm")
public class BgmController {

	@Autowired
	private BgmService bgmService;

	@ApiOperation(value = "获取背景音乐列表", notes = "获取背景音乐列表接口")
	@PostMapping("/list")
	public IMoocJSONResult Hello() {

		return IMoocJSONResult.ok(bgmService.queryBgmList());
	}
	
}
