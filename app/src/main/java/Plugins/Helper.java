package Plugins;

import lombok.NonNull;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class Helper {
    @NonNull
    public static ArrayList<String> getClassNames(JarInputStream jarFile) throws Exception {
        ArrayList<String> classNames = new ArrayList<>();
        try{
            JarEntry jar;
            while(true){
                jar = jarFile.getNextJarEntry();
                if(jar == null){
                    break;
                }
                if(jar.getName().endsWith(".class")){
                    classNames.add(jar.getName().replaceAll("/", "\\.").substring(0, jar.getName().length() - 6));
                }
            }
        } catch (Exception e){
            throw new Exception("Failed to get class names from jar file");
        }
        return classNames;
    }

    public static List<Class<?>> loadClasses(String jarFilePath) throws Exception {
        List<Class<?>> classes = new ArrayList<>();
        JarInputStream jar = new JarInputStream(new File(jarFilePath).toURI().toURL().openStream());
        ArrayList<String> classNames = getClassNames(jar);
        try {
            File jarFile = new File(jarFilePath);
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { jarFile.toURI().toURL() });
            for (String className : classNames) {
                Class<?> cls = classLoader.loadClass(className);
                classes.add(cls);
            }
        } catch (Exception e) {
            throw new Exception("Failed to load classes from jar file");
        }
        return classes;
    }
}
