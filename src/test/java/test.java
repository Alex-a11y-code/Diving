import com.dive.utils.CoreModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class test {

    private CoreModule coreModule;

    @BeforeEach
    void setUp() {
        coreModule = new CoreModule();
    }

    // 1. 测试命令 `players` 输出格式
    @Test
    void testProcessCommand_players() throws IOException {
        String expected = "Full Name:HART Alexander\nGender:Male\nCountry:Austria\n-----\n";
        String result = coreModule.processCommand("players");
        assertEquals(expected, result);
    }

    // 2. 测试命令 `result <eventName>` 输出格式
    @Test
    void testProcessCommand_result() throws IOException {
        String expected = "Full Name:MULLER Jette\nRank:1\nScore:51.60 + 52.00 + 51.75 + 46.80 + 46.80 = 248.95\n-----\n";
        String result = coreModule.processCommand("result women 1m springboard");
        assertEquals(expected, result);
    }

    // 3. 测试命令 `result <eventName> detail` 输出格式
    @Test
    void testProcessCommand_result_detail() throws IOException {
        String expected = "Full Name:MULLER Jette\nRank:1 | 2 | 3\nPreliminary Score:51.60 + 52.00 = 248.95\n-----\n";
        String result = coreModule.processCommand("result women 1m springboard detail");
        assertEquals(expected, result);
    }

    // 4. 测试无效命令处理
    @Test
    void testProcessCommand_invalidCommand() {
        String result = coreModule.processCommand("invalid command");
        assertEquals("Error: Invalid command", result);
    }

    // 5. 测试命令 `result` 缺少参数
    @Test
    void testProcessCommand_result_missingParameters() {
        String result = coreModule.processCommand("result women");
        assertEquals("Error: Invalid result command .'", result);
    }

    // 6. 测试选手数据为空时的返回
    @Test
    void testProcessCommand_emptyPlayersFile() throws IOException {
        String result = coreModule.processCommand("players");
        assertEquals("", result);
    }

    // 7. 测试没有比赛数据时的错误处理
    @Test
    void testProcessCommand_noEventFound() throws IOException {
        String result = coreModule.processCommand("result women 1m springboard");
        assertTrue(result.contains("Error: Event file not found"));
    }

    // 8. 测试选手数据包含双人项目
    @Test
    void testProcessCommand_duoEvent() throws IOException {
        String expected = "Full Name:JACHIM Filip & LUKASZEWICZ Robert\nRank:1\nScore:123.45 + 120.30 = 243.75\n-----\n";
        String result = coreModule.processCommand("result men 3m synchronised");
        assertEquals(expected, result);
    }

    // 9. 测试错误的文件路径
    @Test
    void testProcessCommand_invalidFilePath() throws IOException {
        String result = coreModule.processCommand("result women 1m springboard");
        assertTrue(result.contains("Error: Event file not found"));
    }

    // 10. 测试命令 `result` 返回的错误信息
    @Test
    void testProcessCommand_invalidEventName() throws IOException {
        String result = coreModule.processCommand("result invalid event");
        assertTrue(result.contains("Error: Invalid result command"));
    }
}
