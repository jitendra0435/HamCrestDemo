package hamcresttest;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class HamCrestTest {
    @Test
    public void coreMatcherTest() {
        Assert.assertThat(1L, Matchers.instanceOf(Long.class));
        Assert.assertThat(1L,Matchers.isA(Long.class));
    }

    @Test
    public void listMatcherTest() {
        List<Integer> list= Arrays.asList(5,2,4);
        MatcherAssert.assertThat(list, Matchers.hasSize(3));
        MatcherAssert.assertThat(list, Matchers.contains(5,2,4));
        MatcherAssert.assertThat(list, Matchers.everyItem(Matchers.greaterThan(1)));
    }

    @Test
    public void arrayMatcherTest() {
        Integer[]ints=new Integer[]{7,5,12,16};
        Integer[] emptyArray=new Integer[]{};
        MatcherAssert.assertThat(ints,Matchers.arrayWithSize(4));
        MatcherAssert.assertThat(ints,Matchers.arrayContaining(7,5,12,16));
        MatcherAssert.assertThat(ints,Matchers.arrayContainingInAnyOrder(16,5,12,7));
        MatcherAssert.assertThat(emptyArray, Matchers.emptyArray());
    }

    @Test
    public void objectMatcherTest() {
            TODO todo1 = new TODO(1, "Learn Hamcrest", "Important");
            TODO todo2 = new TODO(1, "Learn Hamcrest", "Important");
            MatcherAssert.assertThat(todo1,Matchers.hasProperty("summary"));
            MatcherAssert.assertThat(todo1,Matchers.hasProperty("summary",Matchers.equalTo("Learn Hamcrest")));
            MatcherAssert.assertThat(todo1,Matchers.samePropertyValuesAs(todo2));
    }
}
