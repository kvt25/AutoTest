package com.autotest.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import com.autotest.configure.Environment;
import com.autotest.utils.StackTraceInfo;

public class ApplicationReflectionsSearch extends Environment{

	Reflections reflections = null;

	public ApplicationReflectionsSearch(String packageName) {
		List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
		classLoadersList.add(ClasspathHelper.contextClassLoader());
		classLoadersList.add(ClasspathHelper.staticClassLoader());

		reflections = new Reflections(new ConfigurationBuilder()
		.setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
		.setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
		.filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(packageName))));
	}

	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = this.reflections.getSubTypesOf(Object.class);
		return classes;
	}

	public ArrayList<String> getMethodsOfClass(Class cls) {
		ArrayList<String> methodList = new ArrayList<String>();
		try {
			Object obj = cls.newInstance();
			Method[] tempmethod = cls.getDeclaredMethods();
			for (int i = 0; i < tempmethod.length; i++) {
				methodList.add(tempmethod[i].getName());
			}
			log.error("FINISH TO GET ALL METHODS OF CLASS:" + cls.getName());
		}
		catch (Exception e) {
			log.error("COULD NOT GET METHOD OF CLASS:" + cls.getName());
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
		}
		return methodList;
	}
}
