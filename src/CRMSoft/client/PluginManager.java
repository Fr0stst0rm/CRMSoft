package CRMSoft.client;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;

import PluginInterface.client.PluginInterface;

public class PluginManager {

	static HashMap<String, PluginInterface> loadedPlugins = new HashMap<>();
	
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
					URLClassLoader cLoder = (URLClassLoader) ClassLoader.getSystemClassLoader();
					Class classToLoad = Class.forName(plugin.getName().split("\\.")[0] + ".Plugin", true, cLoder);
					Constructor<?> constructor = classToLoad.getConstructor();
					PluginInterface pluginInstance = (PluginInterface) constructor.newInstance();

					System.out.println(pluginInstance.getName() + " loaded");

					loadedPlugins.put(pluginInstance.getName(), pluginInstance);

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
				}
			}
		}

		return loadedPlugins;
	}

}
