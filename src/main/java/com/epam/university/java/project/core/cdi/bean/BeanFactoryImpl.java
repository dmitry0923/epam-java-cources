package com.epam.university.java.project.core.cdi.bean;

import com.epam.university.java.project.core.cdi.structure.ListDefinition;
import com.epam.university.java.project.core.cdi.structure.MapDefinition;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BeanFactoryImpl implements BeanFactory {
    private final BeanDefinitionRegistry beanDefinitionRegistry;
    private final Map<BeanDefinition, Object> singletons = new HashMap<>();

    public BeanFactoryImpl(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    @Override
    public <T> T getBean(Class<T> beanClass) {
        return (T) getBean(beanClass.getName());
    }

    @Override
    public Object getBean(String beanName) {
        return getBean(beanDefinitionRegistry.getBeanDefinition(beanName));
    }

    @Override
    public <T> T getBean(String beanName, Class<T> beanClass) {
        return (T) getBean(beanName);
    }

    public <T> T getBean(BeanDefinition definition) {
        // if properties = null and scope is prototype
        if (definition.getProperties() == null
                && definition.getScope().equals("prototype")) {
            T instance = null;
            Class<T> clazz = null;
            try {
                clazz = (Class<T>) Class.forName(definition.getClassName());
                instance = clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException();
            }
            return instance;
        }

        Predicate<String> isNullOrBlank = x -> x == null || x.isBlank();

        if (definition.getProperties().stream()
                .anyMatch(n -> isNullOrBlank.test(n.getValue())
                        && isNullOrBlank.test(n.getRef())
                        && n.getData() == null)
        ) {
            throw new RuntimeException();
        }

        try {
            T instance;
            Class<T> clazz = (Class<T>) Class.forName(definition.getClassName());
            if (definition.getScope() == null) {
                instance = clazz.getDeclaredConstructor().newInstance();
                return instance;
            }
            if (definition.getScope().equals("singleton")
                    && singletons.containsKey(definition)) {
                instance = (T) singletons.get(definition);
                return instance;
            } else {
                instance = clazz.getDeclaredConstructor().newInstance();
                if ("singleton".equals(definition.getScope())) {
                    singletons.put(definition, instance);
                }
            }

            for (BeanPropertyDefinition property : definition.getProperties()) {
                Field beanField = clazz.getDeclaredField(property.getName());
                beanField.setAccessible(true);
// here
                if (!isNullOrBlank.test(property.getRef())) {
                    Object dependency = getBean(property.getRef());
                    beanField.set(instance, dependency);
                }

                if (!isNullOrBlank.test(property.getValue())) {
                    try {
                        beanField.set(instance, Integer.parseInt(property.getValue()));
                    } catch (Exception e) {
                        beanField.set(instance, property.getValue());
                    }
                }

                if (property.getData() == null) {
                    continue;
                }

                if (property.getData() instanceof ListDefinition) {
                    ListDefinition listDefinition = (ListDefinition) property.getData();
                    Collection<String> items = listDefinition.getItems()
                            .stream()
                            .map(ListDefinition.ListItemDefinition::getValue)
                            .collect(Collectors.toList());
                    beanField.set(instance, items);
                }

                if (property.getData() instanceof MapDefinition) {
                    MapDefinition mapDefinition = (MapDefinition) property.getData();
                    Map<String, Object> itemMap = new HashMap<>();
                    for (MapDefinition.MapEntryDefinition entryDefinition
                            : mapDefinition.getValues()) {
                        if (!isNullOrBlank.test(entryDefinition.getValue())
                                && !isNullOrBlank.test(entryDefinition.getRef())) {
                            throw new RuntimeException();
                        }
                        if (!isNullOrBlank.test(entryDefinition.getValue())) {
                            itemMap.put(entryDefinition.getKey(), entryDefinition.getValue());
                        } else if (!isNullOrBlank.test(entryDefinition.getRef())) {
                            Object dependency = getBean(entryDefinition.getRef());
                            itemMap.put(entryDefinition.getKey(), dependency);
                        }
                    }
                    beanField.set(instance, itemMap);
                }
            }
            return instance;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
