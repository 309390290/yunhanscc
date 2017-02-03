package com.yunhan.scc.backto.web.service.impl.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunhan.scc.backto.web.dao.mapper.system.ConstantBacktoDao;
import com.yunhan.scc.backto.web.entities.system.ConstantBacktoDo;
import com.yunhan.scc.backto.web.service.system.ConstantBacktoService;
/**
 * 获取常数配置实现
 * ConstantBacktoServiceImpl  
 * @author luohoudong
 * @version created at 2016-8-1 上午11:21:15
 */
@Service
public class ConstantBacktoServiceImpl implements ConstantBacktoService{

	@Autowired
	private ConstantBacktoDao constantBacktoDao;
	
	@Override
	public List<ConstantBacktoDo> getConstantsByType(String typeCode) {
		Map<String,Object> map = new HashMap<String, Object>();
		if(StringUtils.isEmpty(typeCode)){
			return null;
		}
		map.put("typeCode", typeCode);
		List<ConstantBacktoDo> constants = constantBacktoDao.getConstantsByType(map);
		return constants;
	}
	
	public ConstantBacktoDo getConstantByTypeAndCode(String typeCode,String consName){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("typeCode", typeCode);
		params.put("consName", consName);
		return constantBacktoDao.getConstantByTypeAndCode(params);
	}

}
