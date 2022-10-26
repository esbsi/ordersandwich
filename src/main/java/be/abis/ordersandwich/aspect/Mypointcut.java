package be.abis.ordersandwich.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class Mypointcut {



    @Pointcut("execution( * be.abis.ordersandwich.*.*.*(..))")
    public static void except(){


    }



}
