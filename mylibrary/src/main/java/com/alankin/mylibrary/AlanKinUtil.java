package com.alankin.mylibrary;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alankin on 2017/1/9.
 * 上传github
 */
public class AlanKinUtil {
    public static final String SET_CONTENT_VIEW = "setContentView";

    /**
     * 绑定activity
     *
     * @param activity
     */
    public static void bind(Activity activity) {
        bindContentView(activity);
        bindView(activity);
        bindOnClickListener(activity);
    }

    /**
     * 为Acitvity绑定ContentView
     *
     * @param activity
     */
    public static void bindContentView(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        ContentView contentView = aClass.getAnnotation(ContentView.class);
        if (contentView != null) {
            int resId = contentView.value();
            try {
                Method setContentView = aClass.getMethod(SET_CONTENT_VIEW, int.class);
                setContentView.setAccessible(true);//设置可进入
                //唤醒方法只能唤醒已存在对象的方法，而不是唤醒对象类的方法。
                setContentView.invoke(activity, resId);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 绑定View
     *
     * @param activity
     */
    public static void bindView(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        for (Field field : aClass.getDeclaredFields()) {
            BindView bindView = field.getAnnotation(BindView.class);
            if (bindView != null) {
                int IdRes = bindView.value();
                View viewById = activity.findViewById(IdRes);
                try {
                    field.set(activity, viewById);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 绑定点击事件(OnClickListener)
     *
     * @param activity
     */
    public static void bindOnClickListener(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            OnClick onClick = method.getAnnotation(OnClick.class);
            if (onClick != null) {
                int[] IdRes = onClick.value();
                EventBase eventBase = onClick.annotationType().getAnnotation(EventBase.class);
                if (eventBase != null) {
                    String listenerSetter = eventBase.listenerSetter();
                    String methodName = eventBase.methodName();
                    Class listenerType = eventBase.listenerType();

                    DynamicHandler dynamicHandler = new DynamicHandler(activity);
                    dynamicHandler.addMethod(methodName, method);
                    Object listenerProxy = Proxy.newProxyInstance(
                            listenerType.getClassLoader(),
                            new Class[]{listenerType}, dynamicHandler);

                    for (int idRe : IdRes) {
                        View viewById = activity.findViewById(idRe);
                        try {
                            Method setListenerMethod = viewById.getClass().getMethod(listenerSetter, listenerType);
                            setListenerMethod.setAccessible(true);
                            //唤醒方法时的参数为已有对象而不是类对象
                            setListenerMethod.invoke(viewById, listenerProxy);
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static class DynamicHandler implements InvocationHandler {
        private Map<String, Method> methodMap;
        private Activity activity;

        public DynamicHandler(Activity activity) {
            methodMap = new HashMap<>();
            DynamicHandler.this.activity = activity;
        }

        public void addMethod(String methodName, Method activityMethod) {
            methodMap.put(methodName, activityMethod);
        }

        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            String methodName = method.getName();
            Method activityMethod = methodMap.get(methodName);
            if (activityMethod != null) {
                return activityMethod.invoke(activity, objects);
            }
            return null;
        }
    }
}
