package com.jrutz.coincounterjr;

import com.google.gson.Gson;

public class CoinCounter {

    private int mCountOfPennies, mCountOfNickels, mCountOfDimes, mCountOfQuarters;

    public int getCentsValueTotal ()
    {
        return getCentsValueOfPennies () + getCentsValueOfNickels ()
                + getCentsValueOfDimes () + getCentsValueOfQuarters ();
    }

    public int getCentsValueOfPennies ()
    {
        return mCountOfPennies;
    }

    public int getCentsValueOfNickels ()
    {
        return mCountOfNickels * 5;
    }

    public int getCentsValueOfDimes ()
    {
        return mCountOfDimes * 10;
    }

    public int getCentsValueOfQuarters ()
    {
        return mCountOfQuarters * 25;
    }


    public int getCountOfPennies ()
    {
        return mCountOfPennies;
    }

    public void setCountOfPennies (int countOfPennies)
    {
        this.mCountOfPennies = Math.max(0, countOfPennies);
    }

    public void setCountOfPennies (String countOfPennies)
    {
        this.mCountOfPennies = (countOfPennies == null || countOfPennies.equals (""))
                ? 0 : Integer.parseInt (countOfPennies);
    }

    public int getCountOfNickels ()
    {
        return mCountOfNickels;
    }

    public void setCountOfNickels (int countOfNickels)
    {
        this.mCountOfNickels = Math.max(0, countOfNickels);
    }

    public void setCountOfNickels(String nickels)
    {
        this.mCountOfNickels = (nickels == null || nickels.equals ("")) ? 0 : Integer.parseInt (nickels);
    }

    public int getCountOfDimes ()
    {
        return mCountOfDimes;
    }

    public void setCountOfDimes (int countOfDimes)
    {
        this.mCountOfDimes = Math.max(0, countOfDimes);
    }

    public void setCountOfDimes(String dimes)
    {
        this.mCountOfDimes = (dimes == null || dimes.equals ("")) ? 0 : Integer.parseInt (dimes);
    }

    public int getCountOfQuarters ()
    {
        return mCountOfQuarters;
    }

    public void setCountOfQuarters (int countOfQuarters)
    {
        this.mCountOfQuarters = Math.max(0, countOfQuarters);
    }

    public void setCountOfQuarters(String quarters)
    {
        this.mCountOfQuarters = (quarters == null || quarters.equals ("")) ? 0 : Integer.parseInt (quarters);
    }

    public static String getJSONStringFrom (CoinCounter currentCC)
    {
        return new Gson ().toJson (currentCC);
    }

    public String getJSONStringFromThis()
    {
        return new Gson ().toJson (this);
    }


    public static CoinCounter getCoinCounterObjectFromJSONString(String currentCC)
    {
        return new Gson ().fromJson (currentCC, CoinCounter.class);
    }
}
