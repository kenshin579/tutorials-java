package kr.jdm;

import java.lang.reflect.Field;

public class MyContextContainer {

    public MyContextContainer() {
    }

    /**
     * 객체를 반환하기 전 어노테이션을 적용합니다.
     *
     * @param instance
     * @param <T>
     * @return
     * @throws IllegalAccessException
     */
    private <T> T invokeAnnonations(T instance) throws IllegalAccessException {
        Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            StringInjector annotation = field.getAnnotation(StringInjector.class);
            if (annotation != null && field.getType() == String.class) {
                field.setAccessible(true);
                field.set(instance, annotation.value());
            }
        }
        return instance;
    }

    /**
     * 매개변수로 받은 클래스의 객체를 반환합니다.
     *
     * @param clazz
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public <T> T get(Class clazz) throws IllegalAccessException, InstantiationException {
        T instance = (T) clazz.newInstance();
        instance = invokeAnnonations(instance);
        return instance;
    }
}