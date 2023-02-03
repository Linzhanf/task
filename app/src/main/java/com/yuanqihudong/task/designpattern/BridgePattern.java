package com.yuanqihudong.task.designpattern;

import android.util.Log;

public class BridgePattern {

    public static void show() {
        Phone phone = new NormalPhone(new Honor());
        phone.open();
        Phone phone1 = new NormalPhone(new Huawei());
        phone1.open();
    }
}


/* ---------------------------------- */
abstract class Phone {

    protected final String TAG = this.getClass().getSimpleName();

    private final Brand brand;

    public Phone(Brand brand) {
        this.brand = brand;
    }

    public void open() {
        brand.open();
    }
}

class NormalPhone extends Phone {

    public NormalPhone(Brand brand) {
        super(brand);
    }

    @Override
    public void open() {
        super.open();
        Log.i(TAG, "open: NormalPhone");
    }
}

/* ---------------------------------- */
interface Brand {

    String TAG = Brand.class.getSimpleName();

    void open();
}

class Honor implements Brand {

    @Override
    public void open() {
        Log.i(TAG, "open: Honor");
    }
}

class Huawei implements Brand {

    @Override
    public void open() {
        Log.i(TAG, "open: Huawei");
    }
}