# Android Chess

Android implementation of chess, repurposed from a [terminal version of chess](https://github.com/AndrewYW/Chess). Features all chess functions including checks, checkmates, drawing, castling, pawn promotion, and en passant. Additionally, it includes the option to record games and play then back move by move.
+ Minimum SDK: 21
+ Target SDK: 23

## Technology

This application was built completely with Java 8 in Android Studio. Data storage for recordings was implemented using `java.io.Serializable` and stored locally. 

### UML

The initial UML we implemented for the base chess game. 

![uml](./uml.PNG)

### 1d array Board implementation

At the time of doing this project, my partner and I both hated doing any sort of 2d array calculation and movement, so we decided to see if we could implement a `Board` class with only a one dimensional array with 64 Pieces.

By doing this, we were able to map position (e.g: a2, e6) and movement to integer values 0 - 63 rather than to 2d array values. For some cases, this eased up methods, such as determining whether or not a piece was on the left or right border:

```java
//Piece.java
public static boolean isLeftBorder(int space){
        switch(space){
            case 56:
            case 48:
            case 40:
            case 32:
            case 24:
            case 16:
            case 8:
            case 0:
                return true;
            default:
                return false;
        }
    }
```

Another benefit was that at the time this made movement more intuitive for us. Moving vertically was increasing or decreasing the index position of a piece by 8, while horizontally was generally by 1 or -1. In this manner, moving a pawn diagonally was simply moving by 7, 9, -7, or -9. 

### Serialization

Using `java.io.Serializable`, we store `Recording` instances, which are objects that contain a name, date of recording, and a list of moves (as strings) made and recorded during a game. 

### Playback

Playback and game recording are extensions of logic used for the base game. By setting a click listener to read each line of a list of moves, 