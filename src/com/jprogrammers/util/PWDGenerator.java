package com.jprogrammers.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Ali Reza Akbarian
 *         created on 27/06/14.
 */
public class PWDGenerator {

    public static String generatePassword(int n)
    {

        Random rd = new Random();

        char lowerChars[] = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char upperChars[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char numbers[] = "0123456789".toCharArray();
        char specialChars[] = "~!@#$%^&*()-_=+[{]}|;:<>/?".toCharArray();

        List<Character> pwdLst = new ArrayList<Character>();
        for (int g = 0; g < 4; g++)
        {
            for (int z = 0; z < 1; z++)
            {
                if (g == 0)
                {
                    pwdLst.add(numbers[rd.nextInt(10)]);
                }
                else if (g == 1)
                {
                    pwdLst.add(lowerChars[rd.nextInt(26)]);
                }
                else if (g == 2)
                {
                    pwdLst.add(upperChars[rd.nextInt(26)]);
                }
                else if (g == 3)
                {
                    //pwdLst.add(specialChars[rd.nextInt(26)]);
                }
            }
            if (pwdLst.size() == n)
            {
                break;
            }
            if (g + 1 == 4)
            {
                g = (int) Math.random() * 5;

            }
        }
        StringBuilder password = new StringBuilder();
        Collections.shuffle(pwdLst);
        for (int c = 0; c < pwdLst.size(); c++)
        {
            password.append(pwdLst.get(c));
        }
        return password.toString();
    }
}

