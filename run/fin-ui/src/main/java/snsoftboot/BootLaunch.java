package snsoftboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2017年8月10日下午1:32:40</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@SpringBootApplication(scanBasePackages = { "snsoft.**.controller", "snsoftboot" })
public class BootLaunch
{
	public static void main(String[] args)
	{
		new BootSpringApplication(BootLaunch.class).run(args);
	}
}
