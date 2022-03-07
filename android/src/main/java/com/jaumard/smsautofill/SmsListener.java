//package com.jaumard.smsautofill;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.telephony.SmsMessage;
//import android.util.Log;
//
//import androidx.annotation.NonNull;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import io.flutter.embedding.engine.plugins.FlutterPlugin;
//import io.flutter.plugin.common.BinaryMessenger;
//import io.flutter.plugin.common.MethodCall;
//import io.flutter.plugin.common.MethodChannel;
//
//public class SmsListener extends BroadcastReceiver implements FlutterPlugin, MethodChannel.MethodCallHandler {
//
//    private MethodChannel channel;
//    private static final String channelName = "SMSChannel";
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        // TODO Auto-generated method stub
//
//        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
//            Bundle bundle = intent.getExtras();           //---get the SMS message passed in---
//            SmsMessage[] msgs = null;
//            String msg_from;
//            if (bundle != null){
//                //---retrieve the SMS message received---
//                try{
//                    Object[] pdus = (Object[]) bundle.get("pdus");
//                    msgs = new SmsMessage[pdus.length];
//                    for(int i=0; i<msgs.length; i++){
//                        msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
//                        msg_from = msgs[i].getOriginatingAddress();
//                        String msgBody = msgs[i].getMessageBody();
//                        Log.wtf(SmsListener.class.getSimpleName(), "msgBody : "+parseCode(msgBody));
//                        channel.invokeMethod("receivedOtp", parseCode(msgBody));
//                    }
//                }catch(Exception e){
////                            Log.d("Exception caught",e.getMessage());
//                }
//            }
//        }
//    }
//
//    private String parseCode(String message) {
//        Pattern p = Pattern.compile("\\b\\d{6}\\b");
//        Matcher m = p.matcher(message);
//        String code = "";
//        while (m.find()) {
//            code = m.group(0);
//        }
//        return code;
//    }
//
//    private void setupChannel(BinaryMessenger messenger) {
//        channel = new MethodChannel(messenger, channelName);
//        channel.setMethodCallHandler(this);
//    }
//
//    @Override
//    public void onAttachedToEngine(@NonNull FlutterPlugin.FlutterPluginBinding binding) {
//        setupChannel(binding.getBinaryMessenger());
//    }
//
//    @Override
//    public void onDetachedFromEngine(@NonNull FlutterPlugin.FlutterPluginBinding binding) {
//    }
//
//    @Override
//    public void onMethodCall(@NonNull  MethodCall call, @NonNull MethodChannel.Result result) {
////        if (call.method == "getGooglePlay") {
////
////        }
//    }
//}