package com.epam.university.java.project.core.cdi.bean;

import java.beans.BeanProperty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Author Dmitry Novikov 08-Oct-20.
 */
public class BeanDefinitionRegistryImpl implements BeanDefinitionRegistry {
    private final Map<String, BeanDefinition> registry = new HashMap<>();

    @Override
    public void addBeanDefinition(BeanDefinition definition) {
        registry.put(definition.getId().toLowerCase(), definition);
        registry.put(definition.getClassName().toLowerCase(), definition);


        try {
            Class<?> clazz = Class.forName(definition.getClassName());
            for (Class<?> hasInterface : clazz.getInterfaces()) {
                if (hasInterface != null) {
                    registry.put(hasInterface.getName().toLowerCase(), definition);
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return registry.get(beanId.toLowerCase());
    }
}
