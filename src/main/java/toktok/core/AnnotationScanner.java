package toktok.core;

import com.google.common.collect.Sets;
import com.google.common.reflect.ClassPath;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * @author Jan Kischkel
 */
class AnnotationScanner<T extends Annotation> {

    private final String packageName;

    private final Class<T> annotation;

    private AnnotationScanner(String packageName, Class<T> annotation) {
        this.packageName = packageName;
        this.annotation = annotation;
    }

    private Set<Class<?>> scan() throws Exception {
        Set<Class<?>> controllers = Sets.newHashSet();

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        ClassPath classPath = ClassPath.from(loader);
        Set<ClassPath.ClassInfo> infos =
                classPath.getTopLevelClassesRecursive(packageName);

        for (ClassPath.ClassInfo cpi : infos) {
            Class<?> topClass = cpi.load();

            if (isController(topClass)) controllers.add(topClass);

            for (Class<?> subClass : topClass.getDeclaredClasses())
                if (isController(subClass)) controllers.add(subClass);
        }

        return controllers;
    }

    private void create(Iterable<Class<?>> classes) throws Exception {
        for (Class<?> ctrl : classes) {
            System.out.printf("create %s\n", ctrl.getName());
            ctrl.newInstance();
        }
    }

    private boolean isController(Class<?> clazz) {
        return clazz.getAnnotation(annotation) != null
                && !clazz.isInterface()
                && !clazz.isEnum();
    }

    static <T extends Annotation> void createAllIn(String packageName, Class<T> annotation) {
        try {
            AnnotationScanner<T> scanner = new AnnotationScanner<>(packageName, annotation);
            scanner.create(scanner.scan());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
