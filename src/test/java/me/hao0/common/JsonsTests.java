package me.hao0.common;

import me.hao0.common.json.Jsons;
import org.junit.Test;

/**
 * Author: haolin
 * Date:   7/26/16
 * Email:  haolin.h0@gmail.com
 */
public class JsonsTests {

    @Test
    public void testBenchmark(){

        Jsons.DEFAULT.toJson(new Person(1L, "haolin", true, 25));

        long start = System.currentTimeMillis();

        for (int i=0; i<100000; i++){
            Jsons.DEFAULT.toJson(new Person(i, "haolin"+i, true, 25));
        }

        System.out.println("cost: " + (System.currentTimeMillis() - start));
    }

    private class Person{

        private long id;
        private String name;
        private Boolean adult;
        private Integer age;

        public Person(long id, String name, Boolean adult, Integer age) {
            this.id = id;
            this.name = name;
            this.adult = adult;
            this.age = age;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Boolean getAdult() {
            return adult;
        }

        public void setAdult(Boolean adult) {
            this.adult = adult;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
