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
  final TextEditingController roomController = TextEditingController();

  String gameID = "";
  String playerName = "";
  String rivalName = "";

  String _serverIp = '';
  final String _serverPort = '8888';

  IOWebSocketChannel? _channel;

  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();

  void _connectToServer() {
    if (serverController.text.isEmpty || portController.text.isEmpty) {
      ScaffoldMessenger.of(context).showSnackBar(
          const SnackBar(content: Text('Ingrese una dirección IP válida')));
      return;
    }
    if (nameController.text.isEmpty) {
      ScaffoldMessenger.of(context).showSnackBar(
          const SnackBar(content: Text('Ingrese un nombre de usuario')));
      return;
    }
    _serverIp = serverController.text;
    String server = "ws://$_serverIp:$_serverPort";
    _channel = IOWebSocketChannel.connect(server);
    Future.delayed(const Duration(seconds: 2));
    _channel!.stream.listen(
      (mensaje) {
        final data = jsonDecode(mensaje);
        if (data['type'] == "conn") {
          ScaffoldMessenger.of(context)
              .showSnackBar(const SnackBar(content: Text('Conectado')));
        } else if (data['type'] == "gameCreated") {
          gameID = data['gameID'];
          playerName = nameController.text;
          setState(() {
            rivalName = "Waiting for rival...";
          });
        } else {
          ScaffoldMessenger.of(context).showSnackBar(
              const SnackBar(content: Text('Error al conectar2')));
        }
      },
      onError: (error) {
        print("Error: $error");
        _channel!.sink.close();
        ScaffoldMessenger.of(context)
            .showSnackBar(const SnackBar(content: Text('Error al conectar')));
      },
      onDone: () {},
    );
  }

  void _sendMessage(String type, String mensaje) {
    final message = type == "createGame"
        ? {
            'type': 'createGame',
            'name': playerName,
          }
        : type == "joinGame"
            ? {
                'type': 'joinGame',
                'name': playerName,
                'gameName': mensaje,
              }
            : null;

    if (message != null) {
      _channel!.sink.add(jsonEncode(message));
    }
  }

  void _onConnectPressed() async {
    _connectToServer();

    if (_channel != null) {
      String? result = await showDialog<String>(
        context: context,
        builder: (BuildContext context) => AlertDialog(
          content: Container(
            width: 300.0,
            padding: const EdgeInsets.all(8.0),
            child: Column(
              mainAxisSize: MainAxisSize.min,
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                const Text('Enter a game name:',
                    style: TextStyle(fontSize: 20)),
                SizedBox(height: 15),
                Container(
                  padding: EdgeInsets.all(10.0),
                  decoration: BoxDecoration(
                    color: Colors.white,
                    borderRadius: BorderRadius.circular(8.0),
                  ),
                  child: TextFormField(
                    controller: roomController,
                    decoration: InputDecoration(labelText: 'Game Name'),
                    validator: (value) {
                      if (value == null || value.isEmpty) {
                        return 'Please enter a game name';
                      }
                      return null;
                    },
                  ),
                ),
                const SizedBox(height: 20),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: [
                    ElevatedButton(
                      onPressed: () {
                        // Handle "Create Game" button action
                        Navigator.pop(context, 'create');
                        _sendMessage("createGame", "a");
                      },
                      child: const Text('Create Game'),
                    ),
                    ElevatedButton(
                      onPressed: () {
                        // Handle "Join Game" button action
                        Navigator.pop(context, 'join');
                        _sendMessage("joinGame", roomController.text);
                      },
                      child: const Text('Join Game'),
                    ),
                  ],
                ),
              ],
            ),
          ),
        ),
      );

      // Navigate to the next screen based on the result
      if (result == 'create' || result == 'join') {
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
        title: const Text('Login Screen'),
        centerTitle: true,
      ),
      body: Padding(
        padding: const EdgeInsets.all(200.0),
        child: Container(
          padding: const EdgeInsets.all(80.0),
          decoration: BoxDecoration(
            color: Colors.green,
            borderRadius: BorderRadius.circular(10.0),
          ),
          child: Form(
            key: _formKey,
            child: Column(
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
                    decoration: const InputDecoration(labelText: 'Name'),
                    validator: (value) {
                      if (value == null || value.isEmpty) {
                        return 'Please enter the name';
                      }
                      return null;
                    },
                  ),
                ),
                const SizedBox(height: 20),
                ElevatedButton(
                  onPressed: _onConnectPressed,
                  child: const Text('Connect'),
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
      title: 'Memory',
      theme: ThemeData(
        primarySwatch: Colors.green,
      ),
      home: LoginScreen(),
    ),
  );
}

// Show the window when it's ready
void showWindow(_) async {
  windowManager.setMinimumSize(const Size(800.0, 1000.0));
  await windowManager.setTitle('Memory');
}
