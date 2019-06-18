package camp.nextstep.edu.racingcar.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class GamePlayerTest implements CarNamesHelper {
    private MovingStrategy moveOnlyStrategy = new MoveOnlyStrategy();
    private List<String> validCarNameStrings;

    @BeforeEach
    void setUp() {
        validCarNameStrings = Arrays.asList(
                CAR_NAME_FIRST_VALUE,
                CAR_NAME_SECOND_VALUE,
                CAR_NAME_THIRD_VALUE
        );
        assertThat(validCarNameStrings.size()).isEqualTo(3);
    }

    @DisplayName("객체를 잘 생성하는지")
    @Test
    void constructor() {
        final GamePlayer gamePlayer = GamePlayer.from(moveOnlyStrategy);
        assertThat(gamePlayer).isNotNull();
    }

    @DisplayName("주어진 이동 전략이 null 이면, 객체를 생성할 수 없어야함")
    @Test
    void constructorThrowsExceptionWhenMovingStrategyIsNull() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> GamePlayer.from(null));
    }

    @DisplayName("게임이 예외를 던지지 않고 잘 실행되는지")
    @Test
    void playAllRound() {
        // given
        final GamePlayer gamePlayer = GamePlayer.from(moveOnlyStrategy);
        gamePlayer.initializeGame(validCarNameStrings, 5);
        // when
        assertThatCode(gamePlayer::playAllRounds)
                // then
                .doesNotThrowAnyException();
    }

    @DisplayName("게임이 세팅되지 않았을 때, 게임을 플레이하면 IllegalStateException 을 던져야함")
    @Test
    void playAllRoundsThrowsIllegalStateExceptionWhenGameIsNotInitialized() {
        // given
        final GamePlayer gamePlayer = GamePlayer.from(moveOnlyStrategy);
        // when
        assertThatIllegalStateException()
                .isThrownBy(gamePlayer::playAllRounds)
                // then
                .withMessageContaining("not initialized");
    }

    @DisplayName("게임 결과가 잘 조회되는지")
    @Test
    void getGameResult() {
        // given
        final GamePlayer gamePlayer = GamePlayer.from(moveOnlyStrategy);
        gamePlayer.initializeGame(validCarNameStrings, 5);
        gamePlayer.playAllRounds();
        // when
        final Rounds gameResult = gamePlayer.getGameResult();
        // then
        assertThat(gameResult).isNotNull();
        assertThat(gameResult.size()).isEqualTo(5);
    }

    @DisplayName("게임이 세팅되지 않았을 때, 게임 결과를 조회하면 IllegalStateException 을 던져야함")
    @Test
    void getGameResultThrowsIllegalStateExceptionWhenGameIsNotInitialized() {
        // given
        final GamePlayer gamePlayer = GamePlayer.from(moveOnlyStrategy);
        // when
        assertThatIllegalStateException()
                .isThrownBy(gamePlayer::getGameResult)
                // then
                .withMessageContaining("not initialized");
    }
}