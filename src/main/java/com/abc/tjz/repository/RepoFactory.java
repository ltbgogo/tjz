package com.abc.tjz.repository;

import com.abc.tjz.entity.CouponTb;
import com.abc.tjz.util.misc.SpringManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 数据仓库工程
 */
public interface RepoFactory {

	QuickEntryRepository getQuickEntryRepo();
	CouponTbRepository getCouponTbRepo();
	
	RepoFactory rf = (RepoFactory) Proxy.newProxyInstance(RepoFactory.class.getClassLoader(), new Class[] {RepoFactory.class}, new InvocationHandler() {
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			return SpringManager.getBean(method.getReturnType());
		}
	});
}








