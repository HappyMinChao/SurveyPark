package com.atguigu.survey.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.atguigu.survey.guest.entity.Bag;







/*
 * 检查字符串是否有效
 */

public class ValidateUtils {
	
	/**
	 * 验证map是否为空
	 * @param bagList
	 * @return
	 */
	public static boolean validateMap(Map map){
		return map != null && map.size() != 0;
	}
	
	/*
	 * 验证数据是否重复
	 */
	public static boolean validateNotRepeat(List<Bag> bagList){
		Set set = new HashSet();
		
		for(int i = 0 ; i<bagList.size() ; i++){
			int  bagOrder = bagList.get(i).getBagOrder();
			set.add(bagOrder);
		}
		
		return bagList.size() == set.size();
	}
	/*
	 * 验证collection是否为空，   
	 *    空： false
	 *   不空： true
	 */

	public static boolean validateCollection(Collection coll){
		return coll != null && coll.size() != 0;
	}

	
	public static boolean stringValidate(String logoPath){
		return logoPath != null && logoPath.length() > 0;
	}


	public static boolean arrayValidate(Object[] arr) {
		// TODO Auto-generated method stub
		return arr != null && arr.length != 0;
	}
}
