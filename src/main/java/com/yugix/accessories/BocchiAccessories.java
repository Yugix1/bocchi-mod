package com.yugix.accessories;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public class BocchiAccessories {
    public static void init(){
        BowtieAccessory.init();
        KessokuBandAccessory.init();
        SakeAccessory.init();
    }
    @Environment(EnvType.CLIENT)
    public static void initClient(){
        KessokuBandAccessory.clientInit();
        BowtieAccessory.clientInit();
    }
}
