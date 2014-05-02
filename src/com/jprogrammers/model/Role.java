package com.jprogrammers.model;

/**
 * @author Ali Reza Akbarian
 *         created on 25/04/14.
 */
public class Role {

    public static final int ADMINISTRATOR = 0;
    public static final int USER = 1;
    public static final int CUSTOMER = 2;

    public static final String[] roleNames = new String[]{"administrator", "user", "customer"};
    public static int[] roleIds = new int[]{ADMINISTRATOR , USER , CUSTOMER};
}
