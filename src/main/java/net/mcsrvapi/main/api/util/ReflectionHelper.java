package net.mcsrvapi.main.api.util;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is a utility class.
 * All needed reflection methods are located in here.
 */
public class ReflectionHelper {

    private static final Logger LOG = Logger.getLogger(ReflectionHelper.class.getName());

    /**
     * This class is a util class, so it's not needed to instantiate it.
     */
    private ReflectionHelper() {
        throw new UnsupportedOperationException("This is a util class.");
    }

    /**
     * Create a new instance of a class
     * @param clazz Class<T> - Class to instantiate
     * @return <T> - Instance of Class
     */
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LOG.log(Level.WARNING, e.getMessage());
            return null;
        }
    }

    /**
     * Change or set a variable of an instance
     * @param instance Object - Instance to manipulate
     * @param name String - Name of field (variable)
     * @param value Object - object to set the variable to
     */
    public static void set(Object instance, String name, Object value) {
        set(instance.getClass(), instance, name, value);
    }

    /**
     * Get the field of an instance
     * @param instance Object - instance of Class
     * @param name String - Name of field (variable)
     * @return Object - Field to get
     */
    public static Object get(Object instance, String name) {
        return get(instance.getClass(), instance, name);
    }

    /**
     * Change or set a variable of an instance
     * @param clazz Class<?> - Class of instance
     * @param instance Object - Instance to manipulate
     * @param name String - Name of field (variable)
     * @param value Object - object to set the variable to
     */
    public static void set(Class<?> clazz, Object instance, String name, Object value) {
        try {
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            field.set(instance, value);
        } catch (Exception e) {
            LOG.log(Level.WARNING, e.getMessage());
        }
    }

    /**
     * Get the field of an instance
     * @param clazz Class<?> - Class of instance
     * @param instance Object - instance of Class
     * @param name String - Name of field (variable)
     * @return Object - Field to get
     */
    public static Object get(Class<?> clazz, Object instance, String name) {
        try {
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            return field.get(instance);
        } catch (Exception e) {
            LOG.log(Level.WARNING, e.getMessage());
        }
        return null;
    }

}
