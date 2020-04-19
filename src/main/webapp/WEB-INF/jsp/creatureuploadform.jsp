<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Upload Creature</title>
    <link href="https://cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">

</head>
<body>
<h3 th:text="#{home.welcome}">Default Title</h3>

<form th:action="@{/uploadcreature}" th:object="${creature}" method="post" onsubmit="cleanupSubmitForm()">
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
            <td>
                <label th:for="hitDieCount">HitDie:</label>
                <input type="number" th:field="*{hitDieCount}" id="hitDieCount" name="hitDieCount" min="1" max="40"
                       value="1" th:onchange="writeHitDieDisplay()">
                <select th:field="*{hitDieBase}" id="hitDieBase" name="hitDieBase" th:onchange="writeHitDieDisplay()">
                    <option th:value="4" th:text="'d4'" selected></option>
                    <option th:value="6" th:text="'d6'"></option>
                    <option th:value="8" th:text="'d8'"></option>
                    <option th:value="10" th:text="'d10'"></option>
                    <option th:value="12" th:text="'d12'"></option>
                    <option th:value="20" th:text="'d20'"></option>
                </select>
                <span id="hpDisplay"> - </span>
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
                            <select th:field="*{abilityScores[__${iterStat.index}__]}"
                                    th:name="|abilityScores${iterStat.index}|" th:onchange="writeHitDieDisplay()"/>
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
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <label th:for="damageResistancesChoices">Damage Resistances:</label>
                <select id="damageResistancesChoices" size="3" th:field="*{damageResistances}" multiple>
                    <option th:each="res : ${@resistanceList}" th:text="${res}" th:value="${res}"></option>
                </select>
            </td>
            <td>
                <label th:for="damageImmunityChoices">Damage Immunities:</label>
                <select id="damageImmunityChoices" size="3" th:field="*{damageImmunities}"
                        style="text-indent: hanging; width:150px; white-space: pre-wrap" multiple>
                    <option th:each="res : ${@resistanceList}" th:text="${res}" th:value="${res}"></option>
                </select>

            </td>

            <td>
                <label th:for="damageVulnerabilityChoices">Damage Vulnerabilities:</label>
                <select id="damageVulnerabilityChoices" size="3" th:field="*{damageVulnerabilities}" multiple>
                    <option th:each="res : ${@resistanceList}" th:text="${res}" th:value="${res}"></option>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                <label th:for="conditionImmunities">Condition Immunities:</label>
                <select id="conditionImmunities" size="3" th:field="*{conditionImmunities}" multiple>
                    <option th:each="con : ${@conditionList}" th:text="${con}" th:value="${con}"></option>
                </select>
            </td>

            <td>
                <label th:for="skills">Skills:</label>
                <select th:field="*{skills}" id="skills" size="3" multiple>
                    <option th:each="skill : ${@skillList}" th:text="${skill}" th:value="${skill}"></option>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                <span><strong>Senses: </strong></span>
                <label th:for="blindsight" th:text="Blindsight "></label><input type="number" th:id="blindsight"
                                                                                th:field="*{blindsight}" min="0"
                                                                                max="300" step="10">
                <label th:for="darkvision" th:text="Darkvision "></label><input type="number" th:id="darkvision"
                                                                                th:field="*{darkvision}" min="0"
                                                                                max="300" step="10">
                <label th:for="tremorsense" th:text="Tremorsense "></label><input type="number" th:id="tremorsense"
                                                                                  th:field="*{tremorsense}" min="0"
                                                                                  max="300" step="10">
                <label th:for="truesight" th:text="Truesight "></label><input type="number" th:id="truesight"
                                                                              th:field="*{truesight}" min="0" max="300"
                                                                              step="10">

            </td>
        </tr>
        <tr>
            <td>
                <span><strong>Speed: </strong></span>
                <label th:for="ground" th:text="Ground"></label><input type="number" th:id="ground" th:field="*{groundSpeed}" min="0" max="200" step="5">
                <label th:for="burrow" th:text="Burrow"></label><input type="number" th:id="burrow" th:field="*{burrowSpeed}" min="0" max="200" step="5">
                <label th:for="climb" th:text="Climb"></label><input type="number" th:id="climb" th:field="*{climbSpeed}" min="0" max="200" step="5">
                <label th:for="fly" th:text="Fly"></label><input type="number" th:id="fly" th:field="*{flySpeed}" min="0" max="200" step="5">
                <label th:for="swim" th:text="Swim"></label><input type="number" th:id="swim" th:field="*{swimSpeed}" min="0" max="200" step="5">

            </td>
        </tr>

        <tr>
            <td>
                <label th:for="challengeRating">CR: </label>
                <select id="challengeRating" th:field="*{challengeRating}">
                    <option th:text="'0'" th:value="0.0"></option>
                    <option th:text="'1/8'" th:value="0.125"></option>
                    <option th:text="'1/4'" th:value="0.25"></option>
                    <option th:text="'1/2'" th:value="0.5"></option>
                    <option th:each="i : ${#numbers.sequence(1,30)}" th:value="${i}"
                            th:text="${i}"></option>
                </select>
            </td>
        </tr>
        <tr id="abilityBoxes">
            <table th:each="ability, iter :${creature.abilities}" th:id="|ability${iter.index}|"
                   style="display: none;">
                <tr>
                    <td>
                        <label th:for="abilityName">Title: </label><input th:id="abilityName"
                                                                          th:field="*{abilities[__${iter.index}__].abilityName}">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" th:field="*{abilities[__${iter.index}__].abilityType}"
                               th:each="t : ${T(org.nerdcore.dmdatatools.GameEntities.CreatureAbility.AbilityType).values()}"
                               th:value="${t}" th:iter="${iter.index}"
                               th:text="${#strings.capitalizeWords(#strings.toLowerCase(#strings.replace(t, '_', ' ')))}"
                                th:onchange="updateActionWindow(this.getAttribute('iter'))">
                    </td>
                </tr>
                <tr>
                    <td>
                        <div th:id="|editor${iter.index}|"></div>
                        <input th:type="hidden" th:field="*{abilities[__${iter.index}__].abilityDescription}"
                               th:id="|abilityDescription${iter.index}|">
                    </td>
                </tr>

            </table>

        </tr>


    </table>
    <input type="submit" value="Submit">
</form>


</body>
<script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
<script type="text/javascript">

    var quills = [];

    window.onload = function (ev) {
        document.getElementById("ability0").style.display = "table";

        for (var i = 0; i < 99; i++) {
            var quillID = '#editor' + i;
            quills.push(new Quill(quillID, {theme: 'snow'}));
        }
    };

    function cleanupSubmitForm() {

        for (var i = 0; i < 99; i++) {
            document.getElementById('abilityDescription' + i).value = quills[i].root.innerHTML.toString();
        }

    }

    function writeHitDieDisplay() {
        var dieBase = parseInt(document.getElementById("hitDieBase").value);
        var dieCount = parseInt(document.getElementById("hitDieCount").value);
        var con = parseInt(document.getElementById("abilityScores2").value);
        var conHP = parseInt(((con - 10) / 2) * dieCount);
        var avgHP = parseInt(conHP + (((dieBase + 1) / 2.0)) * dieCount);
        document.getElementById("hpDisplay").innerText = `${avgHP} (${dieCount}d${dieBase} + ${conHP})`;
    }

    function updateActionWindow(actionCount){
        var radioName = "abilities["+actionCount+"].abilityType"
        alert(actionCount);


    }



</script>
<script>

</script>

</html>