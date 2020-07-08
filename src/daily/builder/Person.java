package daily.builder;

/**
 * @author Li-Xiaoxu
 * @version 1.0
 * @date 2020/7/8 15:12
 * 日常测试
 * 2020年7月8日15:16:47 新增Builder样例 https://www.jianshu.com/p/815e1a73eed4
 */
public class Person {
    private String name;
    private int age;
    private float height;
    private float weight;

    public Person(Builder builder){
        this.name = builder.name;
        this.age = builder.age;
        this.height = builder.height;
        this.weight = builder.weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    static class Builder{
        private String name;
        private int age;
        private float height;
        private float weight;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setHeight(float height) {
            this.height = height;
            return this;
        }

        public Builder setWeight(float weight) {
            this.weight = weight;
            return this;
        }

        public Person build(){
            return new Person(this);
        }
    }



}



