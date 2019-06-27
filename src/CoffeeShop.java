import java.util.*;

public class CoffeeShop {


    Iterator i;

    public static void main(String[] args) {
        LinkedHashMap<Integer,Integer> order=new LinkedHashMap<>();
        String name;
        Scanner sc=new Scanner(System.in);
        CoffeeShop shop=new CoffeeShop();

        System.out.println("==============Menu==============");
        System.out.println("\tCoffee\t\tPrice");
        System.out.println("1.Cold Coffee\t\t50");
        System.out.println("2.Hot Coffee\t\t50");
        System.out.println("3.Mocha      \t\t100");
        System.out.println("4.Expresso   \t\t150");
        System.out.println("5.Capacuino  \t\t170");
        System.out.println("==================================");
        System.out.println("==================================\n\n");
        System.out.println("Enter the user Name");
        name=sc.nextLine();
        System.out.println("==============Order===============");

        System.out.println("Enter your order");
        System.out.println("Enter the cofee code and quantity seprated by , and two coffees seprated by !");
        String ord=sc.nextLine();
        shop.getOrder(ord,order);
        System.out.println("Enter discount");
        int dst=sc.nextInt();

        System.out.println("======"+name+" Your Order Is=======");
        shop.printOrder(order,dst);


    }

    private void printOrder(LinkedHashMap<Integer, Integer> order,int dst) {
        Set se=order.entrySet();
        i=se.iterator();
        int total_Price=0;
        while (i.hasNext())
        {
            Map.Entry me=(Map.Entry) i.next();
            int type=(Integer)me.getKey();
            switch (type)
            {
                case 1:System.out.println("1.Cold Coffee\t\t"+me.getValue()+"\t\t"+(Integer)me.getValue()*50);
                       total_Price=(Integer)me.getValue()*50+total_Price;
                       break;
                case 2:System.out.println("2.Hot Coffee\t\t"+me.getValue()+"\t\t"+(Integer)me.getValue()*50);
                       total_Price=(Integer)me.getValue()*50+total_Price;
                       break;
                case 3:System.out.println("3.Mocha      \t\t"+me.getValue()+"\t\t"+(Integer)me.getValue()*100);
                       total_Price=(Integer)me.getValue()*100+total_Price;
                       break;
                case 4:System.out.println("4.Expresso   \t\t"+me.getValue()+"\t\t"+(Integer)me.getValue()*150);
                       total_Price=(Integer)me.getValue()*150+total_Price;
                       break;
                case 5:System.out.println("5.Capacuino   \t\t"+me.getValue()+"\t\t"+(Integer)me.getValue()*170);
                       total_Price=(Integer)me.getValue()*170+total_Price;
                       break;
            }

        }
        System.out.println("===========================================");
        System.out.println("Total Price    \t\t\t\t"+total_Price);
        System.out.println("Tax            \t\t\t\t"+total_Price*14/100);
        System.out.println("Discount       \t\t\t\t"+total_Price*dst/100);
        System.out.println("===========================================");
        int amt=total_Price+(total_Price*14/100)-(total_Price*dst/100);
        System.out.println("Amount paid    \t\t\t\t"+amt);

    }

    public void getOrder(String ord,LinkedHashMap<Integer,Integer> order) {
        System.out.println("IN get order");
        CoffeeShop shop=new CoffeeShop();
        String or[]=ord.split("!");
         for(int i=0;i<or.length;i++)
         {
             String o[]=or[i].split(",");
              if(shop.checkOrder((String)o[0],(String)o[1],order))
              {
                  int type=Integer.parseInt(o[0]);
                  int quant=Integer.parseInt(o[1]);
                  order.put(type,quant);
              }
         }
    }

    private boolean checkOrder(String s, String s1,LinkedHashMap<Integer,Integer> order) {
       // System.out.println("In checkOrder");
        int type=Integer.parseInt(s);
        int quant=Integer.parseInt(s1);
        Set se=order.entrySet();
        i=se.iterator();
        if(order!=null) {

            while (i.hasNext()) {
                Map.Entry me = (Map.Entry) i.next();
                int tp = (Integer) me.getKey();
                int qt = (Integer) me.getValue();
                if (type == tp) {
                    me.setValue(qt + quant);
                    return false;
                }
            }
        }
        return true;
    }

}
