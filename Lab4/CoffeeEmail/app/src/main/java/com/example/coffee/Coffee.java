package com.example.coffee;

class Coffee {
    /*
    Single coffee costs $4.00
    Chocolate costs $1.00
    Whipped Cream costs $.50
 */

    private static boolean cream;
    private static boolean choco;
    private static int quantity;

    Coffee()
    {
        cream = false;
        choco = false;
        quantity = 0;

    }

    void editCream()
    {
        cream = !cream;
    }
    boolean getCream(){
        return cream;
    }

    void editChoco()
    {
        choco = !choco;
    }
    boolean getChoco(){
        return choco;
    }

    void increaseCount() {
        quantity++;
    }
    void decreaseCount() {
        if(quantity > 0)
        {
            quantity--;
        }
    }
    int getCount(){
        return quantity;
    }

    float getPrice()
    {
        float total = 4;
        if(cream)
        {
            total += .50;
        }
        if(choco)
        {
            total += 1;
        }
        return total * quantity;
    }

}
