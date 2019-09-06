package com.ocean.swak.utils;

import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * @author: junqing.li
 * @date: 2019/9/5
 */
public class ClassUtils extends org.springframework.util.ClassUtils {


    /**
     * 根据特定注解获取接口的类型
     *
     * @param inClass
     * @return
     */
    public static Optional<Class<?>> getInterfaceClassByAnnotation(Class inClass, Class annotationClass) {

        Set<Class<?>> interfaceSet = ClassUtils.getAllInterfacesForClassAsSet(inClass);

        return interfaceSet.stream().filter(clazz -> {

            Annotation annotation = clazz.getDeclaredAnnotation(annotationClass);
            return Objects.nonNull(annotation);

        }).findFirst();

    }

    public static String getQualifiedNameByAnnotation(Class inClass, Class annotationClass) {
        Optional<Class<?>> optional = getInterfaceClassByAnnotation(inClass, annotationClass);
        if (!optional.isPresent()) {
            return null;
        }
        return getQualifiedName(optional.get());
    }


    public static boolean isOriginMethod(Method method) {

        return ReflectionUtils.isHashCodeMethod(method)
                || ReflectionUtils.isToStringMethod(method)
                || ReflectionUtils.isEqualsMethod(method)
                || ReflectionUtils.isObjectMethod(method);
    }
}
