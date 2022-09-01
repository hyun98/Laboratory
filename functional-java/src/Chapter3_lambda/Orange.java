package Chapter3_lambda;

import Chapter1_2.Color;

import static Chapter1_2.Color.RED;

public class Orange implements Fruit {
    
    private int weight;

    private Color color = RED;

    public Orange() {
    }

    public Orange(int weight) {
        this.weight = weight;
    }

    public Orange(int weight, Color color) {
        this.weight = weight;
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public Color getColor() {
        return color;
    }
}
