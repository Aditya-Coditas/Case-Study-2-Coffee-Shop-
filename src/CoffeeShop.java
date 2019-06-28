import java.util.*;

public class CoffeeShop {
    Iterator i;
    Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        LinkedHashMap<Integer,Integer> order=new LinkedHashMap<>();
        LinkedHashMap<Integer,String> menu=new LinkedHashMap<>();
        String name;
        int flag=1;
        menu.put(1,"Cold Coffee");
        menu.put(2,"Hot Coffee");
        menu.put(3,"Mocha");
        menu.put(4,"Expresso");
        menu.put(5,"Capacuino");
        Scanner sc=new Scanner(System.in);
        CoffeeShop shop=new CoffeeShop();
        System.out.println("---------------Menu---------------");
        System.out.println("\tCoffee\t\t  Price");
        System.out.println("1.Cold Coffee\t\t50");
        System.out.println("2.Hot Coffee\t\t50");
        System.out.println("3.Mocha      \t\t100");
        System.out.println("4.Expresso   \t\t150");
        System.out.println("5.Capacuino  \t\t170");
        System.out.println("----------------------------------");
        System.out.println("----------------------------------\n\n");
        System.out.println("Enter the user Name");
        name=sc.nextLine();
        System.out.println("--------------Order---------------");
        System.out.println("Enter your order And Enter '0' for exit");
       // System.out.println("Enter the cofee code and quantity seprated by , and two coffees seprated by !");
        while(flag==1) {
            String ord = sc.nextLine();
            if(ord.equals("0"))
            {
                flag=1;
                break;
            }
            String str=shop.validateOrder(ord,menu);
             if(str!=null) {
                 ord=str;
                 shop.getOrder(ord, order,menu);
             }
        }
        System.out.println("Enter discount %");
        int dst=sc.nextInt();
        System.out.println("-----------"+name+" Your Bill Is------------");
        System.out.println("\tCoffee\t\tQuantity\tPrice");
        shop.printBill(order,dst);
    }

    public String validateOrder(String ord,LinkedHashMap<Integer,String> menu) {
        String s[]=ord.split(",");
        CoffeeShop shop=new CoffeeShop();
        int s2=Integer.parseInt(s[1]);
        if(s[0].matches(".*\\d.*")) {
            int s1 = Integer.parseInt(s[0]);
            if (s1 < 0 || s1 > 5) {
                System.out.println("Invalid Order ID\nPlease Enter Valid Menu ID");
                return null;
            }
            if (s2 < 0) {
                System.out.println("Invalid Quantity\nPlease Enter Valid Quantity");
                return null;
            }
        }
        else {
            if (s2 < 0) {
                System.out.println("Invalid Quantity\nPlease Enter Valid Quantity");
                return null;
            }
               Set se=menu.entrySet();
               i=se.iterator();
               while(i.hasNext())
               {
                   Map.Entry me=(Map.Entry) i.next();
                   if(me.getValue().equals(s[0]))
                   {
                       return ord;
                   }
               }
               String str=shop.predictor(s[0],menu);
               System.out.println("Do you want to order "+str+" (yes/no)");
               String str2=sc.nextLine();
               if(str2.equals("yes"))
               {
                   String str3=str+","+s[1];
                   System.out.println(str3);
                   return str3;
               }
               else {
                   System.out.println("Please Enter Proper Name");
                   return null;
               }
        }
        return ord;
    }

    public void printBill(LinkedHashMap<Integer, Integer> order,int dst) {
        Set se=order.entrySet();
        i=se.iterator();
        int total_Price=0;
        int c=1;
        while (i.hasNext())
        {
            Map.Entry me=(Map.Entry) i.next();
            int type=(Integer)me.getKey();
            switch (type)
            {
                case 1:System.out.println(c+".Cold Coffee\t\t"+me.getValue()+"\t\t"+(Integer)me.getValue()*50);
                       total_Price=(Integer)me.getValue()*50+total_Price;
                       c++;
                       break;
                case 2:System.out.println(c+".Hot Coffee\t\t"+me.getValue()+"\t\t"+(Integer)me.getValue()*50);
                       total_Price=(Integer)me.getValue()*50+total_Price;
                       c++;
                       break;
                case 3:System.out.println(c+".Mocha      \t\t"+me.getValue()+"\t\t"+(Integer)me.getValue()*100);
                       total_Price=(Integer)me.getValue()*100+total_Price;
                       c++;
                       break;
                case 4:System.out.println(c+".Expresso   \t\t"+me.getValue()+"\t\t"+(Integer)me.getValue()*150);
                       total_Price=(Integer)me.getValue()*150+total_Price;
                       c++;
                       break;
                case 5:System.out.println(c+".Capacuino   \t\t"+me.getValue()+"\t\t"+(Integer)me.getValue()*170);
                       total_Price=(Integer)me.getValue()*170+total_Price;
                       c++;
                       break;
            }

        }
        System.out.println("-------------------------------------------");
        System.out.println("Total Price    \t\t\t\t"+total_Price);
        System.out.println("Tax            \t\t\t\t"+total_Price*14/100);
        System.out.println("Discount       \t\t\t\t"+total_Price*dst/100);
        System.out.println("-------------------------------------------");
        int amt=total_Price+(total_Price*14/100)-(total_Price*dst/100);
        System.out.println("Amount paid    \t\t\t\t"+amt);

    }

    public void getOrder(String ord, LinkedHashMap<Integer, Integer> order, LinkedHashMap<Integer, String> menu) {
        CoffeeShop shop=new CoffeeShop();
        String o[]=ord.split(",");
        if(shop.checkOrder((String)o[0],(String)o[1],order,menu))
        {
            int type=0;
            if(o[0].matches(".*\\d.*")) {
                type = Integer.parseInt(o[0]);
            }
            else
            {
                Set se=menu.entrySet();
                i=se.iterator();
                while(i.hasNext())
                {
                    Map.Entry me=(Map.Entry) i.next();
                    if(me.getValue().equals(o[0]))
                    {
                        type=(Integer)me.getKey();
                    }
                }
            }
            int quant=Integer.parseInt(o[1]);
            order.put(type,quant);
        }

    }

    public boolean checkOrder(String s, String s1, LinkedHashMap<Integer, Integer> order, LinkedHashMap<Integer, String> menu) {
        int type=0;
        if(s.matches(".*\\d.*")) {
            type = Integer.parseInt(s);
        }
        else
        {
            Set se=menu.entrySet();
            i=se.iterator();
            while(i.hasNext())
            {
                Map.Entry me=(Map.Entry) i.next();
                if(me.getValue().equals(s))
                {
                    type=(Integer)me.getKey();
                }
            }
        }
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
   public String predictor(String s,LinkedHashMap<Integer,String> menu)
   {
       int count[]={0,0,0,0,0};
       int c=0;
       String str;

       int key=1;
       String str2=null;
       Set se=menu.entrySet();
       i=se.iterator();
       while(i.hasNext())
       {
           Map.Entry me=(Map.Entry) i.next();
           str=(String)me.getValue();
           if(str.length()>s.length())
           {
               for(int k=0;k<s.length();k++)
               {
                   if(str.charAt(k)==s.charAt(k))
                       count[c]++;
               }
               c++;
           }
           else
           {
               for(int k=0;k<str.length();k++)
               {
                   if(str.charAt(k)==s.charAt(k))
                       count[c]++;
               }
               c++;
           }
       }
       int max=count[0];
        for(int k=1;k<5;k++)
        {
            if(max<count[k]) {
                max = count[k];
                key=k+1;
            }
        }
        Iterator m=se.iterator();
        while(m.hasNext())
        {
            Map.Entry me=(Map.Entry) m.next();
            int km=(Integer)me.getKey();
            if(km==key)
            {
                str2=(String) me.getValue();
            }
        }
      return str2;
   }
}
