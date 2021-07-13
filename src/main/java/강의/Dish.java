package 강의;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
// Maven, Gradle
public class Dish {
    private String name;
    private boolean vegetarian;
    private int calories;
    private Type type;

    public Dish(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Dish h = new Dish();
//        Dish aa = new Dish()
//        Dish kk = new Dish();
        Dish namedish = new Dish("kk");
//        Dish dd = new Dish();
        Dish ddd = Dish.builder()
            .name("귀여워")
            .type(Type.MEAT)
            .calories(400)
            .vegetarian(true)
            .build();
        System.out.println("ddd = " + ddd);
        int k = 3;
//        boolean t = k > 5 ? true : false;
    }
}

enum Type {
    MEAT,
    FISH,
    OTHER
}