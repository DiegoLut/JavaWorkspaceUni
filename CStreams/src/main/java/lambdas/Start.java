/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lambdas;

/**
 *
 * @author blaszczyk
 */
public class Start {
    
    public void main()
    {
        Calculations add = new Calculations() {
            @Override
            public int calculate(int a, int b) {
                return a + b;
            }
        };
        
        System.out.println(add.calculate(12, 20));
        
        Calculations lambdaAdd = (int a, int b) -> a + b;
        
        System.out.println(lambdaAdd.calculate(12, 20));
        
        Calculations lambdaMultiple = (int a, int b) -> a * b;
        
        calc((a,b) -> {
            return a + b;
        });
        
        calc((a, b) -> a * b);
       
    }
    
    private int calc(Calculations calculations)
    {
        return calculations.calculate(10, 20);
    }
}
