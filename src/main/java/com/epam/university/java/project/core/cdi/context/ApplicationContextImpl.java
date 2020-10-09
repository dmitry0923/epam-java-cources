package com.epam.university.java.project.core.cdi.context;

import com.epam.university.java.project.core.cdi.io.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Author Dmitry Novikov 09-Oct-20.
 */
public class ApplicationContextImpl implements ApplicationContext {
    private final List<Object> listOfLoadedBeans = new ArrayList<>();
    @Override
    public int loadBeanDefinitions(Resource resource) {
        return 0;
    }

    @Override
    public int loadBeanDefinitions(Collection<Resource> resources) {
        return 0;
    }

    @Override
    public <T> T getBean(Class<T> beanClass) {
        return null;
    }

    @Override
    public Object getBean(String beanName) {
        return null;
    }

    @Override
    public <T> T getBean(String beanName, Class<T> beanClass) {
        return null;
    }
}
