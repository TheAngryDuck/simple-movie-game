package com.movie.game;

import com.movie.game.enums.Type;
import com.movie.game.helper.MovieHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MovieGameApplicationTests {



	@Test
	void helperMethodGeneratesValidId() {
		MovieHelper helper = new MovieHelper();
		var id = helper.getId();
		assertThat(id.compareTo(new BigInteger("0")) > 0);
		assertThat(id.compareTo(new BigInteger("4798")) < 0);
	}

	@Test
	void isUpperReturnsTrueOnValuesAtLimit(){
		MovieHelper helper = new MovieHelper();
		assertThat(helper.isUpperValue(Type.REVENUE,new BigInteger("2787965087"),0,0));
		assertThat(helper.isUpperValue(Type.VOTE,new BigInteger("0"),10,0));
		assertThat(helper.isUpperValue(Type.RUNTIME,new BigInteger("0"),338,0));
		assertThat(helper.isUpperValue(Type.POPULARITY,new BigInteger("0"),0,875.5813));
	}

	@Test
	void isLowerReturnsTrueOnValuesAtLimit(){
		MovieHelper helper = new MovieHelper();
		assertThat(helper.isLowerValue(Type.REVENUE,new BigInteger("0"),0,0));
		assertThat(helper.isLowerValue(Type.VOTE,new BigInteger("0"),0,0));
		assertThat(helper.isLowerValue(Type.POPULARITY,new BigInteger("0"),0,0.000372));
	}

}
