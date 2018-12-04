package personlife.gst.com.bluetoothdemo;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.math.BigInteger;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class MainActivity extends Activity {

    private BluetoothManager manager;
    private BluetoothAdapter adapter;

    /**
     * 蓝牙交互的char
     */
    public static final String BLE_DEVICE_INFO_CHAR = "0000ffe1-0000-1000-8000-00805f9b34fb";

    /**
     * 与血压计交互时的Service；
     */
    String SERVICE_UUID="00001810-0000-1000-8000-00805f9b34fb";

    public UUID  uuid=UUID.fromString(SERVICE_UUID);
    /**
     * 发送命令的通道；
     */
    public static final String SERVICE_CHARACTERISTIC_SEND = "00002a50-0000-1000-8000-00805f9b34fb";
    /**
     * 接受数据的通道；
     */
    public static final String SERVICE_CHARACTERISTIC_RECEIVE = "00002a51-0000-1000-8000-00805f9b34fb";
    private BluetoothGatt mBluetoothGatt;
    /**
     * 开始测量血压
     */

    public static final byte[] START_MEASURE = {(byte) 0xEB, 0x20, 0x00,
            (byte) 0xf5, (byte) 0xEB};
    /**
     * 停止测试血压；
     */
    public static final byte[] STOP_MEASURE = {(byte) 0xEB, 0x20, 0x01,
            (byte) 0xf4, (byte) 0xEB};
    /**
     * 获取设备信息
     */
    public static final byte[] GET_DEV_INFO = {(byte) 0xEB, 0x01, 0x14,
            (byte) 0xEB};
    /**
     * 获取当前电量
     */
    public static final byte[] GET_BATTERY = {(byte) 0xEB, 0x02, 0x13,
            (byte) 0xEB};
    /**
     * 获取结果
     */
    public static final byte[] GET_REUSLT = {(byte) 0xEB, 0x21,
            (byte) 0xf4, (byte) 0xEB};

    BluetoothGattCharacteristic  wirteGattCharacteristic;
    BluetoothGattCharacteristic  readGattCharacteristic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportBlueTooth();
    }

    //

    public  void  SupportBlueTooth(){
        //检查当前手机是否是支持蓝牙ble，如果不支持则退出
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)){
            Toast.makeText(MainActivity.this, "暂不支持蓝牙功能", Toast.LENGTH_SHORT).show();
            finish();
        }

        //初始化蓝牙适配器 Bluetooth adapter  通过蓝牙管理器得到一个蓝牙适配器

        manager = (BluetoothManager)getSystemService(Context.BLUETOOTH_SERVICE);
        adapter = manager.getAdapter();


        if(adapter==null){
            Toast.makeText(this, "暂不支持", Toast.LENGTH_SHORT).show();
            finish();
        }

        //没有启用蓝牙权限
        if(!adapter.isEnabled()){

        }
        //有权限
        if(adapter.isEnabled()){
            adapter.startLeScan(leScanCallback);
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                 adapter.stopLeScan(leScanCallback);
//                }
//            },1000);

        }





    }
    BluetoothAdapter.LeScanCallback  leScanCallback=new BluetoothAdapter.LeScanCallback(){

        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            String name= device.getName();
            System.out.println("获取的蓝牙名字"+name);
      //            device.connectGatt(); //收到蓝牙设备后要做的下一步

            if("KBB3-1-BLE ".equals(name)){
                adapter.stopLeScan(leScanCallback);
                mBluetoothGatt = device.connectGatt(MainActivity.this,false,gattCallback);
            }




        }
    };

    BluetoothGattCallback   gattCallback=new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);
            System.out.println("onConnectionStateChange"  +gatt+ "  "+status  +"   "+newState);
               //A GATT operation completed successfully
                if(status== BluetoothGatt.GATT_SUCCESS ){

                }
              //  The profile is in connected state
            if(newState==BluetoothGatt.STATE_CONNECTED){

                    gatt.discoverServices();  //Attempting to start service discovery:


            }
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);

            System.out.println("onServicesDiscovered"  +gatt+ "  "+status  );
            //获取指定的通道
                BluetoothGattService  gattService=gatt.getService(uuid);

               List<BluetoothGattCharacteristic>  list= gattService.getCharacteristics();
               System.out.println("获取制定的"+list.size());
               for(int i=0;i<list.size();i++){
                    BluetoothGattCharacteristic  characteristic=list.get(i);
                    if(UUID.fromString(SERVICE_CHARACTERISTIC_SEND).equals(characteristic.getUuid())){
                        wirteGattCharacteristic=characteristic;
                        /**
                         * 设定当前的char，打开通道；
                         */
                        gatt.readCharacteristic(characteristic);
                        setCharacteristicNotification(characteristic,true);
                    }
                    if(UUID.fromString(SERVICE_CHARACTERISTIC_RECEIVE).equals(characteristic.getUuid())){
                       readGattCharacteristic=characteristic;

                        /**
                         * 设定当前的char，打开通道；
                         */
//                        gatt.readCharacteristic(characteristic);
//                        setCharacteristicNotification(characteristic,true);

                    }


                }
        }



        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicRead(gatt, characteristic, status);
            System.out.println("onCharacteristicRead  "  +characteristic+ "  "+status  );

            System.out.println("onCharacteristicRead  "  +characteristic.getValue().length );


            final byte[] broadCastData = characteristic.getValue();
            if (broadCastData != null && broadCastData.length > 0) {
                final StringBuilder stringBuilder = new StringBuilder(broadCastData.length);
                for (byte byteChar : broadCastData)
                    stringBuilder.append(String.format("%02X ", byteChar));
                System.out.println("onCharacteristicRead的数据是：" + stringBuilder.toString());
            }

            // 本次回传的数据是：EB 21 80 00 30 00 00 00 00 00 00 44 EB --- 6,7收缩压
            float heightPress = getdata(broadCastData[5], broadCastData[6]);
            float lowPress = getdata(broadCastData[7], broadCastData[8]);
            float heartRate = getdata(broadCastData[9], broadCastData[10]);
            System.out.println("高压：" + heightPress);
            System.out.println("低压：" + lowPress);
            System.out.println("心率：" + heartRate);
            System.out.println("袖带压力：" + getdata(broadCastData[3], broadCastData[4]));
            System.out.println("接收到的数据为： " + broadCastData);
        }

        @Override
        public void onCharacteristicWrite(final BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicWrite(gatt, characteristic, status);
            System.out.println("onCharacteristicWrite  "  +gatt+ "  "+status +"  "+characteristic );


//                   timer.schedule(new TimerTask() {
//                       @Override
//                       public void run() {
//                           readGattCharacteristic.setValue(GET_REUSLT);
//                           gatt.readCharacteristic(readGattCharacteristic);
//                       }
//                   },250,250);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    readGattCharacteristic.setValue(GET_REUSLT);
                    gatt.readCharacteristic(readGattCharacteristic);
                }
            },250);



        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            super.onCharacteristicChanged(gatt, characteristic);

            System.out.println("onCharacteristicChanged  "  +gatt+"  "+characteristic );
        }
    };

    //开启或者关闭notification  虽然里面有heart 的内容
    public void setCharacteristicNotification(final BluetoothGattCharacteristic characteristic,
                                              boolean enabled) {
        if (adapter == null || mBluetoothGatt == null) {
            Log.w("123", "BluetoothAdapter not initialized");
            return;
        }
       boolean flag= mBluetoothGatt.setCharacteristicNotification(characteristic, enabled);

        if(flag&&SERVICE_CHARACTERISTIC_SEND.equals(characteristic.getUuid().toString())){
            characteristic.setValue(START_MEASURE);
            mBluetoothGatt.writeCharacteristic(characteristic);

        }

//        if(flag&&SERVICE_CHARACTERISTIC_RECEIVE.equals(characteristic.getUuid().toString())){
//            characteristic.setValue(GET_REUSLT);
//            mBluetoothGatt.readCharacteristic(characteristic);
//        }

    }

    private Handler  handler=new Handler();

    Timer  timer=new Timer();

    /**
     * 处理16进制数据
     *
     * @param data
     * @return
     */
    private float getdata(byte... data) {
        BigInteger bigNum = new BigInteger(data);
        float intNum = Float.parseFloat(bigNum.intValue() + "");
        return intNum;
    }

}
