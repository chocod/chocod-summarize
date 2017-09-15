package com.chocod.summarize.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 描述: 日期转换类，把前端出过来格式为 yyyy-MM-dd或yyyy-MM-dd HH:mm:ss的字符串转化为Date对象
 * 
 */
public class DateConverter implements Converter<String, Date> {
	private String[] patterns = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};//其他日期格式直接在此数组中添加

	@Override
	public Date convert(String source)
	{
		SimpleDateFormat dateFormat = null;
		Date date = null;
		for (String p : patterns)
		{
			dateFormat = new SimpleDateFormat(p);

			try
			{
				date = dateFormat.parse(source);
				break;
			} catch (ParseException e)
			{
				continue;
			}
		}

		return date;
	}
}
