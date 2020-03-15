package org.century.scp.spocr.base.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.models.domain.IdentifiedEntity;
import org.century.scp.spocr.exceptions.SpocrException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

/**
 * dark magic territory
 */

// TODO: покрыть тестами

@Slf4j
public class CustomBeanUtils {

  public static <T extends IdentifiedEntity> void copyProperties(
      T source, T target, @NonNull List<String> properties) throws BeansException {
    Assert.notNull(source, "Source must not be null");
    Assert.notNull(target, "Target must not be null");
    Class<?> actualEditable = target.getClass();

    PropertyDescriptor[] targetPropertyDescriptors =
        BeanUtils.getPropertyDescriptors(actualEditable);

    for (PropertyDescriptor propertyDescriptor : targetPropertyDescriptors) {
      Method writeMethod = propertyDescriptor.getWriteMethod();
      if (canCopy(propertyDescriptor, writeMethod, properties)) {
        copyProperty(source, target, propertyDescriptor, writeMethod);
      }
    }
  }

  private static <T extends IdentifiedEntity> void copyProperty(
      T source, T target, PropertyDescriptor targetPropertyDescriptor, Method writeMethod) {
    PropertyDescriptor sourcePropertyDescriptor =
        BeanUtils.getPropertyDescriptor(source.getClass(), targetPropertyDescriptor.getName());
    if (sourcePropertyDescriptor != null) {
      Method readMethod = sourcePropertyDescriptor.getReadMethod();
      if (availableForReadWrite(writeMethod, readMethod)) {
        try {
          Object sourceValue = readMethod.invoke(source);
          copy(target, writeMethod, sourceValue);
        } catch (Throwable e) {
          log.error("could-not-copy-property.exception", e);
          throw new SpocrException("could-not-copy-property.exception",
              targetPropertyDescriptor.getName());
        }
      }
    }
  }

  private static <T extends IdentifiedEntity> void copy(
      T target, Method writeMethod, Object sourceValue)
      throws IllegalAccessException, InvocationTargetException {
    writeMethod.invoke(target, sourceValue);
  }

  private static boolean availableForReadWrite(Method writeMethod, Method readMethod) {
    return readMethod != null
        && ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType());
  }

  private static boolean canCopy(
      PropertyDescriptor propertyDescriptor, Method writeMethod, List<String> properties) {
    return (writeMethod != null && properties.contains(propertyDescriptor.getName()));
  }
}
