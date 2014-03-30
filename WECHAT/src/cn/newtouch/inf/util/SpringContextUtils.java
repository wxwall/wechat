package cn.newtouch.inf.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * spring���bean�����ּ࣬���ṩ���Ӽ򵥵ĵõ�springʵ������
 * 
 * @author wuxw
 * 
 */
public class SpringContextUtils implements ApplicationContextAware {// Spring�Ĺ����࣬������������ļ��е�bean

	private static ApplicationContext applicationContext = null;

	/***
	 * ���̳���ApplicationContextAware��֮����ô�����ڵ��� getBean(String)��ʱ����Զ����ø÷����������Լ�����
	 */
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContextUtils.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/***
	 * ����һ��bean��id��ȡ�����ļ�����Ӧ��bean
	 * 
	 * @param name
	 * @return
	 * @throws BeansException
	 */
	public static Object getBean(String name) throws BeansException {
		return applicationContext.getBean(name);
	}

	/***
	 * ������getBean(String name)ֻ���ڲ������ṩ����Ҫ���ص������͡�
	 * 
	 * @param name
	 * @param requiredType
	 * @return
	 * @throws BeansException
	 */
	public static Object getBean(String name, Class requiredType)
			throws BeansException {
		return applicationContext.getBean(name, requiredType);
	}

	/**
	 * ���BeanFactory����һ������������ƥ���bean���壬�򷵻�true
	 * 
	 * @param name
	 * @return boolean
	 */
	public static boolean containsBean(String name) {
		return applicationContext.containsBean(name);
	}

	/**
	 * �ж��Ը�������ע���bean������һ��singleton����һ��prototype��
	 * ��������������Ӧ��bean����û�б��ҵ��������׳�һ���쳣��NoSuchBeanDefinitionException��
	 * 
	 * @param name
	 * @return boolean
	 * @throws NoSuchBeanDefinitionException
	 */
	public static boolean isSingleton(String name)
			throws NoSuchBeanDefinitionException {
		return applicationContext.isSingleton(name);
	}

	/**
	 * @param name
	 * @return Class ע����������
	 * @throws NoSuchBeanDefinitionException
	 */
	public static Class getType(String name)
			throws NoSuchBeanDefinitionException {
		return applicationContext.getType(name);
	}

	/**
	 * ���������bean������bean�������б������򷵻���Щ����
	 * 
	 * @param name
	 * @return
	 * @throws NoSuchBeanDefinitionException
	 */
	public static String[] getAliases(String name)
			throws NoSuchBeanDefinitionException {
		return applicationContext.getAliases(name);
	}
}