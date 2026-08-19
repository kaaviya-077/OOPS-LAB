import java.util.Scanner;
interface stackADT{
void push(int item);
void display();
}
class stack implements stackADT{
int[] stack;
int top;
int size;
stack(int size){
this.size=size;
stack=new int[size];
top=-1;
}
public void push(int item){
try{
if(top==size-1){
throw new Exception("Stack overflow");
}
stack[++top]=item;
System.out.println(item+"Pushed in Stack");
}
catch(Exception e){
System.out.println(e.getMessage());
}
}
public void display(){
try{
if(top==-1){
throw new Exception("Stack empty");
}
System.out.println("Stack elements are:");
for(int i=top;i>=0;i--){
System.out.println(stack[i]);
}
}
catch(Exception e){
System.out.println(e.getMessage());
}
}
}
public class stackexception{
public static void main(String[]args){
Scanner sc=new Scanner(System.in);
int size=sc.nextInt();
stack s=new stack(size);
while(true){
System.out.println("\n1.Push");
System.out.println("2.Display");
System.out.println("Enter your choice");
int choice=sc.nextInt();
switch(choice){
case 1:
System.out.println("Enter element:");
int item=sc.nextInt();
s.push(item);
break;
case 2:
s.display();
break;
case 3:
System.out.println("Program Terminater");
sc.close();
return;
default:
System.out.println("Invaild Choice");
}
}
}
}


