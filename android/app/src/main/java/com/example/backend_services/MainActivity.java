package com.example.backend_services;

import io.flutter.embedding.android.FlutterActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterActivity {
    private static final String CHANNEL = "com.backend";
    private Intent forService;

    @Override
        public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
            super.configureFlutterEngine(flutterEngine);
        forService = new Intent(MainActivity.this,MyService.class);

        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL)
                    .setMethodCallHandler(
                            (call, result) -> {
                                if (call.method.equals("startService")) ;
                                startService();
                                Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
                                result.success("services Started");
                            }
                    );
//        if (call.method.equals("stop"));
//                    stopService(forService);
//                     Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(forService);
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show();
    }

    private void startService(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            startForegroundService(forService);
        } else {
            startService(forService);
        }
    }



}

// if (call.method.equals("startService")) {
//                                startService();
//                                result.success("Service Started");
//                            }