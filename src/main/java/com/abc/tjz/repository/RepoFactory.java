package com.abc.tjz.repository;

import com.abc.tjz.util.SpringManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 数据仓库工程
 */
public interface RepoFactory {

	GoodsCategoryRepository getGoodsCategoryRepo();
	
	RepoFactory rf = (RepoFactory) Proxy.newProxyInstance(RepoFactory.class.getClassLoader(), new Class[] {RepoFactory.class}, new InvocationHandler() {
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			return SpringManager.getBean(method.getReturnType());
		}
	});
}








