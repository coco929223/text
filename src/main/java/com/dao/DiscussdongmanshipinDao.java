package com.dao;

import com.entity.DiscussdongmanshipinEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussdongmanshipinVO;
import com.entity.view.DiscussdongmanshipinView;


/**
 * 动漫视频评论表
 * 
 * @author 
 * @email 
 * @date 2023-02-14 16:48:50
 */
public interface DiscussdongmanshipinDao extends BaseMapper<DiscussdongmanshipinEntity> {
	
	List<DiscussdongmanshipinVO> selectListVO(@Param("ew") Wrapper<DiscussdongmanshipinEntity> wrapper);
	
	DiscussdongmanshipinVO selectVO(@Param("ew") Wrapper<DiscussdongmanshipinEntity> wrapper);
	
	List<DiscussdongmanshipinView> selectListView(@Param("ew") Wrapper<DiscussdongmanshipinEntity> wrapper);

	List<DiscussdongmanshipinView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussdongmanshipinEntity> wrapper);
	
	DiscussdongmanshipinView selectView(@Param("ew") Wrapper<DiscussdongmanshipinEntity> wrapper);
	

}
