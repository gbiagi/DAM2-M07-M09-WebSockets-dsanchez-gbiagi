import 'dart:convert';
import 'dart:io';
import 'package:web_socket_channel/io.dart';
import 'package:client_flutter/memoryGame.dart';
import 'package:flutter/material.dart';
import 'package:window_manager/window_manager.dart';

class LoginScreen extends StatefulWidget {
  @override
  _LoginScreenState createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  final TextEditingController serverController = TextEditingController();
  final TextEditingController portController = TextEditingController();
  final TextEditingController nameController = TextEditingController();

  String _serverIp = '';
  final String _serverPort = '8888';

  IOWebSocketChannel? _channel;

  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();

  void _connectToServer() {
    if (serverController.text.isEmpty) {
      ScaffoldMessenger.of(context).showSnackBar(
          const SnackBar(content: Text('Ingrese una dirección IP válida')));
      return;
    }
    _serverIp = serverController.text;
    String server = "ws://$_serverIp:$_serverPort";
    _channel = IOWebSocketChannel.connect(server);
    Future.delayed(const Duration(seconds: 2));
  }

  void _sendMessage(String mensaje) {
    if (_channel != null) {
      final message = {
        'type': 'message',
        'value': mensaje,
        'format': 'text',
      };
      _channel!.sink.add(jsonEncode(message));
    }
  }

  void _onConnectPressed() {
    if (_formKey.currentState!.validate()) {
      // Validation passed, proceed with connection logic
      _connectToServer();
      if (_channel != null) {
        _sendMessage(nameController.text);
        Navigator.push(
          context,
          MaterialPageRoute(
            builder: (context) => MemoryGame(),
          ),
        );
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Login Screen'),
        centerTitle: true,
      ),
      body: Padding(
        padding: EdgeInsets.all(100.0),
        child: Container(
          padding: EdgeInsets.all(16.0),
          decoration: BoxDecoration(
            color: Colors.green,
            borderRadius: BorderRadius.circular(10.0),
          ),
          child: Form(
            key: _formKey,
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                Container(
                  padding: EdgeInsets.all(10.0),
                  decoration: BoxDecoration(
                    color: Colors.white,
                    borderRadius: BorderRadius.circular(8.0),
                  ),
                  child: TextFormField(
                    controller: serverController,
                    decoration: InputDecoration(labelText: 'Server'),
                    validator: (value) {
                      if (value == null || value.isEmpty) {
                        return 'Please enter the server';
                      }
                      return null;
                    },
                  ),
                ),
                SizedBox(height: 10),
                Container(
                  padding: EdgeInsets.all(10.0),
                  decoration: BoxDecoration(
                    color: Colors.white,
                    borderRadius: BorderRadius.circular(8.0),
                  ),
                  child: TextFormField(
                    controller: portController,
                    decoration: InputDecoration(labelText: 'Port'),
                    keyboardType: TextInputType.number,
                    validator: (value) {
                      if (value == null || value.isEmpty) {
                        return 'Please enter the port';
                      }
                      return null;
                    },
                  ),
                ),
                SizedBox(height: 10),
                Container(
                  padding: EdgeInsets.all(10.0),
                  decoration: BoxDecoration(
                    color: Colors.white,
                    borderRadius: BorderRadius.circular(8.0),
                  ),
                  child: TextFormField(
                    controller: nameController,
                    decoration: InputDecoration(labelText: 'Name'),
                    validator: (value) {
                      if (value == null || value.isEmpty) {
                        return 'Please enter the name';
                      }
                      return null;
                    },
                  ),
                ),
                SizedBox(height: 20),
                ElevatedButton(
                  onPressed: _onConnectPressed,
                  child: Text('Connect'),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}

void main() async {
  // For Linux, macOS and Windows, initialize WindowManager
  try {
    if (Platform.isLinux || Platform.isMacOS || Platform.isWindows) {
      WidgetsFlutterBinding.ensureInitialized();
      await WindowManager.instance.ensureInitialized();
      windowManager.waitUntilReadyToShow().then(showWindow);
      //windowManager.setSize(const Size(800.0, 600.0));
    }
  } catch (e) {
    // ignore: avoid_print
    print(e);
  }

  // Define the app as a ChangeNotifierProvider
  runApp(
    MaterialApp(
      title: 'App',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: LoginScreen(),
    ),
  );
}

// Show the window when it's ready
void showWindow(_) async {
  windowManager.setMinimumSize(const Size(800.0, 1000.0));
  await windowManager.setTitle('App');
}
