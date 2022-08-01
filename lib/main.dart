import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Service In Background',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  // startServiceInPlatform()  async {
  // try {
  // await platform.invokeMethod('startService');
  // } on PlatformException catch (e) {
  // print(e.message);
  // }
  // }
  void startServiceInPlatform() async {
    if (Platform.isAndroid) {
      var methodChannel = MethodChannel("com.backend");
      String data = await methodChannel.invokeMethod("startService");
      debugPrint(data);
    }
  }


  @override
  Widget build(BuildContext context) {
    return Container(
              color: Colors.white,
              child: Center(
                child: ElevatedButton.icon(
                    onPressed: () {
                      startServiceInPlatform();
                    }, label: Text('Start Background'), icon: Icon(Icons.android),),
              ),
            );
  }
}
