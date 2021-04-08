package com.yc.springframework.context;

import com.yc.springframework.stereotype.*;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Field;

import java.lang.reflect.InvocationTargetException;

import java.lang.reflect.Method;
import java.net.InterfaceAddress;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

/**
 * @program: testspring
 * @description:
 * @author: 汤僖龙
 * @create: 2021-04-05 16:35
 */
public class MyAnnotationConfigApplicationContext implements MyApplicationContext{
    private Map<String,Object>beanMap=new HashMap<String, Object>();
    public MyAnnotationConfigApplicationContext(Class<?>...componentClasses){
        try {
            register(componentClasses);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void register(Class<?>[]componentClasses) throws Exception {
        if (componentClasses==null||componentClasses.length<=0){
            throw new RuntimeException("没有指定的配置类");

        }
        for (Class cl:componentClasses){
            if (!cl.isAnnotationPresent(MyConfiguration.class)){
                continue;
            }
            String[]basePackges=getAppConfigBasePackges(cl);
            if(cl.isAnnotationPresent(MyComponentScan.class)){
                MyComponentScan mcs=(MyComponentScan)cl.getAnnotation(MyComponentScan.class);
                if(mcs.basePackages()!=null&&mcs.basePackages().length>0){
                    basePackges=mcs.basePackages();
                }
            }
            //处理@Bean的情况
            Object obj=cl.newInstance();
            handleAtMyBean(cl,obj);
            //处理basePackge下所有的托管bean
            for (String basePackage:basePackges){
                System.out.println("扫描包路径"+basePackage);
                scanPackageAndSubPackageClasses(basePackage);
            }
            handleManageBean();
            //循环beanMao中的每个bean，找到每个类中又@Autowired和@Resource注解的方法以实现bean
            handleDi(beanMap);
        }

    }
    //循环beanMao中的每个bean，找到每个类中又@Autowired和@Resource注解的方法以实现bean
    private void handleDi(Map<String, Object> beanMap) throws InvocationTargetException, IllegalAccessException {
        Collection<Object>objectCollection=beanMap.values();
        for (Object obj:objectCollection){
            Class cls=obj.getClass();
            Method[]ms=cls.getDeclaredMethods();
            for (Method m:ms){
                if (m.isAnnotationPresent(MyAutowired.class)&&m.getName().startsWith("set")){
                invokeAutowiredMethod(m,obj);
                }else if (m.isAnnotationPresent(MyResource.class)&&m.getName().startsWith("set")){
                invokeResourceMethod(m,obj);
                }
            }
            Field []fs=cls.getDeclaredFields();
            for (Field field:fs){
                if (field.isAnnotationPresent(MyAutowired.class)){

                }else if (field.isAnnotationPresent(MyResource.class)){

                }
            }
        }

    }

    private void invokeResourceMethod(Method m, Object obj) throws InvocationTargetException, IllegalAccessException {
        //取出Myresource中的name属性值，当成beanid
        MyResource mr =m.getAnnotation(MyResource.class);
        String beanId =mr.name();
        //如果没有，则取出m方法中参数的类型名改成首字母小写 当成beanid
        if (beanId==null||beanId.equalsIgnoreCase("")){
            String pname=m.getParameterTypes()[0].getSimpleName();
            beanId=pname.substring(0,1).toLowerCase()+pname.substring(1);
        }

        //从beanMap中取出
        Object o=beanMap.get(beanId);
        //invoke
        m.invoke(obj,o);
    }

    private void invokeAutowiredMethod(Method m, Object obj) throws InvocationTargetException, IllegalAccessException {
        //1.取出m的参数类型
        Class typeClass =m.getParameterTypes()[0];
        Set <String>keys=beanMap.keySet();
        //2.从BeanMap中循环所有的Objec
        for (String key:keys){
            //3.如果是从beanMap中取出
            Object o=beanMap.get(key);
            //4.判断这些object是否为参数类型的实例 instanceof
            Class[] interfaces=o.getClass().getInterfaces();
            for (Class c: interfaces){
                System.out.println(c.getName()+"\t"+typeClass);
                if (c==typeClass){

                    m.invoke(obj ,o);
                    break;
                }
                //m.invoke

            }

        }
    }

    /*
    处理manageBeanClasses所有的class类，包括@Component,@SService,@Reporsitory并实例化，存到BeanMap中。
     */
    private void handleManageBean() throws Exception {
        for (Class c:managedBeanClasse){
            if (c.isAnnotationPresent(MyComponent.class)){
            saveManageBean(c);
            }else if(c.isAnnotationPresent(MyRepository.class)){
                saveManageBean(c);
            }else if (c.isAnnotationPresent(MyService.class)){
                saveManageBean(c);
            }else if (c.isAnnotationPresent(MyController.class)){
                saveManageBean(c);
            }else {
                continue;
            }
        }
    }
    private void saveManageBean(Class c) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Object o=c.newInstance();
        handlePostConstruct(o,c);
        String beanId=c.getSimpleName().substring(0,1).toLowerCase()+c.getSimpleName().substring(1);
        beanMap.put(beanId,o);
    }

    private void scanPackageAndSubPackageClasses(String basePackage) throws IOException, ClassNotFoundException {
        String packagePath=basePackage.replaceAll("\\.","/");
        System.out.println("扫描包路径："+basePackage+"替换后"+packagePath);
        Enumeration<URL>files= Thread.currentThread().getContextClassLoader().getResources(packagePath);
        while (files.hasMoreElements()){
            URL url=files.nextElement();
            System.out.println("配置的扫描路径为"+url.getFile());
            findClassesInPackages(url.getFile(),basePackage);
        }
    }
    private Set<Class>managedBeanClasse=new HashSet<Class>();
    private void findClassesInPackages(String file, String basePackage) throws ClassNotFoundException {
        File f=new File(file);
        File[]classFiles=f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.getName().endsWith(".class")||file.isDirectory();
            }



        });
        for (File cf:classFiles){
            if (cf.isDirectory()){
                basePackage+="."+cf.getName().substring(cf.getName().lastIndexOf("/")+1);
            }else {
                URL[] urls=new URL[]{};
                URLClassLoader ucl=new URLClassLoader(urls);
                Class c=ucl.loadClass(basePackage+"."+cf.getName().replaceAll(".class",""));
                managedBeanClasse.add(c);



            }
        }

    }

    private  String[]getAppConfigBasePackges(Class cl){
    String[]paths=new String[1];
    paths[0]=cl.getPackage().getName();
    return paths;
    }
    private void handleAtMyBean(Class cls ,Object obj) throws InvocationTargetException, IllegalAccessException {
        Method[]ms=cls.getDeclaredMethods();
        for (Method m:ms){
            if (m.isAnnotationPresent(MyBean.class)){
                Object o=m.invoke(obj);
            handlePostConstruct(o,o.getClass());
                beanMap.put(m.getName(),o);
            }
        }
    }
    private void handlePostConstruct(Object o,Class<?>cls) throws InvocationTargetException, IllegalAccessException {
        Method[]ms=cls.getDeclaredMethods();
        for (Method m:ms){
            if (m.isAnnotationPresent(MyPostConstruct.class)){
                m.invoke(o);
            }
        }
    }
    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}
