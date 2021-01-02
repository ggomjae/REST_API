package test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    // @Disabled  : 테스트를 하지 않을때
    @Test
    @DisplayName("예시 메소드") // 이모지도 가능 ㅋ_ㅋ
    void create() {
        Study study = new Study(-10);
        assertAll(
                ()-> assertNotNull(study),
                ()-> assertEquals(StudyStatus.DRAFT, study.getStatus(), "default : DRAFT")
        );
    }

    @Test
    @DisplayName("limit 메소드")
    void limitTest() {
        IllegalArgumentException illegalArgumentException =
                assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        String message = illegalArgumentException.getMessage();
        assertEquals("limit는 0보다 커야한다.",message);
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    @DisplayName("assume 메소드")
    void assumeTest() {
        assumeTrue("Local".equalsIgnoreCase("Local"));
        assumingThat("Local".equalsIgnoreCase(System.getenv("TEST_ENV")), () -> {
            System.out.println("sucess");
        });

        assumingThat("Local".equalsIgnoreCase("Local"), () -> {
            System.out.println("sucess");
        });
    }

    // 딱 한번만 실행 가능, static을 지향, 리턴 타입 x
    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    /* - Each : 모든 테스트를 실행할 때, 이전과 이후의 한번씩, static x*/
    @BeforeEach
    void befoerEach() {
        System.out.println("before each");
    }

    @AfterEach
    void afterEach() {
        System.out.println("After each");
    }
}