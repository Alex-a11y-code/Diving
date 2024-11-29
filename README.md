# Diving 项目

## 1. 爬取信息

通过爬取相关信息并将其保存为 JSON 格式，文件保存在与 `.jar` 文件相同的目录下，供后续使用。系统通过解析这些 JSON 文件来获取运动员信息和赛事结果。

## 2. 模块设计

### 2.1 DataParser 类

**职责**：

- 读取 `players.json` 文件并提取运动员信息。
- 读取赛事结果文件（如 `women 1m springboard.json`）并提取比赛成绩。

**关键方法**：

- `getAllPlayers()`：通过读取 `players.json` 文件返回所有运动员信息。
- `getEventResult(String eventName)`：根据赛事名称读取对应的结果文件并返回赛事成绩。
- `getEventDetailedResult(String eventName)`：读取指定赛事的详细成绩。

### 2.2 ResultFormatter 类

**职责**：

- 将运动员和赛事结果格式化为易读的输出字符串。

**关键方法**：

- `formatPlayers(List<Player> players)`：格式化运动员信息。
- `formatEventResult(List<ContestDetailed> contestDetailedList)`：格式化赛事成绩。
- `formatDetailedContestResult(List<ContestDetailed> contestDetailedList)`：格式化详细赛事成绩（如初赛、半决赛、决赛成绩）。

### 2.3 DWASearch 类

**职责**：

- 从输入文件读取命令，使用 `CoreModule` 进行处理，并将结果写入输出文件。

**关键方法**：

- `main(String[] args)`：读取输入命令，执行处理，并将结果写入输出。

### 2.4 CoreModule 类

**职责**：

- 解析输入命令，并调用 `DataParser` 和 `ResultFormatter` 来获取并格式化数据。

**关键方法**：

- `processCommand(String command)`：解析和处理每个命令（如 `players` 或 `result <event_name>`），并返回格式化结果。

## 3. 关键算法设计

系统的核心算法包括解析输入命令、获取相应的数据（运动员或赛事成绩），并将这些数据格式化为输出。

### 算法流程：

1. **命令解析**：从输入文件读取每行命令。
2. **数据获取**：
   - 如果命令是 `players`，调用 `DataParser.getAllPlayers()` 方法获取所有运动员信息。
   - 如果命令是 `result <event_name>`，调用 `DataParser.getEventResult()` 方法获取特定赛事的结果。
3. **数据格式化**：调用 `ResultFormatter.formatPlayers()` 和 `ResultFormatter.formatEventResult()` 方法进行格式化。
4. **输出结果**：将格式化后的数据写入输出文件 `output.txt`。

### 命令处理流程图：

```
css复制代码[输入文件] --> [读取命令]
                  |
                  v
           [命令类型检查]
                  |
  +---------------+----------------+
  |                                |
[players]                     [result <event_name>]
  |                                |
  v                                v
[DataParser.getAllPlayers()]   [DataParser.getEventResult()]
  |                                |
  v                                v
[ResultFormatter.formatPlayers()] [ResultFormatter.formatEventResult()]
                  |
                  v
          [写入输出文件]
```

## 4. 性能改进

****：

- **高效的文件处理**：使用 Java 的 `BufferedReader` 和 `BufferedWriter` 确保大文件的高效读取和写入。

  

## 5.单元测试

### 5.1 单元测试设计

设计了单元测试来验证核心逻辑的正确性：

- DataParser 测试用例

  ：

  - 验证 `players.json` 和赛事结果文件是否正确解析。
  - 验证 `getEventResult()` 方法能否正确获取指定赛事结果。

- ResultFormatter 测试用例

  ：

  - 验证运动员和赛事结果是否能正确格式化为预期的输出。

### 5.2 单元测试示例

```
java复制代码import static org.junit.Assert.*;
import org.junit.Test;

public class DataParserTest {

    @Test
    public void testGetAllPlayers() throws Exception {
        DataParser dataParser = new DataParser();
        List<Player> players = dataParser.getAllPlayers();
        assertNotNull(players);
        assertFalse(players.isEmpty());
    }

    @Test
    public void testGetEventResult() throws Exception {
        DataParser dataParser = new DataParser();
        List<ContestDetailed> results = dataParser.getEventResult("women 1m springboard");
        assertNotNull(results);
        assertTrue(results.size() > 0);
    }
}
```

### 5.3 测试工具

- **JUnit**：使用 JUnit 进行单元测试，验证各个模块的功能。
- **Mockito**：在单元测试中使用 Mockito 来模拟文件读取等依赖。

## 6. 异常处理

### 6.1 错误场景

程序能够处理以下错误：

1. **文件未找到**：如果运动员或赛事结果的 JSON 文件不存在，程序会输出错误信息。
2. **无效命令**：输入无效命令时，输出错误提示。
3. **JSON 格式错误**：如果 JSON 文件格式错误，程序会捕获 `JsonSyntaxException` 并提示用户错误信息。

### 6.2 异常处理示例

```
public List<Player> getAllPlayers() throws IOException {
    try (Reader reader = new FileReader(PLAYERS_FILE)) {
        return new Gson().fromJson(reader, new TypeToken<List<Player>>() {}.getType());
    } catch (FileNotFoundException e) {
        System.err.println("错误：未找到运动员文件。");
        throw e;
    } catch (JsonSyntaxException e) {
        System.err.println("错误：运动员文件的 JSON 格式无效。");
        throw e;
    }
}
```

### 6.3 异常处理总结

- 对于 **文件读取** 和 **JSON 解析** 错误，系统会及时捕获并给出清晰的错误信息。
- 对于 **无效命令**，系统会返回 `"Error: Invalid command"` 提示，帮助用户发现并修正错误。
- 对于 **其他未知错误**，系统会通过异常捕获机制，确保程序不会因为意外错误崩溃，并返回详细的错误信息供用户排查。
