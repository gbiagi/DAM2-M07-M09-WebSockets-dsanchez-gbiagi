import 'package:flutter/material.dart';
import 'package:flip_card/flip_card.dart';

class MemoryGame extends StatelessWidget {
  String player1Name = "";
  String player2Name = "";
  String gameID = "";

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('Memory'),
          centerTitle: true,
        ),
        body: MyWidget(),
      ),
    );
  }
}

class MyWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    double cardSize = 25.0; // Adjust the card size as needed

    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        const Row(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: [
            Text('Player1',
                style: TextStyle(
                  fontSize: 20.0,
                )),
            Text('GameID',
                style: TextStyle(
                  fontSize: 20.0,
                )),
            Text('Player2',
                style: TextStyle(
                  fontSize: 20.0,
                )),
          ],
        ),
        const SizedBox(height: 20),
        Container(
          padding:
              const EdgeInsets.only(left: 250, right: 250, top: 5, bottom: 0),
          child: GridView.builder(
            shrinkWrap: true,
            gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
              crossAxisCount: 4,
              crossAxisSpacing: 10,
              mainAxisSpacing: 10,
            ),
            itemCount: 16,
            itemBuilder: (context, index) {
              return SizedBox(
                width: cardSize,
                height: cardSize,
                child: FlipCard(
                  direction: FlipDirection.HORIZONTAL,
                  front: Card(
                    color: Colors.blue,
                    child: Center(
                      child: Text(
                        'Front $index',
                        style: TextStyle(color: Colors.white),
                      ),
                    ),
                  ),
                  back: Card(
                    color: Colors.red,
                    child: Center(
                      child: Text(
                        'Back $index',
                        style: TextStyle(color: Colors.white),
                      ),
                    ),
                  ),
                ),
              );
            },
          ),
        ),
      ],
    );
  }
}
