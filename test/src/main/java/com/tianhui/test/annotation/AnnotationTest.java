package com.tianhui.test.annotation;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by bullet on 2018/2/5.
 */

public class AnnotationTest {
    private static final String TAG = "AnnotationTest";


    public static void inject(Activity activity) {
        jnectContentView(activity);
        injectView(activity);
//        injectEvent(activity);
        injectEvents(activity);
    }

    private static void jnectContentView(Activity activity) {
        //获取activity的类实例
        Class<? extends Activity> clazz = activity.getClass();
        //获取到activity的ContentView注解
        live contentView = clazz.getAnnotation(live.class);
        if (contentView != null) {
            //如果activity上面存在这个注解的话，就取出这个注解对应的value值，就是前面设置的布局
            int layoutId = contentView.value();
            try {
                //利用反射调用setContentView方法，完成注入
                Method setViewMethod = clazz.getMethod("setContentView", int.class);
                setViewMethod.invoke(activity, layoutId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void jnextCOntentVIew(Activity activity) {
        Class<? extends Activity> clazz =activity.getClass();
        live contentView = clazz.getAnnotation(live.class);
        if (contentView!=null){
            int layoutId = contentView.value();
            try {
                Method setViewMethod = clazz.getMethod("setContentView", int.class);
                setViewMethod.invoke(activity,layoutId);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private static void injectView(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        //获取activity的所有成员变量
        Field[] fields = clazz.getDeclaredFields();
        //遍历成员变量，获取成员变量上的ViewInject注解
        for (Field field : fields) {
            //获取字段上面的注解对象，同有则继续下一个字段
            ViewNew viewInject = field.getAnnotation(ViewNew.class);
            if (viewInject != null) {
                //获取ViewInject注解的View的id
                int viewId = viewInject.value();
                //获取控件
                View view = activity.findViewById(viewId);
                try {
                    //设置field为可访问，就算私有的也能访问到
                    field.setAccessible(true);
                    //将该控件设置给field对象
                    field.set(activity, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void injectEvent(final Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        //获取所有方法（私有方法也可以获取到）
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            //获取方法上面的OnClick注解
            ViewOnClick click = method.getAnnotation(ViewOnClick.class);
            //有则继续下面代码
            if (click != null) {
                //获取注解中的数据，因可以给多个button绑定点击事件，因此定义的注解类时使用的是int[]数据类型
                int[] viewId = click.value();
                method.setAccessible(true);
                //设置一个代理对象,当调用setOnClickListener时，把代理对象传进去，当点击发生时，就会invoke方法，可以调用带有onClick注解的method方法
                Object listener = Proxy.newProxyInstance(View.OnClickListener.class.getClassLoader(),
                        new Class[]{View.OnClickListener.class}, new InvocationHandler() {
                            @Override
                            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                return method.invoke(activity, args);
                            }
                        });
                try {
                    for (int id : viewId) {
                        //获取相应的控件
                        View v = activity.findViewById(id);
                        Method setClickListener = v.getClass().getMethod("setOnClickListener", View.OnClickListener.class);
                        setClickListener.invoke(v, listener);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public static void injectEvents(Activity activity) {
        Class a = activity.getClass();
        // 得到Activity的所有方法
        Method[] methods = a.getDeclaredMethods();
        for (Method method : methods) {
            // 得到被OnClick注解的方法
            if (method.isAnnotationPresent(ViewOnClick.class)) {
                // 得到该方法的OnClick注解
                ViewOnClick onClick = method.getAnnotation(ViewOnClick.class);
                // 得到OnClick注解的值
                int[] viewIds = onClick.value();
                // 得到OnClick注解上的EventBase注解
                EventBase eventBase = onClick.annotationType().getAnnotation(EventBase.class);
                // 得到EventBase注解的值
                String listenerSetter = eventBase.listenerSetter();
                Class<?> listenerType = eventBase.listenerType();
                String methodName = eventBase.methodName();
                // 使用动态代理
                DynamicHandler handler = new DynamicHandler(activity);
                Object listener = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class<?>[] { listenerType }, handler);
                handler.addMethod(methodName, method);
                // 为每个view设置点击事件
                for (int viewId : viewIds) {
                    try {
                        Method findViewByIdMethod = a.getMethod("findViewById", int.class);
                        findViewByIdMethod.setAccessible(true);
                        View view  = (View) findViewByIdMethod.invoke(activity, viewId);
                        Method setEventListenerMethod = view.getClass().getMethod(listenerSetter, listenerType);
                        setEventListenerMethod.setAccessible(true);
                        setEventListenerMethod.invoke(view, listener);
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
