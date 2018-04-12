package CRMSoft.client;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import PluginInterface.client.PluginInterface;

public class PluginManager {

	public static HashMap<String, PluginInterface> loadedPlugins = new HashMap<>();

	public static HashMap<String, PluginInterface> loadPlugins() {

		File pluginPath = new File("plugins");

		if (!pluginPath.isDirectory()) {
			pluginPath.delete();
			pluginPath.mkdir();
		}

		for (File plugin : pluginPath.listFiles()) {
			if (plugin.isFile()) {
				Object URI;

				try {
					URL[] urls = { new URL("jar:file:" + pluginPath.getPath() + "/" + plugin.getName() + "!/") };
					System.out.println(urls[0]);
					URLClassLoader cLoder = URLClassLoader.newInstance(urls);

					JarFile jarFile = new JarFile(pluginPath.getPath() + "/" + plugin.getName());
					Enumeration<JarEntry> e = jarFile.entries();

					while (e.hasMoreElements()) {
						JarEntry je = e.nextElement();
						if (je.isDirectory() || !je.getName().endsWith(".class")) {
							continue;
						}
						// -6 because of .class
						String className = je.getName().substring(0, je.getName().length() - 6);
						className = className.replace('/', '.');
						if (className.equals(plugin.getName().split("\\.")[0] + ".Plugin")) {
							Class classToLoad = cLoder.loadClass(className);
							Constructor<?> constructor = classToLoad.getConstructor();
							PluginInterface pluginInstance = (PluginInterface) constructor.newInstance();

							System.out.println(pluginInstance.getName() + " loaded");

							loadedPlugins.put(pluginInstance.getName(), pluginInstance);
						}

					}

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

		return loadedPlugins;
	}

}
