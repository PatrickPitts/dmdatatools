<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Upload Creature</title>
</head>
<body>
<h3 th:text="#{home.welcome}">Default Title</h3>
<form th:action="@{/uploadcreature}" th:object="${creature}" method="post">
    <table border="1">
        <tr>
            <td><label for="creatureName">Name:</label></td>
            <td><input th:type="text" th:value="*{creatureName}" id="creatureName"/></td>
        </tr>

        <tr>
            <td><label for="size">Size: </label><select th:value="*{size}" id="size">
                <option th:each="s : ${T(org.nerdcore.dmdatatools.GameEntities.Creature.Size).values()}" th:value="${s}"
                        th:text="${#strings.capitalizeWords(#strings.toLowerCase(s))}"></option>
            </select></td>
            <td><label for="creatureType">Creature Type: </label><select th:value="*{creatureType}" id="creatureType">
                <option th:each="t : ${T(org.nerdcore.dmdatatools.GameEntities.Creature.DefaultCreatureType).values()}"
                        th:value="${t}" th:text="${#strings.capitalizeWords(#strings.toLowerCase(t))}"></option>
            </select>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <table>
                    <tr>
                        <th></th>
                        <th>STR</th>
                        <th>DEX</th>
                        <th>CON</th>
                        <th>INT</th>
                        <th>WIS</th>
                        <th>CHA</th>
                    </tr>
                    <tr>
                        <td></td>
                        <td th:each="score, iterStat : ${creature.abilityScores}">
                            <select th:field="*{abilityScores[__${iterStat.index}__]}"/>
                            <option th:each="i : ${#numbers.sequence(0,30)}" th:value="${i}" th:text="${i}"
                                    th:selected="${i==score}"/>
                            </select>
                            <div>(+0)</div>
                        </td>
                    </tr>
                    <tr>
                        <td th:text="'Saving Throws?'"></td>
                        <td th:each="entry : ${creature.savingThrows}">
                            <input type="checkbox" th:field="*{savingThrows[__${entry.key}__]}">
                        </td>
                    </tr>
                        <td>
                            <select th:field="*{resistances}"  id = "resistances">
                                <option th:each="resist : #{stats.resistances}" th:text="${resist}" th:value="${resist}">
                            </select>
                        </td>
                    <tr>
                        <td>
                            <label th:for="challengeRating">CR: </label>
                            <select id="challengeRating" th:field="*{challengeRating}">
                                <option th:text="'0'" th:value="0.0"></option>
                                <option th:text="'1/8'" th:value="0.125"></option>
                                <option th:text="'1/4'" value="0.25"></option>
                                <option th:text="'1/2'" value="0.5"></option>
                                <option th:each="i : ${#numbers.sequence(1,30)}" th:value="${i}"
                                        th:text="${i}"></option>
                            </select>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>


    </table>


    <input type="submit" value="Submit">
</form>

</body>
<script>

</script>

</html>