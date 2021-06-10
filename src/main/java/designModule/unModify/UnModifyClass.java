package designModule.unModify;

/**
 * 通过私有的静态class返回，构建一个某些方法不可变的对象
 * Executors中有大量的运用 如newSingleThreadScheduledExecutor方法
 *
 * Created by supaur on 2021/5/24 15:59
 */
public class UnModifyClass {


    public static void main(String[] args) {
        People people = getPeople();
        int age = people.age();
        System.out.println(age);
    }

    public static People getPeople() {
        return new DelegateMan(new Man());
    }

    /**
     * 通过构造一个静态的私有的内部类，在外部不可修改DelegateMan了，同时把man中的set方法给禁用掉了
      */
    private static class DelegateMan implements People {
       private People people;
       public DelegateMan(People people) {
           this.people = people;
       }

       @Override
       public void say() {
           people.say();
       }

       @Override
       public int age() {
           return people.age();
       }
   }

   public interface People {
       void say();
       int age();
   }

   public static class Man implements People {
       private int age;
       @Override
       public void say() {

       }
       @Override
       public int age() {
           return this.age;
       }

       public void setAge(int age) {
           this.age = age;
       }
   }


}
