# 🎮 Brick Breakers

Brick Breakers is a modern re-implementation of the classic arcade game, built in **Java**. Players control a paddle to deflect a ball, aiming to break all the bricks on the screen while avoiding losing the ball. The game features multiple levels, interactive menus, and responsive controls for an engaging retro-inspired experience.

Demo:
https://www.youtube.com/watch?v=0II7eruo9NU
---

## 🚀 Features
- Classic brick-breaker gameplay with modern polish  
- Multiple levels with increasing difficulty  
- Custom graphics and menus  
- Smooth keyboard controls  
- Modular Java class structure for extensibility  

---

## 🎮 Controls
- ⬅️ **Left Arrow**: Move paddle left  
- ➡️ **Right Arrow**: Move paddle right  
- ⏎ **Enter**: Start the game / Confirm menu selection  
- ⎋ **Escape**: Pause or return to the main menu  

---

## 🗂️ Project Structure
Brick-Breakers/
│── src/main/java/com/mycompany/game/
│ ├── BrickBreaker.java # Main game entry point
│ ├── Paddle.java # Player paddle logic
│ ├── Ball.java # Ball mechanics and collision
│ ├── Brick.java # Brick objects and durability
│ ├── GamePanel.java # Rendering and game loop
│ ├── Menu.java # Main menu interface
│ ├── Instructions2.java # Instructions screen
│ └── ... other supporting files
│
│── src/main/resources/
│ ├── icon.png # Game icon
│ └── additional assets
│
└── README.md # Project documentation

yaml
Copy code

---

## 🛠️ Technologies Used
- **Language**: Java (JDK 8+)  
- **Graphics**: Java Swing / AWT  
- **Build Tool**: Maven/Gradle compatible  

---

## 📖 How It Works
1. **Initialization**: `BrickBreaker.java` sets up the game window and initializes objects  
2. **Game Loop**: `GamePanel.java` manages rendering, collisions, and game state  
3. **Entities**:  
   - `Paddle.java` → User input and paddle movement  
   - `Ball.java` → Velocity, rebound angles, collisions  
   - `Brick.java` → Brick durability and scoring  
4. **UI**: `Menu.java` and `Instructions2.java` provide menus and instructions  

---

## 📜 License
Licensed under the **GNU General Public License v3.0**. See the [LICENSE](LICENSE) file for details.

---
