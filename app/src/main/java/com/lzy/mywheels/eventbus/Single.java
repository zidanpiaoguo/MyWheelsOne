package com.lzy.mywheels.eventbus;

/**
 *
 * Created by bullet on 2018/1/3.
 */

public class Single {


    //懒汉模式
    private static Single data ;

    public static Single getInstance(){
        if(data==null){
            data =new Single();

        }
        return data;
    }


    //DCL
    private  static volatile  Single single ;

    public static Single getInstance3(){
        if(single==null){
            synchronized (Single.class){

                if(single==null){
                    single = new Single();
                }
            }
        }
        return single;
    }





    //饿汉模式
    private static Single data2 = new Single();

    public Single getInstance2(){
        return data2;
    }



    //静态内部类
    private static class SingleOne{
        public static Single single = new Single();
    }

    public static Single getInstance4(){
        return SingleOne.single;
    }


}
