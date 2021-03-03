import java.util.*;
import java.io.*;
public class Polynomial 
{
  private final int[] coef;   
  private int degree;   
    
  public Polynomial(int coeffient, int power) 
  {
    if(power<0) 
    {
      throw new IllegalArgumentException("exponent cannot be negative: " + power);
    }
    coef=new int[power+1];
    coef[power]=coeffient;
    reduce();
  }

    
 private void reduce() 
  {
    degree=-1;
    for(int i=coef.length-1;i>=0;i--)
    {
      if(coef[i]!=0) 
      {
        degree=i;
        return;
      }
    }
  }

    
  public int degree() 
  {
    return degree;
  }

    
  public Polynomial plus(Polynomial that)
  {
    Polynomial poly=new Polynomial(0,Math.max(this.degree,that.degree));
    for (int i=0;i<=this.degree;i++){ poly.coef[i]+=this.coef[i];}
        for (int i=0;i<=that.degree;i++){ poly.coef[i]+=that.coef[i];}
        poly.reduce();
        return poly;
  }

    public Polynomial times(Polynomial that) {
        Polynomial poly = new Polynomial(0, this.degree + that.degree);
        for (int i = 0; i <= this.degree; i++)
            for (int j = 0; j <= that.degree; j++)
                poly.coef[i+j] += (this.coef[i] * that.coef[j]);
        poly.reduce();
        return poly;
    }



//    public int evaluate(int x) {
//        int p = 0;
//        for (int i = degree; i >= 0; i--)
//            p = coef[i] + (x * p);
//        return p;
//    }

    @Override
    public String toString() {
        if      (degree == -1) return "0";
        else if (degree ==  0) return "" + coef[0];
        else if (degree ==  1) return coef[1] + "x + " + coef[0];
        String s = coef[degree] + "x^" + degree;
        for (int i = degree - 1; i >= 0; i--) {
            if      (coef[i] == 0) continue;
            else if (coef[i]  > 0) s = s + " + " + (coef[i]);
            else if (coef[i]  < 0) s = s + " - " + (-coef[i]);
            if      (i == 1) s = s + "x";
            else if (i >  1) s = s + "x^" + i;
        }
        return s;
    }

    
    public static void main(String...args)
    { 
        Polynomial zero=new Polynomial(0, 0);

        Polynomial variable1=new Polynomial(4, 3);
        Polynomial variable2=new Polynomial(3, 2);
        Polynomial variable3=new Polynomial(1, 0);
        Polynomial variable4=new Polynomial(2, 1);
        Polynomial equation1=variable1.plus(variable2).plus(variable3).plus(variable4);
        Polynomial variable5=new Polynomial(3, 2);
        Polynomial variable6=new Polynomial(5, 0);
        Polynomial equation2=variable5.plus(variable6);                    
        Polynomial addition= equation1.plus(equation2);
        Polynomial multiplication= equation1.times(equation2);
        System.out.println("zero(x)     = " + zero);
        System.out.println("\nEquation 1 : ");
        System.out.println("p(x) = " + equation1);
        System.out.println("\nEquation 2 : ");
        System.out.println("q(x) = " + equation2);
        System.out.println("\nAddition of equation 1 & equation 2 is : ");
        System.out.println("p(x) + q(x) = " + addition);
        System.out.println("\nmultiplication of equation 1 & equation 2 is : ");
        System.out.println("p(x) * q(x) = " + multiplication);
       
    }
}