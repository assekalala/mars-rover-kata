import java.io.*;

public class Rover {

    public int x = 0;
    public int y = 0;

    public static final int N = 1;
    public static final int E = 2;
    public static final int S = 3;
    public static final int W = 4;

    public int direction = N;

    public Rover() {

    }

    public void setPosition(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;

        System.out.println("Rover position set to x: " + x + " y: " + y + " facing: " + direction);
    }

    public void setPosition(int x, int y, Character direction) {
        this.x = x;
        this.y = y;

        if(direction.equals('N')) {
            this.direction = N;
        } else if (direction.equals('E')) {
            this.direction = E;
        } else if(direction.equals('S')) {
            this.direction = S;
        } else if(direction.equals('W')) {
            this.direction = W;
        } else {
            throw new IllegalArgumentException("Invalid direction");
        }
    }


    public void printPosition() {
        char dir = 'N';

        switch (direction) {
            case 1:
                dir = 'N';
                break;
            case 2:
                dir = 'E';
                break;
            case 3:
                dir = 'S';
                break;
            case 4:
                dir = 'W';
        }

        System.out.print("x: " + x + " y: " + y + " facing: " + dir);
    }

    public void move(String commands) {
        for (int i=0; i < commands.length(); i++) {
            process(commands.charAt(i));
        }

        System.out.print("Rover moved to x: " + x + " y: " + y + " facing: " + direction);
    }

    public void process(Character command) {
        if(command.equals('L')) {
            turnLeft();
        } else if (command.equals('R')) {
            turnRight();
        } else if(command.equals('F')) {
            moveForward();
        } else if(command.equals('B')) {
            moveBackward();
        } else {
            throw new IllegalArgumentException("Command no understood");
        }
    }

    public void moveForward() {
        if (this.direction == N) {
            this.y++;
        } else if (this.direction == S) {
            this.y--;
        }
    }

    public void moveBackward() {
        if(this.direction == S) {
            this.y--;
        } else if (this.direction == N) {
            this.y++;
        }

    }

    public void turnLeft() {
        this.direction = ((direction - 1) < N) ? W : direction - 1;
    }

    public void turnRight() {
        this.direction = ((direction - 1) < W) ? N : direction - 1;
    }


    public static void log(String message) {
        System.out.println(message);
    }

    public static void print_usage(String args[]) {
        log("usage: set_position x y direction");
        log("       move commands");
    }

    public static void main (String[] args) {
        Rover rover = new Rover();

        if (args[0].equals("set_position") && args.length == 4) {
            int x = Integer.parseInt(args[1]);
            int y = Integer.parseInt(args[2]);
            Character direction = args[3].charAt(0);
            rover.setPosition(x,y,direction);
        } else if(args[0].equals("move") && args.length == 2) {
            String commands = args[1];
            rover.move(commands);
        } else {
            print_usage(args);
            System.exit(1);
        }

        System.exit(0);
    }
}
