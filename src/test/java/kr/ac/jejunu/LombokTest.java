package kr.ac.jejunu;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LombokTest {

    @Test
    public void equlas(){
        User user1=User.builder().name("1111").password("1111").build();
        User user2=User.builder().name("1111").password("1111").build();
        assertThat(user1,is(user2));
    }

}
