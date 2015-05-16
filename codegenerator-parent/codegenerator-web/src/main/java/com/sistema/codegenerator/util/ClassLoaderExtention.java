package com.sistema.codegenerator.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class ClassLoaderExtention extends ClassLoader {

	// public static ClassLoaderExtention getInstance(List<String> lClasspath) {
	// return new
	// ClassLoaderExtention(ClassLoaderExtention.class.getClassLoader(),
	// lClasspath);
	// }
	private List<URL> classPath;

	// public ClassLoaderExtention(ClassLoader parent, List<String> lClasspath)
	// {
	//
	// super(parent);
	//
	// classPath = new ArrayList<>();
	//
	// for (String string : lClasspath) {
	// try {
	// classPath.add(new File(string).toURI().toURL());
	// } catch (MalformedURLException e2) {
	// e2.printStackTrace();
	// }
	// }
	//
	// }

	public static ClassLoaderExtention getInstance(List<File> lClasspath) {
		return new ClassLoaderExtention(ClassLoaderExtention.class.getClassLoader(), lClasspath);
	}

	public ClassLoaderExtention(ClassLoader parent, List<File> lClasspath) {

		super(parent);

		classPath = new ArrayList<>();

		for (File string : lClasspath) {
			try {
				classPath.add(string.toURI().toURL());
			} catch (MalformedURLException e2) {
				e2.printStackTrace();
			}
		}

	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {

		try {
			return super.loadClass(name);
		}

		catch (Exception e) {

			URLClassLoader cl = URLClassLoader.newInstance((URL[]) classPath.toArray(new URL[classPath.size()]));
			return cl.loadClass(name);

		}
	}
}
