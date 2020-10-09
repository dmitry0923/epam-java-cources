package com.epam.university.java.project.core.cdi.bean;

/**
 * Author Dmitry Novikov 08-Oct-20.
 */
public class BeanFactoryImpl implements BeanFactory {
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
