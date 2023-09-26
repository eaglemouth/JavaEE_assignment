package edu.whu;

public class MyClass {
    private String name;
    private int value;

    @InitMethod
    public void init(){

        System.out.println("Initializing MyClass...");
    }

    public void function(){

    }

    public MyClass() {
        System.out.println("Constructing...");
    }

    public MyClass(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
