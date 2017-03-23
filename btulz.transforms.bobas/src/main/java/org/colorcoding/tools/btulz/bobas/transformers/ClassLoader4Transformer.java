package org.colorcoding.tools.btulz.bobas.transformers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 业务对象类型加载器
 * 
 * @author manager
 *
 */
public class ClassLoader4Transformer extends URLClassLoader {

	public ClassLoader4Transformer(URL[] urls, ClassLoader parent) {
		super(urls, parent);
	}

	public ClassLoader4Transformer(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
		super(urls, parent, factory);
	}

	public ClassLoader4Transformer(URL[] urls) {
		super(urls);
	}

	public Enumeration<String> getClassNames() {
		return new Enumeration<String>() {

			int fileIndex = 0;

			private boolean hasMore = true;

			@Override
			public boolean hasMoreElements() {
				return this.hasMore;
			}

			Enumeration<JarEntry> jarEntries;

			@SuppressWarnings("resource")
			@Override
			public String nextElement() {
				try {
					if (jarEntries == null) {
						for (int i = fileIndex; i < getURLs().length; i++) {
							URL item = getURLs()[i];
							File file = new File(java.net.URLDecoder.decode(item.getPath(), "UTF-8"));
							if (file.getName().endsWith(".jar")) {
								JarFile jarFile = new JarFile(file);
								Enumeration<JarEntry> jarEntries = jarFile.entries();
								if (jarEntries != null) {
									while (jarEntries.hasMoreElements()) {
										JarEntry jarEntry = (JarEntry) jarEntries.nextElement();
										if (jarEntry.isDirectory()) {
											continue;
										}
										if (jarEntry.getName().toLowerCase().endsWith(".class")) {
											String name = jarEntry.getName().replace("/", ".");
											name = name.replace(".class", "");
											return name;
										}
									}
								}
								jarFile.close();
							}
							fileIndex++;
						}
					}
					return null;
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		};
	}

	protected Class<?> defineClass(String name, InputStream inputStream)
			throws IOException, ClassFormatError, NoClassDefFoundError, ClassNotFoundException {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int data = inputStream.read();
		while (data != -1) {
			buffer.write(data);
			data = inputStream.read();
		}
		byte[] bytes = buffer.toByteArray();
		inputStream.close();
		return this.defineClass(name, bytes, 0, bytes.length);
	}

	@Override
	public Class<?> findClass(String name) throws ClassNotFoundException {
		// 首先，查看是否已经加载
		Class<?> type = this.findLoadedClass(name);
		if (type != null) {
			return type;
		}
		try {
			// 父项加载失败，子项加载
			return super.findClass(name);
		} catch (ClassNotFoundException e) {
			// 没有加载，尝试父项加载
			return this.getParent().loadClass(name);
		}
	}
}
