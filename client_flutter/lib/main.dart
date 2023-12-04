import 'package:flutter/material.dart';
import 'package:flip_card/flip_card.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: LoginPage(),
    );
  }
}

class LoginPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Login'),
      ),
      body: Center(
        child: ElevatedButton(
          onPressed: () {
            // TODO: Implement login logic
            // For simplicity, let's navigate to the game screen directly
            Navigator.push(
              context,
              MaterialPageRoute(builder: (context) => GameScreen()),
            );
          },
          child: Text('Login'),
        ),
      ),
    );
  }
}

class GameScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Memory Game'),
      ),
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Text('Player 1'),
          Text('Player 2'),
          Expanded(
            child: GridView.builder(
              gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisCount: 4,
              ),
              itemCount: 16,
              itemBuilder: (context, index) {
                return Padding(
                  padding: EdgeInsets.all(8.0),
                  child: FlipCard(
                    direction: FlipDirection.HORIZONTAL,
                    front: Card(
                      color: Colors.blue,
                      child: Center(
                        child: Text('Card $index'),
                      ),
                    ),
                    back: Card(
                      color: Colors.red,
                      child: Center(
                        child: Text('.'),
                      ),
                    ),
                  ),
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}
