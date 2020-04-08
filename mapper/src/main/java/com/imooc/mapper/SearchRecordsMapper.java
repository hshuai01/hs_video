package com.imooc.mapper;

import java.util.List;

import com.imooc.pojo.SearchRecords;
import com.imooc.utils.MyMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRecordsMapper extends MyMapper<SearchRecords> {
	
	public List<String> getHotWords();

}